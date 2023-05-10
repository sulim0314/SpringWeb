package com.common.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
/*[1]���̺귯�� ���
 * ===pom.xml=========
 * <!-- File Upload -->
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>
 * ====================
 * 
 * [2] servlet-context.xml�� �� ���
 * <!-- ���Ͼ��ε带 ����  MultipartResolver ���� =============================== -->
	<!--����: ���� id�� �ݵ�� multipartResolver�� ����ؾ� �Ѵ�.�ٸ� ���̵� �ָ� DispatcherServlet�� MultipartResolver��
	�ν����� ���Ѵ�.  -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="defaultEncoding" value="utf-8"/>
		<beans:property name="maxUploadSize" value="-1"/>
		<!-- -1�� �ָ� ������ ���ε� ���� -->
	</beans:bean>
	
	<beans:bean id="upDir" class="java.lang.String">
		<beans:constructor-arg index="0" value="C:/myjava/Upload"/>
	</beans:bean>
	<!-- String upDir=new String("C:/myjava/Upload"); -->
	
 * 
 * */

@Controller
@Log4j
public class FileController {
	
	@Resource(name="upDir") //���ҽ� �̸����� ��������.
	private String upDir;
	
	@GetMapping("/fileForm")
	public String fileForm() {
		return "file/fileForm";
		//WEB-INF/views/fileForm.jsp
	}
	
	//[1] MultipartFile�� �̿��ϴ� ��� => transferTo()�� �̿��Ͽ� ���ε� ó��
	//[2] MultipartHttpServletRequet�� �̿��ϴ� ���
	@PostMapping("/fileUp")
	public String fileUpload(Model m, @RequestParam("name") String name,
			@RequestParam("mfilename") MultipartFile mfilename) {
		log.info("name: "+name+", mfilename: "+mfilename);
		log.info("upDir: "+upDir);
		//1. ��������(���ϸ�, ����ũ��, ÷�� ����) �˾Ƴ��� (DB�� �����ϱ� ����)
		if(!mfilename.isEmpty()) {//÷���ߴٸ�
			String filename=mfilename.getOriginalFilename();//���ϸ�
			long filesize=mfilename.getSize();//����ũ��
			String ctype=mfilename.getContentType();//��������
			log.info("filename: "+filename+", filesize: "+filesize);
			m.addAttribute("fname",filename);
			m.addAttribute("fsize", filesize);
			m.addAttribute("ftype",ctype);
			m.addAttribute("name",name);
			//2. ���Ͼ��ε� ó�� => transferTo()
			try {
			mfilename.transferTo(new File(upDir, filename));
			}catch(IOException e) {
				log.info("���ε� ����: "+e);
				log.error(e);
			}
			
		}//if-----
		return "file/fileResult";
		//WEB-INF/views/fileResult.jsp
	}//-----------------------------------------
	////[2] MultipartHttpServletRequet�� �̿��ϴ� ���
	@PostMapping("/fileUp2")
	public String fileUpload2(Model m, HttpServletRequest req) {
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		//1. �ø��� �ޱ�
		String name=mr.getParameter("name");
		//2. ÷������ ��� ��� List<MultipartFile> getFiles("�Ķ���͸�")
		List<MultipartFile> fList=mr.getFiles("mfilename");
		if(fList!=null) {
			for(int i=0;i<fList.size();i++) {
				MultipartFile mf=fList.get(i);
				//÷�����ϸ�
				//"�����ѹ��ڿ�_���ϸ�" 
				UUID uid=UUID.randomUUID();
				String uidStr=uid.toString();
				
				//���� ���ε� �Ǵ� ���ϸ�
				String fname=uidStr+"_"+mf.getOriginalFilename();//uuid_face.png
				//����ڰ� ���ε��� �������ϸ�
				String origin=mf.getOriginalFilename();
				
				//������ ���ϸ��� ���ε�� ���߿� ���ε��� ������ ���� ������ �������
				long fsize=mf.getSize();
				String ctype=mf.getContentType();
				//���ε� ó��
				try {
					mf.transferTo(new File(upDir, fname));
				} catch (Exception e) {
					log.error(e);
				}
				m.addAttribute("fname"+(i+1), fname);
				m.addAttribute("fsize"+(i+1), fsize);
				m.addAttribute("ftype"+(i+1), ctype);
				
			}//for-----
		}//if---------------------------------
		
		
		
		return "file/fileResult";
	}
	
	

}




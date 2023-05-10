package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;

//servlet-context.xml�� �����ϰ� �����Ѵ�
/*<interceptors>
 * �߷�...
  <interceptor>
			<mapping path="/admin/**"/>
			<beans:bean class="com.common.interceptor.AdminCheckInterceptor"/>
	</interceptor>
  </interceptors>	
 * */
@Log4j
public class AdminCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
		log.info("AdminCheckInterceptor preHandle()...");
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user!=null) {
			if(user.getMstate()!=9) {//mstate:0 (�Ϲ�ȸ��),-1(����ȸ��),-2(Ż��ȸ��),9(������)
				req.setAttribute("msg", "�����ڸ� �̿� �����մϴ�");
				req.setAttribute("loc", req.getContextPath()+"/index");
				
				RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/views/message.jsp");
				disp.forward(req, res);
				
				return false;//�����ڰ� �ƴѰ��
			}
		}
		
		return true;
	}

}

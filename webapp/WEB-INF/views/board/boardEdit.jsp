<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!-- ckeditor 4 cdn ------------------------------------------------------- -->
<script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>    
<!-- ---------------------------------------------------------------------- -->
<script>
	$(function(){
		CKEDITOR.replace('bcontent');
		$('#bf').submit(function(){
			//제목
			if(!$('#subject').val()){
				alert('제목을 입력하세요');
				$('#subject').focus();
				return false;
			}			
			//작성자 아이디
			if(!$('#userid').val()){
				alert('글쓴이를 입력하세요');
				$('#userid').focus();				
				return false;
			}
			if(!CKEDITOR.instances.bcontent.getData()){
				alert('글내용을 입력하세요');
				CKEDITOR.instances.bcontent.focus();
				return false;
			}
			
			//비밀번호
			if(!$('#bpwd').val()){
				alert('비밀번호를 입력하세요');
				$('#bpwd').focus();				
				return false;
			}
			
			return true
		})//submit------------
		
	})//$() end----------------
</script>    

<div class="row">

<div align="center" id="bbs" class="col-md-8 offset-md-2 my-4">
   <h2>Spring Board Edit</h2>
   <p>
      <a href="write">글쓰기</a>| <a
         href="list">글목록</a>
      <p>
  <!--파일 업로드시
   method: POST
   enctype: multipart/form-data     
    -->   

   <form name="bf" id="bf" role="form" action="write" method="post"
    enctype="multipart/form-data">
	<!-- hidden data---------------------------------  -->
	<input type="hidden" name="num" value="<c:out value="${board.num}"/>">
	<input type="hidden" name="mode" value="edit">
	<!-- 원본글쓰기: mode=> write	 답변글쓰기: mode=> rewrite   글수정:: mode=> edit
	 -->
	<!-- -------------------------------------------- -->       
    <table class="table">
       <tr>
          <td style="width:20%"><b>제목</b></td>
          <td style="width:80%">
          <input type="text" name="subject" id="subject"
          	value="<c:out value="${board.subject}"/>"
           class="form-control">
          </td>
       </tr>
       <tr>
          <td style="width:20%"><b>글쓴이</b></td>
          <td style="width:80%">
          <input type="text" name="userid" id="userid" value="<c:out value="${board.userid}"/>"            
           class="form-control">
          </td>
       </tr>       
       <tr>
          <td style="width:20%"><b>글내용</b></td>
          <td style="width:80%">
          <textarea name="content" id="bcontent" rows="10" cols="50"
                  class="form-control">${board.content}</textarea>
          </td>
       </tr>
       <tr>
          <td style="width:20%"><b>비밀번호</b></td>
          <td style="width:80%">
          <div class="col-md-5">
          <input type="password" name="passwd" id="bpwd" class="form-control">
          </div>
          </td>
      </tr>
      <tr>
         <td style="width: 20%"><b>첨부파일</b></td>
         <td style="width: 80%">
         <c:if test="${board.filename ne null}">
         	<c:out value="${board.originFilename}"/> [<c:out value="${board.filesize}"/>] bytes
         	
         	<!-- 파일 확장자 검사를 위해 모두 소문자로 바꾸자 -->
           	<c:set var="fname" value="${fn:toLowerCase(board.filename)}"/>
           	
			<c:if test="${fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.png') or fn:endsWith(fname,'.gif')}">
			<!-- /board/view/5 -->
				<img class="img img-thumbnail" src="../resources/board_upload/<c:out value="${board.filename}"/>" style="width:80px">
			</c:if>  
         	
         </c:if>
         
         <!-- 새로 첨부하는 파일명--------------------------------------------------- -->
         <input type="file" name="mfilename"  id="filename" class="form-control">
         <!-- 예전에 첨부한 파일명을 hidden으로 보내자.-------------------------------- -->
         <input type="hidden" name="old_filename" value="<c:out value="${board.filename}"/>">
         <!-- ----------------------------------------------------------------- -->   
         </td>
      </tr>
      <tr>
         <td colspan="2" class="text-center">
            <button type="submit" id="btnWrite" class="btn btn-success">글수정</button>
            <button type="reset" id="btnReset" class="btn btn-warning">다시쓰기</button>
         </td>
      </tr>
   
      </table>
   

</form>       
</div><!-- .col end-->
</div><!-- .row end-->
    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Valpago</title>
</head>
<body>

<div id="root">
   <!-- header 시작  -->
   <header id="header">
   </header>
   <!-- hearder 끝 -->   
   <nav id="nav">
   </nav>
   
   <section id="container">
      <div id="container_box">
   
<!-- content 시작 -->
<section id="content">
   <form role="form" method="post" autocomplete="off">
   <table align="center" border="3" cellspacing="0"  >
      <colgroup>
         <col width="110px"/>
         <col width="500px"/>
         
      </colgroup>
       <tr>
          <td colspan="5" height="30" align="center" bgcolor=#000000" span style="color:white;">회원기본정보</td>
          
        </tr>
        <tr> 
          <td align="right">아이디</td>
          <td colspan="4">   
          <input type="text" name="memId" id="memId" maxlength="15" value="${member.memId}" readonly="readonly"/>
          </td>
       </tr>
    
       <tr>
          <td align="right">비밀번호</td>
           <td colspan="4"><input type="password" class="pw" id="memPassword1" maxlength="12" name="memPassword"  > 
           <span style="font-size: 9px; ">8~15자리의 영문 대소문자와 숫자로만 입력</span>
           </td>
       </tr>
       <tr>
          <td align="right">비밀번호확인</td>
           <td colspan="5"><input type="password" class="pw" id="memPassword2" maxlength="12">
           <span id="alert-success" style="display: none; color: blue; font-size: 10px;">비밀번호가 일치합니다.</span>
          <span id="alert-danger" style="display: none; color: red; font-size: 10px; ">비밀번호가 일치하지 않습니다.</span>
           </td>
           
       </tr>
       <tr>
          <td align="right">이름</td>
          <td colspan="3"><input type="text" name="memName" id="memName" value="${member.memName}"></td>
        </tr>
       <tr>
          <td align="right">닉네임</td>
          <td colspan="4"><input type="text" name="memNickname" id="memNickname" value="${member.memNickname}">
          <span style="font-size: 9px; ">2~10자리의 한글로만 입력</span>
          </td>
       </tr>
        <tr>
          <td align="right">메일주소</td>
          <td colspan="4"><input type="text" name="memEmail" id="memEmail" value="${member.memEmail}">
          예)id@domain.com</td>
       </tr>
       <tr>
          <td align="right">전화번호</td>
          <td colspan="4"><input type="text" name="memPhone" id="memPhone" value="${member.memPhone}">
          예)01012345678</td>
       </tr>
       <tr>
          <td align="right">성별</td>
          <td colspan="4">
             <c:if test="${member.memSex == '남성'}">
            <input type="radio" name="memSex" value="남성" checked="checked" />남성&nbsp;&nbsp;
         </c:if>
         <c:if test="${member.memSex == '여성'}">
            <input type="radio" name="memSex" value="여성" checked="checked"/>여성
         </c:if>
          </td>
       </tr>
        <tr>
           <td align="right">생년월일</td>
           <td colspan="4">
       		 <input type="text" name="memBirthday" id="memBirthday" value="${member.memBirthday}" readonly="readonly">
           </td>
        </tr>
            <td align="right">주소</td>
             <td colspan="4">
               <input type="text" id="sample2_postcode" name="memAddress1" placeholder="우편번호" readonly="readonly" >
            <input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" id="sample2_address" name="memAddress2" placeholder="주소"><br>
            <input type="text" id="sample2_detailAddress" name="memAddress3" placeholder="상세주소">
            <input type="text" id="sample2_extraAddress" name="memAddress4" placeholder="참고항목">
            <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
               <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
            </div>
             </td>
       </tr>
 
</table>

   <p align="center" style="margin-top: 10px;">
        <input type="submit" value="수정" >&nbsp;&nbsp;&nbsp; 
        <input type="reset" value="되돌리기">&nbsp;&nbsp;&nbsp;
        <a href="#"><input type="button" value="비밀번호 변경" ></a>&nbsp;&nbsp;&nbsp;
        <a href="#"><input type="button" value="계정 삭제" ></a>&nbsp;&nbsp;&nbsp;
     </p>
     
   </form>   
</section>
<!-- content 끝 -->

      </div>
   </section>
   <!-- container 끝 -->

   <footer id="footer">
      <div id="footer_box">
         <%@ include file="../include/footer.jsp" %>
      </div>      
   </footer>
   
</div>
</body>
</html>
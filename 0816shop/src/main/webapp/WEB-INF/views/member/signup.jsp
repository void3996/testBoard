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
      <div id="header_box">
         <%@ include file="../include/header.jsp" %>
      </div>
   </header>
   <!-- hearder 끝 -->   
   <nav id="nav">
      <div id="nav_box">
         <%@ include file="../include/nav.jsp" %>
      </div>
   </nav>
   
   <section id="container">
      <div id="container_box">
   
<!-- content 시작 -->
<section id="content">
   <form role="form" method="post" autocomplete="off">
     <div class="input_area">    
     </div>
     
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
          <input type="text" name="memId" id="memId" maxlength="15" required="required" placeholder="ID"/>
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
          <td colspan="3"><input type="text" name="memName" id="memName"></td>
        </tr>
       <tr>
          <td align="right">닉네임</td>
          <td colspan="4"><input type="text" name="memNickname" id="memNickname">
          <span style="font-size: 9px; ">2~10자리의 한글로만 입력</span>
          </td>
       </tr>
        <tr>
          <td align="right">메일주소</td>
          <td colspan="4"><input type="text" name="memEmail" id="memEmail">
          예)id@domain.com</td>
       </tr>
       <tr>
          <td align="right">전화번호</td>
          <td colspan="4"><input type="text" name="memPhone" id="memPhone">
          예)01012345678</td>
       </tr>
       <tr>
          <td align="right">성별</td>
          <td colspan="4">
            <input type="radio" name="memSex" value="남성" checked="checked" />남성&nbsp;&nbsp; 
            <input type="radio" name="memSex" value="여성" />여성
          </td>
       </tr>
        <tr>
           <td align="right">생년월일</td>
           <td colspan="4">
           
           <script type="text/javascript">
                         var today = new Date();
                         var toyear = parseInt(today.getFullYear ());
                         var start = toyear;
                         var end = toyear - 100;

                         document.write("<select name=memBirthday1> ");
                         document.write("<option value='"+toyear+"' selected>"+toyear);
                         for (i=start;i>=end;i--) document.write("<option value='"+(i-1)+"'>"+(i-1));
                         document.write("</select>년  ");

                         document.write("<select name=memBirthday2>");
                         document.write("<option value='1' selected>1");
                         for (i=1;i<=11;i++) document.write("<option value='"+(i+1)+"'>"+(i+1));
                         document.write("</select>월  ");

                         document.write("<select name=memBirthday3>");
                         document.write("<option value='1' selected>1");
                         for (i=1;i<=30;i++) document.write("<option value='"+(i+1)+"'>"+(i+1));
                         document.write("</select>일  </font>");
                  </script>
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
     
     <button type="submit" id="signup_btn" name="signup_btn">회원가입</button>
     
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
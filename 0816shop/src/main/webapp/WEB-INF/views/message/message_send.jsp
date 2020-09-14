<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지보내기</title>
<link rel="stylesheet" type="text/css" href="/resources/css/message.css">
</head>
<body>
	<div id="write_note_in">
	<form action="/message/send" method="post" enctype="multipart/form-data">
	    <div id="write_t">${member.memNickname}님의 쪽지보내기</div>
	      <input type='hidden' name='senderid' value='${member.memId }'>
        <div id="write_form">
            <div class="wr_ip"><input type="text" name="targetid" value="${target }" disabled/> </div>
            <div class="wr_ip wr_ip_top"><input type="text" name="title" placeholder="제목" required/></div>
            <div class="wr_ip wr_ip_top"><textarea name="content" placeholder="내용" required></textarea></div>
            <button type="submit" class="wri_bt wr_ip_top">보내기</button>
        </div>
    </form>
    </div>
</body>
</html>
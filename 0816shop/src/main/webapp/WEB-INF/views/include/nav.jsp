<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<ul>
<c:if test="${member == null}">
 <li>
  <a href="/member/signin">로그인</a>
 </li>
 <li>
  <a href="/member/signup">회원가입</a>
 </li>
</c:if>
<c:if test="${member != null}">

<c:if test="${member.verify == 9}">
<li>
 <a href="/admin/index">관리자 화면</a> 
</li> 
</c:if>
 <li>
  ${member.memNickname}님 환영합니다.
 </li>
 
 <li>
  <a href="/member/memedit">내 정보</a>
 </li>
 <li>
  <a href="/member/msg">쪽지함</a>
 </li>
  <li>
  <a href="/shop/cartList">카트 리스트</a>
 </li>
 <li>
  <a href="/shop/orderList">주문 리스트</a>
 </li>
 <li>
  <a href="/member/signout">로그아웃</a>
 </li>
</c:if>
</ul>



<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary sendBtn">Send Message</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>

<!-- JS, Popper.js, and jQuery -->

<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>


var modal = $(".modal");

function showMessageBox(){
	
	modal.modal("show");
	
}

$(".sendBtn").on("click", function(){
	
	console.log("sendBtn click...");
	
	
	
	var msg = {
		senderid: 'test1@naver.com',
		targetid: 'a',
		content:'한글테스트'
	};
	
	console.log($);
	
 	$.ajax({
		type : 'post',
		url : '/message/',
		data : JSON.stringify(msg),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr) {
			if (callback) {
				callback(result);
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	})
	
});


</script>
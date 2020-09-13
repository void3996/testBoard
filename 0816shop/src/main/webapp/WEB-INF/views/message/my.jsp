<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
<style>
</style>
    </head>
    <body>    
    	<h2>${member.memNickname}님의 쪽지함</h2>
       <div id="main_in">
	<table class="list-table">
    <thead>
      <tr>
        <th width="50" class="tc"></th>
        <th width="150" class="tl">보낸사람 </th>
        <th width="600" class="tl">내용</th>
        <th width="200" class="tc">받은날짜</th>
        <th width="200" class="tc">읽은날짜</th>
        <th width="150" class="tc">수신여부</th>
      </tr>
    </thead>
    <tbody>
    
    
    	<c:forEach items="${msglist }" var="msglist">
    		<tr>
    			<td><input type="checkbox" /></td>
    			<td><a href="/message/send?target=${msglist.senderid }">${msglist.senderid }</a></td>
    			<td>${msglist.content }</td>
    			<td>
    				
    			</td>
    			<td>
    				
   				</td>
    			<td>${msglist.status }</td>
    			
    		</tr>
    	</c:forEach>
    </tbody>
    
     </table>
    </div>
    
    
<script src="/resources/jquery/jquery-3.3.1.min.js"></script>    
<!-- <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script> -->
        <script src="/resources/bootstrap/bootstrap.min.js"></script>
        
        <script>
        
        function getMessageList(){
        	
        	console.log("get Message list............");
        	
        	 $.getJSON('/message/list', function(arr){
        		
        		console.log(arr)
        	}); 
        	
        }
        
        getMessageList();
        
        setInterval(function() {
			
        	getMessageList();
        	
		}, 1000 * 10);
        
        
        </script>
        
   </body>
</html>


function removeMem(){
	
	var getCheck= RegExp(/^[a-zA-Z0-9]{8,15}$/);
	
	if($("#memPassword1").val() == "" ){
	  	  
        alert("비밀번호를 입력해주세요");
        $("#Password1").focus();
        return false;
        
	} else if(!getCheck.test($("#memPassword1").val())){ // 비밀번 유효성 검사   	
  	  alert("비밀번호 형식에 맞게 입력해주세요"); 
  	  $("#memPassword1").focus(); 
  	  return false; 
  	  
   } else if($("#memPassword2").val() == "" ){
	  	  
        alert("나머지 비밀번호도 입력해주세요");
        $("#Password2").focus();
        return false;
        
	} else if(!getCheck.test($("#memPassword2").val())){ // 비밀번 유효성 검사   	
  	  alert("비밀번호 형식에 맞게 입력해주세요"); 
  	  $("#memPassword2").focus(); 
  	  return false; 
  	  
   }else if ($("#memPassword1").val() != $("#memPassword2").val()){
   	 alert("비밀번호가 일치하지 않습니다.");
   	 $("#memPassword1").val("");
   	 $("#memPassword2").val("");
   	 $("#memPassword1").focus(); 
   	 return false; 
   }
   
   if(confirm("계정을 정말로 삭제하시겠습니까?")){  
   		return true;
	} else {
		return false; // 취소
	} 
	
}
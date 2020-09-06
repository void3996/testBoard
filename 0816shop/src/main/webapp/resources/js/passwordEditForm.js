
function editPw(){
	var getEmail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
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
	  	  
        alert("새 비밀번호를 입력해주세요");
        $("#Password2").focus();
        return false;
        
	} else if(!getCheck.test($("#memPassword2").val())){ // 비밀번 유효성 검사   	
  	  alert("비밀번호 형식에 맞게 입력해주세요"); 
  	  $("#memPassword2").focus(); 
  	  return false; 
  	  
   } else if ($("#memPassword1").val() == $("#memPassword2").val()){
   	 alert("기존 비밀번호와 다르게 변경해주세요");
   	 $("#memPassword2").val("");
   	 $("#memPassword3").val("");
   	 $("#memPassword2").focus(); 
   	 return false; 
   }else if($("#memPassword3").val() == "" ){
	  	  
        alert("비밀번호를 입력해주세요");
        $("#Password3").focus();
        return false;
        
	} else if(!getCheck.test($("#memPassword3").val())){ // 비밀번 유효성 검사   	
  	  alert("비밀번호 형식에 맞게 입력해주세요"); 
  	  $("#memPassword3").focus(); 
  	  return false; 
  	  
   } else if ($("#memPassword2").val() != $("#memPassword3").val()){
   	 alert("새비밀번호가 일치하지 않습니다.");
   	 $("#memPassword2").val("");
   	 $("#memPassword3").val("");
   	 $("#memPassword2").focus(); 
   	 return false; 
   }
   
	
	
	
	
	
	
	
	
	

	return true;
}
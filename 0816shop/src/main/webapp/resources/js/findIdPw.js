function findId(){
	var getEmail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

	if($("#memEmail").val() == ""){
		alert("이메일을 입력해주세요");
		$("#memEmail").val("");
        $("#memEmail").focus();	
        return false;
	} else if(!getEmail.test($("#memEmail").val())){
		alert("이메일 형식에 맞게 입력해주세요");
        $("#memEmail").focus();	
        return false;
	}	
	return true;
}
function findPw(){
	var getEmail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	var getCheck= RegExp(/^[a-zA-Z0-9]{8,15}$/);
	
	if($("#memId2").val() == ""){
	  	  
        alert("아이디 입력해주세요");
        $("#memId2").val("");
        $("#memId2").focus();
        return false;
        
	}else if(!getCheck.test($("#memId2").val())){ // 아이디 유효성 검사
    	
  	  alert("아이디 형식에 맞게 입력해주세요"); 
  	  $("#memId2").val(""); 
  	  $("#memId2").focus(); 
  	  return false; 
  	  
   }else if($("#memEmail2").val() == ""){
		alert("이메일을 입력해주세요");
		$("#memEmail2").val("");
        $("#memEmail2").focus();	
        return false;
	} else if(!getEmail.test($("#memEmail2").val())){
		alert("이메일 형식에 맞게 입력해주세요");
        $("#memEmail2").focus();	
        return false;
	}
	return true;
}
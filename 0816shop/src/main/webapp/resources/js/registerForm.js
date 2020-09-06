$(function(){
		//아이디가 s_id인 요소에서 입력된 문자가 4자 이상 입력시
		//서버에 비동기식 호출을 수행한다.
		$("#memId").bind("keyup", function(){
			//s_id에 입력된 자원 가져오기
			var memId = $(this).val();
			//console.log(s_id);
			if(memId.length >= 8){
				//비동기식 통신 수행
				//console.log("비동기식 통신!");
				$.ajax({
					url: "checkId.do",
					type: "post",
					data: "id="+encodeURIComponent(memId),
					dataType: "json"
				}).done(function(data){
					//정상적으로 처리완료 시 수행하는 곳
					$("#msg").html(data.res);
					$("#chk_id").val(data.chk);
				}).fail(function(err){
					console.log(err);
				});
			}else{
				//아이디가 msg인 요소의 내용을 지워야 한다.
				$("#msg").html("");
			}
		});
	});
	
	function sendData(){
	
		var getCheck= RegExp(/^[a-zA-Z0-9]{8,15}$/);
		var getName= RegExp(/^[가-힣]{2,10}$/);
		var getNickname = RegExp(/^[가-힣]{2,10}$/);
		var getEmail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		var getPhone = RegExp( /^\d{10,11}$/);



			//아이디 공백 확인
			
	        if($("#memId").val() == ""){
	    	  
	        alert("아이디 입력해주세요");
	        $("#memId").val("");
	        $("#memId").focus();
	        return false;
	        
	        } else if(!getCheck.test($("#memId").val())){ // 아이디 유효성 검사
	        	
	    	  alert("아이디 형식에 맞게 입력해주세요"); 
	    	  $("#memId").val(""); 
	    	  $("#memId").focus(); 
	    	  return false; 
	    	  
	        } else if($("#memPassword1").val() == ""){ //비밀번호 공백 확인 
	        	
	    	alert("패스워드 입력해주세요"); 
	    	$("#memPassword1").focus(); 
	    	return false; 
	    	
	    	} else if($("#memId").val() == $("#memPassword1").val()){ //아이디 비밀번호 같음 확인 
	    		
	    	alert("아이디와 비밀번호가 같습니다"); 
	    	$("#memPassword1").focus(); 
	    	return false; 
	    	
	    	} else if(!getCheck.test($("#memPassword1").val())){ 
	    		
	    	alert("비밀번호 형식에 맞게 입력해주세요"); 
	    	$("#memPassword1").focus(); 
	    	return false; 
	    	
	    	} else if($("#memPassword2").val() == ""){  //비밀번호 확인란 공백 확인 
	    		
	  		  alert("비밀번호 확인란을 입력해주세요"); 
			  $("#memPassword2").focus(); 
			  return false; 
			  
			} else if($("#memPassword1").val() != $("#memPassword2").val()){ 
				
			  alert("비밀번호가 상이합니다"); 
			  $("#memPassword1").val(""); 
			  $("#memPassword2").val(""); 
			  $("#memPassword1").focus(); 
			  return false; 
			  
		    } else if($("#memName").val() == ""){ // 이름 체크
		    	alert("이름을 입력해주세요"); 
		    	$("#memName").focus(); 
		    	return false; 
		    } else if(!getName.test($("#memName").val())){ // 이름 유효성 검사
		    	
		      	  alert("이름 형식에 맞게 입력해주세요"); 
		    	  $("#memName").focus(); 
		    	  return false; 
		    	  
			} else if($("#memNickname").val() == ""){ // 닉네임 체크
				
		    	  alert("닉네임을 입력해주세요"); 
		    	  $("#memNickname").focus(); 
		    	  return false; 
		    	  
		    } else if(!getNickname.test($("#memNickname").val())){   // 닉네임 유효성 검사
		    	
	        	  alert("닉네임 형식에 맞게 입력해주세요"); 
	        	  $("#memNickname").focus(); 
	        	  return false; 
	        	  
	    	} else if($("#memEmail").val() == ""){  // 이메일 체크
	    		
	      	  alert("이메일을 입력해주세요"); 
	    	  $("#memEmail").focus(); 
	    	  return false; 
	    	  
	        } else  if(!getEmail.test($("#memEmail").val())){ // 이메일 유효성 검사
	        	
        	  alert("이메일 형식에 맞게 입력해주세요"); 
        	  $("#memEmail").focus(); 
        	  return false; 
        	  
    	   } else if($("#memPhone").val() == ""){  // 전화번호 체크
    		   
	    	  alert("전화번호를 입력해주세요"); 
	    	  $("#memPhone").focus(); 
	    	  return false; 
	    	  
           } else if(!getPhone.test($("#memPhone").val())){ // 전화번호 유효성 검사
        	   
    	  	  alert("전화번호 형식에 맞게 입력해주세요"); 
	    	  $("#memPhone").focus(); 
	    	  return false; 
	    	  
		   } else if($("#sample2_postcode").val() == ""){ // 주소1 체크
			   
			  alert("주소를 입력해주세요"); 
			  $("#sample2_postcode").focus(); 
			  return false; 
			  
		   }  else if($("#sample2_detailAddress").val() == ""){  // 주소2 체크
			   
			  alert("상세 주소를 입력해주세요"); 
			  $("#sample2_detailAddress").focus(); 
			  return false; 
			  
		   }  else if($("#chk_id").val() == 0){//id 체크
			   
		      alert("아이디가 중복된 아이디 입니다. 다시 확인 부탁드립니다."); 
			  $("#memId").focus(); 
			  return false; 
		   }
			
			// 최종 가입 여부
	      if ( confirm("회원 가입 하시겠습니까?") ) { 
	    	  return true; // 확인
	    	} else {
			  return false; // 취소
	    	} 
	
      }
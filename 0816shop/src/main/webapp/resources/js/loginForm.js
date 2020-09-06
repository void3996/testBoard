function actionA(){
	
	if($("#memId").val() == "" || $("#memPassword").val() == ""){

        $("#memId").focus();
        return null;
	 }

	
	  document.form.action="loginCheck.do";
	  document.form.submit();	
}
 function actionB(){
  document.form.action="findIdPwForm.do";
  document.form.submit();
 }

var password = document.getElementById("password"), confirm_password = document.getElementById("confirm_password");

function validatePassword(){
	if(password.value != confirm_password){
		confirm_password.setCustomValidity("Passwords do not Match")
	}else{
		confirm_password.setCustomValidity('');
	}
}

password.onchange=validatePassword;
confirm_password.onkeyup = validatePassword;
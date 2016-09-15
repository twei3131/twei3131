window.onload = function(){
	setKey();
}

//设置localStorage
function setKey(){
	var username = $("#username").text();
	var password = $("#password").text();
	if(localStorage['stuname'] == null && localStorage['pass'] == null){
		if(username != "" || password != ""){
			localStorage['stuname'] = username;
			localStorage['pass'] = password;
		}else{
			window.location = "/user/login.jsp";
		}
	}
}
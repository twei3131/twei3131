window.onload = function(){
	
}

//设置localStorage
function setKey(){
	var username = $("#username").text();
	var password = $("#password").text();
	if(localStorage['username'] == null && localStorage['password'] == null){
		if(username != "" || password != ""){
			localStorage['username'] = username;
			localStorage['password'] = password;
		}else{
			window.location = "/user/login.jsp";
		}
	}
}
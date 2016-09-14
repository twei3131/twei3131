window.onload = function(){
	aja();
	var i = 0;
	setInterval(function(){
		i++;
		if (i > 7) {
			i = 1;
		}
		$("body").fadeIn(3000,function(){
			$(this).css({"background-image":"url(./../img/register"+i+".jpg)"});
		})
	},30000);
}

function aja(){
	if(window.localStorage['username'] != null && window.localStorage['password'] != null){
		$.post("/teacher/getInfo",{"username":localStorage['username'],"password":localStorage['password']},function(data){
			var username = $("#username").text();
			var password = $("#password").text();
			if(localStorage['username'] == null && localStorage['password'] == null){
				localStorage['username'] = username;
				localStorage['password'] = password;
			}

		});
	}else{
		window.location="/user/login.jsp";
	}
}

function sub(){
	var username = $("#username").text();
	var password = $("#password").text();
	var subjectId = $("#subId").val();
	var groupId = $("#gpId").val();
	$.post("/teacher/initQRCode",{"teacherId":username,"password":password,"subjectId":subjectId,"groupId":groupId},function(data){
		
	});
}
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
		$.post("",{"username":localStorage['username'],"password":localStorage['password']},function(data){
			
		});
	}else{
		window.location="/user/login.jsp";
	}
}
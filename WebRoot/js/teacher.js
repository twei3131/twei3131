window.onload = function(){
	setKey();
	aja();
	listenSelect();
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

//获取课程
function aja(){
	if(window.localStorage['username'] != "" && window.localStorage['password'] != ""){
		$.post("/teacher/getInfo",{"username":localStorage['username'],"password":localStorage['password']},function(data){
			var datas = "";
			var i = 0;
			$.each(data,function(){
				datas+='<option value="'+ data[i].subjectId +'">'+data[i].subjectName+'</option>';
				i++;
			});
			$("#subId").html(datas);
		});
	}else{
		window.location="/user/login.jsp";
	}
}

//监听select
function listenSelect(){
	$("select").on('click',function(){
		getGroupIds();
	});
}

//获取组编号
function getGroupIds(){
	$.post("/teacher/getGroupId",{"username":localStorage['username'],"subjectId":$("#subId option:selected").val()},function(data){
		if(JSON.stringify(data) == '[]'){
			$("#gpId").html('<option>无</option>');
		}
	});
}

//设置localStorage
function setKey(){
	var username = $("#username").text();
	var password = $("#password").text();
	if(localStorage['username'] == "" && localStorage['password'] == ""){
		localStorage['username'] = username;
		localStorage['password'] = password;
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
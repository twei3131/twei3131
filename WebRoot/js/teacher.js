window.onload = function(){
	setKey();
	aja();
	getName();
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
	if(window.localStorage['username'] != null && window.localStorage['password'] != null){
		$.post("getInfo",{"username":localStorage['username'],"password":localStorage['password']},function(data){
			var datas = "";
			var i = 0;
			$.each(data,function(){
				datas+='<option value="'+ data[i].subjectId +'">'+data[i].subjectName+'</option>';
				i++;
			});
			$("#subId").html(datas);
		});
	}
}

//监听select
function listenSelect(){
	$("#subId").on('click',function(){
		getGroupIds();
	});
}

//获取组编号
function getGroupIds(){
	$.post("getGroupId",{"username":localStorage['username'],"subjectId":$("#subId option:selected").val()},function(data){
		if(JSON.stringify(data) == '[]'){
			$("#gpId").html('<option>无</option>');
		}else{
			var i = 0;
			var datas = "";
			$.each(data,function(){
				datas += '<option value="'+data[i].groupId+'">'+data[i].groupName+'</option>';
				i++;
			});
			$("#gpId").html(datas);
		}
	});
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
			window.location = "user/login.jsp";
		}
	}
}

function sub(){
	var username = localStorage['username'];
	var password = localStorage['password'];
	var subjectId = $("#subId  option:selected").val();
	var groupId = $("#gpId option:selected").val();
	$.post("initQRCode",{"teacherId":username,"password":password,"subjectId":subjectId,"groupId":groupId},function(data){
		if(data.errcode == "100"){
			alert("非法");
		}else if(data.errcode == "200"){
			alert("不能重复");
		}else if(data.errcode == "000"){
			window.localStorage['qrcodeUrl'] = data.url; 
			window.location = "qrcode.jsp";
		}
	});
}

//获取姓名
function getName(){
	var username = localStorage['username'];
	$.post("getNameByUserId",{"username":username},function(data){
		$("#teaname").text(data.name);
	});
}
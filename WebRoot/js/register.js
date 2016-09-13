window.onload = function(){
	$("body").css({"background-image":"url(./../img/register"+getRandom()+".jpg)"});
}

function getRandom(){
	var randomNum = Math.random() * 7 + 1;
	return Math.floor(randomNum);
}

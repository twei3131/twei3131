window.onload = function(){
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
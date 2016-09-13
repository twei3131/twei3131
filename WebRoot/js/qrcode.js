function seta(){
	var text = $("a").text();
	if(text == "上课"){
		$("a").text("下课");
		$("a").attr('href',"#");
	}else if(text == "下课"){
		$("a").text("放学");
		$("a").attr('href',"#");
	}
}
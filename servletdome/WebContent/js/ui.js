//判断浏览器是不是Ie8,ie8 window和document的height相差4个像素
var isIE=!!window.ActiveXObject;
var isIE6=isIE&&!window.XMLHttpRequest;
var isIE8=isIE&&!!document.documentMode;

window.onresize=function(){
	//调整高度
	changeHeight();
}
//调整高度
function changeHeight(){
	if(isIE8){
		if($(window).height()<$(document).height()-4){
			$("#dialogBody").css("height",$(document).height()-4);
		}else{
			$("#dialogBody").css("height",'100%');
		}
	}else{
		if($(window).height()<$(document).height()){
			$("#dialogBody").css("height",$(document).height());
		}else{
			$("#dialogBody").css("height",'100%');
		}
	}
}

function showDialog(id){
	if($("#dialogBody").length==0){
		$("body").prepend("<div id='dialogBody' class='dialogBody'></div>");
		$("#dialogBody").append("<div id='dialogBody_content' class='dialogBody_content'></div>");
		$("#dialogBody_content").append("<div class='dialogBody_content_div' onclick='closeShowDialog();'>X</div>");
		$("#dialogBody_content").append($("#"+id).html());
		//调整高度
		changeHeight();
	}
	$("#dialogBody").show();
}

function showAlert(text){
	$("body").append("<div id='dialogBody_content_html' class='dialogBody_content_html'></div>");
	$("#dialogBody_content_html").show();
	$("#dialogBody_content_html").html(text);
	setTimeout(function(){
		$("#dialogBody_content_html").hide();
	},2000)
}

function showTips(id,value){
	var top = $("#"+id).offset().top;
	var left = $("#"+id).offset().left;
	showAlert(value);
}

function closeShowDialog(){
	$("#dialogBody").hide();
}
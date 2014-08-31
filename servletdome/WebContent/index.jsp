<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ui.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.min.js"></script>
<script type="text/javascript">
	function progress(){
		var uploadFile = $("#uploadFile").val();
		if(uploadFile!=""){
			uploadForm.action="upload";
			uploadForm.submit();
			settime=setInterval(function(){
				$.ajax({
					url:"upload",
					success:function(data){
						$("#progresstxt").text(data+"%");
						if(data==100){
							clearInterval(settime);
							showAlert('上传成功！');
						}
					}
				});
			},500);
		}else{
			showTips("douploadFile","搞毛");
		}
	}
</script>
</head>
<body>
    <a href="javascript:void(0);" onclick="showDialog('div3');" style="float: right">登陆</a>
    <div id="div3" style="display: none;">
		<form action="">
			<br><br><br><br>
			<table align="center">
    			<tr>
    				<td><input class="text" type="text"></td>
    			</tr>
    			<tr>
    				<td><input class="text" type="password"></td>
    			</tr>
    			<tr>
    				<td><input class="button" type="button" value="登陆" onclick="showAlert('你好！');"></td>
    			</tr>
    		</table>
		</form>
    </div>
    <form id="uploadForm" method="post" target="hidden_frame" enctype="multipart/form-data">
    	<input class="text2" type="text" id="uploadFiletxt" onclick="uploadFile.click()">  
		<input type="file" id="uploadFile" name="uploadFile" style="display:none" onchange="uploadFiletxt.value=this.value">
    	<input class="button" type="button" id="douploadFile" value="上传" onclick="progress();">
    </form>
    <iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe> 
    <label id="progresstxt"></label>
    <a href="download?fileName=1.txt">下载文件</a>
</body>
</html>

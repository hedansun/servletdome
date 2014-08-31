<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>hello easyui</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4/demo/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div style="margin:20px 0;"></div>
	<div data-options="region:'north'" style="height:50px;display: none;"></div>
	<div data-options="region:'south',split:true" style="height:50px;display: none;"></div>
	<div data-options="region:'east',split:true,collapsed:true" title="East" style="width:180px;"></div>
	<div data-options="region:'west',split:true" title="导航菜单" style="width:200px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="用户管理" data-options="selected:true" style="padding:10px;">
				<%@include file="easyui_west.jsp" %>
			</div>
			<div title="权限管理" data-options="" style="padding:10px;">
				content2
			</div>
			<div title="系统管理" style="padding:10px">
				content3
			</div>
			<div title="其他设置" style="padding:10px">
				content4
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'主内容'">
		<div id="tt" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title="About" data-options="tabWidth:120,href:'js/jquery-easyui-1.4/demo/tabs/_content.html'" style="padding:10px"></div>
			<div title="DataGrid" data-options="tabWidth:120" style="padding:5px">
				<table class="easyui-datagrid"
						data-options="url:'${pageContext.request.contextPath }/js/jquery-easyui-1.4/demo/layout/datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'itemid'" width="80">Item ID</th>
							<th data-options="field:'productid'" width="100">Product ID</th>
							<th data-options="field:'listprice',align:'right'" width="80">List Price</th>
							<th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
							<th data-options="field:'attr1'" width="150">Attribute</th>
							<th data-options="field:'status',align:'center'" width="50">Status</th>
						</tr>
					</thead>
				</table>
			</div>
			<%@include file="easyui_center.jsp" %>
		</div>
	</div>
	<!-- 右键菜单 begin -->
	<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
		<div data-options="name:'new'">New</div>
		<div data-options="name:'save',iconCls:'icon-save'">Save</div>
		<div data-options="name:'print',iconCls:'icon-print'">Print</div>
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">Exit</div>
	</div>
	<!-- 右键菜单 end -->
	<script type="text/javascript">
		var tabs = $('#tt').tabs().tabs('tabs');
		for(var i=0; i<tabs.length; i++){
			tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
				$('#tt').tabs('select', e.data.index);
			});
		}
		
		$(function(){
			$('#easyuiTree').tree({
				onContextMenu: function (e, title) {
                    e.preventDefault();
                    $("#mm").menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                },
				onClick:function(node){
				    if (node.attributes && $('#easyuiTree').tree('isLeaf',node.target)) {
				    	addTab(node);
		            }
				}
			});
			
			var tt = $('#tt');
			
			function addTab(node){
				if(tt.tabs("exists",node.text)){
					tt.tabs("select",node.text);
				}else{ 
					tt.tabs("add",{
						tabWidth:120,
						title:node.text,
						closable:true,
						//content:'<iframe frameborder="0" src="'+node.attributes.url+' style="width:100%;height:100%;"></iframe>'
						content:'<iframe frameborder="0" src="index.jsp" style="width:100%;height:100%;"></iframe>'
					});
					var tabs = $('#tt').tabs().tabs('tabs');
					for(var i=0; i<tabs.length; i++){
						tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
							$('#tt').tabs('select', e.data.index);
						});
					}
				}
			}
			
			$(document).bind('contextmenu',function(e){
				e.preventDefault();
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			});
			
		});
		
		function menuHandler(item){
			//$('#log').prepend('<p>Click Item: '+item.name+'</p>');
		}
	</script>
</body>
</html>
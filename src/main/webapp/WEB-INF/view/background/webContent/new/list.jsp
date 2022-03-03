<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:20px;
	margin-left: 220px;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .title_span{
	margin-left: 13px;
}
.tab1_div .toolbar .title_inp{
	width: 120px;height: 25px;
}
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var webContentPath=path+'background/webContent/';
$(function(){
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var mc=$("#toolbar #mc_inp").val();
			var wzlxmc=$("#toolbar #wzlxmc_inp").val();
			tab1.datagrid("load",{mc:mc,wzlxmc:wzlxmc});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=webContentPath+"new/new";
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"新闻查询",
		url:webContentPath+"queryNewList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"title",title:"标题",width:200},
			{field:"createTime",title:"创建时间",width:200},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{title:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"title",colspan:3});
				data.total=0;
			}

			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function setFitWidthInParent(o){
	var width=$(o).css("width");
	return width.substring(0,width.length-2)-250;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<span class="title_span">标题：</span>
			<input type="text" class="title_inp" id="title_inp" placeholder="请输入标题"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="add_but">添加</a>
			<a id="remove_but">删除</a>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>
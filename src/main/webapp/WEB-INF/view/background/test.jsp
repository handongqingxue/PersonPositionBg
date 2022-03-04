<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"
		+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath %>resource/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
var path='<%=basePath %>';
$(function(){
	$.ajax({
		url:"http://localhost:8080/PersonPositionBg/foreground/getNewById",
		contentType: "application/json",
		data:JSON.stringify({"id":3}),
		dataType:"jsonp",
		type: "post",
		jsonpCallback:"jsonpCallback",
		success:function(result){
			console.log(result);
			var data=JSON.parse(result);
			var status=data.status;
			alert(status)
			if(status=="ok"){
				var contentDiv=$("#content_div");
				contentDiv.append(data.entity.content);
			}
			else{
				
			}
		}
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<div id="content_div"></div>
</body>
</html>
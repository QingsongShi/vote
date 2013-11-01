<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
a:link {
 COLOR: #2000B4;
 TEXT-DECORATION: none;
}
a:visited {
 COLOR: #2000B4;
 TEXT-DECORATION: none;
}
a:hover {
 COLOR: #E2B500;
 text-decoration: none;
}
a:active {
 COLOR: #E2B500;  
 text-decoration: none;
}
</style>
</head>
<body>
	<div style="font-size: 20px;">
		<a href="javascript:()" target="right">投票统计</a> <p>
		
		<a href="a/showUserInfo" target="right">人员管理</a> <p>
		
		<a href="a/showDeptInfo" target="right">部门管理</a> <p>
		
		<a href="a/getSystemInfo" target="right">系统设置</a> <p>
	</div>
</body>
</html>
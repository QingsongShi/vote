<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>
<body>
	<div style="font-size:20px;">部门管理&gt;&gt;添加部门：</div>
	<p>
	<form action="a/deptAdd" method="post">
		<table>
			<tr>
				<td>年份：</td>
				<td><input type="text" value="<s:property value='systemYear'/>" disabled="disabled"></td>
			</tr>
			<tr>
				<td>部门名称：</td>
				<td><input type="text" name="deptName"></td>
			</tr>
			<tr>
				<td><input type="submit" value="保存"></td>
			</tr>
		</table>
	</form>
</body>
</html>
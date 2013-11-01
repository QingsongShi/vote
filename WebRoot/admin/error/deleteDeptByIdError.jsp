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
<script type="text/javascript">
	function goBack() {
		history.go(-1);
	}
</script>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<div style="font-size:20px;">部门管理：</div>
<p style="color: red;font-size: 16px;font-weight: 700">部门删除失败</p>
<table class="solidTable" width="20%">
	<tr>
		<td>年份</td>
		<td>名称</td>
	</tr>
	<tr>
		<td><s:property value="deleteFailedDept.year"/></td>
		<td><s:property value="deleteFailedDept.deptName"/></td>
	</tr>
</table>
<p>失败原因：可能是有多个用户属于该部门，请先删除该部门所拥有的用户。<p>
<input type="button" value="返回" onclick="goBack()">
<s:debug></s:debug>
</body>
</html>
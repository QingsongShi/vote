<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="userLogin" method="post">
    	<div style="font-family: 宋体;font-size: 18px;color: red;"><s:property value="forbidLoginMessage" /></div>
    	<table>
    		<tr>
	    		<td align="right">账号：</td>
	    		<td align="left"><input type="text" name="account"></td>
    		</tr>
    		<tr>
	    		<td align="right">密码：</td>
	    		<td align="left"><input type="password" name="password"></td>
    		</tr>
    		<tr>
    			<td colspan="2"><input type="submit" value="登陆" <s:if test="forbidLoginMessage!=null">disabled</s:if>></td>
    		</tr>
    	</table>
    	<div style="font-family: 宋体;font-size: 18px;color: red;"><s:property value="fieldErrors.loginFailMsg[0]" /></div>
    </form>
    <s:debug></s:debug>
  </body>
</html>

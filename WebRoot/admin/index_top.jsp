<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<div align="right" style="margin-top: 10px;color: #808080;font-family: 宋体;font-size: 16px">
		正在进行<font color="black"><b><s:property value="vSystem.year"/></b></font>年投票，
		<s:if test="vSystem.allowLogin == 1">
			<font color="black"><b>允许</b></font>
		</s:if>
		<s:elseif test="vSystem.allowLogin == 0">
			<font color="red"><b>不允许</b></font>
		</s:elseif>
		用户登录&nbsp;<a href="a/getSystemInfo" target="right"><span style="font-size: 14px">【修改】</span></a>
	</div>
	<div align="right" style="margin-top: 15px">
		<a href="adminLogout">【注销】</a>
	</div>
</body>
</html>





















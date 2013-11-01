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
</head>
<body style="font-size: 18px">
<div style="font-size: 20px;">系统设置：</div>
	<p>
<form action="a/updateSystemInfo" method="post">
	<select name="systemYear">
				<s:iterator begin="2013" end="2020" step="1" status="status">
					<option value="<s:property value='#status.count+2012'/>" <s:if test="#status.count+2012==vSystem.year">selected</s:if>>
						<s:property value='#status.count+2012'/>
					</option>
				</s:iterator>
	</select>年投票正在进行
	<p>
	<select name="systemAllowLogin">
		<option value="1" <s:if test="vSystem.allowLogin == 1">selected</s:if>>允许</option>
		<option value="0" <s:if test="vSystem.allowLogin == 0">selected</s:if>>不允许</option>
	</select>
	用户登录
	<p>
	<input type="submit" value="修改">
</form>
</body>
</html>
















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
    <title>My JSP 'poll_personal.jsp' starting page</title>
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
  	<div style="font-size: 18px">被投票人：<s:property value="votee.username"/>	<p>
  	<div style="font-size: 18px">述职述廉报告：</div><s:property value="votee.report" escapeHtml="false"/> <p>
  <form action="u/addBallot?deptId=<s:property value='deptId'/>&pager.offset=<s:property value='pager.offset'/>" method="post"> 
  <input type="hidden" name="ballot.votee.id" value="<s:property value="votee.id"/>">
  	<b>一：综合评价</b><br><br>
		<input type="radio" id="zhcp_1" name="ballot.overallMerit" value="4"/>优秀&nbsp;&nbsp;
		<input type="radio" id="zhcp_2" name="ballot.overallMerit" value="3"/>称职&nbsp;&nbsp;
		<input type="radio" id="zhcp_3" name="ballot.overallMerit" value="2"/>基本称职&nbsp;&nbsp;
		<input type="radio" id="zhcp_4" name="ballot.overallMerit" value="1"/>不称职<br><br>
    <b>二：定性考核</b><br><br>
		1.思想品德<br><br>
		<input type="radio" id="sxpd_1" name="ballot.ideology" value="3"/>好&nbsp;&nbsp;&nbsp;
		<input type="radio" id="sxpd_2" name="ballot.ideology" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="sxpd_3" name="ballot.ideology" value="1"/>差<br><br>
		2.工作能力<br><br>
		<input type="radio" id="gznl_1" name="ballot.workingAbility" value="3"/>高&nbsp;&nbsp;&nbsp;
		<input type="radio" id="gznl_2" name="ballot.workingAbility" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="gznl_3" name="ballot.workingAbility" value="1"/>低<br><br>
		3.敬业精神<br><br>
		<input type="radio" id="jyjs_1" name="ballot.professionalEthics" value="3"/>好&nbsp;&nbsp;&nbsp;
		<input type="radio" id="jyjs_2" name="ballot.professionalEthics" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="jyjs_3" name="ballot.professionalEthics" value="1"/>差<br><br>
		4.廉洁自律<br><br>
		<input type="radio" id="jynl_1" name="ballot.clean" value="3"/>好&nbsp;&nbsp;&nbsp;
		<input type="radio" id="jynl_2" name="ballot.clean" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="jynl_3" name="ballot.clean" value="1"/>差<br><br>
		5.创新能力<br><br>
		<input type="radio" id="cxnl_1" name="ballot.innovationAbility" value="3"/>好&nbsp;&nbsp;&nbsp;
		<input type="radio" id="cxnl_2" name="ballot.innovationAbility" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="cxnl_3" name="ballot.innovationAbility" value="1"/>差<br><br>
	<b>三：定量考核</b>(工作目标完成情况)<br><br>
		<input type="radio" id="gzjx_1" name="ballot.completionOfJobs" value="3"/>好&nbsp;&nbsp;&nbsp;
		<input type="radio" id="gzjx_2" name="ballot.completionOfJobs" value="2"/>中&nbsp;&nbsp;&nbsp;
		<input type="radio" id="gzjx_3" name="ballot.completionOfJobs" value="1"/>差<br><br>	
		<input type="submit" value="投票">	
   </form>
    <s:debug></s:debug>
  </body>
</html>

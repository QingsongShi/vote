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

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="kindeditor-4.1.9/themes/default/default.css" />
<script charset="utf-8" src="kindeditor-4.1.9/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor-4.1.9/lang/zh_CN.js"></script>

<script type="text/javascript">
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="report"]', {
		items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
		         'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		         'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		         'superscript', '|', 'selectall', '-',
		         'title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold',
		         'italic', 'underline', 'strikethrough', 'removeformat', '|',
		         'advtable', 'hr', 'emoticons', 'link', 'unlink']
	});

});


</script>
</head>
<body style="font-size: 18px">
<div style="font-size:20px;">人员管理&gt;&gt;添加人员：</div>
<p>
<form action="a/addUser" method="post">
	姓名：<input type="text" name="username"> <p>
	账号：<input type="text" name="account"> <span>账号如果不填写，则系统自动生成。</span><p>
	密码：<input type="password" name="password"> <span>密码如果不填写，则系统自动生成。</span><p>
	所属部门：<select name="deptId">
			<s:iterator value="deptList" var="dept">
				<option value="<s:property value='#dept.id'/>">
					<s:property value='#dept.deptName'/>
				</option>
			</s:iterator>
			
		</select><p>
	是否是领导班子：<input type="radio" name="isLeaderShip" value="1">是领导班子
				<input type="radio" name="isLeaderShip" value="0">不是领导班子 <p>
	具有投票权限的部门：<br>
		<table class="solidTable" width="20%">
			<tr>
				<td width="80%">部门</td>
				<td>操作</td>
			</tr>
			<s:iterator value="deptList" var="dept">
				<tr>
					<td><s:property value="#dept.deptName"/></td>
					<td>
						<input type="checkbox" name="permitDeptIds" value="<s:property value="#dept.id"/>">
					</td>
				</tr>
			</s:iterator>
		</table><p>
	述职述廉报告：
		<textarea name="report" style="width:900px;height:400px;visibility:hidden;"></textarea>	<p>
		<input type="submit" value="保存">
</form>
</body>
</html>
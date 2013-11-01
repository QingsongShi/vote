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
<div style="font-size:20px;">人员管理&gt;&gt;修改人员：</div>
<p>
	<form action="a/updateUser" method="post">
		<input type="hidden" name="id" value="<s:property value='user.id'/>">
	年份：<input type="text" value="<s:property value='user.year'/>" disabled="disabled"><p>
	姓名：<input type="text" name="username" value="<s:property value='user.username'/>"><p>
	账号：<input type="text" name="account" value="<s:property value='user.account'/>"><p>
	密码：<input type="text" name="password" value="<s:property value='user.password'/>"><p>
	所属部门：<select name="deptId">
			<s:iterator value="deptList" var="dept">
				<option value="<s:property value='#dept.id'/>" <s:if test="#dept.id==user.dept.id">selected</s:if>>
					<s:property value='#dept.deptName'/>
				</option>
			</s:iterator>
			
		</select><p>
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
						<input type="checkbox" name="permitDeptIds" value="<s:property value="#dept.id"/>"
						<s:iterator value="user.deptSet" var="permitDept">
							<s:if test="#dept.id==#permitDept.id">checked="checked"</s:if>
						</s:iterator>
						>
					</td>
				</tr>
			</s:iterator>
		</table><p>
			
	述职述廉报告：
		<textarea name="report" style="width:900px;height:400px;visibility:hidden;"><s:property value="user.report" /></textarea>	<p>
		<input type="submit" value="保存">
	</form>
<s:debug></s:debug>
</body>
</html>
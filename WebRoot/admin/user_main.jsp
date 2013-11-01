<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
function deleteUserById(userId) {
	if(window.confirm("确定要删除吗?")) {
		window.self.location = "<%=basePath%>a/deleteUserById?userId="+userId;
	}
}
function updateUserById(userId) {
	window.self.location = "<%=basePath%>a/userUpdateInput?userId="+userId;
}
</script>
</head> 
<body>
	<div style="font-size:20px;">人员管理：</div>
	<p>
	<pg:pager items="${userPM.totalRecords }"
	maxIndexPages="${initParam.maxIndexPages }" maxPageItems="${pageSize }"
	export="pageOffset,currentPageNumber=pageNumber"
	url="a/showUserInfo">
	<s:if test="null!=userPM.list && !userPM.list.isEmpty()">
		<table border="1" width="80%" class="solidTable">
			<tr>
				<td width="10%">年份</td>
				<td width="25%">姓名</td>
				<td width="25%">部门</td>
				<td width="20%">账号</td>
				<td width="20%">基本操作</td>
			</tr>
			<s:iterator value="userPM.list" var="user">
				<tr>
					<td><s:property value="#user.year"/></td>
					<td><s:property value="#user.username"/></td>
					<td><s:property value="#user.dept.deptName"/></td>
					<td><s:property value="#user.account"/></td>
					<td>
						<input type="button" value="删除" onclick="deleteUserById(<s:property value='#user.id'/>)">
						<input type="button" value="修改" onclick="updateUserById(<s:property value='#user.id'/>)">
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<s:else>
		<div style="font-size: 16px">
			<s:property value="systemYear"/>年无可用数据
		</div>
	</s:else>
	<table width="80%">
	<tr>
	<td align="right">
	<pg:index>
        <pg:first>
        	<a href="${pageUrl }">首页</a>
        </pg:first>
        <pg:prev>
            <a href="${pageUrl }">上一页</a>
        </pg:prev>
        <pg:pages>
            <c:choose>
                <c:when test="${pageNumber eq currentPageNumber}">
                    <font color="red">[${pageNumber }]</font>
                </c:when>
                <c:otherwise>
                    <a href="<%=pageUrl%>">${pageNumber }</a>
                </c:otherwise>
            </c:choose>
        </pg:pages>
        <pg:next>
            <a href="<%=pageUrl%>">下一页</a>
        </pg:next>
        <pg:last>
            <a href="<%=pageUrl%>">尾页</a>
        </pg:last>
        ||<a href="a/userInfoExport">导出</a>
    </pg:index>
    <a href="a/userAddInput">添加</a>
    </td>
    </tr>
    </table>
	</pg:pager>
</body>
</html>
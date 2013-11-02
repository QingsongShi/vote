<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dept_internal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<script type="text/javascript">
	
		function goPoll(userId) {
			location = "<%=basePath%>u/showBallot?userId="+userId+"&deptId=<s:property value='deptId'/>"+"&pager.offset=<s:property value='pager.offset'/>";
		}
	</script>
  </head>
  
  <body>
  	<pg:pager items="${voteePM.totalRecords }"
		maxIndexPages="${initParam.maxIndexPages }" maxPageItems="${pageSize }"
		export="pageOffset,currentPageNumber=pageNumber"
		url="u/permitDeptNameList">
	<s:if test="null!=voteePM.list && !voteePM.list.isEmpty()">
		<table border="1" width="40%" class="solidTable">
			<tr>
				
				<td width="40%">姓名</td>
				<td width="40%">部门</td>
				<td width="20%">操作</td>
			</tr>
			<s:iterator value="voteePM.list" var="votee">
				<tr>
					<td><s:property value="#votee.username"/></td>
					<td><s:property value="dept.deptName"/></td>
					<td>
						<input type="button" value="投票" 
							<s:iterator value="logSet" var="log">
								<s:if test="#log.votee.id==#votee.id">disabled="disabled" </s:if>
							</s:iterator>
						onclick="goPoll(<s:property value='#votee.id'/>)">
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<s:else>
		无可用数据
	</s:else>
	<table width="40%">
	<tr>
	<td align="right">
	<pg:index>
		<pg:param name="deptId"/>
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
        </pg:last>||
    </pg:index>
    <a href="u/index">返回主页</a>
    </td>
    </tr>
    </table>
	</pg:pager>
	
  </body>
</html>

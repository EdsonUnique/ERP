<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<link rel="stylesheet"	href="${pageContext.request.contextPath}/js/treeview/jquery.treeview.css" />
		<link rel="stylesheet"	href="${pageContext.request.contextPath}/js/treeview/screen.css" />
		<script src="${pageContext.request.contextPath}/js/treeview/jquery.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/treeview/jquery.cookie.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/treeview/jquery.treeview.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/treeview/jquery.treeview.async.js" type="text/javascript"></script>
	</head>
	<body>
		<ul id="black" class="filetree"></ul>
	</body>
	<script type="text/javascript">
		$("#black").treeview({
			url : "${pageContext.request.contextPath}/menu/menu_showMenu.action"
		})
	</script>
</html>


<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<style> 
	body{
	background-color: blue;
	font-size: 90px;
	}
	.button{
	font-size: 120px;
	width:500px;
	height:500px;
	}
	</style>
	<head>
		<title>Administration</title>
	</head>
	<body>
		<c:if test="${! empty model.error}">
			<div class="error">${model.error}</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/admin" method="get">
<table>
	<c:forEach items="${activeOrder}" var="Order" varStatus="iter">
	
	<button class="button">view order number:${Order.orderId}</button>
	</c:forEach>
</table>   	
</form>
	</body>
</html>
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
	font-size: 80px;
	width:100%;
	height:300px;
	}
	</style>
	<head>
		<title>Administration</title>
	</head>
	<body>
		<c:if test="${! empty model.error}">
			<div class="error">${model.error}</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/admin" method="post">
<table>
	<c:forEach items="${activeOrder}" var="order" varStatus="iter">
	
	<button class="button" name="orderId" value="${order.orderId}">view order number: ${order.orderId}</button>
	</c:forEach>
</table>   	
</form>
	</body>
</html>
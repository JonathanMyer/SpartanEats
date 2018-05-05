<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<style> 
	body{
	background-color: #f2f2f2;;
	font-size: 90px;
	}
	.button{
	font-size: 80px;
	width:100%;
	height:300px;
	}
	h1{
	text-align: center;
	color: white;
	font-size: 80px;
	text-shadow: 6px 6px 10px #980000;
	padding-bottom: 15px;
	}
	.Submit{
	width: 100%;
	height: 200px;
	
	left: 0;
	text-align: center;
	font-size: 60px;
	color: white;
	background-color: #980000;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-shadow: 6px 6px 10px Black;
	box-shadow: 20px 25px 70px #E80000 inset;
}
}
	</style>
	<head>
		<title>Administration</title>
		<h1><b>Admin Active Orders</b></h1>
	</head>
	<body>
		<c:if test="${! empty model.error}">
			<div class="error">${model.error}</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/admin" method="post">
<table>
	<c:forEach items="${activeOrder}" var="order" varStatus="iter">
	
	<button class="Submit" name="orderId" value="${order.orderId}">view order number: ${order.orderId}</button>
	</c:forEach>
</table>   	
</form>
	</body>
</html>
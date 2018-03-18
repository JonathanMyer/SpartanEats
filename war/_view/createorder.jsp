<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Login</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		
		<c:if test="${! empty model.error}">
			<div class="error">${model.error}</div>
		</c:if>
		
		<form action="${pageContext.servletContext.contextPath}/createorder" method="post">
			<table>
				
				<c:forEach items="${model.inventory.item}" var="item">            
        			<span class="item">${item.name}</span>             
        			<input type="submit" name="${item.name}" value="Select" />            
    			</c:forEach>
			</table>
		</form>
	</body>
</html>

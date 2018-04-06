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
		body{
		font-size: 25px;
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
			<c:forEach items="${inventory.itemList}" var="item">                      
      			 <tr>
      			 	<td class = "Label">${item.itemName}</td>
      			 	<td>${item.price}</td>
      			 	<td><input type = "Submit" value="${item.itemName}" name  = "additem"></input></td>
      			 </tr>
   			</c:forEach>
		</table>
			
			<div>Currently Selected Items:</div>
			<table>
				<c:forEach items="${order.itemList}" var="item">
					<tr>
						<td class = "Label">${item.itemName}</td>
      			 		<td>${item.price}</td>          
      			 		<td><input type = "Submit" value="${item.itemName}" name  = "removeitem"></input></td>
      			 	</tr>
   				</c:forEach>
   				<tr>
   					<td class = "Label">Total:</td>
   					<td> ${order.totalPrice}</td>
   				</tr>
   				
			</table>

		
		</form>
		
		
	</body>
</html>

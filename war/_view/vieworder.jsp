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
		
		<form action="${pageContext.servletContext.contextPath}/vieworder" method="post">
			
		<button type = "Submit" value="true" name="continueOrder" style="vertical-align:middle"><span>Continue Ordering</span></button>
		
		<div>Currently Selected Items:</div>
		<table>
			<c:forEach items="${order.itemList}" var="item">
				<tr>
					<td class = "Label">${item.itemName}</td>
		   			 <td>${item.price}</td>          
		   			 <td><button type = "Submit" value="${item.itemName}" name  = "removeitem">remove</button></td>
		   		</tr>
			</c:forEach>
				
						
		</table>
		</table>
			
			<div>Currently Selected Drinks:</div>
			<table>
				<c:forEach items="${order.drinkList}" var="drink">
					<tr>
						<td class = "Label">${drink.itemName}</td>
      			 		<td>${drink.price}</td>          
      			 		<td><button type = "Submit" value="${drink.itemName}" name  = "removedrink">remove</button></td>
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

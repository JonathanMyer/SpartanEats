<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Preferences</title>
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
		<form action="${pageContext.servletContext.contextPath}/preferences" method="post">
			<table>
				<tr>
					<td class="label">Delivery:</td>
					<td><input type="text" name="delivery" size="12" value="${Order.getDelivery}" /></td>
				</tr>
				<tr>
					<td class="label">Pickup:</td>
					<td><input type="text" name="pickup" size="12" value="${order.getPickup}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Order">
		</form>
	</body>
</html>
	</body>
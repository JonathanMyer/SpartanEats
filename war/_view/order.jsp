<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Order</title>
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
		<form action="${pageContext.servletContext.contextPath}/order" method="post">
			<table>
				<tr>
					<td class="label">Item:</td>
					<td><input type="text" name="mainItem" size="12" value="${order.getMainItem}" /></td>
				</tr>
				<tr>
					<td class="label">Side:</td>
					<td><input type="text" name="side" size="12" value="${order.getSide}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Order">
		</form>
	</body>
</html>
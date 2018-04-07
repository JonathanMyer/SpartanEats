<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<style> 
	body{
	background-color: black;
	}
	</style>
	<head>
		<title>Administration</title>
	</head>
	<body>
		<c:if test="${! empty model.error}">
			<div class="error">${model.error}</div>
		</c:if>
<h1>ADMIN</h1>
	</body>
</html>
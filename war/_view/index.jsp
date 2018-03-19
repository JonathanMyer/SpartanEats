<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/createorder" method="get">
			<input type="Submit" name="get" size="24" value="New Order" />
		</form>
		<form action="${pageContext.servletContext.contextPath}/savedorder" method="get">
			<input type="Submit" name="get" size="24" value="Saved Order" />
		</form> 
		
	</body>
</html>

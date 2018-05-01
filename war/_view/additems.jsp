<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<style>
body{
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f0f0f0;
	height: 100%;
	width: 100%;
	font-size: 40px;
	letter-spacing: 2px;
	color: black;
}
h1{
	text-align: center;
	color: white;
	text-shadow: 4px 4px 8px #377A3A;
	padding-bottom: 15px;
}
.itemName{
font-size: 55px;
width:100%;

}
.itemPrice{
color: red;
}
.bttn{
float: right;
width: 100px;
height: 100px;

}
</style>
<body>
<title>Items</title>
<h1><b>All Items</b></h1>
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
		
<form action="${pageContext.servletContext.contextPath}/additems" method="post">
			
<table>
	<c:forEach items="${itemList}" var="item">                      
      	<tr>
		<td><span class="itemName">${item.itemName}</span></td>
      	<td><span class="itemPrice">${item.price}</span></td>
      	<td><button type="Submit" class="bttn" value="${item.itemName}" name="additem"><img src="https://i.imgur.com/IkAyG21.png"></button></td>

      	</tr>
   	</c:forEach>
</table>

		
</form>
</body>
</html>
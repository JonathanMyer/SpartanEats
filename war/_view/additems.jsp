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
.container {
  display: block;
  position: relative;
  padding-left: 20px;
  margin-bottom: 30px;
  font-size: 55px;
  width: 100%;
  border-bottom: 6px dashed #808080;
  padding-right: 10px;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  }
  
.itemName{
font-size: 55px;
width:100%;
}


.itemPrice{
color: #4CAF50;
}
input{
float: right;
width: 60px;
height: 60px;
color: #696969;
background-color: #696969;
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
		<td><label class="container"><span class="itemName">${item.itemName}: </span><span class="itemPrice">${item.price}</span>
      	<input type="Submit" value="${item.itemName}" name="additem"></input></label></td>
      	</tr>
   	</c:forEach>
</table>
		
</form>
</body>
</html>
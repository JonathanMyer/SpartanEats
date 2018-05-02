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
float: right;
padding-right: 100px;
padding-top: 10px;
font-size: 40px;
}
.addButton{
	position: absolute;
	right: 0;
  	background-color: #f2f2f2;
  	border: none;
  	color: #4CAF50;
  	text-align: center;
  	font-size: 50px;
  	padding: 10 10 10 10;
 	width: 10%;
	height: 60px;	`
  	transition: all 0.5s;
  	cursor: pointer;
	margin-left: 10px;
	margin-bottom: 30px;
	font-weight: 900;
	text-shadow: 4px 4px 8px #377A3A;
}
</style>
<body>
<title>Items</title>
<h1><b>All Items</b></h1>
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
		
<form action="${pageContext.servletContext.contextPath}/additems" method="post">
	<c:forEach items="${itemList}" var="item">                      
		<label class="container"><span class="itemName">${item.itemName}: </span><span class="itemPrice">&#36;${item.price}</span>
      	<button class="addButton"type="Submit" value="${item.itemName}" name="additem">&#10010;</button></label>
   	</c:forEach>
</form>
</body>
</html>
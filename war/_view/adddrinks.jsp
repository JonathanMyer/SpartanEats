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
}
h1{
	text-align: center;
	color: white;
	text-shadow: 4px 4px 8px #377A3A;
	padding-bottom: 15px;
}
/* Customize the label (the Drinks) */
.Drinks {
  display: block;
  position: relative;
	padding-left: 15px;
	margin-bottom: 30px;
	font-size: 55px;
	width: 100%;
	border-bottom: 6px dashed #808080;
	padding-right: 170px;
  cursor: pointer;

  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.Drinks input {
  position: fixed;
	right: 0;
	opacity: 0;
  cursor: pointer;
}

/* Create a custom checkbox */
.checkmark {
	position: absolute;
	right: 0;
	bottom: 20px;
  height: 60px;
  width: 60px;
  background-color: #696969;
}
/* On mouse-over, add a grey background color */
.Drinks:hover input ~ .checkmark {
  background-color: #ccc;
}
/* When the checkbox is checked, add a blue background */
.Drinks input:checked ~ .checkmark {
  background-color: #4CAF50;
}
/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}
/* Show the checkmark when checked */
.Drinks input:checked ~ .checkmark:after {
  display: block;
}
/* Style the checkmark/indicator */
.Drinks .checkmark:after {
  left: 16px;
  width: 17px;
  height: 40px;
  border: solid white;
  border-width: 0 8px 8px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
.Drinks .price{
	position: absolute;
	right: 100px;
	font-size: 40px;
	font-style: italic;
}
.Submit input{
	position: fixed;
	bottom: 0;
	left: 0;
	margin-left: -1px;
	font-size: 60px;
	font-family: "Arial Black", Gadget, sans-serif;
	letter-spacing: 5px;
	background-color: #4CAF50;
	color: white;
	text-shadow: 4px 4px 8px Black;
	width: 100%;
	height: 125px;

}
</style>
<title>Drinks</title>
<body>
<h1><b>Drinks</b></h1>
</div>
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/addDrinks" method="post">
<table>
<c:forEach items="${inventory.extraList}" var="Drinks">          
      	<tr>
      	<td><label class = "Drinks">
					<input type="checkbox" name="addDrinks">${Drinks.itemName}<span class="price">${Drinks.price}</span>
					<span class="checkmark"></span>
					</label></td>
				</tr>
				</c:forEach>
</table>
<div class="Submit">
	<input type="Submit" value = "Submit">
</div>
</form>
</body>
</html>

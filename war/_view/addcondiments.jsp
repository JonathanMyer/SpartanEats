<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<style>

body{
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f2f2f2;
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
/* Customize the label (the container) */
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
</style>
<title>Condiments</title>

<body>
<h1><b>Condiments</b></h1>
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
		
<form action="${pageContext.servletContext.contextPath}/addcondiments" method="post">
			
<table>
	<c:forEach items="${condimentList}" var="condiments">                      
      	<tr>
      	<td><label class="container">
  <input type="checkbox" value="${condiments.condName}" name="addcondiments">${condiments.condName}
  <span class="checkmark"></span>
</label></td>
      	</tr>
   	</c:forEach>
</table>
	<input type="Submit">
</form>
</body>
</html>

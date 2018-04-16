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
	font-size: 26px;
	letter-spacing: 2px;
}
.head{
  text-align: center;
  color: white;
  text-shadow: 2px 2px 8px #377A3A;
  font-size: 40px;
  padding-top: 100px;
}
#Logo {
  position: fixed;
  left: 300px;
  top: 335px;
  width:40%;
}
.foreground {
  position: absolute;
  height: 1720px;
  width: 965px;
  background: radial-gradient(#77F97D 5%, #68DE6D 25%, #4CAF50 70%);
  z-index: -1;
}
h1{
	text-align: center;
	color: white;
	text-shadow: 4px 4px 8px #377A3A;
	padding-bottom: 15px;
}
.Items {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 35px;
	letter-spacing: 6px;
	text-transform: uppercase;
  padding: 10px;
  width: 85%;
	height: 80px;	`
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 85px;
	margin-bottom: 20px;
}

.Items span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.Items span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.Items:hover span {
  padding-right: 25px;
}

.Items:hover span:after {
  opacity: 1;
  right: 0;
}

.Continue {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 80px;
	letter-spacing: 6px;
	text-transform: uppercase;
  padding: 10px;
  width: 45px;
	height: 60px;	`
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 10px;
	margin-bottom: 20px;
}

.h2{
font-size: 100 px;
color: #4CAF50;
}

</style>
<title>Items</title>

<body>
<div class="foreground"></div>
<div class="head">
<h1><b>Extras</b></h1>
</div>	 
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
		
<form action="${pageContext.servletContext.contextPath}/addextras" method="post">
			
<table>
	<c:forEach items="${inventory.extraList}" var="extras">                      
      	<tr>
      	<td class = "Items">${Extras.itemName}     ${Extras.price}<button type = "Submit" value="${Extras.itemName}" name  = "addextras"><h2>Add</h2></button></td>
      	</tr>
   	</c:forEach>
</table>

		
</form>
</body>
</html>

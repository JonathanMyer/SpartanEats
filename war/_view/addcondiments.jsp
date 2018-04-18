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
.condiments {
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

.condiments span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.condiments span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.condiments:hover span {
  padding-right: 25px;
}

.condiments:hover span:after {
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
<title>Condiments</title>

<body>
<div class="foreground"></div>
<div class="head">
<h1><b>Condiments</b></h1>
</div>	
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
		
<form action="${pageContext.servletContext.contextPath}/addcondiments" method="post">
			
<table>
	<c:forEach items="${condimentList}" var="condiments">                      
      	<tr>
      	<td><button type = "Submit" value="${condiments.condName}" name  = "addcondiments">${condiments.condName} add</button></td>
      	</tr>
   	</c:forEach>
</table>

		
</form>
</body>
</html>

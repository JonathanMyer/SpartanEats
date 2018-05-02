
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
  padding-top: 700px;
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
.startOrder {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 50px;
	letter-spacing: 6px;
	text-transform: uppercase;
  padding: 20px;
  width: 85%;
	height: 150px;
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 75px;
	margin-bottom: 50px;
}

.startOrder span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.startOrder span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.startOrder:hover span {
  padding-right: 25px;
}

.startOrder:hover span:after {
  opacity: 1;
  right: 0;
}

.trackOrder {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 40px;
	letter-spacing: 4px;
  padding: 20px;
  width: 70%;
	height: 100px;
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 150px;
	margin-bottom: 50px;
}
.currentOrder {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 40px;
	letter-spacing: 4px;
  padding: 20px;
  width: 70%;
	height: 100px;
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 150px;
	margin-bottom: 50px;
}

.setOrder {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 40px;
	letter-spacing: 4px;
  padding: 20px;
  width: 70%;
	height: 100px;
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 150px;
}


</style>
<title>Spartan Eats</title>
<body>
<div class="foreground"></div>
<div class="head">

	<img src="https://i.imgur.com/edPxEqg.png" title="source: imgur.com" id="Logo" />
  <h1><b>SPARTAN EATS</b></h1>
</div>
<form action="${pageContext.servletContext.contextPath}/currentorders" method="post">
	<c:forEach items ="${orderList}" var="order">                      
      	<button class = "currentOrder" id = "currentOrder" type = "Submit" value="${order.orderId}" name  = "orderId"> View Order: ${order.orderId}</button>	
   	</c:forEach>
</form>
</body>
</html>


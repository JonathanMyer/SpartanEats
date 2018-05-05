<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<style>

body{
	font-family: Arial, Helvetica, sans-serif;
  background: radial-gradient(#77F97D 5%, #68DE6D 25%, #4CAF50 70%);
	height: 100%;
	width: 100%;
	font-size: 26px;
	letter-spacing: 2px;
}
#Logo {
  position: fixed;
  left: 300px;
  top: 300px;
  width:40%;
}
h1{
	text-align: center;
	color: White;
	 text-shadow: 6px 6px 12px #377A3A;
	padding-bottom: 15px;
	padding-top: 700px;
	font-size: 125px;
}

.text {
  padding-bottom: 20px;
  color: White;
  text-align: center;
  font-size: 40px;	


}
.Return {
	position: fixed;
	bottom: 0;
	left: 0;
	font-size: 80px;
	letter-spacing: 5px;
	color: #4FAC50;
	text-shadow: 4px 4px 8px Black;
	width: 100%;
	height: 125px;
	background-color: #f2f2f2;
	letter-spacing: 2px;
	padding-top: 20px;
	padding-bottom: 20px;
	text-transform: uppercase;
	text-shadow: 6px 6px 10px Black;
	box-shadow: 10px 10px 30px 	#505050 inset;
}

</style>
<div class="foreground">
<title>Order</title>

<div class="head">
<img src="https://i.imgur.com/edPxEqg.png" title="Spartan Eats" id="Logo" />
</div>
</div>
<body>
		
<c:if test="${!empty model.error}">
<div class="error">${model.error}</div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/ordercomplete" method="post"></form>
<h1><b>Order Complete!</b></h1>
<div class = "text">Your Order is in Queue!</div>
<br>
<div class = "text">Ready in: ${time} minutes</div>
<br>
<div class = "text">Delivery Destination: ${order.deliveryDest}</div>

<form action="${pageContext.servletContext.contextPath}/index" method="get">
<button class = "Return">Return</button>
</form>
</body>
</html>
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
  padding-top: 50px;
}
#Logo {
  position: fixed;
  left: 300px;
  top: 300px;
  width:40%;
}
.foreground {
  position: absolute;
  height: 1720px;
  width: 965px;
  background: #F5F5F5;
  z-index: -1;
}
h1{
	text-align: center;
	color: DarkGreen;
	padding-bottom: 15px;
	padding-top: 700px;
	font-size: 125px;
}

.text {
  
  padding-bottom: 20px;
  background-color: #f2f2f2;
  color: Black;
  text-align: center;
  font-size: 40px;	


}
.Return {
	position: fixed;
	bottom: 0;
	left: 0;
	background-color: #4CAF50;
	font-size: 80px;
	font-family: "Arial Black", Gadget, sans-serif;
	letter-spacing: 5px;
	color: Black;
	text-shadow: 4px 4px 8px Black;
	width: 100%;
	height: 125px;
}

</style>
<title>Order</title>
<div class="foreground">
<div class="head">
<img src="https://i.imgur.com/edPxEqg.png" title="source: imgur.com" id="Logo" />
</div>
</div>
<body>
		
<c:if test="${! empty model.error}">
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
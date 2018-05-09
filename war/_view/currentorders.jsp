
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
  padding-bottom: 200px;
}
#Logo {
  position: fixed;
  left: 300px;
  top: 250px;
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
	position: absolute;
	text-align: center;
	color: white;
	text-shadow: 4px 4px 8px #377A3A;
	padding-left: 250px;
	
}

.currentOrder {
  display: inline-block;
  border-radius: 80px;
  background-color: DarkGreen;
  border: none;
  color: White;
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
<title>Active Orders</title>
<body>
<div class="foreground"></div>
<div class="head">
  <img src="https://i.imgur.com/edPxEqg.png" title="source: imgur.com" id="Logo" />
  <h1><b>Active Orders</b></h1>
</div>
<br>
<div>
<form action="${pageContext.servletContext.contextPath}/currentorders" method="post">
	<c:forEach items ="${orderList}" var="order">                      
      	<button class = "currentOrder" id = "currentOrder" type = "Submit" value="${order.orderId}" name  = "orderId" style= "vertical-align:middle"> View Order: ${order.orderId}</button>	
   	</c:forEach>
</form>
<form action="${pageContext.servletContext.contextPath}/index" method="get">
<button class = "Return">Return</button>
</form>
</div>
</body>
</html>


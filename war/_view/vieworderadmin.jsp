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
	font-size: 35px;
	letter-spacing: 2px;
}
h1{
	text-align: center;
	color: white;
	font-size: 80px;
	text-shadow: 6px 6px 10px #980000;
	padding-bottom: 15px;
}

.Continue {
 	display: inline-block;
 	background-color: #4CAF50;
	border-radius: 80px;
 	color: white;
 	text-align: center;
	letter-spacing: 6px;
  	padding: 10px;
  	width: 70%;
	height: 100px;
  	transition: all 0.5s;
  	cursor: pointer;
	margin-left: 150px;
	margin-bottom: 20px;
	font-size: 50px;
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
  #condiments{
padding-left:70px;
font-size: 60px;
color: #ff0000;
}
 .Submit button{
	width: 100%;
	height: 200px;
	position: fixed;
	bottom: 0;
	right: 0;
	left: 0;
	margin-left: -1px;
	margin-right: -1px;
	margin-bottom: -5px;
	text-align: center;
	font-size: 60px;
	color: white;
	background-color: #980000;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-shadow: 6px 6px 10px Black;
	box-shadow: 20px 25px 70px #E80000 inset;
}
.accountInfo{
	position: fixed;
	bottom: 250px;
	right: 0
	left: 0;
}
.deliveryInfo{
	position: fixed;
	bottom: 200px;
	right: 0
	left: 0;
}
.orderInformation{
font-size: 50px !important;
}
</style>
<title>Admin View Orders</title>
<h1><b>Admin View Orders</b></h1>
<body>	
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/vieworderadmin" method="post">
<table>
	<c:forEach items="${order.condArray}" var="condarray" varStatus="iter">
	<div>
		<label class="container">
			<span class="itemName">${order.itemList[iter.index].itemName}: </span>
		</label>
	</div>
	<div>
		<c:forEach items= "${condarray}" var = "condiments">
			<div id="condiments">${condiments.condName}</div>
		</c:forEach>
	</div>
<div class="orderInformation">
	</c:forEach>
	<div class="accountInfo">Student Name: ${accounts.firstName} ${accounts.lastName}
	<br>
	Student Phone Number: ${accounts.phoneNumber}</div>
	<c:if test = "${ null != order.deliveryDest}">
		<div class="deliveryInfo">Delivery Destination: "${order.deliveryDest}"</div>
	</c:if>
	<c:if test = "${ null == order.deliveryDest}">
		<div class="deliveryInfo">Delivery Destination: Pickup</div>
	</c:if>
</div>

<div class="Submit">
	<button type="Submit" value="true" name="orderComplete">Complete Order &#10008;</button>
</div>

</table>
	<div class = "Submit">
	<button type="Submit" value="true" name="orderComplete">Order is Completed &#10008;</button>
	</div>
</form>
</body>
</html>
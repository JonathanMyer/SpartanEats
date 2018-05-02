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
.Continue {
  display: inline-block;
  border-radius: 80px;
  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  
	letter-spacing: 6px;
	text-transform: uppercase;
  padding: 10px;
  width: 95%;
	height: 80px;
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 10px;
	margin-bottom: 20px;
	font-size: 50px;
}

.removeButton {
	position: absolute;
	right: 0;

  background-color: #f2f2f2;
  border: none;
  color: #4CAF50;
  text-align: center;
  font-size: 30px;
	letter-spacing: 6px;
	text-transform: uppercase;
  padding: 10px;
  width: 85%;
	height: 60px;	`
  transition: all 0.5s;
  cursor: pointer;
	margin-left: 10px;
	margin-bottom: 20px;
}

.h2{
font-size: 200px;
color: #4CAF50;
}
#condiments{
padding-left:50px;
font-size: 35px;
color: black;
}
@keyframes click-wave {
  0% {
    height: 40px;
    width: 40px;
    opacity: 0.35;
    position: relative;
  }
  100% {
    height: 200px;
    width: 200px;
    margin-left: -80px;
    margin-top: -80px;
    opacity: 0;
  }
}
.option-input {
  -webkit-appearance: none;
  -moz-appearance: none;
  -ms-appearance: none;
  -o-appearance: none;
  appearance: none;
  position: relative;
  top: 20px;
  right: 0;
  bottom: 0;
  left: 0;
  height: 100px;
  width: 100px;
  transition: all 0.15s ease-out 0s;
  background: #ebebe4;
	border-style: solid;
	border-width: 1px;
	border-color: #9faab7;
	box-shadow: 2px 2px 2px grey;
  color: #fff;
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

.option-input:checked::after {
  -webkit-animation: click-wave 0.65s;
  -moz-animation: click-wave 0.65s;
  animation: click-wave 0.65s;
  background: #4CAF50;
  content: '';
  display: block;
  position: relative;
  z-index: 100;
}
#textBox{
	display: inline-block;
	height: 70px;
	width: 500px;
	padding-top: 25px;
	font-size: 50px;
	color: #9faab7;
	border-style: solid;
	border-width: 1px;
	border-color: #9faab7;

}

.h2{
font-size: 200px;
color: #4CAF50;
}
#condiments{
padding-left:70px;
font-size: 60px;
color: #ff0000;
}
#items{
font-size: 80px;
}
</style>
<title>Order</title>
<div class="foreground"></div>
<div class="head">
<h1><b>Order</b></h1>
</div>
<body>
		
<c:if test="${! empty model.error}">
<div class="error">${model.error}</div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/vieworder" method="post">

<button class="Continue" type="Submit" value="true" name="continueOrder" style="vertical-align:middle">Continue Ordering</button>

<div id="items"> Currently Selected Items:</div>
<table>
	<c:forEach items="${order.condArray}" var="condarray" varStatus="iter">
	
	<div id="items">${order.itemList[iter.index].itemName}    ${order.itemList[iter.index].price}</div>
	
	<button class = "Items" type = "Submit" value="${iter.index}"name  = "removeItem">remove</button>
	
		<c:forEach items= "${condarray}" var = "condiments">
			<div id="condiments">+ ${condiments.condName}</div>
		</c:forEach>
	
	</c:forEach>
	<div id="items">Total: ${order.totalPrice}</div>
	
	<button class="Continue" type="Submit" value="true" name="orderComplete" style="vertical-align:middle">Submit Order</button>
</table>   	
</form>
</body>
<script>
window.setOrderName = function() {
	if(document.getElementById("custom").checked) {
		document.getElementById("textBox").disabled = false;
		document.getElementsByName('Text')[0].placeholder='Enter Order Name';
	} else {
		document.getElementById("textBox").disabled = true;
	}
}</script>
</html>
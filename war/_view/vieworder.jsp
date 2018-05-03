<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f2f2f2;
	height: 100%;
	width: 100%;
	font-size: 35px;
	letter-spacing: 2px;
}

h1 {
	text-align: center;
	color: white;
	font-size: 80px;
	text-shadow: 6px 6px 10px #377A3A;
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

.removeButton {
	position: absolute;
	right: 0;
	background-color: #f2f2f2;
	border: none;
	color: red;
	text-align: center;
	font-size: 30px;
	padding: 10 10 10 10;
	width: 10%;
	height: 60px; `
	transition: all 0.5s;
	cursor: pointer;
	margin-left: 10px;
	margin-bottom: 20px;
}

.h2 {
	font-size: 200px;
	color: #4CAF50;
}

#condiments {
	padding-left: 50px;
	font-size: 35px;
	color: black;
}

@
keyframes click-wave { 0% {
	height: 40px;
	width: 40px;
	opacity: 0.35;
	position: relative;
}

100%
{
height:200px;
width:200px;
margin-left:-80px;
margin-top:-80px;
opacity:0;}
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
	margin-right: 0.5rem;
	outline: none;
	position: relative;
	z-index: 1000;
}

.option-input:hover {
	background: #9faab7;
}

.option-input:checked {
	background: #4CAF50;
}

.option-input:checked::before {
	height: 100px;
	width: 100px;
	position: absolute;
	content: '\2714';
	display: inline-block;
	font-size: 100px;
	text-align: center;
	line-height: 100px;
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

#textBox {
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

.Submit button {
	width: 50%;
	height: 150px;
	position: fixed;
	bottom: 0;
	right: 0;
	margin-left: -1px;
	margin-right: -1px;
	margin-bottom: -5px;
	text-align: center;
	font-size: 50px;
	color: white;
	background-color: #4CAF50;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-shadow: 6px 6px 10px Black;
}

.total {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 50%;
	margin-left: -1px;
	margin-right: -1px;
	margin-bottom: -5px;
	font-size: 53px;
	color: black;
	letter-spacing: 2px;
	border-top: 2px solid #9faab7;
	padding-top: 37px;
	padding-bottom: 40px;
	padding-left: 50px;
	padding-right: 50px;
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

.itemPrice {
	float: right;
	padding-right: 100px;
	padding-top: 10px;
	font-size: 40px;
}

.titleText {
	font-weight: 400;
	font-size: 36px;
	color: grey;
	padding: 0px 0px 0px 0px !important;
	margin: 0px 0px -20px 0px !important;
}

.ChooseDeliveryMethod {
	display: inline-block;
	position: relative;
	height: auto;
	padding: 0px 20px 0px 150px;
	margin: 20px 20px 0px 20px;
}

h2 {
	color: #4FAC50;
}

.ChooseDeliveryMethod ul {
	list-style: none;
	margin: 0;
	padding: 0;
	overflow: auto;
}

ul li {
	color: grey;
	float: left;
	display: inline-block !important;
	position: relative;
	padding-right: 60px;
	height: 100px;
}

ul li input[type=radio] {
	position: absolute;
	visibility: hidden;
}

ul li label {
	display: inline-block;
	position: relative;
	font-weight: 300;
	font-size: 48px;
	padding: 25px 25px 25px 80px;
	margin: 10px auto;
	height: 30px;
	z-index: 9;
	cursor: pointer;
	-webkit-transition: all 0.25s linear;
}

ul li:hover label {
	color: #FFFFFF;
}

ul li .check {
	display: inline-block;
	position: absolute;
	border: 5px solid #AAAAAA;
	border-radius: 100%;
	height: 25px;
	width: 25px;
	top: 30px;
	left: 20px;
	z-index: 5;
	transition: border .25s linear;
	-webkit-transition: border .25s linear;
}

ul li:hover .check {
	border: 5px solid #FFFFFF;
}

ul li .check::before {
	display: inline-block;
	position: absolute;
	content: '';
	border-radius: 100%;
	height: 15px;
	width: 15px;
	top: 5px;
	left: 5px;
	margin: auto;
	transition: background 0.25s linear;
	-webkit-transition: background 0.25s linear;
}

input[type=radio]:checked ~ .check {
	border: 5px solid #4CAF50;
}

input[type=radio]:checked ~ .check::before {
	background: #4CAF50;
}

input[type=radio]:checked ~ label {
	color: #4CAF50;
}

.ChooseDeliveryDestination {
	color: red;
}

.firstDestination {
	display: inline-block;
	color: blue;
}

.secondDestination {
	display: inline-block;
	color: blue;
}
</style>
<title>View Order</title>
<h1>
	<b>Finalize Order</b>
</h1>
<body>
	<form action="${pageContext.servletContext.contextPath}/vieworder" method="post">
	<hr> 
			<label>
			<input type="checkbox" class="option-input checkbox"
			name="example" id="custom" value="one-time" onclick="setOrderName()" />
			<input type="text" name="orderName" id="textBox"
			placeholder="Click to Save Order" onfocus="this.placeholder = ''"
			disabled />
		</label>
		<hr style="opacity: 0;">
		<hr style="opacity: 0;">
		<hr>
		<p class="titleText">Select Delivery Method</p>
		<div class="ChooseDeliveryMethod">
			<ul>
				<li><input type="radio" id="f-option" name="deliverypref"
					value="false"> <label for="f-option">Delivery</label>
					<div class="check"></div></li>
				<li><input type="radio" id="s-option" name="deliverypref"
					value="true"> <label for="s-option">Pickup</label>
					<div class="check">
						<div class="inside"></div>
					</div></li>
			</ul>
		</div>
		<hr>
		<div class="ChooseDeliveryDestination">
			<div class="firstDestination">
				<input type="radio" value="Beard Hall">Beard Hall
			</div>
			<div class="secondDestination">
				<input type="radio" value="Codorous Hall">Codorous Hall <br>
			</div>
			<div class="firstDestination">
				<input type="radio" value="Penn Hall">Penn Hall
			</div>
			<div class="secondDestination">
				<input type="radio" value="Susquehana Hall">Susquehana Hall
				<br>
			</div>
			<div class="firstDestination">
				<input type="radio" value="Manor Hall">Manor Complex
			</div>
			<div class="secondDestination">
				<input type="radio" value="Tyler Run Appt">Tyler Run Appt <br>
			</div>
			<div class="firstDestination">
				<input type="radio" value="Northside Commons">Northside
				Commons
			</div>
			<div class="secondDestination">
				<input type="radio" value="Little Run Lodge">Little RunLodge<br>
			</div>
			<div class="firstDestination">
				<input type="radio" value="Richland Hall">Richland Hall
			</div>
			<div class="secondDestination">
				<input type="radio" value="Brockie Commons">Brockie Commons<br>
			</div>
			<div class="firstDestination">
				<input type="radio" value="Spring Garden Appt">Spring Garden Appt
			</div>
			<div class="secondDestination">
				<input type="radio" value="Country Club Manor">Country Club Manor<br>
		
			</div>
			<hr>
		</div>
		<button class="Continue" type="Submit" value="true"
			name="continueOrder" style="vertical-align: middle">&#43;
			Add Items</button>

		<table>
			<c:forEach items="${order.condArray}" var="condarray"
				varStatus="iter">
				<div>
					<label class="container"><span class="itemName">${order.itemList[iter.index].itemName}:
					</span><span class="itemPrice">&#36;${order.itemList[iter.index].price}</span>
						<button class="removeButton" type="Submit" value="${iter.index}"
							name="removeItem" title="Remove Item From Cart">&#215;</button></label>
				</div>
				<c:forEach items="${condarray}" var="condiments">
					<div id="condiments">${condiments.condName}
						<input type="hidden" value="${iter.index}" name="fromItem">
						<button class="removeButton" type="Submit" name="removeCondiment"
							value="${condiments.condID}" title="Remove Condiment From Item">&#215;</button>
					</div>
				</c:forEach>
			</c:forEach>
			<div class="Submit">
				<span class="total"><b>Total:</b> &#36;${order.totalPrice}</span>
				<button type="Submit" value="true" name="orderComplete">Submit
					Order&#187;</button>
			</div>
		</table>
	</form>
<script>
window.setOrderName = function() {
	if (document.getElementById("custom").checked) {
		document.getElementById("textBox").disabled = false;
		document.getElementsByName('orderName')[0].placeholder = 'Enter Order Name';
	} else {
		document.getElementById("textBox").disabled = true;
	}
}
</script>
</body>
</html>
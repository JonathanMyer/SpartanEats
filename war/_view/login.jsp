<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<title>Spartan Eats Sign-in</title>
<style type="text/css">
		.error {
			color: red;
		}
body{
	font-family: Arial, Helvetica, sans-serif;
	background: radial-gradient(#77F97D 5%, #68DE6D 25%, #4CAF50 70%);
	height: 100%;
	width: 100%;
	font-size: 26px;
	letter-spacing: 2px;
}

input[type=text], select {
    width: 100%;
    padding: 22px 30px;
    margin: 18px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-sizing: border-box;
		font-size: 24px;
}
input[type=password], select {
    width: 100%;
    padding: 22px 30px;
    margin: 18px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-sizing: border-box;
		font-size: 24px;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 24px 30px;
		padding-bottom: 30px;
    margin: 18px 0;
    border: none;
    border-radius: 20px;
    cursor: pointer;
		font-size: 30px;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 15px;
    background-color: #f2f2f2;
    padding: 50px;
}
h1{
	text-align: center;
	color: white;
	text-shadow: 2px 2px 8px #377A3A;
	padding-bottom: 25px;
}
.footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
		height: 120px;
		padding-top: 0px;
    background-color: #f2f2f2;;
    text-align: margin-left;
		font-size: 22px;
}
#Logo {
		margin-top: 200px;
    display: block;
    margin-left: auto;
    margin-right: auto;
		width: 30%
}
#Atom{
	position:fixed;
	right:40px;
	bottom: 60px;
	width:10%;
}
#Eclipse{
	position:fixed;
	right:120px;
	bottom: 30px;
	width:15%;
}
#developed{
	position:fixed;
	right:75px;
	bottom: 10px;
}
#Java{
	position:fixed;
	right:540px;
	bottom: 45px;
	width:12%;
}
#HTML{
	position:fixed;
	right:300px;
	bottom: 55px;
	width:25%;
}
#written{
	position:fixed;
	right:400px;
	bottom: 8px;
}
</style>
<body>
	<a href="https://imgur.com/edPxEqg"><img src="https://i.imgur.com/edPxEqg.png" title="source: imgur.com" id="Logo" /></a>
	<h1>SPARTAN EATS</h1>
<div>
	<c:if test="${! empty model.error}">
		<div class="error">${model.error}</div>
	</c:if>
  <form action="${pageContext.servletContext.contextPath}/login" method="post">
    <label id="Name">USERNAME:</label>
    <input type="text" id="Name" name="userName" value="${model.accountName}" placeholder="Your York College Username..">

    <label id="Pass">PASSWORD:</label>
    <input type="password" id="Pass" name="password"value="${model.password}" placeholder="Your Network Password..">

    <input type="Submit" name="submit" value="Login">
  </form>
</div>
<div class="footer">
  	<p>Chase Teichmann</p>
	<p>Samantha Kiser</p>
	<p>Johnathan Myer</p>
	<a href="https://imgur.com/O7SkjrK"><img src="https://i.imgur.com/O7SkjrK.png" title="source: imgur.com" id="Eclipse"/></a>
	<a href="https://imgur.com/NOE2ReT"><img src="https://i.imgur.com/NOE2ReT.png" title="source: imgur.com" id="Atom"/></a>
	<p id="developed"> Developed In</p>
	<a href="https://imgur.com/GQwgUY5"><img src="https://i.imgur.com/GQwgUY5.png" title="source: imgur.com" id="Java"/></a>
	<a href="https://imgur.com/B6KUbi8"><img src="https://i.imgur.com/B6KUbi8.png" title="source: imgur.com" id="HTML"/></a>
	<p id="written">Written In</p>
</div>
</body>
</html>

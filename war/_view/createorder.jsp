<!DOCTYPE html>
<html>
<style>
body{
    background-color: #F5F5F5;
}
input[type="image"]{
  margin-left: 30px;
  padding-top: 30px;
}
</style>
<title>Spartan Eats</title>
<body>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
    <input type="image" class="startOrder"value="Pizza" name="type" src="https://i.imgur.com/gLlYsi8.png" alt="Pizza">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Sandwich" name="type" src="https://i.imgur.com/7AgJ1u1.png" alt="Sandwiches">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Mexican" name="type" src="https://i.imgur.com/uu9i1fP.png" alt="Tacos">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Snack" name="type" src="https://i.imgur.com/cRkeVVo.png" alt="Snacks">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Drink" name="type" src="https://i.imgur.com/3bw5jij.png" alt="Drinks">
</form>

</body>
</html>

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
<title>Choose a Shop</title>
<body>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
    <input type="image" class="startOrder"value="Pizza" name="type" src="https://i.imgur.com/gLlYsi8.png" alt="Pizza">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Sandwiches" name="type" src="https://i.imgur.com/7AgJ1u1.png" alt="Sandwiches">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Tacos" name="type" src="https://i.imgur.com/uu9i1fP.png" alt="Tacos">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Snacks" name="type" src="https://i.imgur.com/cRkeVVo.png" alt="Snacks">
</form>
<form action="${pageContext.servletContext.contextPath}/additems" method="get">
 <input type="image" class="startOrder"value="Drinks" name="type" src="https://i.imgur.com/3bw5jij.png" alt="Drinks">
</form>

</body>
</html>

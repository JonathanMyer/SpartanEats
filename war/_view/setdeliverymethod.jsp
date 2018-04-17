<!DOCTYPE html>
<style>
body{
    background-color: #F5F5F5;
}
input[type="image"]{
  margin-left: 30px;
  padding-top: 30px;
  padding-bottom: 30px;
}
</style>
<html>
<body>
  <form action="${pageContext.servletContext.contextPath}/createorder" method="get">
    <input type="image" src="https://i.imgur.com/bgSYjEw.png" alt="Delivery" name="deliverypref" value="delivery">
    <input type="image" src="https://i.imgur.com/vElZnLF.png" alt="Pickup" name="deliverypref" value="pickup">
   </form>
</body>
</html>

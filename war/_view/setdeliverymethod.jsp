
<!DOCTYPE html>
<html>
<style>
/* Dropdown Button */
.dropbtn {
    background-color: #3498DB;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    width: 500px;
    font-size: 50px;
}

/* Dropdown button on hover & focus */
.dropbtn:hover, .dropbtn:focus {
    background-color: #2980B9;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
    position: relative;
    display: inline-block;
}
/* Dropdown Content (Hidden by Default) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    width: 500px;
    font-size: 50px;
}

/* Links inside the dropdown */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: inline-block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {display:block;}
</style>
<body>

  <div class="dropdown">
    <button onclick="myFunction()" class="dropbtn">Dropdown</button>
    <div id="myDropdown" class="dropdown-content">
      <a href="#">Beard Hall</a>
      <a href="#">Codorus Hall</a>
      <a href="#">Penn Hall</a>
      <a href="#">Susquehana Hall</a>
      <a href="#">Manor Hall</a>
      <a href="#">Tyler Run Appt</a>
      <a href="#">Northside Commons</a>
      <a href="#">Little Run Lodge</a>
      <a href="#">Richland Hall</a>
      <a href="#">Brockie Commons</a>
      <a href="#">Spring Garden Appt</a>
      <a href="#">Country Club Manor</a>
    </div>
  </div>
<script>
/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>

  <form action="${pageContext.servletContext.contextPath}/createorder" method="get">
    <input type="image" src="https://i.imgur.com/bgSYjEw.png" alt="Delivery" name="deliverypref" value="true">
    <input type="image" src="https://i.imgur.com/vElZnLF.png" alt="Pickup" name="deliverypref" value="false">
   </form>

</body>
</html>

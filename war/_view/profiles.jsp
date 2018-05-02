<!DOCTYPE html>
<html>
  <style>
  .body{
    background-color: #f0f0f0;
    font-family: Arial, Helvetica, sans-serif;
  }
  .headshot{
    display: block;
    padding-left: 60px;
    width: 90%;
    height: 90%;
  }
  .name{
    text-align: center;
    font-size: 90px;
    color: black;
    text-shadow: 0 0 10px #7c7575, 0 0 10px #7c7575;
    padding: 0 0 0 0;
  }
  .bio{
    text-align: center;
    font-size: 34px;
    font-style: italic;
    color: black;
    padding: 0 0 0 0;
  }
  .Contributions{
    font-size: 30px;
    color: black;
  }
  .revealContributionsButton{
    font-size:40px;
    font-style: bold;
    height:100px;
    width: 400px;
  }
.previous {
    text-decoration: none;
    display: inline-block;
    padding: 20px 20px 20px 20px;
    color: white;
    background-color: #000!important;
    font-size: 40px;
}
li{
font-size: 35px  !important;
}
  </style>
<body class="body" onLoad="myContributionsFunction">
  <a href="http://localhost:8081/spartaneats/login" class="previous"> &#171; Go Back</a>
  <div class="Jonathan Myer"><a Name="John"></a>
    <img src="https://i.imgur.com/TMQL5kt.png" title="Jonathan Myer" class="headshot"/></a>
      <p class="name">Jonathan Myer</p>
      <p class="bio"><i>Github Guru, Looking to do an Internship at Becton Dickinson this Summer</i></p>
      <span rel="HiddenContributionsJohn">Show Contributions</span><br />
      <div id="HiddenContributionsJohn" style="display: none;">
        <ul>
          <li>Initial Project Setup</li>
          <li>Resolving Github Issues</li>
          <li>Derby Database Creation</li>
          <li>Integrated Databases into Servlets</li>
          <li>HTML functions on JSP pages</li>
        </ul>
      </div>
    </div>
  <div class="Chase Teichmann"><a Name="Chase"></a>
    <img src="https://i.imgur.com/sC1osnw.png" title="Chase Teichmann" class="headshot"/></a>
      <p class="name">Chase Teichmann</p>
      <p class="bio"><i>Central Jersey Native, Looking to Work in Front-End Development or Research and Development for mobile apps</i></p>
      <span rel="HiddenContributionsChase">Show Contributions</span><br />
      <div id="HiddenContributionsChase" style="display: none;">
        <ul>
          <li>HTML/CSS/Javascript for JSP pages</li>
          <li>Index, ViewOrder, Admin Servlets</li>
          <li>Design and Graphics</li>
          <li>Derby Database Files</li>
          <li>JUnit Testing</li>
        </ul>
      </div>
  </div>
  <div class="Sam Kiser"><a Name="Sam"></a>
    <img src="https://i.imgur.com/4QqJupu.png" title="Sam Kiser" class="headshot"/></a>
      <p class="name">Sam Kiser</p>
      <p class="bio"><i>York Softball Pitcher, Wants to have a career in Software Engineering and Cyber Security</i></p>
      <span rel="HiddenContributionsSam">Show Contributions</span><br />
      <div id="HiddenContributionsSam" style="display: none;">
        <ul>
          <li>Reimplemented Database Tables for Items and Condiments</li>
          <li>Model Creation and Refactoring</li>
          <li>JUnit Testing</li>
          <li>Servlet Development</li>
        </ul>
      </div>
    </div>
</div>
<script>
window.onload = function() {
    var elements = document.getElementsByTagName("span");
    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var id = element.getAttribute("rel") || "";
        if (id.length > 0) {
            element.onclick = function() {
                var oToShow = document.getElementById(this.getAttribute("rel"));
                if (oToShow){
                    oToShow.style.display = "block";
                  }
            };
        }
    }
};
</script>
</body>
</html>

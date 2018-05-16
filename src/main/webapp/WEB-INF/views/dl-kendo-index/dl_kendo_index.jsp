<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../favicon.ico">

    <title>大连剑道同好会</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dist/css/navbar-fixed-top.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#home" data-toggle="tab">首页</a></li>
            <li><a href="#about" data-toggle="tab">关于我们</a></li>
            <li><a href="#kendo-news" data-toggle="tab">剑道NEWS</a></li>
            <li><a href="#member-show" data-toggle="tab">会员风采</a></li>
            <li><a href="#schedule" data-toggle="tab">课程安排</a></li>
            <li><a href="#equipment-show" data-toggle="tab">装备展示</a></li>
            <li><a href="#bbs-link" data-toggle="tab">论坛链接</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">

      <div class="tab-content">
        <!-- Main component for a primary marketing message or call to action -->
        <div id="home" class="tab-pane active">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">剑道新闻</h3>
            </div>
            <div class="panel-body">
              <div class="list-group">
                  <c:forEach items="${users}" var="item">
                      <a href="#" class="list-group-item">${item.title}</a>
                  </c:forEach>
              </div>
            </div>
          </div>
        </div>

        <!-- Main component for a primary marketing message or call to action -->
        <div id="about" class="tab-pane">
          <h1>添加商品信息</h1>
          <form id="p_add_form">
            <div class="form-group">
              <label for="product_name">商品名称</label>
              <input type="text" class="form-control" id="product_name" name="product_name">
            </div>
            <div class="form-group">
              <label for="product_code">商品条码</label>
              <input type="text" class="form-control" id="product_code" name="product_code">
            </div>
            <div class="form-group">
              <label for="product_image_url">商品图片Url地址</label>
              <input type="text" class="form-control" id="product_image_url" name="product_comment">
            </div>
            <div class="form-group">
              <label for="product_price">商品价格</label>
              <input type="text" class="form-control" id="product_price" name="product_price">
            </div>
            <input type="button" class="btn btn-default" id="p_add_btn" value="Submit"/>
          </form>
        </div>

        <!-- Main component for a primary marketing message or call to action -->
        <div id="contact" class="tab-pane">
          <h1>contact</h1>
          <p>This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
          <p>To see the difference between static and fixed top navbars, just scroll.</p>
          <p>
            <a class="btn btn-lg btn-primary" href="../../../components/#navbar" role="button">View navbar docs &raquo;</a>
          </p>
        </div>
      </div>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="dist/js/jquery-3.3.1.min.js"></script>
    <script>window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="dist/js/common.js"></script>

    <script>
    $(function() {
      $('.nav > li > a').click(function(){
        console.log("hello world!");
        $('#collapse').addClass('collapsed');
        $('#collapse').attr('aria-expanded', false);
        $('#navbar').removeClass('in');
        $('#navbar').attr('aria-expanded', false);
      });
    });

    $(function() {
        //post传参，方式三
        $("#p_add_btn").click(function(){
            var data=JSON.stringify($("#p_add_form").serializeObject());
            //data可以有三种形式：
            //1.var data={"id":"111","user_name":"abc","user_email":"aaa@sina.com"};
            //2.vat data="id=111&user_name=abc&user_email=aaa@sina.com";
            //3.var data=$("#form1").serialize();
            alert(data);
            $.ajax({
                url:'<%=request.getContextPath()%>/update',
                type: 'POST',
                data: data,
                contentType:'application/json;charset=utf-8',
                //返回List或Map，dataType要设置为“json”.
                dataType:'json',
                success:function(data){
                    alert('success');
                    $(data).each(function (i, value) {
                        alert(value);
                    });
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            })
        });
    });

    </script>

  </body>
</html>

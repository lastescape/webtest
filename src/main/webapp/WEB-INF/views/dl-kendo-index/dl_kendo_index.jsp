<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
              <div class="list-group" id="news-list">
              </div>
            </div>
          </div>
          <div id="example" align="right">
            <ul id="pagination"></ul>
          </div>
        </div>

        <!-- Main component for a primary marketing message or call to action -->
        <div id="about" class="tab-pane">
          <h1>添加商品信息</h1>
          <form id="p_add_form">
            <div class="form-group">
              <label for="product_name">商品分类</label>

              <div class="dropdown">
                <input type="text" class="form-control" data-toggle="dropdown" id="product_div" name="product_div" readonly>
                <ul class="dropdown-menu" aria-labelledby="product_div" id="product_div_dropbox" >
                </ul>
              </div>
            </div>
            <div class="form-group">
              <label for="product_name">商品名称</label>
              <input type="text" class="form-control" id="product_name" name="product_name">
            </div>
            <div class="form-group">
              <label for="product_code">商品条码</label>
              <input type="text" class="form-control" id="product_code" name="product_code">
            </div>
            <div class="form-group">
              <label for="product_image_file">上传商品图片</label>
              <input type="file" class="form-control" id="product_image_file" name="product_comment">
            </div>
            <%--<div class="form-group">--%>
              <%--<label for="product_image_url">商品图片Url地址</label>--%>
              <%--<input type="text" class="form-control" id="product_image_url" name="product_comment">--%>
            <%--</div>--%>
            <div class="form-group">
              <label for="product_price">商品价格</label>
              <input type="text" class="form-control" id="product_price" name="product_price">
            </div>
            <input type="button" class="btn btn-default" id="p_add_btn" value="Submit"/>
          </form>
        </div>

        <!-- Main component for a primary marketing message or call to action -->
        <div id="bbs-link" class="tab-pane">
          <h1>添加新闻信息</h1>
          <form id="news_add_form">
            <div class="form-group">
              <label for="news_title">新闻名称</label>
              <input type="text" class="form-control" id="news_title" name="newsTitle">
            </div>
            <div class="form-group">
              <label for="news_detail">新闻内容</label>
              <textarea class="form-control" rows="5" id="news_detail" name="newsDetail"></textarea>
            </div>
            <input type="button" class="btn btn-default" id="news_add_btn" value="保存"/>
          </form>
        </div>



        <!-- Main component for a primary marketing message or call to action -->
        <div id="equipment-show" class="tab-pane">
          <h1>查看新闻信息</h1>
          <form id="news_find_form">
            <div class="form-group">
              <label for="input_news_id">新闻ID</label>
              <input type="text" class="form-control" id="input_news_id" name="newsId">
            </div>
            <input type="button" class="btn btn-default" id="news_find_btn" value="查找"/>
          </form>
          <div class="form-group">
            <label for="news_title_readonly">新闻名称</label>
            <input class="form-control" id="news_title_readonly" disabled>
          </div>
          <div class="form-group">
            <label for="news_detail_readonly">新闻内容</label>
            <textarea class="form-control" rows="20" readonly="true" id="news_detail_readonly" disabled></textarea>
          </div>
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
    <script src="dist/js/bootstrap-paginator.js"></script>



    <script>
    //首页加载时运行
    var totalCount;
    $(function() {
        $("document").ready(function () {

            $.ajax({
                url:'<%=request.getContextPath()%>/news/pages/1',
                type:'GET',
                data:{},
                dataType:'JSON',
                success:function (callback) {
                    $('#news-list').empty();
                    $(callback).each(function (i, value) {
                        $('#news-list').append('<a href="<%=request.getContextPath()%>/detail/' + value.id + '" class="list-group-item">' + value.newsTitle + '</a>');
                    });
                },
                error:function(xhr) {
                    alert('error:' + JSON.stringify(xhr));
                }
            });

            $.ajax({
                url:'<%=request.getContextPath()%>/news/pagecount',
                type:'GET',
                data:{},
                dataType:'TEXT',
                success:function (callback) {
                    totalCount = Number(callback);

                    var options = {
                        currentPage: 1,//当前的请求页面。
                        totalPages: totalCount ,//一共多少页。
                        size:"normal",//应该是页眉的大小。
                        bootstrapMajorVersion: 3,//bootstrap的版本要求。
                        alignment:"right",
                        numberOfPages:5,//一页列出多少数据。
                        itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
                            switch (type) {
                                case "first": return "首页";
                                case "prev": return "上一页";
                                case "next": return "下一页";
                                case "last": return "末页";
                                case "page": return page;
                            }
                        },
                        onPageClicked: function (event, originalEvent, type, page){//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
                            $.ajax({
                                url:'<%=request.getContextPath()%>/news/pages/' + page,
                                type:'GET',
                                data:{},
                                dataType:'JSON',
                                success:function (callback) {
                                    $('#news-list').empty();
                                    $(callback).each(function (i, value) {
                                        $('#news-list').append('<a href="<%=request.getContextPath()%>/detail/' + value.id + '" class="list-group-item">' + value.newsTitle + '</a>');
                                    });
                                },
                                error:function(xhr) {
                                    alert('error:' + JSON.stringify(xhr));
                                }
                            });
                        }
                    };

                    $('#pagination').bootstrapPaginator(options);

                },
                error:function(xhr) {
                    alert('error:' + JSON.stringify(xhr));
                }
            });

            $.ajax({
                url:'<%=request.getContextPath()%>/productDiv',
                type:'GET',
                data:{},
                contentType:'application/json;charset=utf-8',
                dataType:'JSON',
                success:function (callback) {
                    $('#product_div_dropbox').empty();
                    $(callback).each(function (i, value) {
                        $('#product_div_dropbox').
                        append('<li><a href="#">' + value.product_div_name + '</a></li>');
                    });
                    $("#product_div_dropbox > li > a").on('click', function(){
                        $("#product_div").val($(this).text());
                    });

                },
                error:function(xhr) {
                    alert('error:' + JSON.stringify(xhr));
                }
            });


        });
    });


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
        //商品信息保存ajax
        $("#p_add_btn").click(function(){

            //上传图片方法
            var formData = new FormData($("#p_add_form")[0]);
            $.ajax({
                url: '<%=request.getContextPath()%>/update' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert("保存成功");
                    $(':input','#p_add_form')
                        .not(':button,:submit,:reset,:hidden')
                        .val('')  //将input元素的value设为空值
                        .removeAttr('checked')
                        .removeAttr('checked')
                },
                error: function (xhr) {
                    alert('error:' + JSON.stringify(xhr));
                }
            });
        });


    });

    $(function() {
        //新闻保存ajax
        $("#news_add_btn").click(function(){
            var data=JSON.stringify($("#news_add_form").serializeObject());
            //data可以有三种形式：
            //1.var data={"id":"111","user_name":"abc","user_email":"aaa@sina.com"};
            //2.vat data="id=111&user_name=abc&user_email=aaa@sina.com";
            //3.var data=$("#form1").serialize();
            $.ajax({
                url:'<%=request.getContextPath()%>/news/update',
                type: 'POST',
                data: data,
                contentType:'application/json;charset=utf-8',
                //返回List或Map，dataType要设置为“json”.
                dataType:'json',
                success:function(){
                    alert('保存成功');
                    $(':input','#news_add_form').not(':button, :submit, :reset, :hidden')
                        .val('')
                        .removeAttr('checked')
                        .removeAttr('selected');
                },
                error : function() {
                    alert('保存失败');
                }
            })
        });
    });

    $(function() {
        //新闻查询ajax
        $("#news_find_btn").click(function(){
            var data=$("#news_find_form").serialize();
            $.ajax({
                url:'<%=request.getContextPath()%>/news/select',
                type: 'GET',
                data: data,
                contentType:'application/json;charset=utf-8',
                //返回List或Map，dataType要设置为“json”.
                dataType:'json',
                success:function(output){
                    var newsTitle = output.newsTitle;
                    var newsDetail = output.newsDetail;
                    $('#news_title_readonly').val(newsTitle);
                    $('#news_detail_readonly').val(newsDetail);
                    alert('查询成功');
                },
                error : function(xhr) {
                    alert('error:' + JSON.stringify(xhr));
                }
            })
        });
    });

    </script>

  </body>
</html>

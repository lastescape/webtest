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

        <title>新闻详情</title>

        <!-- Bootstrap core CSS -->
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../dist/css/blog.css" rel="stylesheet">
        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="../assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">

            <div class="row">

                <div class="col-sm-12 blog-main">

                    <div class="blog-post">
                        <h2 id="news-title" class="blog-post-title"></h2>
                        <p id="news-add-date" class="blog-post-meta"></p>
                        <p id="news-contents"></p>
                    </div><!-- /.blog-post -->

                </div><!-- /.blog-main -->

            </div><!-- /.row -->

        </div><!-- /.container -->

        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../dist/js/jquery-3.3.1.min.js"></script>
        <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="../dist/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
        <script src="../dist/js/common.js"></script>

        <script>
            $(function () {
               $('document').ready(function () {
                   $.ajax({
                       url:'<%=request.getContextPath()%>/news/detailcontent/${newsDetailId}',
                       type:'GET',
                       data:{},
                       dataType:'JSON',
                       success:function (callback) {
                           $('#news-title').empty();
                           $('#news-add-date').empty();
                           $('#news-contents').empty();
                           $('#news-title').html(callback.newsTitle);
                           $('#news-add-date').html(callback.newsAddTime);
                           $('#news-contents').html(callback.newsDetail.replace(/\r\n/g, '</p><p>'));
                       },
                       error:function(xhr) {
                           alert('error:' + JSON.stringify(xhr));
                       }
                   });
               })
            });
        </script>
    </body>
</html>

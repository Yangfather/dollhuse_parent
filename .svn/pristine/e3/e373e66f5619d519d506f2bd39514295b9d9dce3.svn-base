<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html>
<html>

<head>
	<base href="<%=basePath%>">
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <style type="text/css">
    body {
        padding: 0;
        margin: 0;
        font-family: "microsoft yahei";
        width: 100%;
        height: 100%;
        background: #b5e1fa;
    }
    
    a {
        text-decoration: none;
    }
    
    .error404 {
        height: 775px;
        position: relative;
        overflow: hidden;
    }
    
    .error404 .erbox {
        width: 400px;
        height: 290px;
        position: absolute;
        top: 50%;
        left: 50%;
        margin: -180px 0 0 -199px;
        text-align: center;
    }
    
    .error404 .erbox .cont {
        width: 400px;
        height: 290px;
        position: relative;
        z-index: 100;
        background: url(assets/images/error/box-bg.png) no-repeat;
    }
    /* line 21, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .erbox h3 {
        padding: 0;
        margin: 0;
        padding-top: 90px;
        padding-bottom: 5px;
        font-size: 24px;
        color: #666;
        font-weight: 500;
    }
    /* line 30, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .erbox h4 {
        font-size: 18px;
        color: #666;
        font-weight: 500;
        padding: 0;
        margin: 0;
    }
    /* line 37, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .erbox .back {
        padding: 20px 75px 0 0;
        text-align: right;
        font-size: 14px;
    }
    /* line 41, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .erbox .back a {
        color: #0697da;
    }
    /* line 43, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .erbox .back a:hover {
        text-decoration: underline;
    }
    /* line 49, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .rocket {
        width: 122px;
        height: 111px;
        position: absolute;
        z-index: 200;
        top: -2%;
        right: -12%;
    }
    /* line 57, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun {
        width: 105px;
        position: absolute;
        opacity: 0;
    }
    /* line 61, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun.yun1 {
        top: -24%;
        left: 150%;
        -moz-animation: yun, 50s, linear, 0s, infinite;
        -webkit-animation: yun, 50s, linear, 0s, infinite;
        animation: yun, 50s, linear, 0s, infinite;
    }
    /* line 66, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun.yun2 {
        width: 70px;
        top: 65%;
        left: 135%;
        -moz-animation: yun, 35s, linear, 4s, infinite;
        -webkit-animation: yun, 35s, linear, 4s, infinite;
        animation: yun, 35s, linear, 4s, infinite;
    }
    /* line 72, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun.yun3 {
        width: 100px;
        top: 105%;
        left: -35%;
        -moz-animation: yun, 70s, linear, 6s, infinite;
        -webkit-animation: yun, 70s, linear, 6s, infinite;
        animation: yun, 70s, linear, 6s, infinite;
    }
    /* line 78, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun.yun4 {
        width: 70px;
        top: 30%;
        left: -38%;
        -moz-animation: yun, 50s, linear, 1s, infinite;
        -webkit-animation: yun, 50s, linear, 1s, infinite;
        animation: yun, 50s, linear, 1s, infinite;
    }
    /* line 84, ../../sass/style/common/mod/_notFound.scss */
    
    .error404 .yun.yun5 {
        top: 130%;
        left: 250%;
        -moz-animation: yun, 60s, linear, 4s, infinite;
        -webkit-animation: yun, 60s, linear, 4s, infinite;
        animation: yun, 60s, linear, 4s, infinite;
    }
    
    @-webkit-keyframes yun {
        0% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(100px, 0, 0);
            -ms-transform: translate3d(100px, 0, 0);
            -webkit-transform: translate3d(100px, 0, 0);
            transform: translate3d(100px, 0, 0);
        }
        3% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
        }
        95% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
            -moz-transform: translate3d(-950px, 0, 0);
            -ms-transform: translate3d(-950px, 0, 0);
            -webkit-transform: translate3d(-950px, 0, 0);
            transform: translate3d(-950px, 0, 0);
        }
        100% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(-1000px, 0, 0);
            -ms-transform: translate3d(-1000px, 0, 0);
            -webkit-transform: translate3d(-1000px, 0, 0);
            transform: translate3d(-1000px, 0, 0);
        }
    }
    
    @-moz-keyframes yun {
        0% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(100px, 0, 0);
            -ms-transform: translate3d(100px, 0, 0);
            -webkit-transform: translate3d(100px, 0, 0);
            transform: translate3d(100px, 0, 0);
        }
        3% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
        }
        95% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
            -moz-transform: translate3d(-950px, 0, 0);
            -ms-transform: translate3d(-950px, 0, 0);
            -webkit-transform: translate3d(-950px, 0, 0);
            transform: translate3d(-950px, 0, 0);
        }
        100% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(-1000px, 0, 0);
            -ms-transform: translate3d(-1000px, 0, 0);
            -webkit-transform: translate3d(-1000px, 0, 0);
            transform: translate3d(-1000px, 0, 0);
        }
    }
    
    @-ms-keyframes yun {
        0% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(100px, 0, 0);
            -ms-transform: translate3d(100px, 0, 0);
            -webkit-transform: translate3d(100px, 0, 0);
            transform: translate3d(100px, 0, 0);
        }
        3% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
        }
        95% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
            -moz-transform: translate3d(-950px, 0, 0);
            -ms-transform: translate3d(-950px, 0, 0);
            -webkit-transform: translate3d(-950px, 0, 0);
            transform: translate3d(-950px, 0, 0);
        }
        100% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(-1000px, 0, 0);
            -ms-transform: translate3d(-1000px, 0, 0);
            -webkit-transform: translate3d(-1000px, 0, 0);
            transform: translate3d(-1000px, 0, 0);
        }
    }
    
    @-o-keyframes yun {
        0% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(100px, 0, 0);
            -ms-transform: translate3d(100px, 0, 0);
            -webkit-transform: translate3d(100px, 0, 0);
            transform: translate3d(100px, 0, 0);
        }
        3% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
        }
        95% {
            filter: progid: DXImageTransform.Microsoft.Alpha(enabled=false);
            opacity: 1;
            -moz-transform: translate3d(-950px, 0, 0);
            -ms-transform: translate3d(-950px, 0, 0);
            -webkit-transform: translate3d(-950px, 0, 0);
            transform: translate3d(-950px, 0, 0);
        }
        100% {
            filter: progid: DXImageTransform.Microsoft.Alpha(Opacity=0);
            opacity: 0;
            -moz-transform: translate3d(-1000px, 0, 0);
            -ms-transform: translate3d(-1000px, 0, 0);
            -webkit-transform: translate3d(-1000px, 0, 0);
            transform: translate3d(-1000px, 0, 0);
        }
    }
    </style>
</head>

<body>
    <div class="error404" id="e404">
        <div class="erbox">
            <div class="cont">
                <h3>500错误</h3>
                <div class="back">
                    <p></p><a href="" class="link-back">进入我的网站>></a></div>
            </div>
            <img class="rocket" src="assets/images/error/rocket.gif" />
        </div>
    </div>
</body>

</html>

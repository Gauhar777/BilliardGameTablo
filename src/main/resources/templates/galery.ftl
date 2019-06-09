<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <title>Галерея</title>

    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <!-- Twitter Card data -->
    <meta name="twitter:site" content="">
    <meta name="twitter:title" content="Вторник клуб">
    <meta name="twitter:description" content="">
    <meta name="twitter:image" content="">

    <!-- Open Graph data -->
    <meta property="og:title" content="Вторник клуб">
    <meta property="og:type" content="article">
    <meta property="og:url" content="">
    <meta property="og:image" content="">
    <meta property="og:description" content="">
    <meta property="og:site_name" content="">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="/css/vtornik.css">
    <link rel="stylesheet" href="/css/vtornik2.css">
    <link rel="stylesheet" href="/css/vtornikMedia.css">
    <link rel="stylesheet" href="/css/magnific-popup.css">
</head>
<body>

<div class="wrp">
<main class="main">
        <div class="container">

            <a href="/logout" class="alert alert-light w-100 d-block text-center">${model["resource"].getString("sign out")}</a>

            <div class="main__title">${model["resource"].getString("galery")}</div>

            <div class="zoom-gallery">
                <#list model["photoList"] as photo>
                        <a href="/${photo.id}/img" class="item">
                            <img src="/${photo.id}/img">
                        </a>
                </#list>
            </div>
        </div>
</main>
</div>
<script  src="/js/jquery.js" crossorigin="anonymous"></script>
<script src="/js/jquery.magnific-popup.min.js"></script>
<script src="/js/galery.js"></script>
</body>
</html>

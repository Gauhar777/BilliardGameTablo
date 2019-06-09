<!doctype html>
<html lang="ru">
    <head>
        <title>Пользователи</title>
        <meta charset="UTF-8">

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
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="/css/vtornik2.css">
    </head>
    <body>
        <div class="wrp">
            <div class="logo">
                <img src="img/logo-vtornik.png" alt="">
            </div>
            <main class="main">
                <div class="login">
                    <div class="container">
                        <a href="/chooseList" class="btn btn-dark w-100 mb-4">${model["resource"].getString("user")}</a>
                        <a href="/loginpage" class="btn btn-light w-100">${model["resource"].getString("admin")}</a>
                    </div>
                </div>
            </main>
        </div>

         <script src="/js/vtornik2.js"></script>
    </body>
</html>
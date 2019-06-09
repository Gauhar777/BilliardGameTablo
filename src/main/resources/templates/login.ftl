<!doctype html>
<html lang="ru">
<head>
    <title>Главная</title>
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
        <div class="main__title">Админ</div>
        <div class="login">
            <div class="container">
                <form id="loginform" class="new-tournament" method="POST">
                    <div class="form-group">
                        <input type="text"  name="username"  id="username" placeholder="Имя"  class="form-control" required autofocus/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" placeholder="Пароль" class="form-control" required/>
                    </div>
                    <input type="submit" class="btn btn-dark w-100" value="${model["resource"].getString("signin")}"/>
                </form>
            </div>
        </div>
    </main>
</div>

<script src="/js/vtornik2.js"></script>
</body>
</html>

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
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/vtornik.css">
</head>
<body>
<div class="wrp">
    <main class="main">
        <div class="photo">
            <div class="photo__header">
                <a href="#" class="photo__icon"><img src="/img/camera.png" alt=""></a>

                <h1>${model.competition.name}</h1>

                <div class="photo__control">
                    <a href="#"><img src="/img/edit.png" alt=""></a>
                    <a href="#"><img src="/img/close.png" alt=""></a>
                </div>

            </div>
            <div class="photo__main">

                <img src="/up/${model.photo.name}" alt="">

            </div>
            <div class="photo__footer">
                <a href="/Competition/${model.competition.id}/showGames" class="photo__btn ok">ОК</a>
                <a href="#" class="photo__btn" data-toggle="modal" data-target="#photo">Другое фото</a>
            </div>
        </div>
    </main>
</div>



<!-- Добавить фото -->
<div class="modal photo fade" id="photo" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form class="rating-all__form" method="post" enctype="multipart/form-data" action="/${model.competition.id}/uploading">
                    <div class="rating-all__button" name="file">
                        <label for="file"  name="file" class="img">
                            <img src="/img/gallery.png" alt="">
                        </label>
                        <label for="file" name="file">Загрузить из галереи</label>

                        <input type="file" name="file" onchange="this.form.submit()" id="file" style="display:none;"/>
                    </div>
                    <button type="button" class="rating-all__button">
                        <div class="img"><img src="/img/photo-black.png" alt=""></div>
                        Сделать фото
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="/js/vtornik.js"></script>
</body>
</html>
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/myStyle.css">
        <link rel="stylesheet" href="/css/mediaStyle.css">
        <link rel="stylesheet" href="/css/navBarStyle.css">
        <script src="/javascript/jquery-3.3.1.js"></script>
        <script src="/javascript/myJS.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-dark navbar-expand">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link"  href="/main2">
                        ${model["resource"].getString("Competition")}
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link"  href="#">
                        ${model["resource"].getString("Sign in")}
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </nav>
        <div class="container">
            <h1>${model["resource"].getString("Sign in")}</h1>
            <form id="loginform" method="POST">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label" for="username">${model["resource"].getString("User name")}</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="username" id="username"  value="" required autofocus/>
                        </div>
                </div>
                <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="password">${model["resource"].getString("Password")}</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="password" name="password" id="password" required/>
                            </div>
                </div>
                <button type="submit" class="btn btn-primary btn-lg" >${model["resource"].getString("signin")}</button>
            </form>
        </div>
            <div class="b-popup" id="popup1">
                <div class="b-popup-content">
                    Error!
                </div>
            </div>
    </body>
</html>
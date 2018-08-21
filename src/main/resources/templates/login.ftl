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
</head>
<body>
    <div class="container">
        <h1>Log in</h1>
        <form method="POST">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="username">Username</label>
                    <div class="col-sm-10">
                        <input class="form-control" name="username" id="username" required autofocus/>
                    </div>
            </div>
            <div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label" for="password">Password</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="password" name="password" id="password" required/>
                        </div>
                </div>
            <button type="submit" class="btn btn-primary btn-lg" >Sign in</button>
        </form>
    </div>
</body>
</html>
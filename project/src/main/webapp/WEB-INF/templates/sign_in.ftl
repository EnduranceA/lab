<!doctype html>
<#import "spring.ftl" as spring />
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    <link rel="stylesheet"  href="<@spring.url '/css/signIn.css'/>">
    <title>Sign In</title>
</head>
<body class="text-center">
<form action="/signIn" method="post" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal"><@spring.message 'signIn'/></h1>
    <label for="inputEmail" class="sr-only"><@spring.message 'form.email'/></label>
    <input type="email" name="email" id="inputEmail" class="form-control" placeholder="<@spring.message 'form.email'/>" required autofocus>
    <label for="inputPassword" class="sr-only"><@spring.message 'form.password'/></label>
    <input type="password" name="password"  id="inputPassword" class="form-control" placeholder="<@spring.message 'form.password'/>" required>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="remember-me"><@spring.message 'form.signIn.remember.me'/>
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><@spring.message 'signIn.button'/></button>
</form>

</body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>


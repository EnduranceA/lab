<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="<@spring.url '/css/signIn.css'/>">
    <title>Sign Up</title>
</head>
<style>
    .error {
        color: #ff0000;
    }
</style>
<body class="text-center">
<@spring.bind "signUpDto"/>
<form action="/signUp" method="post" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal"><@spring.message 'signUp.page'/></h1>
    <div class="form-group">
        <label for="exampleFirstName"><@spring.message 'form.first.name'/>:</label>
        <@spring.formInput "signUpDto.firstName"/>
        <@spring.showErrors "<br>", "error"/>
    </div>
    <div class="form-group">
        <label for="exampleLastName"><@spring.message 'form.last.name'/>:</label>
        <@spring.formInput "signUpDto.lastName"/>
        <@spring.showErrors "<br>", "error"/>
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1"><@spring.message 'form.email'/>:</label>
        <@spring.formInput "signUpDto.email"/>
        <li><@spring.showErrors "<br>","error"/></li>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1"><@spring.message 'form.password'/>:</label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1">
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="role" id="exampleRadios1" value="SINGER" checked>
        <label class="form-check-label" for="exampleRadios1">
            <@spring.message 'form.role.singer'/>
        </label>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="role" id="exampleRadios2" value="USER">
        <label class="form-check-label" for="exampleRadios2">
            <@spring.message 'form.role.listener'/>
        </label>
    </div>
    <div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" class="btn btn-primary" value="<@spring.message 'signUp'/>">
    </div>
</form>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>

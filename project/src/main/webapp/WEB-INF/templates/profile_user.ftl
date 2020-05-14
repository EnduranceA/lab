<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="<@spring.url '/css/style.css'/>">
    <title>Profile</title>
</head>
<body>
<div class="container-fluid" id="content">
    <div class="row">
        <div class="col-md-2">
            <div class="card">
                <img src="<@spring.url '/images/music1.jpg'/>" class="card-img-top" >
            </div>
            <div class="container-xl btn-group-vertical">
                <a href="/music" class="btn btn-primary"><@spring.message 'navbar.main'/></a>
                <a href="#" class="btn btn-primary"><@spring.message 'navbar.overview'/></a>
                <a href="#" class="btn btn-primary"><@spring.message 'navbar.radio'/></a>
                <a href="#" class="btn btn-primary"><@spring.message 'navbar.search'/></a>
                <a href="#" class="btn btn-primary"><@spring.message 'navbar.new'/></a>
                <a href="#" class="btn btn-primary"><@spring.message 'navbar.popular'/></a>
            </div>
        </div>
        <div class="col-8">
            <h2><@spring.message 'profile.page.welcome'/></h2>
            <dl class="row">
                <dt class="col-sm-2"><@spring.message 'profile.page.name'/>:</dt>
                <dd class="col-sm-9">${user.getFirstName()} ${user.getLastName()}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-2"><@spring.message 'form.email'/>:</dt>
                <dd class="col-sm-9">${user.email}</dd>
            </dl>
            <h5><@spring.message 'profile.page.added.songs'/></h5>
            <#if user.songs?size != 0>
                <#list user.songs as song>
                    <audio controls>
                        <source src="${song.url}" type="audio/ogg">
                        <source src="${song.url}" type="audio/mpeg">
                        <a href="${song.url}"><@spring.message 'song.upload'/> ${song.originalFileName}</a>
                    </audio>
                </#list>
            <#else><p><@spring.message 'empty'/></p>
            </#if>
        </div>
        <div class="col-2">
            <a href="/logout" class="btn btn-primary" id="header_signup-btn"><@spring.message 'logout'/></a>
        </div>
    </div>
</div>
</body>
</html>
<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="<@spring.url '/css/style.css'/>">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="<@spring.url '/js/chat.js'/>"></script>
</head>
<body>
<h1><@spring.message 'support.page.welcome'/></h1>
<div>
    <#if listMessages?size != 0>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><@spring.message 'support.page.username'/></th>
                <th scope="col"><@spring.message 'support.page.question'/></th>
                <th scope="col"><@spring.message 'support.page.answer'/></th>
            </tr>
            </thead>
            <tbody>
            <#list listMessages as message>
                <tr>
                    <td>${message.sender.firstName}  ${message.sender.lastName}</td>
                    <td>${message.text}</td>
                    <td>
                    <#if message.answer == "Expect an answer">
                        <input id="${message.id}" placeholder="Your message">
                        <button onclick="sendResponse('${message.id}','${userId}', $('#${message.id}').val())" class="btn btn-dark">Send</button>
                    <#else> ${message.answer}
                    </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</div>
</body>
</html>
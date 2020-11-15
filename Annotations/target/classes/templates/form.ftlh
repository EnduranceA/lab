<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HtmlForm</title>
</head>
<body>
<form method="${form.method}" action="${form.action}">
    <#list form.inputs as inputs>
        <input type="${inputs.type}" name="${inputs.name}" placeholder="${inputs.placeholder}"/>
    </#list>
</form>
</body>
</html>
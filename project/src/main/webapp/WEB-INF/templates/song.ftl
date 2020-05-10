<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Music</title>
    <script>
        function addToMyMusic(song_id) {
            $.ajax ({
                type:"POST",
                url: "/song",
                data: {
                    "song_id" : song_id
                },
                success: function () {
                    $("#buttonchik").html("");
                    $('#buttonchik').append(
                        "<input type=\"button\" class=\"floated\" id=\"addToBasket\"  value=\"Добавлено\" >"
                    )
                }
            })
        }
    </script>
</head>
<body>
<h2><@spring.message 'songs'/></h2>
<#if songs?size != 0>
    <table class="table table-hover table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><@spring.message 'song.singer.and.group'/></th>
        <th scope="col"><@spring.message 'song.name'/></th>
        <th scope="col"><@spring.message 'song'/></th>
        <th scope="col"><@spring.message 'song.add'/></th>
    </tr>
    </thead>
    <tbody>
    <#list songs as song>
        <tr>
            <th scope="row">${song.id}</th>
            <td>${song.author.firstName} ${song.author.lastName}</td>
            <td>${song.originalFileName}</td>
            <td><audio style="height: 30px;" src="${song.url}" type="audio/mpeg" controls></audio></td>
            <td>
                <di id="buttonchik">
                    <input type="button" name="go-to-list" class="btns bask"
                           value="<@spring.message 'song.add'/>" onclick=addToMyMusic(${song.id})>
                </di>
            </td>
        </tr>
    </#list>
    </tbody>
    </table>
</#if>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>

    <title>SOCIAL FOOD</title>
</head>
<body style="font-family: Georgia, 'Times New Roman', serif;">
<div style="text-align: right;">
    <b>Ректору<br/>
        ФГАОУВО "Казанский (Приволжский) <br/>
        федеральный университет" <br/>
        И.Р.Гафурову <br/>
        от обучающегося института/филиала/факультета <br/>
    </b>
    ${user.instituteName}<br/>
    ${user.courseNumber} <b>курса</b>  ${user.userDto.groupNumber}<br/>
    ${user.userDto.lastName} ${user.userDto.firstName} ${user.userDto.patronymic} <br/>
    <b>Паспорт: №</b> ${user.passportSeries} ${user.passportNumber} <br/>
    <b>Дата рождения: </b>${user.dateOfBirth}<br/>
    <b>Особые условия:</b> <br/>
    <b>Контактный телефон:</b> ${user.userDto.phoneNumber}<br/>
</div>

<div style="text-align: center; padding-top: 30px">
    <h6>ЗАЯВЛЕНИЕ</h6>
</div>
<b>Прошу Вас оказать мне материальную помощь в связи с удорожанием проезда в общественном транспорте.<br/>
Кассовый чек прилагаю.</b>
<div style="text-align: right;">
    ___________________ <br/>
    (подпись заявителя)
</div>
<br/>
<b>Рекомендация института/филиала/факультета и профсоюзной организации студентов: оказать мат. поддержку обучающемуся
    бюджетной формы обучения в форме предоставления социального питания</b><br/><br/>
<b>Староста академической группы №</b>${user.userDto.groupNumber}<b>______/________________</b><br/><br/>
<b>Председатель студенческого профбюро ______________/Л.С.Ахметзянова</b>

</body>
</html>
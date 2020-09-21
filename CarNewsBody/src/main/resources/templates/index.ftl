<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="<@spring.url '/resources/templates/static/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
    <link href="<@spring.url '/resources/templates/static/js/bootstrap.js'/>">
    <title>Last news</title>
</head>
<body>
<div>
    <table class="table-dark">
        <thead class="h2">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Статья</th>
            <th scope="col">Время</th>
        </tr>
        </thead>
        <tbody>
        <#list allNews as news>
                <tr>
                    <td>
                        ${news_index+1}
                    </td>
                <td>
                    <a href="${news.url}" >

                        ${news.title}

                    </a>
                </td>
                <td>
                    ${news.postTime}
                </td>
                </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style>
        table{
            width: 50%;
            border-spacing: 1px;
        }
     </style>
</head>
<body>
<div class="container">
    <form name="ShowForm" action="/partners" method="POST">
        <table class="table">
            <thead class="active">
            <tr class="active" >
                <th  scope="col">#</th>
                <th  scope="col">id</th>
                <th  scope="col">id_gamer</th>
                <th  scope="col">id_competition</th>
            </tr>
            </thead>
            <tbody>
            <#list model["partners"] as partner>
            <tr>
                <th scope="row">#</th>
                <th>${partner.id} </th>
                <th>${partner.id_gamer} </th>
                <th>${partner.id_competition} </th>
            </tr>
            </#list>
            </tbody>
        </table>
    </form>

</div>
</body>
</html>
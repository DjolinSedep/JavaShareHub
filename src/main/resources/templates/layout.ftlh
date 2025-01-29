<#import "spring.ftl" as spring>
<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../static/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Java Share Hub</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <div class="container">
        <a href="/" class="logo" style="text-decoration: none; color: black">
            Java Share Hub
        </a>
        <div class="ms-auto">
            <#assign known = SPRING_SECURITY_CONTEXT?? >
            <#if known>
                <#assign
                user = SPRING_SECURITY_CONTEXT.authentication.principal
                name = user.getUsername()
                >

                <a class="btn btn-outline-primary me-2" href="/profile">profile</a>
                <#if _csrf??>
                    <form action="/logout" method="post" class="d-inline">
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                        <button type="submit" class="btn btn-outline-primary me-2" id="logout">logout</button>
                    </form>
                <#else>
                    <a href="/logout" class="btn btn-outline-primary me-2" id="logout">logout</a>
                </#if>
            <#else>
                <a class="btn btn-outline-primary me-2" href="/auth/login">sign in</a>
                <a class="btn btn-outline-primary" href="/auth/register">sign up</a>
            </#if>
        </div>
    </div>
</nav>


<div class="content">
    <#nested spring>
</div>

</body>
</html>
</#macro>
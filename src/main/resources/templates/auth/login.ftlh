<#import "../layout.ftlh" as main>
<@main.layout>
<div class="login-container">
    <h4 class="text-center mb-4">Sign in</h4>

    <form id="login-form" action="/auth/login" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="mb-3">
            <label for="email" class="form-label">Email: </label>
            <input type="email" class="form-control" id="email" name="username" placeholder="Enter email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password: </label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Введите пароль" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Sign in</button>
    </form>
</div>
</@main.layout>
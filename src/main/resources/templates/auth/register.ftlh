<#import "../layout.ftlh" as main>
<@main.layout ; spring>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4 col-sm-6">
            <div class="card p-4 shadow-sm">
                <h4 class="text-center mb-3">Sign up</h4>
                <form action="/auth/register" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <@spring.formInput "userDto.username" 'id="username" class="form-control form-control-lg"' />
                        <@spring.showErrors "<br>" "error text-danger mb-5" />

                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <@spring.formInput "userDto.email" 'id="email" class="form-control form-control-lg"' />
                        <@spring.showErrors "<br>" "error text-danger mb-5" />

                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <@spring.formInput "userDto.password" 'id="password" class="form-control form-control-lg"' />
                        <@spring.showErrors "<br>" "error text-danger mb-5" />
                    </div>
                    <button type="submit" class="btn btn-primary w-100">sign up</button>
                </form>
                <p class="text-center mt-3">
                    Already have an account? <a href="/auth/login">Sign in</a>
                </p>
            </div>
        </div>
    </div>
</div>
</@main.layout>
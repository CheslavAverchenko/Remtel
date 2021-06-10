<#macro login path isRegisterForm>
    <form name="login" action="${path}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> User Name </label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid','')}" placeholder="username"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Password </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}" placeholder="password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Registration</a></#if>
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>
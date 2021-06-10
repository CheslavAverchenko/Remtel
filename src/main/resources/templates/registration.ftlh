<#import "parts/common.ftlh" as c>
<@c.page>
<#--    <div class="mb-1">Add new user</div>-->
<form action="/registration" method="post">
    <#if message??>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Email </label>
        <div class="col-sm-6">
            <input type="email" name="email"
                   value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid','')}" placeholder="some@some.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Name </label>
        <div class="col-sm-6">
            <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid','')}" placeholder="Your first name"/>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Phone number </label>
        <div class="col-sm-6">
            <input type="text" name="phonenumber" class="form-control ${(phonenumberError??)?string('is-invalid','')}" placeholder="+375(XX)-XXX-XX-XX"/>
            <#if phonenumberError??>
                <div class="invalid-feedback">
                    ${phonenumberError}
                </div>
            </#if>
        </div>
    </div>
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
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Password confirm</label>
        <div class="col-sm-6">
            <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid','')}" placeholder="Retype password"/>
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
        </div>
    </div>
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    <button type="submit" class="btn btn-primary">Create</button>
</form>
</@c.page>
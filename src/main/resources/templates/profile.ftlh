<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="mb-1" style="color: red">${message!" "}</div>
    <form method="post">
<#--        <#if user??>${user.email}</#if>-->
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Email </label>
            <div class="col-sm-6">
                <input type="email" name="email"
                       value="${email!''}" class="form-control" placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="alert alert-danger" role="alert">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> User Name </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="username" value="${username!" "}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Password </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>

<#--        <div class="form-group">-->
<#--            <label class="col-sm-2 col-form-label"> Password </label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}" placeholder="password"/>-->
<#--                <#if passwordError??>-->
<#--                    <div class="invalid-feedback">-->
<#--                        ${passwordError}-->
<#--                    </div>-->
<#--                </#if>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group">-->
<#--            <label class="col-sm-2 col-form-label"> Password confirm</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid','')}" placeholder="Retype password"/>-->
<#--                <#if password2Error??>-->
<#--                    <div class="invalid-feedback">-->
<#--                        ${password2Error}-->
<#--                    </div>-->
<#--                </#if>-->
<#--            </div>-->
<#--        </div>-->

        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Name </label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" placeholder="Your first name" value="${name!" "}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Phone number </label>
            <div class="col-sm-6">
                <input type="text" name="phonenumber" class="form-control" placeholder="+375(XX)-XXX-XX-XX" value="${phonenumber!" "}"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>

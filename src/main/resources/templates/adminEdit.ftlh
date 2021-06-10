<#import "parts/common.ftlh" as c>
<@c.page>
    User editor
    <form action="/user" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label">${user.username}</label>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary dropdown-toggle"
                        type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Role</button>
                <div class="dropdown-menu">
                    <#list roles as role>
                        <div class="input-group-prepend">
                            <div class="dropdown-item"><input  type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <input type="hidden" value="${user.id}" name="userId"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button class="btn btn-primary mt-3" type="submit">Save</button>
    </form>
</@c.page>
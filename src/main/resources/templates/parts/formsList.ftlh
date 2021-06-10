<#import "pager.ftlh" as p>
<#include "security.ftlh">

<#--    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">-->
<#--        Link with href-->
<#--    </a>-->
<#--    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">-->
<#--        Button with data-target-->
<#--    </button>-->
<div class="card-columns" id="forms-list">
    <#--        <#if page??>-->
    <#list pageActive.content as form>
        <div class="card my-3" data-id="${form.id}">
            <#if form.filename??>
                <img src="/img/${form.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${form.message}</span>
                <strong>${form.subject}</strong>
                <br/>
                <strong>${form.date}</strong>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" href="/user-forms-not-admitted/${form.user.id}">${form.userName}</a>
                    <div>
                        <a class="align-bottom" href="/forms/${form.id}/admit/${form.user.id}">
                            <#if isAdmin>
                                <#if form.meAdmitted>
                                    <i style="color: #71c75c" class="fas fa-check-circle fa-lg"></i>
                                <#else >
                                    <i style="color: #71c75c" class="far fa-check-circle fa-lg"></i>
                                </#if>
                            </#if>
                        </a>
                        <#if isAdmin>
                            <a class="align-bottom" href="/forms/${form.id}/delete/${form.user.id}">
                                <i style="color: #ff6161; margin-left: 5px" class="fas fa-trash-alt fa-lg"></i>
                            </a>
                        </#if>
<#--                        <#if isManager>-->
<#--                            <a class="align-bottom" href="#${form.id}">-->
<#--                                <i style="color: #6363ff; margin-left: 5px;" class="fa fa-question fa-lg" aria-hidden="true"></i>-->
<#--                            </a>-->
<#--                            <div style="margin-top: 7px" class="<#if form??>collapse <#else > collapse show</#if>"-->
<#--                                 id="${form.id}">-->
<#--                                <div class="form-group mt-3">-->
<#--                                    <form action="/admitted/${form.user.id}" method="post" enctype="multipart/form-data">-->
<#--                                        <div class="form-group">-->
<#--                                            <input type="text" class="form-control ${(messageError??)?string('is-invalid','')}"-->
<#--                                                   name="question" placeholder="Имеются вопросы"/>-->
<#--                                            <#if messageError??>-->
<#--                                                <div class="invalid-feedback">-->
<#--                                                    ${messageError}-->
<#--                                                </div>-->
<#--                                            </#if>-->
<#--                                        </div>-->
<#--                                        <input type="hidden" name="id" value="<#if form??>${form.id}</#if>"/>-->
<#--                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                                        <div class="form-group">-->
<#--                                            <button type="submit" class="btn btn-dark">Send question</button>-->
<#--                                        </div>-->
<#--                                    </form>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </#if>-->
                        <#if isUser>
                            <#if form.user.id == currentUserId>
                                <a class="btn btn-primary" href="/user-forms-not-admitted/${form.user.id}?form=${form.id}">
                                    Edit
                                </a>
                            </#if>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        No active forms
    </#list>
    <#--        </#if>-->
</div>

<@p.pager url pageActive />

<#--<div class="card-columns" id="forms-list">-->
<#--    &lt;#&ndash;        <#if page??>&ndash;&gt;-->
<#--    <#list pageAdmitted.content as form>-->
<#--        <div class="card my-3" data-id="${form.id}">-->
<#--            <#if form.filename??>-->
<#--                <img src="/img/${form.filename}" class="card-img-top">-->
<#--            </#if>-->
<#--            <div class="m-2">-->
<#--                <span>${form.message}</span>-->
<#--                <strong>${form.subject}</strong>-->
<#--                <br/>-->
<#--                <strong>${form.date}</strong>-->
<#--            </div>-->
<#--            <div class="card-footer text-muted container">-->
<#--                <div class="row">-->
<#--                    <a class="col align-self-center" href="/user-forms/${form.user.id}">${form.userName}</a>-->
<#--                    <a class="align-bottom" href="/forms/${form.id}/admit">-->
<#--                        <#if isAdmin>-->
<#--                            <#if form.meAdmitted>-->
<#--                                <i style="color: green" class="fas fa-check-circle"></i>-->
<#--                            <#else >-->
<#--                                <i style="color: green" class="far fa-check-circle"></i>-->
<#--                            </#if>-->
<#--                        &lt;#&ndash;Doesn't work&ndash;&gt;-->
<#--                        &lt;#&ndash;<#if form.admits??>&ndash;&gt;-->
<#--                        &lt;#&ndash;                                ${form.admits}&ndash;&gt;-->
<#--                        &lt;#&ndash;                            </#if>&ndash;&gt;-->
<#--                        &lt;#&ndash;                            <#if true>&ndash;&gt;-->

<#--                        &lt;#&ndash;                            </#if>&ndash;&gt;-->
<#--                        </#if>-->
<#--                    </a>-->
<#--                    <#if isAdmin>-->
<#--                        <a class="align-bottom" href="/forms/${form.id}/delete">-->
<#--                            <i style="color: red; margin-left: 5px" class="fas fa-trash-alt"></i>-->
<#--                        </a>-->
<#--                    </#if>-->
<#--                    <#if !isAdmin>-->
<#--                        <#if form.user.id == currentUserId>-->
<#--                            <a class="btn btn-primary" href="/user-forms/${form.user.id}?form=${form.id}">-->
<#--                                Edit-->
<#--                            </a>-->
<#--                        </#if>-->
<#--                    </#if>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--    <#else>-->
<#--        No admitted forms-->
<#--    </#list>-->
<#--    &lt;#&ndash;        </#if>&ndash;&gt;-->
<#--</div>-->

<#--<@p.pager url pageAdmitted />-->






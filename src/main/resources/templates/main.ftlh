<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>
<@c.page>
    <#if isAdmin>
        <div class="card-body">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <form method="get" action="/main" class="form-inline">
                        <input type="text" name="filter" class="form-control" value="${filter!" "}"
                               placeholder="Search by model"/>
                        <button type="submit" class="btn btn-dark ml-2">Search</button>
                    </form>
                </div>
<#--                <a href="#">-->
<#--                    <i style="color: black" class="fa fa-refresh fa-lg" aria-hidden="true"></i>-->
<#--                </a>-->
            </div>
            <#include "parts/formsList.ftlh" />
        </div>
    </#if>

<#--    <div class="form-row">-->
<#--        <div class="form-group col-md-6">-->
<#--            <form method="get" action="/main" class="form-inline">-->
<#--                <input type="text" name="filter" class="form-control" value="${filter!" "}"-->
<#--                       placeholder="Search by model"/>-->
<#--                <button type="submit" class="btn btn-dark ml-2">Search</button>-->
<#--            </form>-->
<#--        </div>-->
<#--    </div>-->
<#--    <a class="btn btn-link"-->
<#--       href="#" type="button"  onclick="initDisplayYears()">-->
<#--        Years-->
<#--    </a>-->
<#--    <a class="btn btn-link"-->
<#--       href="#" type="button"  onclick="initDisplayGenres()">-->
<#--        Genres-->
<#--    </a>-->
    <#if isUser>
        <#include "parts/formEdit.ftlh" />

        <#include "parts/formsList.ftlh" />
    </#if>


<#--    <#include "parts/formsAdmittedList.ftlh" />-->
<#--    <div class="card-columns" id="forms-list">-->
<#--&lt;#&ndash;        <#if page??>&ndash;&gt;-->
<#--        <#list page.content as form>-->
<#--            <div class="card my-3" data-id="${form.id}">-->
<#--                <#if form.filename??>-->
<#--                    <img src="/img/${form.filename}" class="card-img-top">-->
<#--                </#if>-->
<#--                <div class="m-2">-->
<#--                    <span>${form.message}</span>-->
<#--                    <strong>${form.subject}</strong>-->
<#--                    <br/>-->
<#--                    <strong>${form.date}</strong>-->
<#--                </div>-->
<#--                <div class="card-footer text-muted">-->
<#--                    ${form.userName}-->
<#--                </div>-->
<#--            </div>-->
<#--        <#else>-->
<#--            No form-->
<#--        </#list>-->
<#--&lt;#&ndash;        </#if>&ndash;&gt;-->
<#--    </div>-->
<#--    <@p.pager url page/>-->
</@c.page>

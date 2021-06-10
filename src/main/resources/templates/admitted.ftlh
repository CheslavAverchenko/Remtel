<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>
<@c.page>
    <div class="card-body">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/admitted" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter!" "}"
                           placeholder="Search by model"/>
                    <button type="submit" class="btn btn-dark ml-2">Search</button>
                </form>
            </div>
        </div>
        <#include "parts/formsAdmittedList.ftlh" />
    </div>
</@c.page>

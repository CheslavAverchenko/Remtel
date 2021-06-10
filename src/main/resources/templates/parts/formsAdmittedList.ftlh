<#import "pager.ftlh" as p>
<#include "security.ftlh">
<div class="card-columns" id="forms-list">
    <#--        <#if page??>-->
    <#list pageAdmitted.content as form>
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
                    <a class="col align-self-baseline" href="/user-forms-admitted/${form.user.id}">${form.userName}</a>
                    <div>
                        <#if isAdmin>
                        <a class="align-bottom" href="/forms/${form.id}/unMark/${form.user.id}">
                            <#if isAdmin>
                                <#if form.meAdmitted>
                                    <i style="color: #71c75c" class="fas fa-check-circle fa-lg"></i>
                                <#else >
                                    <i style="color: #71c75c" class="far fa-check-circle fa-lg"></i>
                                </#if>
                            </#if>
                        </a>
                        <a class="align-bottom" href="/forms/${form.id}/delete/${form.user.id}">
                            <i style="color: #ff6161; margin-left: 5px" class="fas fa-trash-alt fa-lg"></i>
                        </a>
                        </#if>
                        <#if isManager || isAdmin>
                            <a data-toggle="collapse" href="#${form.id}" role="button" aria-expanded="false"
                               aria-controls="collapseExample">
                                <i style="color: #6363ff; margin-left: 5px;" class="fa fa-question fa-lg"
                                   aria-hidden="true"></i>
                            </a>
                            <a data-toggle="collapse" href="#${form.id+1}" role="button" aria-expanded="false"
                               aria-controls="collapseExample">
                                <i style="color: #ffe716; margin-left: 5px;" class="fa fa-star fa-lg"
                                   aria-hidden="true"></i>
                            </a>
                        </#if>
                    </div>
                    <div style="margin-top: 7px" class="<#if form??>collapse <#else > collapse show</#if>"
                         id="${form.id}">
                        <div class="form-group mt-3">
                            <form action="/admitted/${form.user.id}" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control ${(messageError??)?string('is-invalid','')}"
                                           name="question" placeholder="I have a question.."/>
                                    <#if messageError??>
                                        <div class="invalid-feedback">
                                            ${messageError}
                                        </div>
                                    </#if>
                                </div>
                                <input type="hidden" name="id" value="<#if form??>${form.id}</#if>"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark">Send question</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="margin-top: 7px" class="<#if form??>collapse <#else > collapse show</#if>"
                         id="${form.id+1}">
                        <div class="form-group mt-3">
                            <form action="/admitted-cost/${form.user.id}" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control ${(messageError??)?string('is-invalid','')}"
                                           name="task" placeholder="What was done"/>
                                    <#if messageError??>
                                        <div class="invalid-feedback">
                                            ${messageError}
                                        </div>
                                    </#if>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control ${(subjectError??)?string('is-invalid','')}"
                                           name="cost" placeholder="Payment for repair in USD"/>
                                    <#if subjectError??>
                                        <div class="invalid-feedback">
                                            ${subjectError}
                                        </div>
                                    </#if>
                                </div>
                                <input type="hidden" name="id" value="<#if form??>${form.id}</#if>"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark">Done</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        No admitted forms
    </#list>
    <#--        </#if>-->
</div>

<@p.pager url pageAdmitted />

<#include "security.ftlh">
<#if isUser>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Form
    </a>
    <div class="collapse <#if form??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(messageError??)?string('is-invalid','')}"
                           value="<#if form??>${form.message}</#if>" name="message" placeholder="В чем проблема?"/>
                    <#if messageError??>
                        <div class="invalid-feedback">
                            ${messageError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(subjectError??)?string('is-invalid','')}"
                           value="<#if form??>${form.subject}</#if>" name="subject" placeholder="модель"/>
                    <#if subjectError??>
                        <div class="invalid-feedback">
                            ${subjectError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile"/>
                        <label class="custom-file-label" for="customFile">Выберите файл</label>
                    </div>
                </div>
                <input type="hidden" name="id" value="<#if form??>${form.id}</#if>"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-dark">Save form</button>
                </div>
            </form>
        </div>
    </div>
</#if>

<#import "parts/common.ftlh" as c>

<@c.page>
<#if isCurrentUser && form??>
    <#include "parts/formEdit.ftlh" />
</#if>

<#include "parts/formsList.ftlh" />

</@c.page>

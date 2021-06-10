<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isManager = user.isManager()
        isUser = user.isUser()
        currentUserId = user.getId()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
        isManager = false
        isUser = false
        currentUserId = -1
    >
</#if>

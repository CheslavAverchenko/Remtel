<#include "security.ftlh">
<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Remtel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
<#--            <li class="nav-item">-->
<#--                <a class="nav-link" href="/">Home</a>-->
<#--            </li>-->
            <#if isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Forms</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Active forms</a>
                </li>
            </#if>
            <#if isAdmin || isManager>
                <li class="nav-item">
                    <a class="nav-link" href="/admitted">Admitted forms</a>
                </li>
            </#if>
            <#if isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/user-forms-not-admitted/${currentUserId}">My requests</a>
                </li>
            </#if>
            <#if isAdmin || isManager>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            <#if user?? || isAdmin || isManager>
                <li class="nav-item">
                <a class="nav-link" href="/user/profile">Profile</a>
            </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3">${name}</div>
        <#if name != "unknown">
            <@l.logout />
            <#else>
            <a href="http://localhost:8081/login" class="btn btn-primary" role="button" aria-pressed="true">Login</a>
        </#if>
    </div>
</nav>

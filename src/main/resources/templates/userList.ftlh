<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <table class="table table-sm">
        <thead>
        <tr>
            <th scope="col">Login</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Phone number</th>
            <th scope="col">Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row">${user.username}</th>
                <th scope="row">${user.name}</th>
                <th scope="row">${user.email}</th>
                <th scope="row">${user.phonenumber}</th>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <#if isAdmin>
                    <td><a href="/user/${user.id}">edit</a> </td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>

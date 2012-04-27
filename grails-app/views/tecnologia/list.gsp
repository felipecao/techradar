
<%@ page import="techradar.Tecnologia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tecnologia.label', default: 'Tecnologia')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'tecnologia.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'tecnologia.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="x" title="${message(code: 'tecnologia.x.label', default: 'X')}" />
                        
                            <g:sortableColumn property="y" title="${message(code: 'tecnologia.y.label', default: 'Y')}" />
                        
                            <th><g:message code="tecnologia.avaliacao.label" default="Avaliacao" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tecnologiaInstanceList}" status="i" var="tecnologiaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tecnologiaInstance.id}">${fieldValue(bean: tecnologiaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: tecnologiaInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: tecnologiaInstance, field: "x")}</td>
                        
                            <td>${fieldValue(bean: tecnologiaInstance, field: "y")}</td>
                        
                            <td>${fieldValue(bean: tecnologiaInstance, field: "avaliacao")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tecnologiaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

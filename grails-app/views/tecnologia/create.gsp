

<%@ page import="techradar.Tecnologia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tecnologia.label', default: 'Tecnologia')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tecnologiaInstance}">
            <div class="errors">
                <g:renderErrors bean="${tecnologiaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="tecnologia.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tecnologiaInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${tecnologiaInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="x"><g:message code="tecnologia.x.label" default="X" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tecnologiaInstance, field: 'x', 'errors')}">
                                    <g:textField name="x" value="${fieldValue(bean: tecnologiaInstance, field: 'x')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="y"><g:message code="tecnologia.y.label" default="Y" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tecnologiaInstance, field: 'y', 'errors')}">
                                    <g:textField name="y" value="${fieldValue(bean: tecnologiaInstance, field: 'y')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="avaliacao"><g:message code="tecnologia.avaliacao.label" default="Avaliacao" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tecnologiaInstance, field: 'avaliacao', 'errors')}">
                                    <g:select name="avaliacao.id" from="${techradar.Avaliacao.list()}" optionKey="id" value="${tecnologiaInstance?.avaliacao?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>

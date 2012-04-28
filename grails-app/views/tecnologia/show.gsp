
<%@ page import="techradar.Tecnologia" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tecnologia.label', default: 'Tecnologia')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tecnologia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tecnologia" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tecnologia">
			
				<g:if test="${tecnologiaInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="tecnologia.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${tecnologiaInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tecnologiaInstance?.x}">
				<li class="fieldcontain">
					<span id="x-label" class="property-label"><g:message code="tecnologia.x.label" default="X" /></span>
					
						<span class="property-value" aria-labelledby="x-label"><g:fieldValue bean="${tecnologiaInstance}" field="x"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tecnologiaInstance?.y}">
				<li class="fieldcontain">
					<span id="y-label" class="property-label"><g:message code="tecnologia.y.label" default="Y" /></span>
					
						<span class="property-value" aria-labelledby="y-label"><g:fieldValue bean="${tecnologiaInstance}" field="y"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tecnologiaInstance?.avaliacao}">
				<li class="fieldcontain">
					<span id="avaliacao-label" class="property-label"><g:message code="tecnologia.avaliacao.label" default="Avaliacao" /></span>
					
						<span class="property-value" aria-labelledby="avaliacao-label"><g:link controller="avaliacao" action="show" id="${tecnologiaInstance?.avaliacao?.id}">${tecnologiaInstance?.avaliacao?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tecnologiaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tecnologiaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

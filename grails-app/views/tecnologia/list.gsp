
<%@ page import="techradar.Tecnologia" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tecnologia.label', default: 'Tecnologia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tecnologia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tecnologia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'tecnologia.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="x" title="${message(code: 'tecnologia.x.label', default: 'X')}" />
					
						<g:sortableColumn property="y" title="${message(code: 'tecnologia.y.label', default: 'Y')}" />
					
						<th><g:message code="tecnologia.avaliacao.label" default="Avaliacao" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tecnologiaInstanceList}" status="i" var="tecnologiaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tecnologiaInstance.id}">${fieldValue(bean: tecnologiaInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: tecnologiaInstance, field: "x")}</td>
					
						<td>${fieldValue(bean: tecnologiaInstance, field: "y")}</td>
					
						<td>${fieldValue(bean: tecnologiaInstance, field: "avaliacao")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tecnologiaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

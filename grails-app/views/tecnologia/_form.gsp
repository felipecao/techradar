<%@ page import="techradar.Tecnologia" %>



<div class="fieldcontain ${hasErrors(bean: tecnologiaInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="tecnologia.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${tecnologiaInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnologiaInstance, field: 'x', 'error')} required">
	<label for="x">
		<g:message code="tecnologia.x.label" default="X" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="x" min="0" required="" value="${fieldValue(bean: tecnologiaInstance, field: 'x')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnologiaInstance, field: 'y', 'error')} required">
	<label for="y">
		<g:message code="tecnologia.y.label" default="Y" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="y" min="0" required="" value="${fieldValue(bean: tecnologiaInstance, field: 'y')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnologiaInstance, field: 'avaliacao', 'error')} required">
	<label for="avaliacao">
		<g:message code="tecnologia.avaliacao.label" default="Avaliacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="avaliacao" name="avaliacao.id" from="${techradar.Avaliacao.list()}" optionKey="id" required="" value="${tecnologiaInstance?.avaliacao?.id}" class="many-to-one"/>
</div>


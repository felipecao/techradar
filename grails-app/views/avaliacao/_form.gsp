<%@ page import="techradar.Avaliacao" %>



<div class="fieldcontain ${hasErrors(bean: avaliacaoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="avaliacao.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${avaliacaoInstance?.nome}"/>
</div>


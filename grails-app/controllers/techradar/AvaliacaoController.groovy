package techradar

import org.springframework.dao.DataIntegrityViolationException

class AvaliacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [avaliacaoInstanceList: Avaliacao.list(params), avaliacaoInstanceTotal: Avaliacao.count()]
    }

    def create() {
        [avaliacaoInstance: new Avaliacao(params)]
    }

    def save() {
        def avaliacaoInstance = new Avaliacao(params)
        if (!avaliacaoInstance.save(flush: true)) {
            render(view: "create", model: [avaliacaoInstance: avaliacaoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacaoInstance.id])
        redirect(action: "show", id: avaliacaoInstance.id)
    }

    def show() {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "list")
            return
        }

        [avaliacaoInstance: avaliacaoInstance]
    }

    def edit() {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "list")
            return
        }

        [avaliacaoInstance: avaliacaoInstance]
    }

    def update() {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (avaliacaoInstance.version > version) {
                avaliacaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'avaliacao.label', default: 'Avaliacao')] as Object[],
                          "Another user has updated this Avaliacao while you were editing")
                render(view: "edit", model: [avaliacaoInstance: avaliacaoInstance])
                return
            }
        }

        avaliacaoInstance.properties = params

        if (!avaliacaoInstance.save(flush: true)) {
            render(view: "edit", model: [avaliacaoInstance: avaliacaoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacaoInstance.id])
        redirect(action: "show", id: avaliacaoInstance.id)
    }

    def delete() {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "list")
            return
        }

        try {
            avaliacaoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

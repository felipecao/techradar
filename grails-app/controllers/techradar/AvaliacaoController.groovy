package techradar

class AvaliacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [avaliacaoInstanceList: Avaliacao.list(params), avaliacaoInstanceTotal: Avaliacao.count()]
    }

    def create = {
        def avaliacaoInstance = new Avaliacao()
        avaliacaoInstance.properties = params
        return [avaliacaoInstance: avaliacaoInstance]
    }

    def save = {
        def avaliacaoInstance = new Avaliacao(params)
        if (avaliacaoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacaoInstance.id])}"
            redirect(action: "show", id: avaliacaoInstance.id)
        }
        else {
            render(view: "create", model: [avaliacaoInstance: avaliacaoInstance])
        }
    }

    def show = {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
            redirect(action: "list")
        }
        else {
            [avaliacaoInstance: avaliacaoInstance]
        }
    }

    def edit = {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (!avaliacaoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [avaliacaoInstance: avaliacaoInstance]
        }
    }

    def update = {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (avaliacaoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (avaliacaoInstance.version > version) {

                    avaliacaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'avaliacao.label', default: 'Avaliacao')] as Object[], "Another user has updated this Avaliacao while you were editing")
                    render(view: "edit", model: [avaliacaoInstance: avaliacaoInstance])
                    return
                }
            }
            avaliacaoInstance.properties = params
            if (!avaliacaoInstance.hasErrors() && avaliacaoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacaoInstance.id])}"
                redirect(action: "show", id: avaliacaoInstance.id)
            }
            else {
                render(view: "edit", model: [avaliacaoInstance: avaliacaoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def avaliacaoInstance = Avaliacao.get(params.id)
        if (avaliacaoInstance) {
            try {
                avaliacaoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])}"
            redirect(action: "list")
        }
    }
}

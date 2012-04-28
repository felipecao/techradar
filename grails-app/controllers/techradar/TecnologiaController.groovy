package techradar

import org.springframework.dao.DataIntegrityViolationException

class TecnologiaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tecnologiaInstanceList: Tecnologia.list(params), tecnologiaInstanceTotal: Tecnologia.count()]
    }

    def create() {
        [tecnologiaInstance: new Tecnologia(params)]
    }

    def save() {
        def tecnologiaInstance = new Tecnologia(params)
        if (!tecnologiaInstance.save(flush: true)) {
            render(view: "create", model: [tecnologiaInstance: tecnologiaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), tecnologiaInstance.id])
        redirect(action: "show", id: tecnologiaInstance.id)
    }

    def show() {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "list")
            return
        }

        [tecnologiaInstance: tecnologiaInstance]
    }

    def edit() {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "list")
            return
        }

        [tecnologiaInstance: tecnologiaInstance]
    }

    def update() {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tecnologiaInstance.version > version) {
                tecnologiaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'tecnologia.label', default: 'Tecnologia')] as Object[],
                        "Another user has updated this Tecnologia while you were editing")
                render(view: "edit", model: [tecnologiaInstance: tecnologiaInstance])
                return
            }
        }

        tecnologiaInstance.properties = params

        if (!tecnologiaInstance.save(flush: true)) {
            render(view: "edit", model: [tecnologiaInstance: tecnologiaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), tecnologiaInstance.id])
        redirect(action: "show", id: tecnologiaInstance.id)
    }

    def delete() {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tecnologiaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

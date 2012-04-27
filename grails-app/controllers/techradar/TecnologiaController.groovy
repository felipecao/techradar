package techradar

class TecnologiaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tecnologiaInstanceList: Tecnologia.list(params), tecnologiaInstanceTotal: Tecnologia.count()]
    }

    def create = {
        def tecnologiaInstance = new Tecnologia()
        tecnologiaInstance.properties = params
        return [tecnologiaInstance: tecnologiaInstance]
    }

    def save = {
        def tecnologiaInstance = new Tecnologia(params)
        if (tecnologiaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), tecnologiaInstance.id])}"
            redirect(action: "show", id: tecnologiaInstance.id)
        }
        else {
            render(view: "create", model: [tecnologiaInstance: tecnologiaInstance])
        }
    }

    def show = {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tecnologiaInstance: tecnologiaInstance]
        }
    }

    def edit = {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (!tecnologiaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tecnologiaInstance: tecnologiaInstance]
        }
    }

    def update = {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (tecnologiaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tecnologiaInstance.version > version) {
                    
                    tecnologiaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tecnologia.label', default: 'Tecnologia')] as Object[], "Another user has updated this Tecnologia while you were editing")
                    render(view: "edit", model: [tecnologiaInstance: tecnologiaInstance])
                    return
                }
            }
            tecnologiaInstance.properties = params
            if (!tecnologiaInstance.hasErrors() && tecnologiaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), tecnologiaInstance.id])}"
                redirect(action: "show", id: tecnologiaInstance.id)
            }
            else {
                render(view: "edit", model: [tecnologiaInstance: tecnologiaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tecnologiaInstance = Tecnologia.get(params.id)
        if (tecnologiaInstance) {
            try {
                tecnologiaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tecnologia.label', default: 'Tecnologia'), params.id])}"
            redirect(action: "list")
        }
    }
}

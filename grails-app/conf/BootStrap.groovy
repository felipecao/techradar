import grails.util.Environment
import techradar.Avaliacao
import techradar.Tecnologia

class BootStrap {

    def init = { servletContext ->

        if(Avaliacao.count() == 0){
            new Avaliacao(nome: "Adopt").save()
            new Avaliacao(nome: "Discontinue").save()
        }

        if(Tecnologia.count() == 0){
            new Tecnologia(nome: "SpringMVC", x: 420, y: 380, avaliacao: Avaliacao.list().first()).save()
            new Tecnologia(nome: "JSF", x: 420, y: 80, avaliacao: Avaliacao.list().last()).save()
        }
    }
    def destroy = {
    }
}

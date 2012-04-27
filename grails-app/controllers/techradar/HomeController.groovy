package techradar

class HomeController {

    def index = {
        def avaliacoes = Avaliacao.list()
        def tecnologias = Tecnologia.list()

        [avaliacoes:avaliacoes, tecnologias:tecnologias]
    }
}

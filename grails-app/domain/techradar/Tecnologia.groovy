package techradar

class Tecnologia {

    String nome
    int x
    int y
    Avaliacao avaliacao

    static constraints = {
        nome(blank: false)
        x(min: 0)
        y(min: 0)
        avaliacao(nullable: false)
    }
}

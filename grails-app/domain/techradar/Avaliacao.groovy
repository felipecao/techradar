package techradar

class Avaliacao {

    String nome

    static constraints = {
        nome(blank:false)
    }

    def String toString(){
        nome
    }
}

package techradar



import org.junit.*
import grails.test.mixin.*

@TestFor(AvaliacaoController)
@Mock(Avaliacao)
class AvaliacaoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/avaliacao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.avaliacaoInstanceList.size() == 0
        assert model.avaliacaoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.avaliacaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.avaliacaoInstance != null
        assert view == '/avaliacao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/avaliacao/show/1'
        assert controller.flash.message != null
        assert Avaliacao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/avaliacao/list'


        populateValidParams(params)
        def avaliacao = new Avaliacao(params)

        assert avaliacao.save() != null

        params.id = avaliacao.id

        def model = controller.show()

        assert model.avaliacaoInstance == avaliacao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/avaliacao/list'


        populateValidParams(params)
        def avaliacao = new Avaliacao(params)

        assert avaliacao.save() != null

        params.id = avaliacao.id

        def model = controller.edit()

        assert model.avaliacaoInstance == avaliacao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/avaliacao/list'

        response.reset()


        populateValidParams(params)
        def avaliacao = new Avaliacao(params)

        assert avaliacao.save() != null

        // test invalid parameters in update
        params.id = avaliacao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/avaliacao/edit"
        assert model.avaliacaoInstance != null

        avaliacao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/avaliacao/show/$avaliacao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        avaliacao.clearErrors()

        populateValidParams(params)
        params.id = avaliacao.id
        params.version = -1
        controller.update()

        assert view == "/avaliacao/edit"
        assert model.avaliacaoInstance != null
        assert model.avaliacaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/avaliacao/list'

        response.reset()

        populateValidParams(params)
        def avaliacao = new Avaliacao(params)

        assert avaliacao.save() != null
        assert Avaliacao.count() == 1

        params.id = avaliacao.id

        controller.delete()

        assert Avaliacao.count() == 0
        assert Avaliacao.get(avaliacao.id) == null
        assert response.redirectedUrl == '/avaliacao/list'
    }
}

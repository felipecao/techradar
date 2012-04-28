package techradar



import org.junit.*
import grails.test.mixin.*

@TestFor(TecnologiaController)
@Mock(Tecnologia)
class TecnologiaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tecnologia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tecnologiaInstanceList.size() == 0
        assert model.tecnologiaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tecnologiaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tecnologiaInstance != null
        assert view == '/tecnologia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tecnologia/show/1'
        assert controller.flash.message != null
        assert Tecnologia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnologia/list'


        populateValidParams(params)
        def tecnologia = new Tecnologia(params)

        assert tecnologia.save() != null

        params.id = tecnologia.id

        def model = controller.show()

        assert model.tecnologiaInstance == tecnologia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnologia/list'


        populateValidParams(params)
        def tecnologia = new Tecnologia(params)

        assert tecnologia.save() != null

        params.id = tecnologia.id

        def model = controller.edit()

        assert model.tecnologiaInstance == tecnologia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnologia/list'

        response.reset()


        populateValidParams(params)
        def tecnologia = new Tecnologia(params)

        assert tecnologia.save() != null

        // test invalid parameters in update
        params.id = tecnologia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tecnologia/edit"
        assert model.tecnologiaInstance != null

        tecnologia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tecnologia/show/$tecnologia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tecnologia.clearErrors()

        populateValidParams(params)
        params.id = tecnologia.id
        params.version = -1
        controller.update()

        assert view == "/tecnologia/edit"
        assert model.tecnologiaInstance != null
        assert model.tecnologiaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tecnologia/list'

        response.reset()

        populateValidParams(params)
        def tecnologia = new Tecnologia(params)

        assert tecnologia.save() != null
        assert Tecnologia.count() == 1

        params.id = tecnologia.id

        controller.delete()

        assert Tecnologia.count() == 0
        assert Tecnologia.get(tecnologia.id) == null
        assert response.redirectedUrl == '/tecnologia/list'
    }
}

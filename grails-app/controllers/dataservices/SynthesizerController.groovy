package dataservices

import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
// tag::begin_class[]
class SynthesizerController {
// end::begin_class[]

    SynthesizerService synthesizerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // tag::synth_by_manufacturer[]

    def synthsByManufacturer(String manufacturer) {
        List<Synthesizer> synthesizers = synthesizerService.findSynthesizers(manufacturer)

        respond synthesizers, view: 'index'
    }

    // ...

    // end::synth_by_manufacturer[]

    def index() {
        respond Synthesizer.list()
    }

    def show(Synthesizer synthesizer) {
        respond synthesizer
    }

    def create() {
        respond new Synthesizer(params)
    }

    @Transactional
    def save(Synthesizer synthesizer) {
        if (synthesizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (synthesizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond synthesizer.errors, view:'create'
            return
        }

        synthesizer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'synthesizer.label', default: 'Synthesizer'), synthesizer.id])
                redirect synthesizer
            }
            '*' { respond synthesizer, [status: CREATED] }
        }
    }

    def edit(Synthesizer synthesizer) {
        respond synthesizer
    }

    @Transactional
    def update(Synthesizer synthesizer) {
        if (synthesizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (synthesizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond synthesizer.errors, view:'edit'
            return
        }

        synthesizer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'synthesizer.label', default: 'Synthesizer'), synthesizer.id])
                redirect synthesizer
            }
            '*'{ respond synthesizer, [status: OK] }
        }
    }

    @Transactional
    def delete(Synthesizer synthesizer) {

        if (synthesizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        synthesizer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'synthesizer.label', default: 'Synthesizer'), synthesizer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'synthesizer.label', default: 'Synthesizer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
// tag::end_class[]
}
// end::end_class[]

package dataservices

import grails.gorm.services.Service

/**
 * Created by hschoonjans on 8/09/2017.
 */
@Service(Synthesizer)
interface SynthesizerService {

    List<Synthesizer> findSynthesizers(String manufacturer)
}

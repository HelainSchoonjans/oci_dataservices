package dataservices

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class SynthSpec extends GebSpec {

    void 'test retrieving all synths'() {
        when:
        go '/synthesizer'

        then:
        at SynthListPage

        and:
        numberOfSynthRows == 11
    }

    void 'test retrieving Moog synths'() {
        when:
        go '/synth/manufacturer/Moog'

        then:
        at SynthListPage

        and:
        numberOfSynthRows == 3
    }

    void 'test retrieving Arturia synths'() {
        when:
        go '/synth/manufacturer/Arturia'

        then:
        at SynthListPage

        and:
        numberOfSynthRows == 3
    }

    void 'test retrieving Korg synths'() {
        when:
        go '/synth/manufacturer/Korg'

        then:
        at SynthListPage

        and:
        numberOfSynthRows == 2
    }

    void 'test retrieving DSI synths'() {
        when:
        go '/synth/manufacturer/Dave%20Smith%20Instruments'

        then:
        at SynthListPage

        and:
        numberOfSynthRows == 3
    }
}

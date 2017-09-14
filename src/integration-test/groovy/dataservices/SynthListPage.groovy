package dataservices

import geb.Page

class SynthListPage extends Page {

    static at = { title == 'Synthesizer List'}

    static content = {
        synthRows { $('table tbody tr') }
        numberOfSynthRows { synthRows.size() }
    }
}

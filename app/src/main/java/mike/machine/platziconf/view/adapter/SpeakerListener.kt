package mike.machine.platziconf.view.adapter

import mike.machine.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerCliked(speaker: Speaker, position: Int)
}
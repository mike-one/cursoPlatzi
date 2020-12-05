package mike.machine.platziconf.view.adapter

import mike.machine.platziconf.model.Conference


interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}
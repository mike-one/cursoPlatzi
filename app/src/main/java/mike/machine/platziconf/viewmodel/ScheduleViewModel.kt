package mike.machine.platziconf.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mike.machine.platziconf.model.Conference
import mike.machine.platziconf.network.Callback
import mike.machine.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSecheduleFromFirebase()
    }
    fun getSecheduleFromFirebase(){
        firestoreService.getSechedule(object : Callback<List<Conference>> {
            override fun onFailed(exception: Exception) {
                processFinished()
            }

            override fun onSuccess(result: List<Conference>?) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }

}
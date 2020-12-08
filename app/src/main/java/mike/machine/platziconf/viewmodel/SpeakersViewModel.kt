package mike.machine.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mike.machine.platziconf.model.Speaker
import mike.machine.platziconf.network.Callback
import mike.machine.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>>{
            override fun onFailed(exception: Exception) {
                processFinished()
            }

            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}
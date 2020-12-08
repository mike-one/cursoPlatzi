package mike.machine.platziconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import mike.machine.platziconf.model.Conference
import mike.machine.platziconf.model.Speaker

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback <List<Speaker>>){
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME).orderBy("category")
            .get().addOnSuccessListener {result ->
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                }
            }

    }

    fun getSechedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get().
            addOnSuccessListener {result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                }
            }

    }

}
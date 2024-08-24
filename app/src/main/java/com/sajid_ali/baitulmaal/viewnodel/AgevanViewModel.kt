package com.sajid_ali.baitulmaal.viewnodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.sajid_ali.baitulmaal.callbacks.DataUpdateCallback
import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.utils.AGEVAN_COLLECTION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AgevanViewModel : ViewModel() {
    private var _agevanList = MutableStateFlow<List<Agevan?>>(emptyList())
    var agevanList = _agevanList.asStateFlow()
    private val db = Firebase.firestore
    private val collection = db.collection(AGEVAN_COLLECTION)

    init {
        getAllAgevan()
    }

    private fun getAllAgevan() {
        collection
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    val agevanList = ArrayList<Agevan>()
                    value.documents.forEach {
                        val agevan = it.toObject<Agevan>()
                        agevan?.id = it.id
                        if (agevan != null) {
                            agevanList.add(agevan)
                        }
                    }
                    _agevanList.value = agevanList
                }
            }
    }

    fun addAgevan(agevan: Agevan?, callback: DataUpdateCallback) {
        if (agevan != null) {
            collection
                .add(agevan)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
        }
    }

    fun updateAgevan(agevan: Agevan?, callback: DataUpdateCallback) {
        agevan?.id?.let {
            collection.document(it).set(agevan)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
        }
    }

    fun deleteAgevan(agevanId: String, callback: DataUpdateCallback) {
        collection.document(agevanId).delete().addOnSuccessListener {
            callback.onSuccess()
            }
    }
}
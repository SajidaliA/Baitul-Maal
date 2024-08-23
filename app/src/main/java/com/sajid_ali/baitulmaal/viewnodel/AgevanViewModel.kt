package com.sajid_ali.baitulmaal.viewnodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.utils.AGEVAN_COLLECTION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AgevanViewModel : ViewModel() {
    private var _agevanList = MutableStateFlow<List<Agevan?>>(emptyList())
    var agevanList = _agevanList.asStateFlow()
    val db = Firebase.firestore

    init {
        getAllAgevan()
    }

    private fun getAllAgevan() {
        db.collection(AGEVAN_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    _agevanList.value = value.toObjects()
                }
            }
    }
}
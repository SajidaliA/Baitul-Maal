package com.sajid_ali.baitulmaal.viewnodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.utils.MEMBER_COLLECTION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemberViewModel : ViewModel() {
    private var _members = MutableStateFlow<List<Member?>>(emptyList())
    var members = _members.asStateFlow()
    val db = Firebase.firestore

    init {
        getAllMembers()
    }

    private fun getAllMembers() {
        db.collection(MEMBER_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    _members.value = value.toObjects()
                }
            }
    }
}
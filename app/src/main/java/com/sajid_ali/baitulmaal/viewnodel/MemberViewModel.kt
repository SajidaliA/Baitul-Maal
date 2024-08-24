package com.sajid_ali.baitulmaal.viewnodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.sajid_ali.baitulmaal.callbacks.DataUpdateCallback
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.utils.MEMBER_COLLECTION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemberViewModel : ViewModel() {
    private var _members = MutableStateFlow<List<Member?>>(emptyList())
    var members = _members.asStateFlow()
    private val db = Firebase.firestore
    private val collection = db.collection(MEMBER_COLLECTION)

    init {
        getAllMembers()
    }

    private fun getAllMembers() {
        collection
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    val memberList = ArrayList<Member>()
                    value.documents.forEach {
                        val member = it.toObject<Member>()
                        member?.id = it.id
                        if (member != null) {
                            memberList.add(member)
                        }
                    }
                    _members.value = memberList
                }
            }
    }

    fun addMember(member: Member?, callback: DataUpdateCallback) {
        if (member != null) {
            collection
                .add(member)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
        }
    }

    fun updateMember(member: Member?, callback: DataUpdateCallback) {
        member?.id?.let {
            collection.document(it).set(member)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
        }
    }

    fun deleteMember(memberId: String, callback: DataUpdateCallback) {
        collection.document(memberId).delete().addOnSuccessListener {
            callback.onSuccess()
        }
    }
}
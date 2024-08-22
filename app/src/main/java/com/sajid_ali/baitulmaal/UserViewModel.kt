package com.sajid_ali.baitulmaal

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.sajid_ali.baitulmaal.model.User
import com.sajid_ali.baitulmaal.utils.USER_COLLECTION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private var _users = MutableStateFlow<List<User?>>(emptyList())
    var users = _users.asStateFlow()

    fun login() {
        val db = Firebase.firestore

        db.collection(USER_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    _users.value = value.toObjects()
                }
            }
    }
}
package uz.gita.composeandfirebase.domain.repositories.authrepository.impl

import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.composeandfirebase.domain.repositories.authrepository.AuthRepository
import uz.gita.composeandfirebase.util.myLog
import javax.inject.Inject

/**
 *   Created by Jamik on 6/14/2023 ot 4:15 PM
 **/

var user: FirebaseUser? = null


class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth = Firebase.auth

    override fun logIn(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        myLog("AuthRepository -> logIn")
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            myLog("AuthRepository -> logIn -> Success")
            user = auth.currentUser
            trySend(Result.success(Unit))
        }.addOnFailureListener {
            myLog("AuthRepository -> logIn -> Failure")
            trySend(Result.failure(it))
        }.addOnCanceledListener {
            myLog("AuthRepository -> logIn -> Cancel")
            trySend(Result.failure(Exception("Cancelled Operation")))
        }
        awaitClose { }
    }

    override fun verify(password: String): Flow<Result<Unit>> = callbackFlow {
        myLog("AuthRepository -> verify")
        val credential = EmailAuthProvider.getCredential(user?.email ?: "", password)
        user?.reauthenticate(credential)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                myLog("AuthRepository -> verify user -> Success")
                trySend(Result.success(Unit))
            } else {
                myLog("AuthRepository -> create user -> Failure")
                val exception = task.exception
                trySend(Result.failure(exception!!))
            }
        }
    }

    override fun createUser(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        myLog("AuthRepository -> signUp")
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            myLog("AuthRepository -> create user -> Success")
            user = auth.currentUser
            myLog("${user?.email}")
            trySend(Result.success(Unit))
        }.addOnFailureListener {
            myLog("AuthRepository -> create user -> Failure")
            trySend(Result.failure(it))
        }.addOnCanceledListener {
            myLog("AuthRepository -> create user -> Cancel")
            trySend(Result.failure(Exception("Cancelled Operation")))
        }
        awaitClose { }
    }

    override fun sendEmailVerification(user: FirebaseUser): Flow<Result<Unit>> = callbackFlow {
        myLog("AuthRepository -> sendEmailVerification()")
        user.sendEmailVerification()
            .addOnSuccessListener {
                myLog("AuthRepository -> send code -> Success")
                trySend(Result.success(Unit))
            }.addOnFailureListener { task ->
                val exception = task.message
                myLog("AuthRepository -> send code -> Failure")
                trySend(Result.failure(Exception(exception)))
            }
        awaitClose { }
//        auth.sendPasswordResetEmail(user.email.toString())
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    myLog("AuthRepository -> send code -> Success")
//                    trySend(Result.success(Unit))
//                } else {
//                    val exception = task.exception
//                    myLog("AuthRepository -> send code -> Failure")
//                    trySend(Result.failure(exception!!))
//                }
//            }
        /*    .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    myLog("AuthRepository -> send code -> Success")
                    trySend(Result.success(Unit))
                } else {
                    val exception = task.exception
                    myLog("AuthRepository -> send code -> Failure")
                    trySend(Result.failure(exception!!))
                }
            }*/
    }
}
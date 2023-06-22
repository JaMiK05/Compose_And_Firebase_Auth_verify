package uz.gita.composeandfirebase.domain.repositories.authrepository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

/**
 *   Created by Jamik on 6/14/2023 ot 4:46 PM
 **/
interface AuthRepository {


    fun logIn(email: String, password: String): Flow<Result<Unit>>

    fun verify(password: String): Flow<Result<Unit>>

    fun createUser(email: String, password: String): Flow<Result<Unit>>

    fun sendEmailVerification(user:FirebaseUser): Flow<Result<Unit>>

}
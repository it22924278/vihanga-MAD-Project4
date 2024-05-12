package com.example.IT22924278.database

class UserRepository (
    private val db:User_database){

    suspend fun insert(users: Users) = db.getUserDao().insert(users)
    fun getUserDetails(email:String):Users=db.getUserDao().getUserDetails(email)
}
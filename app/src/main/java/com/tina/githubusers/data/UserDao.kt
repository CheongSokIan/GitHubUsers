package com.tina.githubusers.data

import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Query("SELECT * FROM user_table WHERE id > :id ORDER BY id ASC LIMIT :perPage")
    suspend fun getUserList(id: Long, perPage: Int): List<User>

    @Query("SELECT * FROM user_table WHERE login = :username")
    suspend fun getUser(username: String): User

    @Update
    suspend fun updateUser(vararg user: User)
}
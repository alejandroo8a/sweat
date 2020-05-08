package com.amor.sweatchallenge.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun observerUsers(): Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: UserEntity)

    @Query("DELETE FROM user WHERE userid = :userId")
    fun deleteUserById(userId: String)

}
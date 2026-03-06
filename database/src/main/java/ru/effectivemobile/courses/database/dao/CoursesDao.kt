package ru.effectivemobile.courses.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.database.entity.CoursesEntity

@Dao
interface CoursesDao {

    @Query("SELECT * FROM CoursesEntity WHERE id = :id")
    fun getById(id: Int): CoursesEntity

    @Query("UPDATE CoursesEntity SET hasLike = :hasLike WHERE id = :id")
    suspend fun insert(id: Int, hasLike: Boolean)

    @Query("SELECT * FROM CoursesEntity")
    fun getAllOnFlow(): Flow<List<CoursesEntity>>

    @Query("SELECT * FROM CoursesEntity")
    fun getAll(): List<CoursesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courses: List<CoursesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courses: CoursesEntity)

    @Query("DELETE FROM CoursesEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM CoursesEntity WHERE hasLike")
    fun getLikedOnFlow(): Flow<List<CoursesEntity>>
}

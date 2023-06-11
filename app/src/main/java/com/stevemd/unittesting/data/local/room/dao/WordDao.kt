package com.stevemd.unittesting.data.local.room.dao

import android.database.Observable
import androidx.room.*
import com.stevemd.unittesting.data.local.room.entity.Word
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.CompletableFuture

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    suspend fun getAllWords(): List<Word>

    @Query("SELECT * FROM word where id = :wordId")
     fun getOneWord(wordId: Int): Word

    @Insert
     fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
     suspend fun deleteWord(word: Word)
}
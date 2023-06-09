package com.stevemd.unittesting.data.local.room.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.stevemd.unittesting.MainActivity
import com.stevemd.unittesting.data.local.room.database.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

/**
 * Unit tests for WordDao
 */

@RunWith(JUnit4::class)
class WordDaoTest {

    @Mock
    private lateinit var appDatabase: AppDatabase

    private lateinit var context: MainActivity

    // Executes each task synchronously using Architecture Components
    @get:Rule
    val rule = InstantTaskExecutorRule()


    // Initializing our database
    @Before
    fun initDB() {
        // Using in memory db so that the information stored here disappears
        // process is killed
        appDatabase = Room.inMemoryDatabaseBuilder(
            context = context.applicationContext,AppDatabase::class.java
        ).allowMainThreadQueries().build()

    }

    // Cleaning up the database
    @After
    fun closeDB() = appDatabase.close()

    @Test
    fun insertAllWords() {
        runBlocking {
            val wordDao = appDatabase.wordDao()

            assertThat(wordDao)
        }
    }


}
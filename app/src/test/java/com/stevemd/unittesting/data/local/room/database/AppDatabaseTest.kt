package com.stevemd.unittesting.data.local.room.database

import android.content.Context
import androidx.room.Room

import com.stevemd.unittesting.data.local.room.dao.WordDao
import com.stevemd.unittesting.data.local.room.entity.Word
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock


@RunWith(JUnit4::class)
class AppDatabaseTest : TestCase(){

    val context = mock(Context::class.java)

    @Mock
    lateinit var appDatabase: AppDatabase

    @Mock
    lateinit var wordDao: WordDao

    @Before
    override fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        wordDao = appDatabase.wordDao()
    }

    @After
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun `insert word returns true`() {
        val name = Word(1,"Coding")

        wordDao.insertWord(name)

        assertThat(name.name)
    }



}
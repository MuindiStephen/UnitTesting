package com.stevemd.unittesting.data.local.room.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.stevemd.unittesting.data.local.room.database.ShoppingItemDatabase
import com.stevemd.unittesting.data.local.room.entity.ShoppingItem
import com.stevemd.unittesting.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    private val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var shoppingItemDatabase: ShoppingItemDatabase

    @Mock
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setUp() {
        shoppingItemDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java,
        ).allowMainThreadQueries().build()

        shoppingDao = shoppingItemDatabase.shoppingDao()
    }

    @After
    fun closeDB() {
        shoppingItemDatabase.close()
    }


    @Test
    fun testingToInsertAShoppingItem () {
        runBlockingTest {
            val shoppingItem = ShoppingItem("shoe",1,3000F,"https://path.com/image-1",1)
            shoppingDao.insertShoppingItem(shoppingItem)

            val results = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

            assertThat(results).contains(shoppingItem)

        }
    }

    @Test
    fun testToDeleteAShoppingItem () {
        runBlockingTest {
            val shoppingItem = ShoppingItem("R.A.M",2,4400F,"https://path.com/image-2",2)
            shoppingDao.insertShoppingItem(shoppingItem)
            shoppingDao.deleteShoppingItem(shoppingItem)

            val results = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
            assertThat(results).doesNotContain(shoppingItem)
        }
    }


}
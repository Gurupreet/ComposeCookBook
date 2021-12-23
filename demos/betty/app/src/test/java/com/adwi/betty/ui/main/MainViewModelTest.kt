package com.adwi.betty.ui.main

import com.adwi.betty.MainCoroutineRule
import com.adwi.betty.fake.FakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = MainCoroutineRule()

    @Test
    fun `check progress set return true`() {

        val viewModel = MainViewModel(FakeRepository(), testDispatcher)

        viewModel.setProgress(1)

        assertThat(viewModel.progress.value).isNotNull()
    }
}
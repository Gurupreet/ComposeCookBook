package com.guru.composecookbook.ui.home

import com.google.common.truth.Truth.assertThat
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.HomeScreenItems
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Home Screen Items Tests")
internal class HomeScreenItemsTest {


    @Test
    fun `Home Screen Entry ListView name must contain its type`() {
        val verticalListViewItem = HomeScreenItems.ListView()
        assertThat(verticalListViewItem.name)
            .contains(verticalListViewItem.type)
    }

    @Test
    fun `Default Home Screen Entry ListView type should be Vertical`() {
        val verticalListViewItem = HomeScreenItems.ListView()
        assertThat(verticalListViewItem.type)
            .isEqualTo("Vertical")
    }

    @Test
    fun `Provided Home Screen Items must contain 3 entries for ListViews`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.ListView>()

        ).hasSize(3)
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Dialogs`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.Dialogs>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Layouts`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.Layouts>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Modifiers`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.Modifiers>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Advanced Lists`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.AdvanceLists>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Android Views`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.AndroidViews>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for Carousel`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.Carousel>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for ConstraintsLayout`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.ConstraintsLayout>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for TabLayout`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.TabLayout>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for BottomSheets`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.BottomSheets>()

        ).isNotEmpty()
    }

    @Test
    fun `Provided Home Screen Items must contain at list an entry for PullRefresh`() {
        assertThat(
            DemoDataProvider.homeScreenListItems
                .filterIsInstance<HomeScreenItems.PullRefresh>()

        ).isNotEmpty()
    }


}
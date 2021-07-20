package com.guru.composecookbook.data.model

sealed class HomeScreenItems {
    object Dialogs : HomeScreenItems()
    object TabLayout : HomeScreenItems()
    object Carousel : HomeScreenItems()
    object Layouts : HomeScreenItems()
    data class ListView(val type: String = "Vertical") : HomeScreenItems()
    object AdvanceLists : HomeScreenItems()
    object ConstraintsLayout : HomeScreenItems()
    object CollapsingAppBar : HomeScreenItems()
    object BottomAppBar : HomeScreenItems()
    object BottomSheets : HomeScreenItems()
    object Modifiers : HomeScreenItems()
    object AndroidViews : HomeScreenItems()
    object PullRefresh : HomeScreenItems()
    object CustomFling : HomeScreenItems()
    object MotionLayout: HomeScreenItems()

    val name: String
        get() = when (this) {
            is Dialogs -> "Dialogs"
            is TabLayout -> "TabLayout"
            is Carousel -> "Carousel"
            is ListView -> "$type ListView"
            ConstraintsLayout -> "Constraint Layout"
            MotionLayout -> "Motion Layout"
            CollapsingAppBar -> "Collapsing AppBar"
            BottomAppBar -> "BottomAppBar"
            BottomSheets -> "BottomSheets"
            Layouts -> "Layouts"
            Modifiers -> "Modifiers"
            AndroidViews -> "Compose X Android views"
            AdvanceLists -> "Advance Lists"
            PullRefresh -> "Pull refresh demos"
            CustomFling -> "Custom Fling"
        }
}
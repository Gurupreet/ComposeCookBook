package com.guru.composecookbook.theme.home

sealed class HomeScreenItems {
    object Dialogs : HomeScreenItems()
    object TabLayout : HomeScreenItems()
    object Carousel : HomeScreenItems()
    object Layouts : HomeScreenItems()
    data class ListView(val type: String = "Vertical") : HomeScreenItems()
    object ConstraintsLayout : HomeScreenItems()
    object CollapsingAppBar : HomeScreenItems()
    object BottomAppBar : HomeScreenItems()
    object BottomSheets : HomeScreenItems()
    object Modifiers : HomeScreenItems()

    val name: String
        get() = when (this) {
            is Dialogs -> "Dialogs"
            is TabLayout -> "TabLayout"
            is Carousel -> "Carousel"
            is ListView -> "$type ListView"
            ConstraintsLayout -> "Constraint Layout"
            CollapsingAppBar -> "Collapsing AppBar"
            BottomAppBar -> "BottomAppBar"
            BottomSheets -> "BottomSheets"
            Layouts -> "Layouts"
            Modifiers -> "Modifiers"
        }


    //TODO
    // Scafold with bottom bar, fab, doc fab
    //Snackbar
}
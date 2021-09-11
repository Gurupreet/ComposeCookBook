package com.guru.composecookbook.carousel

import androidx.compose.runtime.Immutable
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

@Immutable
internal data class PageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = this@PageData
}
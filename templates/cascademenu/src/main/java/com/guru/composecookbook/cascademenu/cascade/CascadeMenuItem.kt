package com.guru.composecookbook.cascademenu.cascade

import androidx.compose.ui.graphics.vector.ImageVector

class CascadeMenuItem<T : Any> {
    lateinit var id: T

    lateinit var title: String

    var icon: ImageVector? = null

    var parent: CascadeMenuItem<T>? = null

    var children: MutableList<CascadeMenuItem<T>>? = null

    fun hasChildren() = !children.isNullOrEmpty()

    fun hasParent() = parent != null

    fun getChild(id: T) = children?.let { items -> items.find { item -> item.id == id } }
}
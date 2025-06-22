package com.guru.composecookbook.cascademenu.cascade

import androidx.compose.ui.graphics.vector.ImageVector

class CascadeMenuBuilder<T : Any> {
  var menu = CascadeMenuItem<T>()

  fun icon(value: ImageVector) {
    menu.icon = value
  }

  fun item(id: T, title: String, init: (CascadeMenuBuilder<T>.() -> Unit)? = null) {
    val menuBuilder = CascadeMenuBuilder<T>()
    val child =
      menuBuilder.menu.apply {
        this.id = id
        this.title = title
      }
    init?.let { menuBuilder.init() }
    menu.children = menu.children ?: mutableListOf()
    child.parent = menu
    menu.children!!.add(child)
  }
}

fun <T : Any> cascadeMenu(init: CascadeMenuBuilder<T>.() -> Unit): CascadeMenuItem<T> {
  val menuBuilder = CascadeMenuBuilder<T>()
  menuBuilder.init()
  return menuBuilder.menu
}

package com.guru.composecookbook.ui.home.customfling

/**
 * @author https://github.com/iamjosephmj
 */
enum class FlingListViewTypes(type: Int) {
    // Renders list with native scroll behaviour
    NATIVE(0),

    // Renders list with Smooth Scroll behaviour
    SMOOTH(1),

    // Custom values set by user.
    CUSTOM(2),
}
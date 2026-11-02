package com.guru.composecookbook.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Pins the M3 palette builders ([lightColorSchemeFor] / [darkColorSchemeFor]) against the Material
 * 2 palettes: primary keeps the M2 value, and the roles the builders set explicitly are not the
 * Material 3 baseline fallbacks (which used to leak through for every component role that the
 * builders did not set).
 */
class ColorSchemeTest {
  private val m2LightPrimaries =
    mapOf(
      ColorPallet.GREEN to green500,
      ColorPallet.PURPLE to purple,
      ColorPallet.ORANGE to orange500,
      ColorPallet.BLUE to blue500,
    )

  private val m2DarkPrimaries =
    mapOf(
      ColorPallet.GREEN to green500,
      ColorPallet.PURPLE to purple200,
      ColorPallet.ORANGE to orange200,
      ColorPallet.BLUE to blue200,
    )

  @Test
  fun lightSchemesKeepM2PrimaryAndSetContainerRoles() {
    val baseline = lightColorScheme()
    for (pallet in ColorPallet.values().filter { it != ColorPallet.WALLPAPER }) {
      val scheme = lightColorSchemeFor(pallet)
      assertEquals("$pallet light primary", m2LightPrimaries.getValue(pallet), scheme.primary)
      assertNotEquals(
        "$pallet light surfaceContainerHighest must not be the M3 baseline",
        baseline.surfaceContainerHighest,
        scheme.surfaceContainerHighest,
      )
      assertNotEquals(
        "$pallet light onPrimaryContainer must not be the M3 baseline",
        baseline.onPrimaryContainer,
        scheme.onPrimaryContainer,
      )
    }
  }

  @Test
  fun darkSchemesKeepM2PrimaryAndSetContainerRoles() {
    val baseline = darkColorScheme()
    for (pallet in ColorPallet.values().filter { it != ColorPallet.WALLPAPER }) {
      val scheme = darkColorSchemeFor(pallet)
      assertEquals("$pallet dark primary", m2DarkPrimaries.getValue(pallet), scheme.primary)
      assertNotEquals(
        "$pallet dark surfaceContainerHighest must not be the M3 baseline",
        baseline.surfaceContainerHighest,
        scheme.surfaceContainerHighest,
      )
      assertNotEquals(
        "$pallet dark onPrimaryContainer must not be the M3 baseline",
        baseline.onPrimaryContainer,
        scheme.onPrimaryContainer,
      )
    }
  }
}

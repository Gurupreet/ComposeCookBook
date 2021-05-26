package com.guru.composecookbook.ui.home.customfling

import androidx.compose.runtime.Composable
import io.iamjosephmj.flinger.configs.FlingConfiguration
import io.iamjosephmj.flinger.flings.flingBehavior

/**
 * This is how we make a fling behaviour with all values from the settings page.
 *
 * @author https://github.com/iamjosephmj
 */
data class FlingStateStore(
    var type: FlingListViewTypes = FlingListViewTypes.SMOOTH,
    var scrollFriction: Float = 0.008f,
    var absVelocityThreshold: Float = 0f,
    var gravitationalForce: Float = 9.80665f,
    var inchesPerMeter: Float = 39.37f,
    var decelerationFriction: Float = .09f,
    var decelerationRate: Float = 2.358201f,
    var splineInflection: Float = 0.1f,
    var splineStartTension: Float = 0.1f,
    var splineEndTension: Float = 1.0f,
    var numberOfSplinePoints: Int = 100
) {
    companion object {
        var INSTANCE = FlingStateStore()
    }

    @Composable
    fun buildScrollBehaviour() =
        flingBehavior(
            (FlingConfiguration.Builder()
                .scrollViewFriction(INSTANCE.copy().scrollFriction)
                .absVelocityThreshold(INSTANCE.copy().absVelocityThreshold)
                .gravitationalForce(INSTANCE.copy().gravitationalForce)
                .inchesPerMeter(INSTANCE.copy().inchesPerMeter)
                .decelerationFriction(INSTANCE.copy().decelerationFriction)
                .decelerationRate(INSTANCE.copy().decelerationRate)
                .splineInflection(INSTANCE.copy().splineInflection)
                .splineStartTension(INSTANCE.copy().splineStartTension)
                .splineEndTension(INSTANCE.copy().splineEndTension)
                .numberOfSplinePoints(INSTANCE.copy().numberOfSplinePoints)
                .build())
        )

}
package com.guru.composecookbook.ui.home.customfling

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.TestTags
import com.guru.composecookbook.ui.utils.toFloatNum

/**
 * The below set of methods are used to render the settings page page.
 *
 * @author https://github.com/iamjosephmj
 */

/**
 * Entry point.
 */
@Composable
fun RenderSettingsPage(onApplyClick: () -> Unit) {
    // Heading.
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        /*
         * This copies the fling state instance, going forward we will be updating the copy of the
         * fling behaviour.
         */
        val flingStateCopy = FlingStateStore.INSTANCE.copy()

        val radioSelection = remember {
            // default selection is smooth-scroll behaviour.
            mutableStateOf("smooth")
        }
        // Radio buttons used for selecting native, smooth or custom scroll behaviours.
        BuildRadioButton(radioSelection, flingStateCopy)

        // Edit texts for setting the custom scroll behaviour.
        BuildRadioSelectionPage(radioSelection, flingStateCopy, onApplyClick)
    }
}

@Composable
fun BuildRadioSelectionPage(
    radioSelection: MutableState<String>,
    flingCopyStore: FlingStateStore,
    onApplyClick: () -> Unit
) {
    /*
     * Build EditTexts if the user is selecting the custom radio button.
     */
    if (radioSelection.value == "custom" ||
        flingCopyStore.type.ordinal == 2
    ) {
        // Rendering all the EditTexts to get values for the custom fling behaviour.
        BuildEditTexts(onApplyClick, flingCopyStore)
    } else {
        // Else, only draw `Apply` button
        DrawApplyButton(onApplyClick, flingCopyStore)
    }
}

@Composable
fun DrawApplyButton(onApplyClick: () -> Unit, flingCopyStore: FlingStateStore) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                FlingStateStore.INSTANCE = flingCopyStore
                onApplyClick()
            },
            modifier = Modifier
                .size(200.dp, 60.dp)
                .testTag(TestTags.HOME_FLING_SETTINGS_APPLY)
        ) {
            Text(text = "Apply")
        }
    }
}

@Composable
fun BuildRadioButton(
    radioSelection: MutableState<String>,
    flingCopyStore: FlingStateStore
) {
    val options = listOf(
        "native", "smooth", "custom"
    )
    radioGroup(
        radioOptions = options,
        flingCopyStore = flingCopyStore,
        onItemSelection = { selection ->
            flingCopyStore.type = when (options.indexOf(selection)) {
                0 -> FlingListViewTypes.NATIVE
                1 -> FlingListViewTypes.SMOOTH
                2 -> FlingListViewTypes.CUSTOM
                else -> {
                    FlingListViewTypes.SMOOTH
                }
            }
            radioSelection.value = selection

        }
    )
}

@Composable
fun BuildEditTexts(
    /* apply callback */onApplyClick: () -> Unit,
    /* Fling Behaviour clone */ flingCopyStore: FlingStateStore
) {
    // Used to update the scrollFriction EditText
    val scrollFriction = remember { mutableStateOf(flingCopyStore.scrollFriction.toString()) }

    // Used to update the absVelocityThreshold EditText
    val absVelocityThreshold =
        remember { mutableStateOf(flingCopyStore.absVelocityThreshold.toString()) }

    // Used to update the gravitationalForce EditText
    val gravitationalForce =
        remember { mutableStateOf(flingCopyStore.gravitationalForce.toString()) }

    // Used to update the inchesPerMeter EditText
    val inchesPerMeter = remember { mutableStateOf(flingCopyStore.inchesPerMeter.toString()) }

    // Used to update the decelerationFriction EditText
    val decelerationFriction =
        remember { mutableStateOf(flingCopyStore.decelerationFriction.toString()) }

    // Used to update the decelerationRate EditText
    val decelerationRate = remember { mutableStateOf(flingCopyStore.decelerationRate.toString()) }

    // Used to update the splineInflection EditText
    val splineInflection = remember { mutableStateOf(flingCopyStore.splineInflection.toString()) }

    // Used to update the splineStartTension EditText
    val splineStartTension =
        remember { mutableStateOf(flingCopyStore.splineStartTension.toString()) }

    // Used to update the splineEndTension EditText
    val splineEndTension = remember { mutableStateOf(flingCopyStore.splineEndTension.toString()) }

    // Used to update the numberOfSplinePoints EditText
    val numberOfSplinePoints =
        remember { mutableStateOf(flingCopyStore.numberOfSplinePoints.toString()) }

    // This is obtained to dismiss the keyboard in the future.
    val focusManager = LocalFocusManager.current

    // UI spacing.
    Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp))


    // Building EditTexts.
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .testTag(TestTags.HOME_FLING_SETTINGS_EDITABLE)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "Scroll Friction",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = scrollFriction.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            scrollFriction.value = flingCopyStore.scrollFriction.toString()
                        }
                        it.isNotEmpty() -> {
                            scrollFriction.value = it
                            flingCopyStore.scrollFriction = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            scrollFriction.value = ""
                            flingCopyStore.scrollFriction = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "absVelocityThreshold",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = absVelocityThreshold.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            absVelocityThreshold.value =
                                flingCopyStore.absVelocityThreshold.toString()
                        }
                        it.isNotEmpty() -> {
                            absVelocityThreshold.value = it
                            flingCopyStore.absVelocityThreshold = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            absVelocityThreshold.value = ""
                            flingCopyStore.absVelocityThreshold = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp),
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "gravitationalForce",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = gravitationalForce.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            gravitationalForce.value = flingCopyStore.gravitationalForce.toString()
                        }
                        it.isNotEmpty() -> {
                            gravitationalForce.value = it
                            flingCopyStore.gravitationalForce = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            gravitationalForce.value = ""
                            flingCopyStore.gravitationalForce = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }

    }

    Row(horizontalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "inchesPerMeter",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = inchesPerMeter.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            inchesPerMeter.value = flingCopyStore.inchesPerMeter.toString()
                        }
                        it.isNotEmpty() -> {
                            inchesPerMeter.value = it
                            flingCopyStore.inchesPerMeter = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            inchesPerMeter.value = ""
                            flingCopyStore.inchesPerMeter = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "decelerationFriction",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = decelerationFriction.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            decelerationFriction.value =
                                flingCopyStore.decelerationFriction.toString()
                        }
                        it.isNotEmpty() -> {
                            decelerationFriction.value = it
                            flingCopyStore.decelerationFriction = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            decelerationFriction.value = ""
                            flingCopyStore.decelerationFriction = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp),
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "decelerationRate",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = decelerationRate.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            decelerationRate.value = flingCopyStore.decelerationRate.toString()
                        }
                        it.isNotEmpty() -> {
                            decelerationRate.value = it
                            flingCopyStore.decelerationRate = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            decelerationRate.value = ""
                            flingCopyStore.decelerationRate = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }

    }

    Row(horizontalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "splineInflection",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = splineInflection.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            splineInflection.value = flingCopyStore.splineInflection.toString()
                        }
                        it.isNotEmpty() -> {
                            splineInflection.value = it
                            flingCopyStore.splineInflection = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            splineInflection.value = ""
                            flingCopyStore.splineInflection = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "splineStartTension",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = splineStartTension.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            splineStartTension.value = flingCopyStore.splineStartTension.toString()
                        }
                        it.isNotEmpty() -> {
                            splineStartTension.value = it
                            flingCopyStore.splineStartTension = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            splineStartTension.value = ""
                            flingCopyStore.splineStartTension = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp),
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "splineEndTension",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = splineEndTension.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            splineEndTension.value = flingCopyStore.splineEndTension.toString()
                        }
                        it.isNotEmpty() -> {
                            splineEndTension.value = it
                            flingCopyStore.splineEndTension = it.toFloatNum()
                        }
                        it.isEmpty() -> {
                            splineEndTension.value = ""
                            flingCopyStore.splineEndTension = 0f
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }

    }

    Row(horizontalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .width(100.dp)
        ) {
            Text(
                text = "numberOfSplinePoints",
                maxLines = 1,
                style = typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
            OutlinedTextField(
                value = numberOfSplinePoints.value,
                maxLines = 1,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    when {
                        it.filter { data ->
                            data.toString() == "."
                        }.count() > 1 -> {
                            numberOfSplinePoints.value =
                                flingCopyStore.numberOfSplinePoints.toString()
                        }
                        it.isNotEmpty() -> {
                            numberOfSplinePoints.value = it
                            flingCopyStore.numberOfSplinePoints = it.toInt()
                        }
                        it.isEmpty() -> {
                            numberOfSplinePoints.value = ""
                            flingCopyStore.numberOfSplinePoints = 0
                        }

                    }
                },
                modifier = Modifier.width(100.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                FlingStateStore.INSTANCE = flingCopyStore
                onApplyClick()
            },
            modifier = Modifier
                .size(200.dp, 60.dp)
                .testTag(TestTags.HOME_FLING_SETTINGS_APPLY)
        ) {
            Text(text = "apply")
        }
    }
}

/**
 * This method rennders the radio buttons.
 */
@Composable
fun radioGroup(
    radioOptions: List<String> = listOf(),
    title: String = "",
    flingCopyStore: FlingStateStore,
    onItemSelection: (selection: String) -> Unit
): String {
    if (radioOptions.isNotEmpty()) {
        val selectedOption = remember {
            mutableStateOf(radioOptions[flingCopyStore.type.ordinal])
        }

        val onOptionSelected: (selection: String) -> Unit = { selection ->
            selectedOption.value = selection
            onItemSelection(selection)
        }
        // Background CardView
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(5.dp),
                )

                radioOptions.forEach { item ->
                    Row(
                        Modifier
                            .padding(5.dp)
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (item == selectedOption.value),
                            onClick = { onOptionSelected(item) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colors.primary
                            ),
                            modifier = Modifier.testTag(item)
                        )


                        Text(
                            text = item,
                            modifier = Modifier.clickable {
                                onOptionSelected(item)
                            }
                        )
                    }
                }
            }
        }
        return selectedOption.value
    } else {
        return ""
    }
}
package com.enoch02.volumevue.glance.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.text.Text
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.TextStyle
import com.enoch02.volumevue.R
import com.enoch02.volumevue.Stream

@Composable
fun VolumeControlItem(
    modifier: GlanceModifier = GlanceModifier,
    stream: Stream,
    value: Float,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    val icon = when (stream) {
        Stream.MUSIC -> R.drawable.music_note_24px
        Stream.ALARM -> R.drawable.alarm_24px
        Stream.RINGTONE -> R.drawable.ring_volume_24px
        Stream.NOTIFICATION -> R.drawable.notifications_24px
        Stream.VOICE_CALL -> R.drawable.call_24px
//        Stream.SYSTEM -> R.drawable.smartphone_24px
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleIconButton(
            imageProvider = ImageProvider(icon),
            contentDescription = null,
            onClick = onDecrement
        )

        Text(
            text = "${(value * 100).toInt()}",
            style = TextStyle(color = GlanceTheme.colors.onBackground),
            modifier = GlanceModifier
                .padding(horizontal = 8.dp)
        )

        LinearProgressIndicator(
            value,
            modifier = GlanceModifier.padding(vertical = 4.dp).defaultWeight()
        )

        Spacer(modifier = GlanceModifier.width(4.dp))

        CircleIconButton(
            imageProvider = ImageProvider(R.drawable.baseline_remove_24),
            contentDescription = null,
            onClick = onDecrement
        )

        CircleIconButton(
            imageProvider = ImageProvider(R.drawable.baseline_add_24),
            contentDescription = null,
            onClick = onIncrement
        )
    }
}
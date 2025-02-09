package com.enoch02.volumevue.glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import com.enoch02.volumevue.Stream
import com.enoch02.volumevue.VolumeController
import com.enoch02.volumevue.glance.components.VolumeControlItem
import com.enoch02.volumevue.ui.theme.WidgetColorScheme

class VolumeVueWidget : GlanceAppWidget() {
    companion object {
        private val NORMAL = DpSize(300.dp, 100.dp)
        private val CHUNGUS = DpSize(450.dp, 300.dp)
        private val MEGA = DpSize(700.dp, 350.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(
            NORMAL,
            CHUNGUS,
            MEGA
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(colors = WidgetColorScheme.colors) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = GlanceModifier
                        .background(GlanceTheme.colors.background)
                        .fillMaxSize()
                ) {
                    ContentView(modifier = GlanceModifier.fillMaxSize())
                }
            }
        }
    }

    @Composable
    private fun ContentView(modifier: GlanceModifier = GlanceModifier) {
        val context = LocalContext.current
        val volumeController = remember {
            VolumeController(context)
        }

        LazyColumn(modifier = modifier.padding(8.dp)) {
            item {
                var currentMusicVolume by remember {
                    mutableFloatStateOf(0f)
                }

                SideEffect {
                    currentMusicVolume = volumeController.getVolumeAsFraction(Stream.MUSIC)
                }

                VolumeControlItem(
                    modifier = GlanceModifier.fillMaxWidth(),
                    stream = Stream.MUSIC,
                    value = currentMusicVolume,
                    onIncrement = {
                        volumeController.increaseVolume(Stream.MUSIC)
                        currentMusicVolume = volumeController.getVolumeAsFraction(Stream.MUSIC)
                    },
                    onDecrement = {
                        volumeController.decreaseVolume(Stream.MUSIC)
                        currentMusicVolume = volumeController.getVolumeAsFraction(Stream.MUSIC)
                    }
                )
            }

            item {
                var currentAlarmVolume by remember {
                    mutableFloatStateOf(0f)
                }

                SideEffect {
                    currentAlarmVolume = volumeController.getVolumeAsFraction(Stream.ALARM)
                }

                VolumeControlItem(
                    modifier = GlanceModifier.fillMaxWidth(),
                    stream = Stream.ALARM,
                    value = currentAlarmVolume,
                    onIncrement = {
                        volumeController.increaseVolume(Stream.ALARM)
                        currentAlarmVolume = volumeController.getVolumeAsFraction(Stream.ALARM)
                    },
                    onDecrement = {
                        volumeController.decreaseVolume(Stream.ALARM)
                        currentAlarmVolume = volumeController.getVolumeAsFraction(Stream.ALARM)
                    }
                )
            }

            item {
                var currentRingtoneVolume by remember {
                    mutableFloatStateOf(0f)
                }

                SideEffect {
                    currentRingtoneVolume = volumeController.getVolumeAsFraction(Stream.RINGTONE)
                }

                VolumeControlItem(
                    modifier = GlanceModifier.fillMaxWidth(),
                    stream = Stream.RINGTONE,
                    value = currentRingtoneVolume,
                    onIncrement = {
                        volumeController.increaseVolume(Stream.RINGTONE)
                        currentRingtoneVolume =
                            volumeController.getVolumeAsFraction(Stream.RINGTONE)
                    },
                    onDecrement = {
                        volumeController.decreaseVolume(Stream.RINGTONE)
                        currentRingtoneVolume =
                            volumeController.getVolumeAsFraction(Stream.RINGTONE)
                    }
                )
            }

            item {
                var currentNotificationVolume by remember {
                    mutableFloatStateOf(0f)
                }

                SideEffect {
                    currentNotificationVolume =
                        volumeController.getVolumeAsFraction(Stream.NOTIFICATION)
                }

                VolumeControlItem(
                    modifier = GlanceModifier.fillMaxWidth(),
                    stream = Stream.NOTIFICATION,
                    value = currentNotificationVolume,
                    onIncrement = {
                        volumeController.increaseVolume(Stream.NOTIFICATION)
                        currentNotificationVolume =
                            volumeController.getVolumeAsFraction(Stream.NOTIFICATION)
                    },
                    onDecrement = {
                        volumeController.decreaseVolume(Stream.NOTIFICATION)
                        currentNotificationVolume =
                            volumeController.getVolumeAsFraction(Stream.NOTIFICATION)
                    }
                )
            }

            item {
                var currentVoiceCallVolume by remember {
                    mutableFloatStateOf(0f)
                }

                SideEffect {
                    currentVoiceCallVolume = volumeController.getVolumeAsFraction(Stream.VOICE_CALL)
                }

                VolumeControlItem(
                    modifier = GlanceModifier.fillMaxWidth(),
                    stream = Stream.VOICE_CALL,
                    value = currentVoiceCallVolume,
                    onIncrement = {
                        volumeController.increaseVolume(Stream.VOICE_CALL)
                        currentVoiceCallVolume =
                            volumeController.getVolumeAsFraction(Stream.VOICE_CALL)
                    },
                    onDecrement = {
                        volumeController.decreaseVolume(Stream.VOICE_CALL)
                        currentVoiceCallVolume =
                            volumeController.getVolumeAsFraction(Stream.VOICE_CALL)
                    }
                )
            }

//            item {
//                VolumeControlItem(
//                    modifier = GlanceModifier.fillMaxWidth(),
//                    stream = Stream.SYSTEM,
//                    value = volumeController.getVolumeAsFraction(Stream.SYSTEM),
//                    onIncrement = {
//                        volumeController.increaseVolume(Stream.SYSTEM)
//                    },
//                    onDecrement = {
//                        volumeController.decreaseVolume(Stream.SYSTEM)
//                    }
//                )
//            }
        }
    }
}

package com.enoch02.volumevue

import android.content.Context
import android.media.AudioManager
import java.math.BigDecimal
import java.math.RoundingMode

class VolumeController(context: Context) {
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private fun getAMStream(of: Stream): Int {
        return when (of) {
            Stream.MUSIC -> AudioManager.STREAM_MUSIC
            Stream.ALARM -> AudioManager.STREAM_ALARM
            Stream.RINGTONE -> AudioManager.STREAM_RING
            Stream.NOTIFICATION -> AudioManager.STREAM_NOTIFICATION
            Stream.VOICE_CALL -> AudioManager.STREAM_VOICE_CALL
//            Stream.SYSTEM -> AudioManager.STREAM_SYSTEM
        }
    }

    fun getVolumeAsFraction(of: Stream): Float {
        val streamType = getAMStream(of)

        val currentVolume = audioManager.getStreamVolume(streamType).toFloat()
        val maxVolume = audioManager.getStreamMaxVolume(streamType).toFloat()

        return if (maxVolume > 0) {
            val value = currentVolume / maxVolume
            val roundedValue = BigDecimal(value.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
                .toFloat()

            roundedValue
        } else {
            0f
        }
    }

    fun increaseVolume(of: Stream) {
        val stream = getAMStream(of)

        audioManager.adjustStreamVolume(stream, AudioManager.ADJUST_RAISE, 0)
    }

    fun decreaseVolume(of: Stream) {
        val stream = getAMStream(of)

        audioManager.adjustStreamVolume(stream, AudioManager.ADJUST_LOWER, 0)
    }
}

enum class Stream {
    MUSIC, ALARM, RINGTONE, NOTIFICATION, VOICE_CALL, /*SYSTEM*/
}
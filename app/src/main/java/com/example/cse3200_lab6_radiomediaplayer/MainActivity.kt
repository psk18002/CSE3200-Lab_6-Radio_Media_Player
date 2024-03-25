package com.example.cse3200_lab6_radiomediaplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cse3200_lab6_radiomediaplayer.model.RadioStations
import kotlinx.coroutines.*

lateinit var mediaPlayer: MediaPlayer
val radioStations = RadioStations()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer()
        setContent {
            Radio(mediaPlayer)
        }
    }
}

fun mediaPlayerSetUp(mediaPlayer: MediaPlayer) {
    if(mediaPlayer.isPlaying) {
        mediaStop(mediaPlayer)
    }
    mediaPlayer.apply{
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setDataSource(radioStations.getURL())
        prepare()
    }
}

fun mediaPlay(mediaPlayer: MediaPlayer) {
    mediaPlayer.start()
}

fun mediaPause(mediaPlayer: MediaPlayer) {
    mediaPlayer.pause()
}

fun mediaStop(mediaPlayer: MediaPlayer) {
    mediaPlayer.stop()
    mediaPlayer.reset()
}

fun mediaRestart(mediaPlayer: MediaPlayer) {
    mediaPlayerSetUp(mediaPlayer)
    mediaPlay(mediaPlayer)
}

fun mediaRadioNext(mediaPlayer: MediaPlayer) {
    radioStations.incrementNextURL()
    mediaRestart(mediaPlayer)
}

@Composable
fun Radio(mediaPlayer: MediaPlayer) {

    mediaPlayerSetUp(mediaPlayer)
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = {mediaPlay(mediaPlayer)}) {
            Text("Play")
        }
        Button(onClick = {mediaPause(mediaPlayer)}) {
            Text("Pause")
        }
        Button(onClick = {mediaStop(mediaPlayer)}) {
            Text("Stop")
        }
        Button(onClick = { mediaRadioNext(mediaPlayer) }) {
            Text("Next Radio Station")
        }
    }

}
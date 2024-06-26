package com.clickzin.androidnativedemoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clickzin.android.ClickzinTracker
import com.clickzin.androidnativedemoapp.ui.theme.ClickzinAndroidNativeDemoAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var clickzinTracker: ClickzinTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickzinTracker = ClickzinTracker(this@MainActivity, "Key Provided By Your Admin")
        // clickzinTracker.startTracking() // To be used if no callback on conversion tracked is needed.
        clickzinTracker.startTracking { uid, source, eventId ->
            Log.d("tag", "\"On conversion tracked $uid $source $eventId")
        }
        setContent {
            ClickzinAndroidNativeDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Event", clickzinTracker)
                }
            }
        }


    }
}

@Composable
fun Greeting(name: String, clickzinTracker: ClickzinTracker, modifier: Modifier = Modifier) {
    Text(
        text = "Test $name",
        modifier = modifier.clickable {
            // clickzinTracker.trackEvent("register") // To be used if no callback on conversion tracked is needed.

            // To be used when conversion tracking callback is needed.
            clickzinTracker.trackEvent("register") { uid, source, eventId ->
                Log.d("tag", "\"On conversion tracked $uid $source $eventId")
            }
        }
    )
}


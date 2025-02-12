package fr.unilim.iut.shifumi

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.unilim.iut.shifumi.ui.theme.ShifumiTheme
import fr.unilim.iut.shifumi.utilities.Gyroscope

class Game : ComponentActivity(), SensorEventListener {
    var gyroscope: Gyroscope? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        gyroscope = Gyroscope(this, this)
        setContent {
            ShifumiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInfos(gyroscope!!.positionInfos, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) { }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onResume() {
        super.onResume()
        gyroscope?.startListening()
    }

    override fun onPause() {
        super.onPause()
        gyroscope?.stopListening()
    }
}

@Composable
fun TextInfos(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}
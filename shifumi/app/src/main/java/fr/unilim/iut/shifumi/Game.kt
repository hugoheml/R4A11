package fr.unilim.iut.shifumi

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.textservice.TextInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.unilim.iut.shifumi.ui.theme.ShifumiTheme
import fr.unilim.iut.shifumi.utilities.Gyroscope

class Game : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var gyroscopeSensor: Sensor? = null
    val gyroscope = Gyroscope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        setContent {
            ShifumiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInfos(gyroscope.positionInfos, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        gyroscope.updatePosition(event)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}

@Composable
fun TextInfos(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}
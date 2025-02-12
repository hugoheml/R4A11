package fr.unilim.iut.shifumi.utilities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Gyroscope(context: Context, listener: SensorEventListener?) : SensorEventListener {
    private var x: Float = 0.0f
    private var y: Float = 0.0f
    private var z: Float = 0.0f
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    var positionInfos by mutableStateOf("Aucune données pour l'instant")
        private set

    init {
        this.sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    fun updatePosition(event: SensorEvent?) {
        if (event != null) {
            x = event.values[0]
            y = event.values[1]
            z = event.values[2]
        }

        positionInfos = "X: $x\nY: $y\nZ: $z"
    }

    override fun onSensorChanged(event: SensorEvent?) {
        this.updatePosition(event)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun startListening() {
        sensor?.let {
            sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        } ?: run {
            positionInfos = "Gyroscope non disponible"
        }
    }

    fun stopListening() {
        sensorManager?.unregisterListener(this)
    }
}
package fr.unilim.iut.shifumi.utilities

import android.hardware.SensorEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Gyroscope {
    private var x: Float = 0.0f
    private var y: Float = 0.0f
    private var z: Float = 0.0f
    var positionInfos by mutableStateOf("Aucune données pour l'instant")
        private set

    fun updatePosition(event: SensorEvent?) {
        if (event != null) {
            x = event.values[0]
            y = event.values[1]
            z = event.values[2]
        }

        positionInfos = "X: $x\nY: $y\nZ: $z"
    }
}
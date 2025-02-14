package fr.unilim.iut.shifumi

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.unilim.iut.shifumi.ui.theme.ShifumiTheme
import fr.unilim.iut.shifumi.utilities.Gyroscope

class MainActivity : ComponentActivity(), SensorEventListener {
    var gyroscope: Gyroscope? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        gyroscope = Gyroscope(this, this)
        setContent {
            ShifumiTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = colorResource(id = R.color.primary)
                ) {
                    App_Navigation(gyroscope!!)
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
fun App_Navigation(gyroscope: Gyroscope) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(navController = navController)
        }
        composable("game-page") {
            GamePage(navController, gyroscope)
        }
    }
}

@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameTitle()
        GameLogo()
        Spacer(modifier = Modifier.height(24.dp));
        Button(onClick = {
            navController.navigate("game-page") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.secondary),
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Jouer")
        }
    }
}

@Composable
fun GameTitle() {
    Text(
        text = "Shifumi",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun GameLogo() {
    Image(
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "Shifumi logo"
    )
}
package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tp2.ui.theme.TP2Theme
import com.example.tp2.utilities.AgeCalculator

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val intent = intent

                        if (intent != null) {
                            val username = intent.getStringExtra(USERNAME)
                            val birthYear = intent.getIntExtra(BIRTH_YEAR, 2000)

                            val age = AgeCalculator.calculateAge(birthYear)

                            if (username != null) {
                                Text("Hello $username, vous avez $age ans")
                            }
                        }
                    }
                }
            }
        }
    }
}
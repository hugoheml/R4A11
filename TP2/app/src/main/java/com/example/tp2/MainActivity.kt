package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp2.ui.theme.TP2Theme

const val USERNAME = "username"
const val BIRTH_YEAR = "birthYear"

class MainActivity : ComponentActivity() {
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
                        var inputText by remember { mutableStateOf("") }
                        var birthYear by remember { mutableIntStateOf(2000) }

                        TitleText(name = "Bienvenue")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(inputText)
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            label = { Text("Saisir votre nom") }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = birthYear.toString(),
                            onValueChange = { birthYear = it.toIntOrNull() ?: 0 },
                            label = { Text("Saisir votre année de naissance") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Button(
                            onClick = {
                                if (inputText.isNotEmpty()) {
                                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                    intent.putExtra(USERNAME, inputText)
                                    intent.putExtra(BIRTH_YEAR, birthYear)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this@MainActivity, "Veuillez saisir un nom", Toast.LENGTH_SHORT).show()
                                }
                            },
                            content = {
                                Text("Valider")
                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun TitleText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP2Theme {
        TitleText("Androidddddqsqsqsd")
    }
}

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val EXTRA_TEXT = "text_to_dosplay";

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val premierButton: Button = findViewById(R.id.validateButton);
        val inputEditText: EditText = findViewById(R.id.premierInput);
        val label: TextView = findViewById(R.id.premierLabel);
        val mainLayout: ConstraintLayout = findViewById(R.id.main);

        var buttonClicked: Boolean = false;

        premierButton.setOnClickListener {
            val inputText = inputEditText.getText();

            if (inputText.equals("ajouter nouveau textView")) {
                val deuxiemeTextView: TextView = TextView(this);
                deuxiemeTextView.text = inputText;
                mainLayout.addView(deuxiemeTextView);
            } else {
                label.text = inputText;
            }

            buttonClicked = true
        }

        val nextButton: Button = findViewById(R.id.next);

        nextButton.setOnClickListener {
            if (buttonClicked) {
                val intent = Intent(this, MainActivity2::class.java);
                intent.putExtra(EXTRA_TEXT, inputEditText.text.toString());
                startActivity(intent);
            }

        }
    }
}
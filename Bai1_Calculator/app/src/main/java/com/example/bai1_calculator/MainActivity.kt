package com.example.bai1_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var value1 = 0.0f
    private var pendingOp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        buttons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val t = (it as Button).text.toString()
                if (tvDisplay.text == "0") tvDisplay.text = t
                else tvDisplay.text = tvDisplay.text.toString() + t
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOp("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOp("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOp("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOp("/") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calc() }
        findViewById<Button>(R.id.btnCE).setOnClickListener { tvDisplay.text = "0" }
        findViewById<Button>(R.id.btnC).setOnClickListener { tvDisplay.text = "0" }
        findViewById<Button>(R.id.btnBS).setOnClickListener { backspace() }
        findViewById<Button>(R.id.btnDot).setOnClickListener { dot() }
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { sign() }
    }

    private fun setOp(op: String) {
        value1 = tvDisplay.text.toString().toFloat()
        pendingOp = op
        tvDisplay.text = "0"
    }

    private fun calc() {
        val value2 = tvDisplay.text.toString().toFloat()
        val result = when (pendingOp) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "×" -> value1 * value2
            "/" -> value1 / value2
            else -> value2
        }
        tvDisplay.text = result.toString()
    }

    private fun backspace() {
        val text = tvDisplay.text.toString()
        tvDisplay.text = if (text.length > 1) text.dropLast(1) else "0"
    }

    private fun dot() {
        if (!tvDisplay.text.contains(".")) {
            tvDisplay.text = tvDisplay.text.toString() + "."
        }
    }

    private fun sign() {
        val value = tvDisplay.text.toString().toFloat()
        tvDisplay.text = (-value).toString()
    }
}

package com.example.bai2_data_entry

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etFirst: EditText
    private lateinit var etLast: EditText
    private lateinit var etBirthday: EditText
    private lateinit var etAddress: EditText
    private lateinit var etEmail: EditText
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var calendarView: CalendarView
    private lateinit var btnSelect: Button
    private lateinit var btnRegister: Button
    private lateinit var cbAgree: CheckBox

    private var calendarVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirst = findViewById(R.id.etFirst)
        etLast = findViewById(R.id.etLast)
        etBirthday = findViewById(R.id.etBirthday)
        etAddress = findViewById(R.id.etAddress)
        etEmail = findViewById(R.id.etEmail)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        calendarView = findViewById(R.id.calendarView)
        btnSelect = findViewById(R.id.btnSelect)
        btnRegister = findViewById(R.id.btnRegister)
        cbAgree = findViewById(R.id.cbAgree)

        calendarView.visibility = View.GONE

        // Toggle CalendarView
        btnSelect.setOnClickListener {
            calendarVisible = !calendarVisible
            calendarView.visibility = if (calendarVisible) View.VISIBLE else View.GONE
        }

        // Update Birthday when selecting date
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            etBirthday.setText(date)

            // Hide calendar when date selected
            calendarView.visibility = View.GONE
            calendarVisible = false
        }

        btnRegister.setOnClickListener {
            validateForm()
        }
    }

    private fun validateForm() {
        var isValid = true

        fun checkEmpty(et: EditText): Boolean {
            return if (et.text.toString().trim().isEmpty()) {
                et.setBackgroundColor(Color.parseColor("#FFCDD2")) // đỏ nhạt
                false
            } else {
                et.setBackgroundColor(Color.WHITE)
                true
            }
        }

        isValid = checkEmpty(etFirst) && isValid
        isValid = checkEmpty(etLast) && isValid
        isValid = checkEmpty(etBirthday) && isValid
        isValid = checkEmpty(etAddress) && isValid
        isValid = checkEmpty(etEmail) && isValid

        // Gender check
        if (!radioMale.isChecked && !radioFemale.isChecked) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Checkbox
        if (!cbAgree.isChecked) {
            Toast.makeText(this, "Please agree to Terms of Use", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (isValid) {
            Toast.makeText(this, "Register Success!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show()
        }
    }
}

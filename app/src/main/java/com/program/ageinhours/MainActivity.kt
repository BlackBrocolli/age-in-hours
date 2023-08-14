package com.program.ageinhours

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var textViewSelectedDate: TextView? = null
    private var textViewCalculatedHours: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewCalculatedHours = findViewById(R.id.textViewCalculatedHours)

        val buttonSelectDate = findViewById<Button>(R.id.buttonSelectDate)
        buttonSelectDate.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                textViewSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInHours = theDate.time / 3600000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInHours = currentDate.time / 3600000
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        textViewCalculatedHours?.text = differenceInHours.toString()
                    }
                }
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}
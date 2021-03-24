package com.example.minutesofage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view);
        }

    }

    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance();
        val year = myCalender.get(Calendar.YEAR);
        val month = myCalender.get(Calendar.MONTH);
        val day = myCalender.get(Calendar.DAY_OF_MONTH);
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear";
            val selectedDateTextView = findViewById<TextView>(R.id.selectedDate);
            val ageInMinutes = findViewById<TextView>(R.id.ageInMinute);
            selectedDateTextView.setText(selectedDate);

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

            val theDate = sdf.parse(selectedDate);

            val selectedDateInMinutes = theDate!!.time / 60000;
            val currDate = sdf.parse(sdf.format(System.currentTimeMillis()));
            val currDateInMinutes = currDate!!.time / 60000;

            val diffInMinutes = currDateInMinutes - selectedDateInMinutes;

            ageInMinutes.setText(diffInMinutes.toString());
        },
            year, month, day);

        dpd.datePicker.setMaxDate(Date().time - 86400000);
        dpd.show();
    }



}
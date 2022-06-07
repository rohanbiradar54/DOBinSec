package com.example.dobinsec

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var date : TextView?=null
    private  var result : TextView?=null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val click1: Button=findViewById(R.id.button_date)
        date=findViewById(R.id.date)
        result=findViewById(R.id.res)

        click1.setOnClickListener {
            convert()
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun convert(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val  selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            date?.setText(selectedDate)
            Toast.makeText(this,"Date is $selectedDayOfMonth month is ${selectedMonth+1} year is $selectedYear",Toast.LENGTH_LONG).show()

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            val selectedDateInSec=theDate.time/1000

            val current=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentInSec=current.time/1000
            
            val final=currentInSec-selectedDateInSec
            result?.text=final.toString()

        },
            year,
            month,
            day
        ).show()

    }


}
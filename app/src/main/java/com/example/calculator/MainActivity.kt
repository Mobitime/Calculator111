package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val editTextTime1 = findViewById<EditText>(R.id.editText1)
        val editTextTime2 = findViewById<EditText>(R.id.editText2)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val textViewTV = findViewById<TextView>(R.id.textViewTV)
        button1.setOnClickListener{
            val time1 = editTextTime1.text.toString()
            val time2 = editTextTime2.text.toString()
            val result = addTime(time1, time2)
            textViewTV.text = result
        }
        button2.setOnClickListener{
            val time1 = editTextTime1.text.toString()
            val time2 = editTextTime2.text.toString()
            val result = substractTime(time1, time2)
            textViewTV.text = result
        }
        }
    private fun parseTimeString(timeString: String): Int{
        var totalSeconds = 0
        val regex = Regex("""(\d+)([hms])""")
        val matches = regex.findAll(timeString)
        for (match in matches){
            val value = match.groupValues[1].toInt()
            val unit = match.groupValues[2]
            when (unit) {
                "h" -> totalSeconds += value * 3600
                "m" -> totalSeconds += value * 60
                "s" -> totalSeconds += value
            }
        }
    return totalSeconds
    }
    private fun formatTime(seconds: Int): String{
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        val  result = StringBuilder()
        if (hours > 0 ){
            result.append("${hours}h")
        }
        if (minutes > 0 ){
            result.append("${minutes}m")
        }
        if (remainingSeconds > 0 || result.isEmpty()){
            result.append("${remainingSeconds}s")
        }
return result.toString()
    }
    private fun addTime(time1: String, time2: String): String{
        val seconds1 = parseTimeString(time1)
        val seconds2 = parseTimeString(time2)
        val totalSeconds = seconds1 + seconds2
        return formatTime(totalSeconds)
    }
    private fun substractTime(time1: String, time2: String): String{
        val seconds1 = parseTimeString(time1)
        val seconds2 = parseTimeString(time2)
        val totalSeconds = seconds1 - seconds2
        val positiveSecond = if (totalSeconds < 0) 0 else totalSeconds

        return formatTime(positiveSecond)
    }
}

package com.razorpayassignment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.razorpayassignment.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        val map = intent.getSerializableExtra("map") as? ArrayList<String>
        if (map != null) {
            setLabel(map)
        }
    }

    private fun setLabel(map:ArrayList<String>) {
        for (it in map) {
            val labelTextView = TextView(this)
            labelTextView.text = it
            labelTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            labelTextView.textSize = 16f
            labelTextView.setTextColor(Color.BLACK)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(50, 20, 50, 20)
            labelTextView.layoutParams = params
            binding.linearLayout.addView(labelTextView)
        }

    }
}
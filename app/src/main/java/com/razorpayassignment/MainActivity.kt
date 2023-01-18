package com.razorpayassignment

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.lifecycle.ViewModelProvider
import com.razorpayassignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleObserver()
        mainViewModel.fetchCustomUI()
    }

    private fun handleObserver() {
        mainViewModel.successLiveData.observe(this) {
            if (it != null) {
                setHeading(it)
                for (item in it.uidata) {
                    when (item.uitype) {
                        "label" -> {
                            setLabel(item)
                        }
                        "edittext" -> {
                            setEditText(item)
                        }
                        "button" -> {
                            setButton(item)
                        }
                    }
                }
            }
        }

        mainViewModel.logoLiveData.observe(this) {
            if (it != null) {
                setLogo(it)
            }
        }
    }


    private fun setLogo(data: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        val imageView = ImageView(this)
        imageView.setImageBitmap(bitmap)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 50, 50, 20)
        imageView.layoutParams = params
        binding.parentLayout.addView(imageView,0)
    }

    private fun setHeading(it: CustomUI) {
        val headingText = TextView(this)
        headingText.text = it.heading_text
        headingText.textSize = 16f
        headingText.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 50, 50, 50)
        headingText.layoutParams = params

        binding.parentLayout.addView(headingText)
    }

    private fun setLabel(item: Uidata) {
        val labelTextView = TextView(this)
        labelTextView.text = item.value
        labelTextView.tag = item.key
        labelTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        labelTextView.textSize = 16f
        labelTextView.setTextColor(Color.BLACK)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 20, 50, 20)
        labelTextView.layoutParams = params
        binding.parentLayout.addView(labelTextView)
    }

    private fun setEditText(item: Uidata) {
        val editTextView = EditText(this)
        editTextView.tag = item.key
        editTextView.hint = item.hint
        editTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        editTextView.textSize = 16f
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 20, 50, 20)
        editTextView.layoutParams = params
        binding.parentLayout.addView(editTextView)
    }

    private fun setButton(item: Uidata) {
        val button = Button(this)
        button.text = item.value
        button.textAlignment = View.TEXT_ALIGNMENT_CENTER
        button.textSize = 16f
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 20, 50, 20)
        button.layoutParams = params
        button.setOnClickListener {
            setButtonClick()
        }
        binding.parentLayout.addView(button)
    }

    private fun setButtonClick(){
        val intent = Intent(this,SecondActivity::class.java)
        val map = ArrayList<String>()
         for (view in binding.parentLayout.allViews){
             when(view){
                 is TextView->{
                     if(view.tag!= null)
                         map.add(view.text.toString())
                 }

                 is EditText->{
                     if(view.tag!= null) {
                         map.add(view.text.toString())
                     }
                 }
             }
         }
        intent.putExtra("map",map)
        startActivity(intent)
    }

}
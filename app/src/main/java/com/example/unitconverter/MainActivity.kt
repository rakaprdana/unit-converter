package com.example.unitconverter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    //Pendefinisian view referenced
    private val buttonCelcius by lazy { findViewById<Button>(R.id.btn_celcius) }
    private val buttonFahrenheit by lazy { findViewById<Button>(R.id.btn_fahrenheit) }
    private val tvInput by lazy { findViewById<TextView>(R.id.tv_input) }
    private val tvOutput by lazy { findViewById<TextView>(R.id.tv_output) }
    private val buttonSwap by lazy { findViewById<Button>(R.id.btn_swap) }

    private val btnOne by lazy { findViewById<Button>(R.id.btn_1) }
    private val btnTwo by lazy { findViewById<Button>(R.id.btn_2) }
    private val btnThree by lazy { findViewById<Button>(R.id.btn_3) }
    private val btnClear by lazy { findViewById<Button>(R.id.btn_clear) }

    private val btnFour by lazy { findViewById<Button>(R.id.btn_4) }
    private val btnFive by lazy { findViewById<Button>(R.id.btn_5) }
    private val btnSix by lazy { findViewById<Button>(R.id.btn_6) }
    private val btnPlus by lazy { findViewById<Button>(R.id.btn_plus) }

    private val btnSeven by lazy { findViewById<Button>(R.id.btn_7) }
    private val btnEight by lazy { findViewById<Button>(R.id.btn_8) }
    private val btnNine by lazy { findViewById<Button>(R.id.btn_9) }
    private val btnMinus by lazy { findViewById<Button>(R.id.btn_minus) }

    private val btnZero by lazy { findViewById<Button>(R.id.btn_0) }
    private val btnKoma by lazy { findViewById<Button>(R.id.btn_koma) }
    private val btnConvert by lazy { findViewById<Button>(R.id.btn_convert) }

    //define variable for view model
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeInput()
        observeOutput()


        btnOne.setButtonClickListener("1")
        btnTwo.setButtonClickListener("2")
        btnThree.setButtonClickListener("3")
        btnClear.setOnClickListener {
            viewModel.clearInput()
        }

        btnFour.setButtonClickListener("4")
        btnFive.setButtonClickListener("5")
        btnSix.setButtonClickListener("6")
        btnPlus.setButtonClickListener("+")

        btnSeven.setButtonClickListener("7")
        btnEight.setButtonClickListener("8")
        btnNine.setButtonClickListener("9")
        btnMinus.setButtonClickListener("-")

        btnZero.setButtonClickListener("0")
        btnKoma.setButtonClickListener(".")


        buttonSwap.setOnClickListener {
            animateInputIndicator()
            viewModel.switchInputUnit()
            viewModel.resetInputOutput()
        }

        btnConvert.setOnClickListener {
            viewModel.convertUnit()
        }
    }

    // extension function for setOnClickListener
    private fun Button.setButtonClickListener(value: String) {
        setOnClickListener {
            viewModel.setInput(value)
        }
    }

    // function for display input & output
    private fun observeInput() {
        viewModel.input.observe(this) { input ->
            tvInput.text = input
        }
    }

    private fun observeOutput() {
        viewModel.output.observe(this) { output ->
            tvOutput.text = output
        }
    }

    // function for animation input indicator
    private fun animateInputIndicator() {
        //get initial position => ambil posisi awal
        val initialY1 = buttonCelcius.y
        val initialY2 = buttonFahrenheit.y

        //animation for celcius
        buttonCelcius.animate()
            .alpha(0f) //meredupkan visibilty btn
            .y(initialY2) //tujuan baru
            .setDuration(300) //adjust duration as needed
            .withEndAction {
                buttonCelcius.y = initialY2
                buttonCelcius.alpha = 1f
            }

        //animation for fahrenheit
        buttonFahrenheit.animate()
            .alpha(0f)
            .y(initialY1)
            .setDuration(300)
            .withEndAction {
                buttonFahrenheit.y = initialY1
                buttonFahrenheit.alpha = 1f
            }
    }

}
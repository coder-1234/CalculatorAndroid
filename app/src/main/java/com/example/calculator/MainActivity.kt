package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtInput: TextView
    private var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_constraint)
        txtInput = findViewById(R.id.result)

        val button0 = findViewById<Button>(R.id.btnZero)
        val button1 = findViewById<Button>(R.id.btnOne)
        val button2 = findViewById<Button>(R.id.btnTwo)
        val button3 = findViewById<Button>(R.id.btnThree)
        val button4 = findViewById<Button>(R.id.btnFour)
        val button5 = findViewById<Button>(R.id.btnFive)
        val button6 = findViewById<Button>(R.id.btnSix)
        val button7 = findViewById<Button>(R.id.btnSeven)
        val button8 = findViewById<Button>(R.id.btnEight)
        val button9 = findViewById<Button>(R.id.btnNine)
        val buttonOpen = findViewById<Button>(R.id.btnOpenBracket)
        val buttonClose = findViewById<Button>(R.id.btnCloseBracket)
        val buttonBackspace = findViewById<Button>(R.id.btnBackspace)
        val buttonPlus = findViewById<Button>(R.id.btnAdd)
        val buttonMinus = findViewById<Button>(R.id.btnSub)
        val buttonMultiply = findViewById<Button>(R.id.btnMultiply)
        val buttonDivide = findViewById<Button>(R.id.btnDiv)
        val buttonClear = findViewById<Button>(R.id.btnCLR)
        val buttonEqual = findViewById<Button>(R.id.btnEqual)
        val buttonDecimal = findViewById<Button>(R.id.btnDecimal)

        button0.setOnClickListener{ view -> onDigitClick(view) }
        button1.setOnClickListener{ view -> onDigitClick(view) }
        button2.setOnClickListener{ view -> onDigitClick(view) }
        button3.setOnClickListener{ view -> onDigitClick(view) }
        button4.setOnClickListener{ view -> onDigitClick(view) }
        button5.setOnClickListener{ view -> onDigitClick(view) }
        button6.setOnClickListener{ view -> onDigitClick(view) }
        button7.setOnClickListener{ view -> onDigitClick(view) }
        button8.setOnClickListener{ view -> onDigitClick(view) }
        button9.setOnClickListener{ view -> onDigitClick(view) }
        buttonOpen.setOnClickListener{ view -> onOperator(view) }
        buttonClose.setOnClickListener{ view -> onOperator(view) }
        buttonBackspace.setOnClickListener{ onBackspace() }
        buttonPlus.setOnClickListener{ view -> onOperator(view) }
        buttonMinus.setOnClickListener{ view -> onOperator(view) }
        buttonMultiply.setOnClickListener{ view -> onOperator(view) }
        buttonDivide.setOnClickListener{ view -> onOperator(view) }
        buttonClear.setOnClickListener{ onClear() }
        buttonEqual.setOnClickListener{ onEqual() }
        buttonDecimal.setOnClickListener{ onDecimalPoint() }
    }


    fun onDigitClick(view: View) {
        txtInput.append((view as Button).text)
        lastDot = false
    }

    private fun onDecimalPoint() {
        if(!lastDot){
            txtInput.append(".")
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if(txtInput.text=="" || !txtInput.text.endsWith(' ')){
            txtInput.append(' '.toString())
            txtInput.append((view as Button).text)
            txtInput.append(' '.toString())
        }
        else if((view as Button).text=="("){
            txtInput.append(view.text)
            txtInput.append(' '.toString())
        }
        else{
            txtInput.append((view).text)
        }
        lastDot = false
    }

    fun onClear() {
        this.txtInput.text = ""
        lastDot = false
    }

    private fun onBackspace() {
        if(this.txtInput.text!=""){
            if(this.txtInput.text.endsWith('.'))
                lastDot = false
            if(this.txtInput.text.endsWith(' '))
                this.txtInput.text = this.txtInput.text.dropLast(3)
            else
                this.txtInput.text = this.txtInput.text.dropLast(1)
        }
    }

    private fun onEqual() {
        val txt = txtInput.text.toString()
        try {
            val expression = Expression(txt)
            expression.build()
            val result = expression.evaluate()
            txtInput.text = result.toString()
        } catch (ex: Exception) {
            Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show()
            txtInput.text = getString(R.string.err)
        }
    }
}

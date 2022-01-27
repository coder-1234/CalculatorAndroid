package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainConstraintBinding

class MainActivity : AppCompatActivity() {

    private lateinit var txtInput: TextView
    private var lastDot:Boolean = false
    private var errorState:Boolean = false
    private lateinit var bind: ActivityMainConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainConstraintBinding.inflate(layoutInflater)
        setContentView(bind.root)
        with(bind){
            txtInput = result
            btnZero.setOnClickListener{ view -> onDigitClick(view) }
            btnOne.setOnClickListener{ view -> onDigitClick(view) }
            btnTwo.setOnClickListener{ view -> onDigitClick(view) }
            btnThree.setOnClickListener{ view -> onDigitClick(view) }
            btnFour.setOnClickListener{ view -> onDigitClick(view) }
            btnFive.setOnClickListener{ view -> onDigitClick(view) }
            btnSix.setOnClickListener{ view -> onDigitClick(view) }
            btnSeven.setOnClickListener{ view -> onDigitClick(view) }
            btnEight.setOnClickListener{ view -> onDigitClick(view) }
            btnNine.setOnClickListener{ view -> onDigitClick(view) }
            btnOpenBracket.setOnClickListener{ view -> onOperator(view) }
            btnCloseBracket.setOnClickListener{ view -> onOperator(view) }
            btnBackspace.setOnClickListener{ onBackspace() }
            btnAdd.setOnClickListener{ view -> onOperator(view) }
            btnSub.setOnClickListener{ view -> onOperator(view) }
            btnMultiply.setOnClickListener{ view -> onOperator(view) }
            btnDiv.setOnClickListener{ view -> onOperator(view) }
            btnCLR.setOnClickListener{ onClear() }
            btnEqual.setOnClickListener{ onEqual() }
            btnDecimal.setOnClickListener{ onDecimalPoint() }
        }
    }


    fun onDigitClick(view: View) {
        if(errorState){
            onClear()
            errorState = false
        }
        txtInput.append((view as Button).text)
        lastDot = false
    }

    private fun onDecimalPoint() {
        if(errorState){
            onClear()
            errorState = false
        }
        if(!lastDot){
            txtInput.append(".")
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        val btnText = (view as Button).text
        if(errorState){
            onClear()
            errorState = false
        }
        if(txtInput.text.isEmpty()){
            txtInput.text=btnText
        }
        else if(!txtInput.text.endsWith(' ')){
            txtInput.append(' '.toString())
            txtInput.append(btnText)
            txtInput.append(' '.toString())
        }
        else if(btnText=="("){
            txtInput.append(btnText)
            txtInput.append(' '.toString())
        }
        else{
            txtInput.append(btnText)
        }
        lastDot = false
    }

    fun onClear() {
        txtInput.text = ""
        lastDot = false
    }

    private fun onBackspace() {
        if(txtInput.text!=""){
            if(errorState){
                onClear()
                errorState = false
            }
            if(txtInput.text.endsWith('.'))
                lastDot = false
            if(txtInput.text.endsWith(' '))
                txtInput.text = txtInput.text.dropLast(3)
            else
                txtInput.text = txtInput.text.dropLast(1)
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
            txtInput.text = getString(R.string.err)
            errorState = true
        }
    }
}

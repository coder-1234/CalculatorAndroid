package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


/*class MainActivity : AppCompatActivity() {

    private var tvResult:TextView? = null
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_constraint)
        tvResult = findViewById(R.id.result)
    }

    fun onDigitClick(view: View){
        tvResult?.append((view as Button).text)
        lastDot = false
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvResult?.text = ""
    }

    fun onDecimal(view: View) {
        (tvResult?.text)?.contains('.').let{
            if(it==false) tvResult?.append('.'.toString())
            lastDot = true
        }
    }

    fun onOperator(view:View){
        (tvResult?.text)?.let{
            val tmp = it.toString()
            if(lastNumeric && !isOperatorAdded((tmp))){
                tvResult?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvVal = tvResult?.text.toString()

            try{

            }catch(e:ArithmeticException){
                Log.e("MY_ACTIVITY","logs")
            }
        }
    }

    private fun isOperatorAdded(expr: String):Boolean{
        return if(expr.startsWith("-")){
            false
        }
        else{
            expr.contains("/")||expr.contains("+")||expr.contains("-")||expr.contains("*")
        }
    }
}
*/
/*    fun onDigitClick(view: View) {
        if (stateError) {
            txtInput.text = (view as Button).text
            stateError = false
        } else {
            txtInput.append((view as Button).text)
        }
        // Set the flag
        lastNumeric = true
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txtInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onClear(view: View) {
        this.txtInput.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    fun onEqual(view: View) {
        if (lastNumeric && !stateError) {
            val txt = txtInput.text.toString()
            val expression = ExpressionBuilder(txt).build()
            try {
                val result = expression.evaluate()
                txtInput.text = result.toString()
                lastDot = true
            } catch (ex: ArithmeticException) {
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }*/

class MainActivity : AppCompatActivity() {

    private lateinit var txtInput: TextView
    private var lastDot:Boolean = false
    private var errorState: Boolean = false

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
        buttonOpen.setOnClickListener{ view -> onDigitClick(view) }
        buttonClose.setOnClickListener{ view -> onDigitClick(view) }
        buttonBackspace.setOnClickListener{ view -> onBackspace(view) }
        buttonPlus.setOnClickListener{ view -> onOperator(view) }
        buttonMinus.setOnClickListener{ view -> onOperator(view) }
        buttonMultiply.setOnClickListener{ view -> onOperator(view) }
        buttonDivide.setOnClickListener{ view -> onOperator(view) }
        buttonClear.setOnClickListener{ onClear() }
        buttonEqual.setOnClickListener{ view -> onEqual(view) }
        buttonDecimal.setOnClickListener{ onDecimalPoint() }
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
        if(errorState){
            onClear()
            errorState = false
        }
        txtInput.append((view as Button).text)
        lastDot = false
    }

    fun onClear() {
        this.txtInput.text = ""
        lastDot = false
    }

    private fun onBackspace(view: View) {
        if(this.txtInput.text!=""){
            if(errorState){
                onClear()
                errorState = false
            }
            if(this.txtInput.text.endsWith('.'))
                lastDot = false
            this.txtInput.text = this.txtInput.text.dropLast(1)
        }
    }

    private fun onEqual(view: View) {
        val txt = txtInput.text.toString()
        try {
            val expression = ExpressionBuilder(txt).build()
            val result = expression.evaluate()
            txtInput.text = result.toString()
        } catch (ex: Exception) {
            txtInput.text = getString(R.string.err)
            errorState = true
        }
    }
}

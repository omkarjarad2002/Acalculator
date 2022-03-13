package com.jaradomkar.mycalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val btn= findViewById<Button>(R.id.tvButton)
//        btn.setOnClickListener(){
//            Toast.makeText(this,"Button works !",Toast.LENGTH_SHORT).show()
//        }
    }

    fun btnClick(view: View){
        var textId= findViewById<TextView>(R.id.tvInput)
        textId.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View){
        var textId= findViewById<TextView>(R.id.tvInput)
        textId.text=""
        lastDot = false
        lastNumeric = false
    }



    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length-2)
        return value
    }

    fun onDecimalClick(view: View){
        var textId = findViewById<TextView>(R.id.tvInput)

        if(lastNumeric && !lastDot){
            textId.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var textId = findViewById<TextView>(R.id.tvInput)
            var tvValue = textId.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }


                    textId.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())


                }

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }

            try {
                if(tvValue.startsWith("+")){
                    prefix = "+"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }


                    textId.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())


                }

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }

            try {
                if(tvValue.startsWith("/")){
                    prefix = "/"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }


                    textId.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())


                }

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }

            try {
                if(tvValue.startsWith("*")){
                    prefix = "*"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }


                    textId.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())


                }

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        var textId = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(textId.text.toString())){
            textId.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}


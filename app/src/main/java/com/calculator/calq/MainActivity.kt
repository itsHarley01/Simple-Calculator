package com.calculator.calq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var mainView: TextView
    private lateinit var ac: MaterialButton
    private lateinit var del: MaterialButton
    private lateinit var modulo: MaterialButton
    private lateinit var divide: MaterialButton
    private lateinit var product: MaterialButton
    private lateinit var minus: MaterialButton
    private lateinit var plus: MaterialButton
    private lateinit var equals: MaterialButton
    private lateinit var point: MaterialButton
    private lateinit var num0: MaterialButton
    private lateinit var num1: MaterialButton
    private lateinit var num2: MaterialButton
    private lateinit var num3: MaterialButton
    private lateinit var num4: MaterialButton
    private lateinit var num5: MaterialButton
    private lateinit var num6: MaterialButton
    private lateinit var num7: MaterialButton
    private lateinit var num8: MaterialButton
    private lateinit var num9: MaterialButton
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        ac = findViewById(R.id.ac)
        del = findViewById(R.id.del)
        modulo = findViewById(R.id.modulo)
        divide = findViewById(R.id.divide)
        product = findViewById(R.id.product)
        minus = findViewById(R.id.minus)
        plus = findViewById(R.id.plus)
        equals = findViewById(R.id.equals)
        point = findViewById(R.id.point)
        num0 = findViewById(R.id.num0)
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        num3 = findViewById(R.id.num3)
        num4 = findViewById(R.id.num4)
        num5 = findViewById(R.id.num5)
        num6 = findViewById(R.id.num6)
        num7 = findViewById(R.id.num7)
        num8 = findViewById(R.id.num8)
        num9 = findViewById(R.id.num9)

        bottonClicked()
    }

    fun bottonClicked(){

        val bottons = arrayOf(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, point,ac,del)
        val operatorButton = arrayOf(plus,minus,product,divide,modulo)
        val sOperators = arrayOf("+", "-", "x", "/", "%")

        for (btn in bottons) {
            btn.setOnClickListener {
                when(btn){
                    point ->{
                        if(!mainView.text.toString().contains(".")){
                            mainView.text = mainView.text.toString() + "."
                        }
                    }

                    minus ->{
                        if(mainView.text.isEmpty()){
                            mainView.text = mainView.text.toString() + "-"
                        }
                    }

                    ac ->{
                        mainView.text = ""
                    }
                    del->{
                        if(!mainView.text.isEmpty()){
                            mainView.text = mainView.text.dropLast(1)
                        }
                    }
                    else->{
                        mainView.text = mainView.text.toString() + btn.text
                    }
                }
            }
        }

        for (btn in operatorButton) {
            btn.setOnClickListener {
                var containsOperator = false
                for (sign in sOperators) {
                    if (mainView.text.toString().contains(sign)) {
                        containsOperator = true
                        break
                    }
                }

                if (!mainView.text.isEmpty() && !containsOperator) {
                    mainView.text = mainView.text.toString() + btn.text
                    operator = btn.text.toString()
                }
            }
        }


        equals.setOnClickListener {
            if(!mainView.text.isEmpty() && operator != ""){
                var equation = mainView.text.toString()
                var index = equation.indexOf(operator)

                var num1 = 0.0
                var num2 = 0.0

                if(index != -1 && index < equation.length-1){
                    num1 = equation.substring(0, index).toDouble()
                    num2 = equation.substring(index + 1).toDouble()
                    var result = when (operator) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "x" -> num1 * num2
                        "/" -> num1 / num2
                        "%" -> num1 % num2
                        else -> 0.0
                    }

                    if (result % 1 == 0.0) {
                        mainView.text = result.toInt().toString()
                    } else {
                        mainView.text = result.toString()
                    }

                    operator = ""
                }

            }
        }
    }

}
package vcmsa.ci.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Initialize UI elements as class properties
    private lateinit var editTextNum1: EditText
    private lateinit var editTextNum2: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textViewResult: TextView
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        editTextNum1 = findViewById(R.id.editTextNum1)
        editTextNum2 = findViewById(R.id.editTextNum2)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        textViewResult = findViewById(R.id.textViewResult)
        buttonClear = findViewById(R.id.buttonClear)

        // Set click listeners for operations
        buttonAdd.setOnClickListener { performOperation('+') }
        buttonSubtract.setOnClickListener { performOperation('-') }
        buttonMultiply.setOnClickListener { performOperation('*') }
        buttonDivide.setOnClickListener { performOperation('/') }

        // Clear button functionality
        buttonClear.setOnClickListener {
            editTextNum1.text.clear()
            editTextNum2.text.clear()
            textViewResult.text = ""
        }
    }

    private fun performOperation(operator: Char) {
        val num1 = editTextNum1.text.toString().toDoubleOrNull()
        val num2 = editTextNum2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            textViewResult.text = "Please enter valid numbers"
            return
        }

        val result = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> {
                if (num2 == 0.0) {
                    "Cannot divide by zero"
                } else {
                    num1 / num2
                }
            }
            else -> return
        }

        textViewResult.text = if (result is Double) {
            "Result: ${String.format("%.2f", result)}"
        } else {
            result.toString()
        }
    }
}
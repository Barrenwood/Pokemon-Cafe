package com.example.pokemoncafeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSend = findViewById<Button>(R.id.btnSend)
        val itemRadio = findViewById<RadioGroup>(R.id.radioGroup)
        val editName = findViewById<EditText>(R.id.editName)
        val diningCheck = findViewById<CheckBox>(R.id.diningCheck)

        btnSend.setOnClickListener {
            val customerName = editName.text.toString()

            if (TextUtils.isEmpty(customerName)){
                val toast = Toast.makeText(this, "Please insert your name", Toast.LENGTH_SHORT)
                toast.show()

                // Throw error here and prevent moving to next activity

            }

            val isDining = diningCheck.isChecked

            val selectedId = itemRadio.checkedRadioButtonId
            val selectedItem = findViewById<RadioButton>(selectedId)
            val itemName = selectedItem.text.toString()

            var receiptTotal = 0
            if (selectedItem.text == "$3 Koffing Koffee"){
                receiptTotal = 3
            } else if (selectedItem.text == "$5 Alcreamie Cone"){
                receiptTotal = 5
            } else if (selectedItem.text == "$10 Palossand-wiches"){
                receiptTotal = 10
            } else if (selectedItem.text == "$4 Fidough-nuts"){
                receiptTotal = 4
            }

            if (isDining == false) {
                receiptTotal += 5
            }

            Log.d("EXTRAS_SENT", "Name: $customerName")
            Log.d("EXTRAS_SENT", "Selected Item: $itemName")
            Log.d("EXTRAS_SENT", "Dining in: $isDining")
            Log.d("EXTRAS_SENT", "Receipt Total: $receiptTotal")

            val intent = Intent(this@MainActivity, ReceiptActivity::class.java)
            intent.putExtra("name", customerName)
            intent.putExtra("item", itemName)
            intent.putExtra("isDining", isDining)
            intent.putExtra("receiptTotal", receiptTotal)

            startActivity(intent)
        }

    }
}
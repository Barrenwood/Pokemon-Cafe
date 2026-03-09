package com.example.pokemoncafeapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "LifecyleShow"
class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_receipt)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Capture the text boxes to change
        val textName = findViewById<TextView>(R.id.textName)
        val textItem = findViewById<TextView>(R.id.textItem)
        val textDining = findViewById<TextView>(R.id.textDining)
        val textPrice = findViewById<TextView>(R.id.textPrice)

        // Read the extras
        val customerName = intent.getStringExtra("name")
        val item = intent.getStringExtra("item")
        val dining = intent.getBooleanExtra("isDining", true)
        val price = intent.getIntExtra("receiptTotal", 0)

        // If block to better format about the dining
        var diningText = ""
        if (dining){
            diningText = "Dining in, no charge added"
        } else {
            diningText = "Dining out, $5 fee added"
        }

        // Change all the texts according to what was passed through the intent
        textName.text = "Order name: $customerName"
        textItem.text = "You ordered: $item"
        textDining.text = diningText
        textPrice.text = "Total: $$price"
    }

    // Section for Lifecycle logs
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart → happens when Activity becomes visible")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume → happens when Activity is in the foreground and interactive")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause → happens when you press Home or open another Activity")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop → happens after Home or when Activity is fully hidden")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart → happens when returning from Recents after Home")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy → happens when Back is pressed or Activity is finishing (not guaranteed)")
    }
}
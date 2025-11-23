package com.example.gmail

import android.graphics.Color
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.gmail.adapter.GmailAdapter
import com.example.gmail.model.Gmail

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)

        // Tạo dữ liệu giả
        val gmails = listOf(
            Gmail("Facebook", "09:40 AM", "New Login Detected", "We noticed a new login from Chrome on Windows.", Color.parseColor("#4CAF50")),
            Gmail("Shopee", "08:15 AM", "Flash Sale 50%", "Hôm nay giảm sốc nhiều sản phẩm hot!", Color.parseColor("#FF9800")),
            Gmail("GitHub", "07:30 AM", "New Pull Request", "A new pull request has been opened in your repo.", Color.parseColor("#607D8B")),
            Gmail("Figma", "06:45 AM", "Your design file was updated", "Someone edited the file 'App UI Project'.", Color.parseColor("#009688")),
            Gmail("StackOverflow", "04:22 AM", "Your question has an answer", "Someone posted an answer to your question!", Color.parseColor("#795548"))
        )


        // Gán Adapter
        val adapter = GmailAdapter(gmails)
        listView.adapter = adapter
    }
}
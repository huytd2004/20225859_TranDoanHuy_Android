package com.example.play_store

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.play_store.adapter.CategoryAdapter
import com.example.play_store.model.AppModel
import com.example.play_store.model.CategoryModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMain: RecyclerView = findViewById(R.id.rvMain)

        // 1. Tạo dữ liệu giả
        // List DỌC (Type = 0) - Cho phần "Suggested for you"
        // Cấu trúc: Tên, Rating, Size, Icon, Type
        val listSuggested = listOf(
            AppModel("Asphalt 9: Legends", "4.7", "2.1 GB", R.drawable.anh1, 0),
            AppModel("Shadow Fight Arena", "4.8", "780 MB", R.drawable.anh2, 0),
            AppModel("Cyber Hunter", "4.5", "1.4 GB", R.drawable.anh3, 0),
            AppModel("Dungeon Hunter 6", "4.6", "950 MB", R.drawable.anh4, 0)
        )

        // List NGANG (Type = 1) - Cho phần "Recommended for you"
        val listRecommended = listOf(
            AppModel("CapCut – Video Editor", "4.3", "85 MB", R.drawable.anh5, 1),
            AppModel("Spotify: Music & Podcasts", "4.5", "115 MB", R.drawable.anh6, 1),
            AppModel("Notion – Notes Organizer", "4.8", "65 MB", R.drawable.anh7, 1),
            AppModel("Snapseed", "4.4", "28 MB", R.drawable.anh5, 1),
            AppModel("Pinterest", "4.6", "72 MB", R.drawable.anh6, 1)
        )

        // List các Category (Nhóm)
        val categories = listOf(
            CategoryModel("Suggested for you", listSuggested),
            CategoryModel("Recommended for you", listRecommended),
            CategoryModel("New & Updated", listSuggested), // Tái sử dụng list dọc
            CategoryModel("Offline Games", listRecommended) // Tái sử dụng list ngang
        )

        // 2. Thiết lập Adapter cho RecyclerView cha (Dọc)
        val categoryAdapter = CategoryAdapter(this, categories)
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.adapter = categoryAdapter
    }
}
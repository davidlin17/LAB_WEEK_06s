package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        // Glide is used here to load the images
        // Passing the onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            // When triggered, show pop-up dialog
            override fun onClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the adapter for the recycler view
        recyclerView.adapter = catAdapter

        // Setup the layout manager for the recycler view
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // Add data to the model list in the adapter
        catAdapter.setData(
            listOf(
                CatModel(
                    name = "Fred",
                    breed = CatBreed.BalineseJavanese,
                    biography = "Silent and deadly",
                    gender = Gender.Male,
                    imageUrl = "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    name = "Wilma",
                    breed = CatBreed.ExoticShorthair,
                    biography = "Cuddly assassin",
                    gender = Gender.Female,
                    imageUrl = "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    name = "Curious George",
                    breed = CatBreed.AmericanCurl,
                    biography = "Award winning investigator",
                    gender = Gender.Unknown,
                    imageUrl = "https://cdn2.thecatapi.com/images/bar.jpg"
                )
            )
        )
    }

    // This will create a pop up dialog when one of the items from the recycler view is clicked
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            // Set the title for the dialog
            .setTitle("Cat Selected")
            // Set the message for the dialog
            .setMessage("You have selected cat ${cat.name}")
            // Set if the OK button should be enabled
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}

package io.github.kwanyoon.androidapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity: AppCompatActivity() {
    // when "add to inventory" pressed, move to add_product.xml screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)
    }
}
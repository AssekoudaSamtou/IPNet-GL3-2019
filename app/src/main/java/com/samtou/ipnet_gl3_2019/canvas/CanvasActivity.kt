package com.samtou.ipnet_gl3_2019.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samtou.ipnet_gl3_2019.R

class CanvasActivity : AppCompatActivity() {

    lateinit var circleLayout: CircleLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_canvas)
        circleLayout = findViewById(R.id.myLayout)

    }
}

package com.samtou.ipnet_gl3_2019.sqlite

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.samtou.ipnet_gl3_2019.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AnimalActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imageView : ImageView
    lateinit var currentPhotoBytes: ByteArray
    lateinit var animalController: AnimalController
    lateinit var animalNameEdt : EditText
    lateinit var animalDescEdt : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        menuInflater
        animalController = AnimalController(this)
        imageView = findViewById(R.id.animal_preview)
        animalNameEdt = findViewById(R.id.edtAnimal_name)
        animalDescEdt = findViewById(R.id.edtAnimal_desc)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.animal_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_list) {
            val animalListIntent = Intent(this, AnimalListActivity::class.java)
//            animalListIntent.put
            startActivity(animalListIntent)
            return true
        }
        else if (item.itemId == R.id.second) {

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun dispatchTakePictureIntent(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Toast.makeText(this, "photo prise", Toast.LENGTH_LONG).show()
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            val stream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

            currentPhotoBytes = stream.toByteArray()
//            imageBitmap.recycle()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun saveAnimal(view: View) {
        val animal = Animal(1, animalNameEdt.text.toString(), animalDescEdt.text.toString(), currentPhotoBytes)
        animalController.insertAnimal(animal)
        Toast.makeText(this, "animal saved", Toast.LENGTH_LONG).show()

        val animalListIntent = Intent(this, AnimalListActivity::class.java)
        startActivity(animalListIntent)
    }
}

package com.samtou.ipnet_gl3_2019.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.sqlite.Animal
import com.samtou.ipnet_gl3_2019.recyclerView.AnimalAdapter

class AnimalListActivity : AppCompatActivity() {

    lateinit var animals : ArrayList<Animal>
    lateinit var adapter: AnimalAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var animalController: AnimalController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        recyclerView = findViewById(R.id.rclAnimal)

        this.animals = java.util.ArrayList()
        this.animalController = AnimalController(this)
        this.adapter = AnimalAdapter(this, animals)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        this.animals.addAll(animalController.getAllAnimals())
        this.adapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
//        menuInflater.inflate(R.menu.animal_menu2, menu)
//        Toast.makeText(this, "menu created", Toast.LENGTH_LONG).show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
//        Toast.makeText(this, "item selected", Toast.LENGTH_LONG).show()
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.animal_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_list) {
            val animalListIntent = Intent(this, AnimalListActivity::class.java)
            startActivity(animalListIntent)
            return true
        }
        else if (item.itemId == R.id.second) {

            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

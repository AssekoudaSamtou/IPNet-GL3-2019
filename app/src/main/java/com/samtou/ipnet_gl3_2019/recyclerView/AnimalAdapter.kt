package com.samtou.ipnet_gl3_2019.recyclerView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.RecyclerView
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.sqlite.Animal
import com.samtou.ipnet_gl3_2019.sqlite.AnimalActivity
import com.samtou.ipnet_gl3_2019.sqlite.AnimalController
import kotlinx.android.synthetic.main.animal_item.view.*


class AnimalAdapter(val context: Context?, var animals: ArrayList<Animal>): RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(), View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    lateinit var view: View
    var currentAnimal: View? = null
    lateinit var animalController: AnimalController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        view = LayoutInflater.from(context).inflate(R.layout.animal_item, parent, false)
//        view.startActionMode(ActionMode.Callback)
//        context!!.actionBar
        view.setOnCreateContextMenuListener(this)
        animalController = AnimalController(view.context)

        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal: Animal = animals.get(position)
        holder.view.id = animal.id
        holder.nameView.text = animal.name + animal.id
        holder.descView.text = animal.description
        val bitmap = BitmapFactory.decodeByteArray(animal.image, 0, animal.image.size)
        holder.imgView.setImageBitmap(bitmap)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        Toast.makeText(view.context, "Context Menu created", Toast.LENGTH_LONG).show()
        currentAnimal = v
        val delete: MenuItem = menu!!.add(Menu.NONE, 1, 1, "Delete")
        val detail: MenuItem = menu.add(Menu.NONE, 2, 2, "details")
        val edit: MenuItem = menu.add(Menu.NONE, 3, 3, "Edit")

        delete.setIcon(R.drawable.icon_delete)
        detail.setIcon(R.drawable.view_details)
        edit.setIcon(R.drawable.icon_edit)
        menu.setHeaderTitle(R.string.context_menu_header)

        delete.setOnMenuItemClickListener(this)
        detail.setOnMenuItemClickListener(this)
        edit.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            1 -> {
                animalController.deleteAnimal(currentAnimal!!.id)
                this.animals = animalController.getAllAnimals()
                this.notifyDataSetChanged()
            }
            2 -> {
                Toast.makeText(view.context, "'details' action selected", Toast.LENGTH_LONG).show()
            }
            3 -> {
                Toast.makeText(view.context, "'edit' action selected", Toast.LENGTH_LONG).show()
                val editAnimalIntent = Intent(context, AnimalActivity::class.java)
                editAnimalIntent.putExtra("is_update", true)
                editAnimalIntent.putExtra("animal_id", view.id)
                context!!.startActivity(editAnimalIntent)
            }
            else -> {

            }
        }
        return true
    }

    fun findIndexById(animal: Animal?): Int {
        for (a in animals) {
            if (a.id == animal!!.id) {
                return animals.indexOf(a)
            }
        }
        return -1
    }

    class AnimalViewHolder(
        var view: View,
        val nameView : TextView = view.animal_name,
        val descView : TextView = view.animal_description,
        val imgView : ImageView = view.animal_img
    ): RecyclerView.ViewHolder(view)
}
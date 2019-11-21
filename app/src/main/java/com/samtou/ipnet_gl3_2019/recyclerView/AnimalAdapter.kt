package com.samtou.ipnet_gl3_2019.recyclerView

import android.content.Context
import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.sqlite.Animal
import com.samtou.ipnet_gl3_2019.sqlite.AnimalController
import kotlinx.android.synthetic.main.animal_item.view.*


class AnimalAdapter(val context: Context?, val animals: List<Animal>): RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal: Animal = animals.get(position)
        holder.view.id = animal.id
        holder.nameView.text = animal.name
        holder.descView.text = animal.description
        val bitmap = BitmapFactory.decodeByteArray(animal.image, 0, animal.image.size)
        holder.imgView.setImageBitmap(bitmap)
    }

    class AnimalViewHolder(
        var view: View,
        val nameView : TextView = view.animal_name,
        val descView : TextView = view.animal_description,
        val imgView : ImageView = view.animal_img
    ): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

        var currentAnimal: View? = null
        var animalController: AnimalController

        init {
            view.setOnCreateContextMenuListener(this)
            animalController = AnimalController(view.context)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when (item!!.itemId) {
                1 -> {
                    Toast.makeText(view.context, "'delete' action selected", Toast.LENGTH_LONG).show()
                    animalController.deleteAnimal(currentAnimal!!.id)

                    Toast.makeText(view.context, "animal No"+ currentAnimal!!.id+"deleted", Toast.LENGTH_LONG).show()
                }
                2 -> {
                    Toast.makeText(view.context, "'details' action selected", Toast.LENGTH_LONG).show()
                }
                3 -> {
                    Toast.makeText(view.context, "'edit' action selected", Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
            return true
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
    }
}
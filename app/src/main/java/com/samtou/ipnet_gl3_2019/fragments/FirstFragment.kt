package com.samtou.ipnet_gl3_2019.fragments

import android.icu.text.CaseMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindString
import butterknife.BindView
import butterknife.ButterKnife

import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.recyclerView.AnimalAdapter
import com.samtou.ipnet_gl3_2019.sqlite.Animal
import com.samtou.ipnet_gl3_2019.sqlite.AnimalController
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    @BindView(R.id.rclAnimal) lateinit var recyclerView : RecyclerView
//    @BindView(R.id.txtFname) lateinit var textView: TextView
    @BindString(R.string.hello_blank_fragment) lateinit var fragmentTitle: String

    lateinit var animals : ArrayList<Animal>
    lateinit var adapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_first, container, false)
        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.rclAnimal)

        this.animals = ArrayList()
        this.adapter = AnimalAdapter(context, animals)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity)

        val animalController: AnimalController = AnimalController(view.context)

//        animalController.insertAnimal(Animal(6, "Lion", "Le roi de la foret", R.drawable.icons8_lion_48px_1))
//        updateUI(animalController.getAllAnimals())
//        this.fillUsers()

        return view
    }

//    private fun fillUsers() {
//        val users = ArrayList<Animal>()
//        users.add(Animal(7, "Chat", "Le plus agressif", R.drawable.icons8_cat_100px_2))
//        users.add(Animal(8, "Loin", "Le roi de la foret", R.drawable.icons8_lion_48px_1))
//        users.add(Animal(9, "Leopard", "Le plus rapide", R.drawable.icons8_leopard_40px))
//        users.add(Animal(10, "Tortue", "Le plus lent", R.drawable.icons8_turtle_48px))
//        updateUI(users)
//    }

    private fun updateUI(users : List<Animal>) {
        this.animals.addAll(users)
        adapter.notifyDataSetChanged()
    }
}

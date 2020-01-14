package com.samtou.ipnet_gl3_2019.serveur.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.serveur.Iservice
import com.samtou.ipnet_gl3_2019.serveur.model.Categorie
import com.samtou.ipnet_gl3_2019.serveur.model.Produit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProduitActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var edtNomPrdt: EditText
    lateinit var edtCodePrdt: EditText
    lateinit var spnCateg: Spinner

    lateinit var api: Iservice
    val BaseURL: String = "http://192.168.137.1:9000/"

    var categorieSelected: Categorie? = null

    lateinit var categories: List<Categorie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produit)

        initView()

        initAPICall()

        getCategories()

        spnCateg.onItemSelectedListener = this
    }

    fun initView() {
        edtNomPrdt = findViewById(R.id.edtNomProduit)
        edtCodePrdt = findViewById(R.id.edtCodeProduit)
        spnCateg = findViewById(R.id.spnCategorie)
    }

    fun initAPICall() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Iservice::class.java)
    }

    fun getCategories() {
        api.getCategories().enqueue(object: Callback<List<Categorie>> {

            override fun onFailure(call: Call<List<Categorie>>, t: Throwable) {
                Toast.makeText(this@ProduitActivity, "error : getCategorie", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Categorie>>, response: Response<List<Categorie>>) {
                if (response.isSuccessful) {
                    categories = response.body()!!
                    val adapter = ArrayAdapter<Categorie>(this@ProduitActivity, android.R.layout.simple_dropdown_item_1line, categories as MutableList<Categorie>)
                    spnCateg.adapter = adapter
                } else {
                    Toast.makeText(this@ProduitActivity, "Categorie: Not Successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun saveProduit(view: View) {
        val nom = edtNomPrdt.text.toString()
        val code = edtCodePrdt.text.toString()
        val produit = Produit(nom, code, categorieSelected!!)


        api.createProduct(produit).enqueue(object : Callback<Produit> {

            override fun onFailure(call: Call<Produit>, t: Throwable) {
                Toast.makeText(this@ProduitActivity, "error : createProduit", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Produit>, response: Response<Produit>) {
                if(response.isSuccessful){
                    val produit = response.body()
                    Toast.makeText(this@ProduitActivity, produit!!.nomProduit, Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this@ProduitActivity, "Produit: Not Successful " + response.code(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        categorieSelected = categories.get(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        categorieSelected = null
    }
}

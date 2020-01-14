package com.samtou.ipnet_gl3_2019.serveur

import com.samtou.ipnet_gl3_2019.serveur.model.Produit
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProduitAPIHelper {

    lateinit var api: Iservice

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Iservice::class.java)

        val callObject: Call<Produit> = api.getProduct(1)
//        callObject.enqueue(Callback<Response<Produit>>() {
//
//
//        })
    }
}
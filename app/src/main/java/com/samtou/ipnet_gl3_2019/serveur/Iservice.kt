package com.samtou.ipnet_gl3_2019.serveur

import com.samtou.ipnet_gl3_2019.serveur.model.Categorie
import com.samtou.ipnet_gl3_2019.serveur.model.Produit
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Iservice {

    @GET("produit/{produitID}")
    fun getProduct(@Path("produitID") produitID: Int): Call<Produit>

    @GET("produits")
    fun getProducts(): Call<List<Produit>>

    @GET("categories")
    fun getCategories(): Call<List<Categorie>>

    @POST("produits/new")
    fun createProduct(@Body produit: Produit): Call<Produit>
}
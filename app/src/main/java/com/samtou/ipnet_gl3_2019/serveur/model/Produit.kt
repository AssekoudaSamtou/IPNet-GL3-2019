package com.samtou.ipnet_gl3_2019.serveur.model

class Produit(var nomProduit: String, var codeProduit: String, var categorie: Categorie) {

    var idProduit: Long = 0

    constructor(id: Long, nomProduit: String, codeProduit: String, categorie: Categorie) : this(nomProduit, codeProduit, categorie) {
        this.idProduit = id
    }
}
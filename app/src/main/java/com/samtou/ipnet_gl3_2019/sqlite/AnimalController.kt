package com.samtou.ipnet_gl3_2019.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class AnimalController(context: Context) {
    val DB_VERSION: Int = 1
    val DB_NAME: String = "animalDB.db"
    var db: IPnetDB
    var rdb: SQLiteDatabase
    var wdb: SQLiteDatabase

    init{
        db = IPnetDB(context, DB_NAME, DB_VERSION)
        rdb = db.readableDatabase
        wdb = db.writableDatabase
    }

    @SuppressLint("Recycle")
    fun find(id: Int): Animal {
//        val query = "SELECT * FROM animal WHERE id=$id"
//        val c: Cursor = rdb.rawQuery(query, Array(1){"*"})
        val c: Cursor = rdb.query("animal", Array(1){"*"}, "id=$id",  null, null, null, null)
        c.moveToFirst()
        return Animal(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3))
    }

    fun insertAnimal(animal: Animal) {
        val cv = ContentValues()
        cv.put(IPnetDB.NAME, animal.name)
        cv.put(IPnetDB.DESCRIPTION, animal.description)
        cv.put(IPnetDB.IMG, animal.image)

        wdb.insert("animal", null, cv)
    }

    fun updateAnimal(animal: Animal?) :Boolean {
        val cv = ContentValues()
        cv.put(IPnetDB.NAME, animal!!.name)
        cv.put(IPnetDB.DESCRIPTION, animal.description)
        cv.put(IPnetDB.IMG, animal.image)

        val status = wdb.update("animal", cv, "id="+animal.id, null)

        return status != -1
    }

    fun deleteAnimal(id: Int) : Boolean {
        val status = wdb.delete("animal", "id="+id, null)
        return status != -1
    }

    fun getAllAnimals(): ArrayList<Animal> {

        val animals: ArrayList<Animal> = ArrayList()
        val c: Cursor = wdb.query("animal", Array(1){"*"}, null,  null, null, null, "id DESC")
        var id:Int
        var name:String
        var description:String
        var image:ByteArray

        while (c.moveToNext()) {
            id = c.getInt(0)
            name = c.getString(1)
            description = c.getString(2)
            image = c.getBlob(3)
            animals.add(Animal(id, name, description, image))
        }
        c.close()
        return animals
    }
}
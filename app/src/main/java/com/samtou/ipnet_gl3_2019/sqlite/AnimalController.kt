package com.samtou.ipnet_gl3_2019.sqlite

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

    fun insertAnimal(animal: Animal) {
        val cv = ContentValues()
        cv.put(IPnetDB.NAME, animal.name)
        cv.put(IPnetDB.DESCRIPTION, animal.description)
        cv.put(IPnetDB.IMG, animal.image)

        wdb.insert("animal", null, cv)
    }

    fun updateAnimal(animal: Animal) :Boolean {
        val cv = ContentValues()
        cv.put(IPnetDB.NAME, animal.name)
        cv.put(IPnetDB.DESCRIPTION, animal.description)
        cv.put(IPnetDB.IMG, animal.image)
        wdb.update("animal", cv, null, null)

        val status = wdb.update("animal", cv, "id="+animal.id, null)

        return status != -1
    }

    fun deleteAnimal(id: Int) : Boolean {
        val status = wdb.delete("animal", "id="+id, null)
        return status != -1
    }

    fun getAllAnimals(): List<Animal> {

        val animals: ArrayList<Animal> = ArrayList()
        val c: Cursor = wdb.query("animal", Array(1){"*"}, null,  null, null, null, null)
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
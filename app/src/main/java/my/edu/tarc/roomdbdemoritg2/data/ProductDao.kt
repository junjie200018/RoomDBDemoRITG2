package my.edu.tarc.roomdbdemoritg2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(p:Product)
    @Query("select * from product")
    fun getAll() : List<Product>

    @Query("select * from product Where price < :price ")
    fun getPriceLessThan (price:Double): List<Product>

    @Query("delete from product")
    fun removeAll()
}
package my.edu.tarc.roomdbdemoritg2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import my.edu.tarc.roomdbdemoritg2.data.Product
import my.edu.tarc.roomdbdemoritg2.data.ProductDB
import my.edu.tarc.roomdbdemoritg2.data.ProductDao

class MainActivity : AppCompatActivity() {
    //private lateinit var dao :ProductDao
    lateinit var dao: ProductDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
//
//        dao = ProductDB.getInstance(application).productDao
//        val btn : Button = findViewById(R.id.btnInsert)
//
//        btn.setOnClickListener(){
//
//            val p = Product(0, "Apple", 2.50)
//
//            CoroutineScope(IO).launch {
//                dao.insertProduct(p)
//            }
//        }


    dao = ProductDB.getInstance(application).productDao

        val btnInsert :Button = findViewById(R.id.btnInsert)
        btnInsert.setOnClickListener(){

            val name :String  = findViewById<TextView>(R.id.tfName).text.toString()
            val price:Double =  findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()

            val p = Product(0, name, price)

            CoroutineScope(IO).launch {
                dao.insertProduct(p)
            }

        }

        val btnGet :Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener(){

            CoroutineScope(IO).launch {
                var productName =""
               // val products = dao.getAll() //:List<Product> is not nessary
                val products = dao.getPriceLessThan(1000.00)

                for(product: Product in products){
                    productName += product.name+"\n"
                }

                CoroutineScope(Main).launch {
                    val tvResult: TextView = findViewById(R.id.tvResult)
                    tvResult.text = productName
                }
            }



        }
    }
}
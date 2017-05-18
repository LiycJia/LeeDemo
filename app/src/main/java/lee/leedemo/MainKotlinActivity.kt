package lee.leedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)


        fun sum(a: Int, b: Int): Int {
            return a + b
        }

        fun printSum(a: Int, b: Int): Unit {
            print(a + b)
        }
        
    }
}

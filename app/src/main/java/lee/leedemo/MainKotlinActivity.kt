package lee.leedemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatSeekBar
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        tv_name.text = "这样很好用"
        btn_kotlin.text="吐司"
        var c: String? = "1111111"
        c = null
        c?.length
        fun sum(a: Int, b: Int): Int {
            return a + b
        }

        fun printSum(a: Int, b: Int): Unit {
            print(a + b)
        }

        fun foo(va: Int): Int {
            return 1
        }

        var cl = { foo: Int -> println(foo) }

        var c2 = { foo: Int -> println(foo) }
        fun fooTest(func: (Int) -> Unit) = println("I'm Lee")
        fooTest(c2)
    }

    class Bar(context: Context?) : AppCompatSeekBar(context) {}
    interface Interface : View.OnClickListener {}
}

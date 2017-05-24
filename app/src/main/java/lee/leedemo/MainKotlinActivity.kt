package lee.leedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        tv_name.text = "这样"
        btn_kotlin.text = "吐司"
    }
}

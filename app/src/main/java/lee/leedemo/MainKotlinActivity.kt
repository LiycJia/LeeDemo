package lee.leedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_kotlin.*
import java.security.cert.CertPathValidatorException

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        tv_name.text = "这样"
        btn_kotlin.text = "吐司"

        var a = 1;//var定义可变变量
        a = 2;
        val b = 2;
        //b = 4;val定义不可变变量

    }

    fun Address.swap(){
        
    }
    class Address {
        var name: String
            set(value) {}
            get() = "1221"
    }

    fun coprAddress(address: Address): Address {
        val result = Address()
        result.name = address.name
        return result
    }

    interface Interface {

        fun abc()
        fun foo()
    }
}

package lee.leedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        //获取起点坐标
        val location2 = intArrayOf(0, 0)
        tv.getLocationInWindow(location2)
        val x1 = location2 [0]
        val y1 = location2 [1]
        //获取终点坐标，最近拍摄的坐标
        val location = intArrayOf(0, 0)
        tv2.getLocationInWindow(location)
        val x2 = location [0]
        val y2 = location [1]
        val annimationX = TranslateAnimation(0F, (-(x1 - x2)).toFloat(), 0F, 0F)
        val annimationY = TranslateAnimation(0F, 0F, 0F, (y2 - y1).toFloat())
//        annimationX.setInterpolator(LinearInterpolator())
        annimationX.interpolator = LinearInterpolator()
        annimationX.repeatCount = 0
        annimationY.interpolator = AccelerateInterpolator()
        annimationY.repeatCount = 0

        //组合动画
        val anim = AnimationSet(false)
        anim.fillAfter = false
        anim.addAnimation(annimationX)
        anim.addAnimation(annimationY)
        anim.duration = 8000
        anim.startOffset = 4000
        tv.startAnimation(anim)
    }
}

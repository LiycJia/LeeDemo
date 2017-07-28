package lee.leedemo.customview;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lee.leedemo.R;

/**
 * Created by Yuren on 2017/7/28.
 */

public class UpvoteView extends LinearLayout implements Animator.AnimatorListener {

    private int defaultGravity = Gravity.CENTER_HORIZONTAL;
    private int numDisLike, numLike;
    private float pLike, pDisLike;
    private ImageView imageLike, imageDisLike;
    private AnimationDrawable animLike, animDis;
    private TextView tvPLike, tvPDis;
    private int defaultTextColor = Color.WHITE;
    private String defaultLike = "是";
    private TextView tvLike, tvDis;
    private String defalutDis = "否";

    public UpvoteView(Context context) {
        super(context);
    }

    public UpvoteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UpvoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.removeAllViews();
        //初始化总布局
        setOrientation(HORIZONTAL);
        setGravity(defaultGravity | Gravity.BOTTOM);
        setBackgroundColor(Color.TRANSPARENT);

        //设置百分比
        float all = numLike + numDisLike;
        pLike = numLike / all;
        pDisLike = numDisLike / all;
        numLike = (int) (pLike * all);
        numDisLike = (int) (pDisLike * all);

        //初始化图片
        imageLike = new ImageView(getContext());
        imageLike.setBackgroundResource(R.drawable.animation_like);
        animLike = (AnimationDrawable) imageLike.getBackground();
        //初始化文字
        tvPLike = new TextView(getContext());
        tvPLike.setText(numLike + "%");
        tvPLike.setTextColor(defaultTextColor);
        TextPaint likeNumPaint = tvPLike.getPaint();
        likeNumPaint.setFakeBoldText(true);
        tvPLike.setTextSize(20f);
        tvLike = new TextView(getContext());
        tvLike.setText(defaultLike);
        tvLike.setTextColor(defaultTextColor);

        imageDisLike = new ImageView(getContext());
        imageDisLike.setBackgroundResource(R.drawable.animation_dislike);
        animDis = (AnimationDrawable) imageDisLike.getBackground();
        tvPDis = new TextView(getContext());
        tvPDis.setText(numDisLike + "%");
        tvPDis.setTextColor(defaultTextColor);
        TextPaint disNumPaint = tvPDis.getPaint();
        disNumPaint.setFakeBoldText(true);
        tvPDis.setTextSize(20f);
        tvDis = new TextView(getContext());
        tvDis.setText(defalutDis);
        tvDis.setTextColor(defaultTextColor);

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}

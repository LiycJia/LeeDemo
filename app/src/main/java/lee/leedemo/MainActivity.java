package lee.leedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Activity mActivity = this;

    @OnClick(R.id.btn_bledemo)
    void bledemo() {
        startActivity(new Intent(MainActivity.this, BleDemoActivity.class));
    }

    @OnClick(R.id.btn_blephonedemo)
    void blephonedemo() {
        startActivity(new Intent(mActivity, BlePhoneDemoActivity.class));
    }

    @OnClick(R.id.btn_kotlindemo)
    void kotlindemo() {
        startActivity(new Intent(mActivity, MainKotlinActivity.class));
    }

    @OnClick(R.id.btn_annimationdemo)
    void btn_annimationdemo() {
        startActivity(new Intent(mActivity, AnimationActivity.class));
    }

    @OnClick(R.id.btn_canvasdemo)
    void btn_canvasdemo() {
        startActivity(new Intent(mActivity, CavansAvtivity.class));
    }

    @OnClick(R.id.btn_upvoteview)
    void btn_upvoteview() {
        startActivity(new Intent(mActivity, UpvoteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}

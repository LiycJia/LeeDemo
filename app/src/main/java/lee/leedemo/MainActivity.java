package lee.leedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.btn_bledemo)
    void bledemo() {
        startActivity(new Intent(MainActivity.this, BleDemoActivity.class));
    }

    @OnClick(R.id.btn_blephonedemo)
    void blephonedemo() {
        startActivity(new Intent(MainActivity.this, BlePhoneDemoActivity.class));
    }

    @OnClick(R.id.btn_kotlindemo)
    void kotlindemo() {
        startActivity(new Intent(MainActivity.this, MainKotlinActivity.class));
    }

    @OnClick(R.id.btn_annimationdemo)
    void btn_annimationdemo() {
        startActivity(new Intent(MainActivity.this, AnimationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}

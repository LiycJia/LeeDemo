package lee.leedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import lee.leedemo.customview.CanvasStar;

public class CavansAvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cavans_avtivity);
        CanvasStar canvasStar = new CanvasStar(this);
        ViewGroup rootview = (ViewGroup) findViewById(android.R.id.content);
        rootview.addView(canvasStar);
    }
}

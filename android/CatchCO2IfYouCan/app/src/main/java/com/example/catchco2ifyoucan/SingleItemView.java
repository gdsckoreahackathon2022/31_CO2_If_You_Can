package com.example.catchco2ifyoucan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SingleItemView extends LinearLayout {
    TextView textView1, textView2;

    public SingleItemView(Context context) {
        super(context);
        init(context);//인플레이션해서 붙여주는 역
    }

    public SingleItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_item_list,this, true);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void setJob(String Job){
        textView1.setText(Job);
    }
    public void setCO2(double CO2){
        textView2.setText(Double.toString(CO2) + "g");
    }
}

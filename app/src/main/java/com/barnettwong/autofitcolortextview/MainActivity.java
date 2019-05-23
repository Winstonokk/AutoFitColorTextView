package com.barnettwong.autofitcolortextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.barnettwong.autofitcolortextview_library.AutoFitColorTextView;

public class MainActivity extends AppCompatActivity {
    private AutoFitColorTextView autoFitColorTextView;
    private AutoFitColorTextView autoFitColorTextView2;
    private AutoFitColorTextView autoFitColorTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        autoFitColorTextView=findViewById(R.id.textview);
        autoFitColorTextView2=findViewById(R.id.textview2);
        autoFitColorTextView3=findViewById(R.id.textview3);

        //1.设置一段文字内容（必须要先设置一段文字）
        String formatStr = getResources().getString(R.string.str_open_member_tip);
        String finalStr = String.format(formatStr, "396","456");
        autoFitColorTextView.setText(finalStr);
        //2.设置文字整体的字体颜色
        autoFitColorTextView.setTextColor(getResources().getColor(R.color.color_333333));
        //3.开始设置指定字体的颜色，大小，样式
        autoFitColorTextView.setTextArrColor("396",getResources().getColor(R.color.color_ff2d2d));
        autoFitColorTextView.setTextArrColor("456",getResources().getColor(R.color.color_ff2d2d));

        //1.设置一段文字内容（必须要先设置一段文字）
        String formatStr2 = getResources().getString(R.string.str_open_member_tip2);
        String finalStr2 = String.format(formatStr2, "396","456");
        autoFitColorTextView2.setText(finalStr2);
        //2.设置文字整体的字体颜色
        autoFitColorTextView2.setTextColor(getResources().getColor(R.color.color_333333));
        //3.开始设置指定字体的颜色，大小，样式
        autoFitColorTextView2.setTextArrColor("396",getResources().getColor(R.color.color_ff2d2d));
        autoFitColorTextView2.setTextArrColor("456",getResources().getColor(R.color.color_ff2d2d));

        //1.设置一段文字内容（必须要先设置一段文字）
        String formatStr3 = getResources().getString(R.string.str_open_member_tip3);
        String finalStr3 = String.format(formatStr3, "396","456");
        autoFitColorTextView3.setText(finalStr3);
        //2.设置文字整体的字体颜色
        autoFitColorTextView3.setTextColor(getResources().getColor(R.color.color_333333));
        //3.开始设置指定字体的颜色，大小，样式
        autoFitColorTextView3.setTextArrColor("396",getResources().getColor(R.color.color_ff2d2d));
        autoFitColorTextView3.setTextArrColor("456",getResources().getColor(R.color.color_ff2d2d));

    }
}

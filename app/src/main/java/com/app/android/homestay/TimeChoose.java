package com.app.android.homestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.lzy.okgo.OkGo;
import com.app.android.homestay.base.BaseFragment;
public class TimeChoose extends AppCompatActivity {
    private EditText arrive_time, leave_time;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        arrive_time=findViewById(R.id.arrive_time);
        leave_time=findViewById(R.id.leave_time);
        Intent intent =getIntent();
        String username =intent.getStringExtra("username");
        String introduce=(intent.getStringExtra("introduce"));
        String original_price=(intent.getStringExtra("original_price"));
        String discount_price=(intent.getStringExtra("discount_price"));
        String address=(intent.getStringExtra("address"));
        String house_img=(intent.getStringExtra("house_img"));
        context=getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choose);
        Button button = findViewById(R.id.reservation);
        button.setOnClickListener(v -> addOrder(username, introduce,original_price,
                discount_price,address,house_img
                , String.valueOf(arrive_time), String.valueOf(leave_time)));

    }
    private void addOrder(String username,String introduce,String original_price,
                          String discount_price,String address,String house_img,
                          String arrive_time,String leave_time) {

        OkGo.<String>post(Config.ADD_ORDER_URL)
                .params("username", username)
                .params("introduce", introduce)
                .params("original_price", original_price)
                .params("discount_price", discount_price)
                .params("address", address)
                .params("house_img", house_img)
                .params("arrive_time", arrive_time)
                .params("leave_time", leave_time)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_SHORT);
                        finish();
                    }
                });

    }
//    protected void BaseToast(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//    }
}
package com.lyt.requestcallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lyt.requestcallback.mylibrary.RequestCallBackManager;
import com.lyt.requestcallback.mylibrary.annotation.RequestCallBack;
import com.lyt.requestcallback.mylibrary.type.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestCallBackManager.getInstance().register(this);


        RequestCallBackManager.getInstance().post(new Response("login",true,"---------------"));
        RequestCallBackManager.getInstance().post(new Response("login",false,"---------------"));
    }
    @RequestCallBack
    public void onRec1(Response response){
        Log.e("liyunte","onRec1"+response.toString());
    }
    @RequestCallBack(requestType = "login")
    public void onRec2(Response response){
        Log.e("liyunte","onRec2"+response.toString());
    }
    @RequestCallBack(requestType = "register")
    public void onRec3(Response response){
        Log.e("liyunte","onRec3"+response.toString());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestCallBackManager.getInstance().unRegister(this);
    }
}

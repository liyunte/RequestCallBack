package com.lyt.requestcallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lyt.requestcallback.mylibrary.RequestCallBackManager;
import com.lyt.requestcallback.mylibrary.annotation.RequestFailure;
import com.lyt.requestcallback.mylibrary.annotation.RequestSuccess;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String request = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestCallBackManager.getInstance().register(this);
        List<String> list = new ArrayList<String>();
        list.add("恭喜你成功了");
        RequestCallBackManager.getInstance().postSuccess(request,list);
    }


    @RequestSuccess(request)
    public void success(List<String> data){
        Log.e("liyunte","收到了Success信息="+data.get(0));
    }
    @RequestFailure(request)
    public void failure(String failure){
        Log.e("liyunte","收到了failure信息="+failure);
    }

    @RequestFailure("222")
    public void dd(String failure){
        Log.e("liyunte","收到了dd  failure信息="+failure);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestCallBackManager.getInstance().unRegister(this);
    }
}

package com.example.ttb.regisn.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ttb.regisn.R;

import java.util.zip.Inflater;

/**
 * Created by ttb on 16/4/13.
 */
public class PopTips {

//    private Context contex;
//    private View popUpView;
//
//    private LayoutInflater inflater;
//    public void PopTips(Context context){
//        this.contex = context;
//        inflater = LayoutInflater.from(context);
//    }
    public static View getPop(Context context,String tips){
        LayoutInflater inflater = LayoutInflater.from(context);
        View popUpView = inflater.inflate(R.layout.pop_layout,null);
        TextView tv_tips = (TextView)popUpView.findViewById(R.id.location);
        tv_tips.setText(tips);
        return popUpView;
    }
}

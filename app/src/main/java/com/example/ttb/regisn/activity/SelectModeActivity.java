package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ttb.regisn.Auto_Complet_Activity;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by ttb on 16/4/11.
 */
public class SelectModeActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mShow_1,mShow_2;
    final boolean mIsShow1 = false;
    final boolean mIsShow2 = false;
    private ButtonRectangle mBtnRead, mBtnWrite;
    private ButtonFlat mBtnHj,mBtnFhj;
    private ButtonRectangle mBtnLogin;
    private Animation myAnimation_Translate;
    private LinearLayout tv;

    //    private TextView mWhite;
    private LinearLayout mInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_mode);
//        mShow_1 = (LinearLayout)findViewById(R.id.show_1);
//        mShow_2 = (LinearLayout)findViewById(R.id.show_2);
        mBtnRead = (ButtonRectangle)findViewById(R.id.read);
        mBtnWrite = (ButtonRectangle)findViewById(R.id.write);
        mBtnHj = (ButtonFlat)findViewById(R.id.huji);
        mBtnFhj = (ButtonFlat)findViewById(R.id.feihuji);
        mBtnLogin = (ButtonRectangle)findViewById(R.id.login);
//        mWhite = (TextView)findViewById(R.id.white);
        mInfo = (LinearLayout)findViewById(R.id.info_2);
        tv = (LinearLayout)findViewById(R.id.see_view);

        /*if(!mIsShow1 && !mIsShow2){
//            mWhite.setVisibility(View.GONE);
            mInfo.setVisibility(View.GONE);
            mShow_2.setVisibility(View.GONE);
            mShow_1.setVisibility(View.GONE);

        }*/
        mBtnRead.setOnClickListener(this);
        mBtnWrite.setOnClickListener(this);
        mBtnFhj.setOnClickListener(this);
        mBtnHj.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.write:
                tv.setVisibility(View.VISIBLE);

                myAnimation_Translate = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -1,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0);
                myAnimation_Translate.setDuration(1000);
                myAnimation_Translate.setInterpolator(AnimationUtils
                        .loadInterpolator(SelectModeActivity.this,
                                android.R.anim.accelerate_decelerate_interpolator));
                tv.startAnimation(myAnimation_Translate);
                break;
            /*case R.id.write:
//                mShow_2.setVisibility(View.GONE);
                if(mInfo.getVisibility() == View.VISIBLE && mShow_2.getVisibility()==View.VISIBLE){
                    //mInfo.setVisibility(View.GONE);
                    mShow_2.setVisibility(View.GONE);
                    mShow_1.setVisibility(View.VISIBLE);
                }else if(mInfo.getVisibility() == View.VISIBLE && mShow_2.getVisibility()==View.GONE){
                    mInfo.setVisibility(View.GONE);
                    mShow_2.setVisibility(View.GONE);
                    mShow_1.setVisibility(View.GONE);
                }
                else{
                    mInfo.setVisibility(View.VISIBLE);
                    mShow_1.setVisibility(View.VISIBLE);
                    mShow_2.setVisibility(View.GONE);
                }
                break;
            case R.id.read:
//                mShow_1.setVisibility(View.GONE);
                if(mInfo.getVisibility() == View.VISIBLE && mShow_1.getVisibility()==View.VISIBLE){
//                    mInfo.setVisibility(View.GONE);
                    mShow_1.setVisibility(View.GONE);
                    mShow_2.setVisibility(View.VISIBLE);

                }else if(mInfo.getVisibility() == View.VISIBLE && mShow_1.getVisibility()==View.GONE){
                    mInfo.setVisibility(View.GONE);
                    mShow_2.setVisibility(View.GONE);
                    mShow_1.setVisibility(View.GONE);
                }else{
                    mInfo.setVisibility(View.VISIBLE);
                    mShow_2.setVisibility(View.VISIBLE);
                    mShow_1.setVisibility(View.GONE);
                }
                break;*/
            case R.id.huji:
                intent = new Intent(SelectModeActivity.this,BaseInfoHJActivity.class);
                FunctionHelper.isModify = false;
                FunctionHelper.inMap.clear();
                startActivity(intent);
                break;
            case R.id.feihuji:
                intent = new Intent(SelectModeActivity.this,BaseInfoFHJActivity.class);
                FunctionHelper.isModify = false;
                FunctionHelper.inMap.clear();
                startActivity(intent);
                break;
            case R.id.login:
//                intent = new Intent(SelectModeActivity.this, Auto_Complet_Activity.class);
//                startActivity(intent);
                break;

        }
    }
}

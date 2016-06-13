package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.PopWindow;
import com.example.ttb.regisn.util.SelectPicPopupWindow;
import com.example.ttb.regisn.util.ServerAsynTask;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

public class MainActivity extends AppCompatActivity {
    private Button mWrite,mLogin;
    private ButtonFlat mHuji,mFeihuji;
    private Animation myAnimation_Translate;
    private LinearLayout tv;
    private SelectPicPopupWindow menuWindow;

    private Intent intent;

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.cp_hj:
                    intent = new Intent(MainActivity.this,BaseInfoHJActivity.class);
                    FunctionHelper.isModify = false;
                    startActivity(intent);
                    break;
                case R.id.cp_fhj:
                    intent = new Intent(MainActivity.this,BaseInfoFHJActivity.class);
                    FunctionHelper.isModify = false;
                    startActivity(intent);
                    break;
                default:
                    break;
            }


        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportActionBar().hide();

        mWrite = (Button) findViewById(R.id.write);
        mLogin = (Button) findViewById(R.id.login);

        mWrite.setOnClickListener(new View.OnClickListener() {
            AlertDialog dlg = null;
            Button hj = null;
            Button fhj = null;
            Button cancle = null;
            @Override
            public void onClick(View view) {
                menuWindow = new SelectPicPopupWindow(MainActivity.this,itemsOnClick);
                menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


//                this.dlg = new AlertDialog.Builder(MainActivity.this).create();
//                View localView = MainActivity.this.getLayoutInflater().inflate(R.layout.clearpan,null);
//                localView.setAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_bottom_to_top));
////                localView.getBackground().setAlpha(100);
//
//                Window localWindow = this.dlg.getWindow();
//                WindowManager.LayoutParams lp = localWindow.getAttributes();
//                this.dlg.show();
//                lp.alpha = 0.7f;
//
//                localWindow.setContentView(localView);
//                localWindow.setGravity(Gravity.BOTTOM);
//
//                localWindow.setLayout(-1,400);
//
//                this.hj = (Button)this.dlg.findViewById(R.id.cp_hj);
//                this.fhj = (Button)this.dlg.findViewById(R.id.cp_fhj);
//                this.cancle = (Button)this.dlg.findViewById(R.id.cp_cancle);
//
//                this.hj.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dlg.cancel();
//                        intent = new Intent(MainActivity.this,BaseInfoHJActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                this.fhj.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        intent = new Intent(MainActivity.this,BaseInfoFHJActivity.class);
//                        startActivity(intent);
//                        dlg.cancel();
//                    }
//                });
//                this.cancle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dlg.cancel();
//                    }
//                });
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

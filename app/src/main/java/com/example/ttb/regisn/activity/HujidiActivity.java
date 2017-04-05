package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.ServerCountiesAsynTask;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

public class HujidiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button next;
    private View m_Masker;
    private OptionsPickerView m_OptionMenu;
    private TextView m_Hukouxingzhi,m_Liudongrenkouzhuangkuang,m_Sheng,m_Shi,m_Xian;
    private String mSelhukouxingzhi,mSelliudongrenkouzhuangkuang,mSelsheng,mSelshi,mSelxian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hujidi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("户籍地");

        if(FunctionHelper.isModify){
//            Utils.setString2InMap(FunctionHelper.testStr);
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }


        initView();
        setListner();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                View viewtemp = this.getWindow().getDecorView();
                OutPut.setTempMap(OutPut.getAllChildViews(viewtemp));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        View viewtemp = this.getWindow().getDecorView();
        OutPut.setTempMap(OutPut.getAllChildViews(viewtemp));
        OutPut.setInMap(OutPut.getAllChildViews(viewtemp));
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hukouxingzhi:
                Utils.initPickViewData(m_Hukouxingzhi,"请选择身份证类型",m_OptionMenu, FunctionHelper.hukouxingzhiList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.liudongrenkouzhuangkuang:
                Utils.initPickViewData(m_Liudongrenkouzhuangkuang,"请选择身份证类型",m_OptionMenu, FunctionHelper.liudongrenkouList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.hujidisheng:
                Utils.initPickViewData(m_Sheng,"请选择身份证类型",m_OptionMenu, FunctionHelper.provinces,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.hujidishi:
                if(m_Sheng.getText().equals("山东省")){
                    Utils.initPickViewData(m_Shi,"请选择身份证类型",m_OptionMenu, FunctionHelper.city1,m_Masker);
                    m_OptionMenu.show();
                }else{
                    m_Shi.setText("-");
                }

                break;
            case R.id.hujidixian:
                if(m_Shi.getText().length() > 0 && !m_Shi.getText().equals("－请选择－") && !m_Shi.getText().equals("-")){
//                    new ServerCountiesAsynTask().execute(m_Shi.getText());
//                    new ServerCountiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,m_Shi.getText());
//                    Utils.initPickViewData(m_Xian,"请选择身份证类型",m_OptionMenu, FunctionHelper.country,m_Masker);
                    Utils.initPickViewData(m_Xian,"请选择身份证类型",m_OptionMenu,FunctionHelper.country0405.get(m_Shi.getText()),m_Masker);
                    m_OptionMenu.show();
                }else{
                    m_Xian.setText("-");
                }
//                Utils.initPickViewData(m_Minzuyuyan,"请选择身份证类型",m_OptionMenu, FunctionHelper.languageList,m_Masker);
//                m_OptionMenu.show();
                break;
        }
    }

    private void initView(){
        next = (Button)findViewById(R.id.next);
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);


        m_Hukouxingzhi = (TextView)findViewById(R.id.hukouxingzhi);
        m_Liudongrenkouzhuangkuang = (TextView)findViewById(R.id.liudongrenkouzhuangkuang);
        m_Sheng = (TextView)findViewById(R.id.hujidisheng);
        m_Shi = (TextView)findViewById(R.id.hujidishi);
        m_Xian = (TextView)findViewById(R.id.hujidixian);
    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = HujidiActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                Intent intent = new Intent(HujidiActivity.this,NowAddrActivity.class);
                intent.putExtra("type","FHJ");
                startActivity(intent);
            }
        });

        m_Hukouxingzhi.setOnClickListener(this);
        m_Liudongrenkouzhuangkuang.setOnClickListener(this);
        m_Sheng.setOnClickListener(this);
        m_Shi.setOnClickListener(this);
        m_Xian.setOnClickListener(this);


    }
}

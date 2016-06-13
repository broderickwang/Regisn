package com.example.ttb.regisn.mycontrols;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by ttb on 16/4/11.
 */
public class SelectDialog extends AlertDialog {
    public SelectDialog(Context context, int theme) {
        super(context, theme);
    }
    public SelectDialog(Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

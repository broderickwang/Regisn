package com.example.ttb.regisn.activity;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.PCAAsynTask;
import com.example.ttb.regisn.util.ServerCitiesAsynTask;
import com.example.ttb.regisn.util.ServerJiedaoAsynTask;
import com.example.ttb.regisn.util.ServerProvinceAsynTask;
import com.example.ttb.regisn.util.ServerQDCountiesAsynTask;

public class BackService extends Service {
	public static final String TAG = "BackService";

	private MyBinder mBinder = new MyBinder();

	public class MyBinder extends Binder {
		public BackService getService() {
			return BackService.this;
		}
	}

	public BackService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		new ServerProvinceAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		new ServerCitiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		new ServerQDCountiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		new ServerJiedaoAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


		FunctionHelper.hujidiisequaltonow.add(new InfoBean("1","是-y",""));
		FunctionHelper.hujidiisequaltonow.add(new InfoBean("0","否-n",""));
		FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
		FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

		FunctionHelper.hujidiisnow.add(new InfoBean("1","是-y",""));
		FunctionHelper.hujidiisnow.add(new InfoBean("0","否-n",""));
		FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
		FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

		FunctionHelper.shifoushouguoxqjy.add(new InfoBean("1","是-y",""));
		FunctionHelper.shifoushouguoxqjy.add(new InfoBean("0","否-n",""));
		FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
		FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

		FunctionHelper.gongzuoxingzhi.add(new InfoBean("0","务工",""));
		FunctionHelper.gongzuoxingzhi.add(new InfoBean("1","个体",""));

		FunctionHelper.shifoujianhuaren.clear();
		FunctionHelper.shifoujianhuaren.add(new InfoBean("1","是-y",""));
		FunctionHelper.shifoujianhuaren.add(new InfoBean("0","否-n",""));
		FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
		FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

		FunctionHelper.ertong2huzhu.add(new InfoBean("子","子",""));
		FunctionHelper.ertong2huzhu.add(new InfoBean("女","女",""));
		FunctionHelper.ertong2huzhu.add(new InfoBean("孙子","孙子",""));
		FunctionHelper.ertong2huzhu.add(new InfoBean("孙女","孙女",""));
		FunctionHelper.ertong2huzhu.add(new InfoBean("外孙子","外孙子",""));
		FunctionHelper.ertong2huzhu.add(new InfoBean("外孙女","外孙女",""));
		FunctionHelper.zongList.add(new InfoBean("子","子",""));
		FunctionHelper.zongList.add(new InfoBean("女","女",""));
		FunctionHelper.zongList.add(new InfoBean("孙子","孙子",""));
		FunctionHelper.zongList.add(new InfoBean("孙女","孙女",""));
		FunctionHelper.zongList.add(new InfoBean("外孙子","外孙子",""));
		FunctionHelper.zongList.add(new InfoBean("外孙女","外孙女",""));

		FunctionHelper.fangwulaiyuan.add(new InfoBean("自有","自有",""));
		FunctionHelper.fangwulaiyuan.add(new InfoBean("租赁","租赁",""));
		FunctionHelper.zongList.add(new InfoBean("自有","自有",""));
		FunctionHelper.zongList.add(new InfoBean("租赁","租赁",""));
		initTeshuAddr();

		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	private void initTeshuAddr(){
		String[] a = new String[]{
				"颐中银街D区","颐中银街E区","错埠岭靶场","错埠岭东舍","延安路37003委","长山路2号平房",
				"长山路2号筒子楼","长山路2号楼房","国棉二厂楼房","钢舍","电焊条宿舍","北山二路1号","三厂三舍",
				"舞阳路自来水宿舍","舞阳路电车站大楼","广昌路1-1","广昌路5-3","宜昌路31-1","兴元路1号楼","市场二路小区"
		};
		for(int i=0;i<a.length;i++) {
			FunctionHelper.teshuaddrlist.add(new InfoBean(a[i],a[i],""));
			FunctionHelper.zongList.add(new InfoBean(a[i],a[i],""));
		}
	}
}

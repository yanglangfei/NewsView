package com.ylf.jucaipen.newsview.com.ylf.jucaipen.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.newsview.R;
import com.ylf.jucaipen.newsview.com.ylf.jucaipen.view.MyPainBoard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/11/5.
 *
 *   画图并保存图片
 */
public class MainPainActivity extends Activity {
    @ViewInject(R.id.paintBoard2)
    private MyPainBoard paintBoard2;
    private  String fineName[];
    private int mScreenWidth;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_board);
        ViewUtils.inject(this);
        initView();

    }

    private void initView() {
        WindowManager wm=getWindowManager();
        mScreenHeight=wm.getDefaultDisplay().getHeight();
        mScreenWidth=wm.getDefaultDisplay().getWidth();
    }

    /*
     保存图片
     */
    public void OnSaveClicked(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            OutputStream stream = new FileOutputStream(file);
            paintBoard2.saveBitmap(stream);
            stream.close();
            Intent intent = new Intent();
            //判断版本是不是4.4或者4.4 之上
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                String[] paths = new String[]{Environment.getExternalStorageDirectory().toString()};
                MediaScannerConnection.scanFile(this, paths, null, null);
            }else{
                intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
                intent.setData(Uri.fromFile(Environment
                        .getExternalStorageDirectory()));
            }
            sendBroadcast(intent);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /*
      打开图片
     */
    public void OnOpen(View view){
        File files=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if(!files.exists()){
            Toast.makeText(this,"文件不存在",Toast.LENGTH_SHORT).show();
            return;
        }
        if(files.isDirectory()){
            File file[]=files.listFiles();
            fineName=new String[file.length];
            for(int i=0;i<file.length;i++){
                String name=file[i].getName();
                fineName[i]=name;
            }
            createDialog();
        }

    }

    private void createDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("选择要打开的文件");
        builder.setSingleChoiceItems(fineName, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+fineName[which]);
                if (!f.exists()) {
                    Toast.makeText(MainPainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (f.isFile()) {
                    Bitmap bitmap= BitmapFactory.decodeFile(f.getAbsolutePath());
                    paintBoard2.setmBitmap(bitmap);
                }
                dialog.cancel();
            }

        });
        builder.show();
    }


    /*
    重置画布
     */
    public  void OnReset(View view){
        Bitmap mBitmap = Bitmap.createBitmap(mScreenWidth,mScreenHeight, Bitmap.Config.ARGB_8888);
        paintBoard2.setmBitmap(mBitmap);

    }
}

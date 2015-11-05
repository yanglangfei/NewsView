package com.ylf.jucaipen.newsview.com.ylf.jucaipen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.newsview.R;
import com.ylf.jucaipen.newsview.com.ylf.jucaipen.view.MyPainBoard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/11/5.
 */
public class MainPainActivity extends Activity {
    @ViewInject(R.id.paintBoard2)
    private MyPainBoard paintBoard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_board);
        ViewUtils.inject(this);

    }

    public void OnSaveClicked(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            OutputStream stream = new FileOutputStream(file);
            paintBoard2.saveBitmap(stream);
            stream.close();
            // send broadcast to Media to update data
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment
                    .getExternalStorageDirectory()));
            sendBroadcast(intent);
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "save failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

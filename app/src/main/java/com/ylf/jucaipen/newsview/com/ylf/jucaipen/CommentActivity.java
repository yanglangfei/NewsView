package com.ylf.jucaipen.newsview.com.ylf.jucaipen;

import android.app.Activity;
import android.graphics.Color;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.newsview.R;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.Process;

/**
 * Created by Administrator on 2015/11/5.
 */
public class CommentActivity extends Activity {
    @ViewInject(R.id.tv_show)
    private TextView tv_show;
    @ViewInject(R.id.input)
    private EditText input;
    @ViewInject(R.id.btn_submit)
    private Button btn_submit;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_comment);
        ViewUtils.inject(this);
        initView();
    }

    private void initView() {
        toast=Toast.makeText(this,"权限不足",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        LinearLayout linearLayout= (LinearLayout) toast.getView();
        ImageView iv=new ImageView(this);
        iv.setImageResource(R.drawable.title);
        linearLayout.addView(iv);
        linearLayout.setBackgroundColor(Color.GREEN);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String result=input.getText().toString();
                if (!TextUtils.isEmpty(result)) {
                    runRootCommand(result);
                    input.setText("");
                }
            }
        });
    }

    private void runRootCommand(String input) {
        Process process=null;
        try {
            process=Runtime.getRuntime().exec(input);
            StringBuffer buffer=new StringBuffer();
            DataInputStream dis=new DataInputStream(process.getInputStream());
            String line;
            while ((line = dis.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            process.wait();
            tv_show.setText(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            toast.show();
        }catch (InterruptedException e){
        }finally {
            if(process!=null) {
                process.destroy();
            }
        }

    }
}

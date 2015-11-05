package com.ylf.jucaipen.newsview;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends Activity {
    @ViewInject(R.id.news_content)
    private TextView news_content;
    private  String content="<p>\n" +
            "\t北京时间11月14日上午消息，《华尔街日报》报道，美国石油服务巨头哈里伯顿公司与另一家油田服务供应商贝克休斯公司展开收购洽谈。此桩交易的达成，将有助于两大油田服务企业共同渡过油价下跌难关。\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t知情人士透露，谈判进展的很迅速，可能很快便达成共识。当然，目前尚不能保证交易双方约定能达成交易，以及监管部门一定能为此桩交易亮绿灯。\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t交易的达成，很可能会拉高贝克休斯的市值。在《华尔街日报》曝光收购消息之前，该公司市值居于220亿美元下方。受此消息提振，贝克休斯股价上涨25%，市值增至260亿美元。哈里伯顿目前的市值在480亿美元左右。\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t就营收方面来讲，哈里伯顿和贝克休斯分别是全球第二大和第三大油田服务供应商，位列斯伦贝谢公司之后。\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t当前，油价的持续下跌，石油天然气生产利润大幅萎缩，致使客户要么削减运作，要么要求供应商提供更好的价格。受此拖累，油田服务企业苦不堪言\n" +
            "</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initView();
    }

    private void initView() {
        news_content.setText(Html.fromHtml(content));
        news_content.setLineSpacing(-8,1);
        


    }
}

package android.mobile.widget;

import android.content.Intent;
import android.mobile.widget.ui.BannerView.BannerViewActivity;
import android.mobile.widget.ui.TabAndViewPagerPage.TabAndViewPagerPageActivity;
import android.mobile.widget.ui.pullrefresh.PullRefreshActivity;
import android.mobile.widget.ui.toast.WealthToastAcitivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pull_refresh_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PullRefreshActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WealthToastAcitivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.banner_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BannerViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tab_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TabAndViewPagerPageActivity.class);
                startActivity(intent);
            }
        });
    }
}

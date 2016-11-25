package android.mobile.widget;

import android.content.Intent;
import android.mobile.widget.ui.pullrefresh.PullRefreshActivity;
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
    }
}

package com.comm.util.component.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class SerachActivity extends AppCompatActivity {
    public static final int REQUEST_TAG_SEARCH_ROOM = 233;
    public static final int RESULT_TAG_SEARCH_ROOM = 433;
    public static final String txtSearch = "xtSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);

        EditText etSearch = findViewById(R.id.tv_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //如果actionId是搜索的id，则进行下一步的操作
                    Intent intent = new Intent();
                    intent.putExtra(txtSearch, v.getText().toString());
                    setResult(RESULT_TAG_SEARCH_ROOM, intent);
//                    SearchRoomActivity.this.finish();
                    SerachActivity.this.finish();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.tv_cancel).setOnClickListener(v->{
            //                finish();
            startActivity(new Intent(this, ActivityB.class));
        });
    }
}

package com.comm.util.openlib.eventbus;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import org.greenrobot.eventbus.EventBus;

/**
 * @author Jon
 */
public class GoalEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_event);
        findViewById(R.id.bt_message_send).setOnClickListener(v->{
            new Thread(()->{
                EventBus.getDefault().post(new MessageEvent("我是 GoalEventActivity"));
            }).start();
        });
    }
}

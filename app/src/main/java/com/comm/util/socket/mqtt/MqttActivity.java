package com.comm.util.socket.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import timber.log.Timber;

public class MqttActivity extends AppCompatActivity {


    private final static String MSGHOST = "tcp://" + "dev.casanubeserver.com " + ":1883";

//    private final static String HOST = "tcp://120.76.215.143:1883";


    private final static String USER_NAME = "admin";
    private final static String PASSWORD = "admin";
    private final static int TIME_OUT = 30;
    private final String clientId = "clientId";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String message = String.valueOf(msg.obj);
            Timber.i("时间" + message);

        }
    };
    String mTopicPublish = "10000";
    private MqttAndroidClient mMqttClient;
    private MqttConnectOptions mMqttConnectOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);
        findViewById(R.id.bt_connect).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean b = MqttManager.getInstance().creatConnect(MSGHOST, USER_NAME, PASSWORD, clientId);
                    Timber.d("isConnected: " + b);
//                    init();
                }
            }).start();
        });
    }

    private void init() {
//        MemoryPersistence persistence = new MemoryPersistence();
        try {
//            mMqttClient = new MqttClient(HOST, mTopicFilters[0], persistence);
            mMqttClient = new MqttAndroidClient(this, MSGHOST, mTopicPublish);
            mMqttConnectOptions = new MqttConnectOptions();
//            mMqttConnectOptions.setServerURIs(new String[]{HOST});
//            mMqttConnectOptions.setCleanSession(true);
            mMqttConnectOptions.setKeepAliveInterval(30);
            mMqttConnectOptions.setUserName(USER_NAME);
            mMqttConnectOptions.setPassword(PASSWORD.toCharArray());
            mMqttConnectOptions.setConnectionTimeout(TIME_OUT);
            mMqttConnectOptions.setAutomaticReconnect(true);
            mMqttConnectOptions.setCleanSession(false);
            receiveMQMessage();

            if (!mMqttClient.isConnected()) {
                mMqttClient.connect(mMqttConnectOptions);
            }

        } catch (Exception e) {
            Timber.e("连接失败:" + e.getMessage() + ":" + MSGHOST);
            //CrashPost.//CrashPost("连接异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void receiveMQMessage() {
        mMqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectionLost(Throwable cause) {
                //将错误消息上报
                //CrashPost.//CrashPost("MQ断开连接" + cause.getMessage());

//                打印错误消息
                Timber.e("MQ断开连接：" + cause.getMessage());

                //重连
//                reconnect();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String messageStr = new String(message.getPayload());
                Timber.d("MQ接收数据" + messageStr + "---" + messageStr);
//                if (mIHandleMessage != null) {
//                    mIHandleMessage.message(messageStr);
//                    Timber.d("MQ_message", messageStr);
//                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Timber.d(String.valueOf(token.getMessageId()));
            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

                if (reconnect) {
                    Timber.d(mMqttClient.getClientId() + " Reconnected to : " + serverURI);
                    // Because Clean Session is true, we need to re-subscribe
//                    handler.sendEmptyMessageDelayed(0, 5000);
                } else {
                    Timber.d(mMqttClient.getClientId() + " Connected to: " + serverURI);
                }
            }
        });
    }
}

package com.comm.util.socket.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import timber.log.Timber;

public class MqttCallbackBus implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
//        Logger.e(cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        Timber.d(topic + "====" + message.toString());
//        EventBus.getDefault().post(message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
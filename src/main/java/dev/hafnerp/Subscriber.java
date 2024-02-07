package dev.hafnerp;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber {
    public static void main(String[] args) throws MqttException, InterruptedException {
        System.out.println("Welcome to the MQTT Client; by github.com/HaCoGames");

        String topic = "/school/4ahif/sensor/sensor1";

        MqttClient mqttClient;
        mqttClient = new MqttClient(
                "tcp://localhost:1883",
                MqttClient.generateClientId(),
                new MemoryPersistence()
        );

        MqttConnectOptions mqttConnectOptions;
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName("hafnerp");
        mqttConnectOptions.setPassword("hafnerp".toCharArray());

        mqttClient.connect(mqttConnectOptions);
        mqttClient.subscribe(topic);

        mqttClient.subscribe(topic, new IMqttMessageListener() {
            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println(s +"::"+ mqttMessage);
            }
        });
    }
}
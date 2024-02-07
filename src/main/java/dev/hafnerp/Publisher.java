package dev.hafnerp;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Publisher {
    public static void main(String[] args) throws MqttException {
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

        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Give me a message: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("EXIT")) throw new RuntimeException("EXITING");
                mqttClient.publish(
                        "/school/4ahif/sensor/sensor1",
                        message.getBytes(StandardCharsets.UTF_8),
                        0, //0... no check; 1... at least once; 2... Only one and ensured!
                        true
                );
            }
        } catch (RuntimeException ignore) {
            System.out.println("SOMETHING");
            System.exit(0);
        }
        mqttClient.disconnect();

    }
}

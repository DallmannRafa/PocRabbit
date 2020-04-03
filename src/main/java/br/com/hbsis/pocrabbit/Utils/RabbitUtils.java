package br.com.hbsis.pocrabbit.Utils;

import br.com.hbsis.pocrabbit.XMLManagement.ConsumerServiceThree;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class RabbitUtils {

    private static ConnectionFactory factory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setPort (5672);

        return factory;
    }

    public static Channel makeConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = factory();

        Connection connection = factory.newConnection();

        return connection.createChannel();

    }

    public static void send(String mensagem, String routingKey) throws IOException, TimeoutException {
        ConnectionFactory factory = factory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("logs", "direct");

            channel.basicPublish("logs", routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent '" + mensagem + "'");
        }

    }
}

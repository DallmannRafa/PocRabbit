package br.com.hbsis.pocrabbit.XMLManagement;

import br.com.hbsis.pocrabbit.Cargo.CargoService;
import br.com.hbsis.pocrabbit.Utils.RabbitUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class ConsumerServiceTwo {

    private final CargoService cargoService;

    public ConsumerServiceTwo(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void Reciever() throws IOException, TimeoutException {
        Channel channel = RabbitUtils.makeConnection();

        channel.exchangeDeclare("logs", "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs", "messageA");
        channel.basicQos(1);

        log.info(" [*] [ServiceTwo] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            log.info(" [x] [Reciever2] Received '" + message + "'");

            try {
                cargoService.saveFromXML(message);
            } finally {
                log.info(" [x] [Reciever2] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };

        boolean autoAck = false;

        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> { });
    }

}

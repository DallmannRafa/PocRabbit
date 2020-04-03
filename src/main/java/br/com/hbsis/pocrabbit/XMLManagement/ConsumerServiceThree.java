package br.com.hbsis.pocrabbit.XMLManagement;

import br.com.hbsis.pocrabbit.Cargo.CargoService;
import br.com.hbsis.pocrabbit.Utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class ConsumerServiceThree {

    private final CargoService cargoService;

    @Autowired
    public ConsumerServiceThree(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void reciever() throws IOException, TimeoutException {

        Channel channel = RabbitUtils.makeConnection();

        channel.exchangeDeclare("logs", "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs", "messageB");
        channel.basicQos(1);

        log.info(" [*] [ServiceThree] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            log.info(" [x] [Reciever3] Received '" + message + "'");
            try {
                saveMessageB(message);
            } finally {
                log.info(" [x] [Reciever3] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };

        boolean autoAck = false;

        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> { });
    }

    public void saveMessageB(String xml) {

        cargoService.saveFromXML(xml);
    }
}

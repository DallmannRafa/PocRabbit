package br.com.hbsis.pocrabbit.XMLManagement;

import br.com.hbsis.pocrabbit.Utils.RabbitUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class ConsumerServiceOne {

    public void reciever() throws IOException, TimeoutException {

        Channel channel = RabbitUtils.makeConnection();

        channel.exchangeDeclare("logs", "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs", "messageA");
        channel.basicQos(1);

        log.info(" [*] [ServiceOne] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            log.info(" [x] [Reciever1] Received '" + message + "'");

            try {
                editaXML(message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            log.info(" [x] [Reciever1] Done");

        };

        boolean autoAck = false;

        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> { });

    }

    private void editaXML(String string) throws IOException, TimeoutException {
        LocalDate data = LocalDate.now();
        string = string.replaceAll("<situacao.*>((.|\\n)*?)<\\/situacao>", "<situacao>entregue</situacao>\n" + "    <datarecebimento>" + data + "</datarecebimento>");

        RabbitUtils.send(string, "messageB");
    }

}

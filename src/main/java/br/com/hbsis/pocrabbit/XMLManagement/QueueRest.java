package br.com.hbsis.pocrabbit.XMLManagement;

import br.com.hbsis.pocrabbit.Utils.RabbitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/queue")
public class QueueRest {

    private final RabbitUtils sender;
    private final ConsumerServiceOne consumer;
    private final ConsumerServiceTwo consumer2;
    private final ConsumerServiceThree consumer3;

    @Autowired
    public QueueRest(RabbitUtils sender, ConsumerServiceOne consumer, ConsumerServiceTwo consumer2, ConsumerServiceThree consumer3) throws IOException, TimeoutException {
        this.sender = sender;
        this.consumer = consumer;
        this.consumer2 = consumer2;
        this.consumer3 = consumer3;
        consumer.reciever();
        consumer2.Reciever();
        consumer3.reciever();
    }

    @PostMapping
    public String send(@RequestBody String string) {

        try {
            RabbitUtils.send(string, "messageA");
            return "Mensagem enviada";

        } catch (Exception e) {
            return "Erro ao enviar mensagem " + e;
        }

    }
}

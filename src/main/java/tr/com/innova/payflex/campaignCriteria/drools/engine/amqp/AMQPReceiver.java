package tr.com.innova.payflex.campaignCriteria.drools.engine.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by meyildirim on 20-Jan-17.
 */
@Component
@Slf4j
public class AMQPReceiver {

    public String handleMessage(String message) {
        log.info("Received message: {}", message);
        return "test_result";
    }
}

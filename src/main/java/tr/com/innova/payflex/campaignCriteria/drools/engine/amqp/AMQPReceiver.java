package tr.com.innova.payflex.campaignCriteria.drools.engine.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.innova.payflex.campaignCriteria.drools.engine.service.CampaignCriteriaScenario1Service;

/**
 * Created by meyildirim on 20-Jan-17.
 */
@Component
@Slf4j
public class AMQPReceiver {

    private final CampaignCriteriaScenario1Service campaignCriteriaScenario1Service;

    @Autowired
    public AMQPReceiver(CampaignCriteriaScenario1Service campaignCriteriaScenario1Service) {
        this.campaignCriteriaScenario1Service = campaignCriteriaScenario1Service;
    }

    public String handleMessage(String message) {
        log.info("Received message: {}", message);

        String result = campaignCriteriaScenario1Service.getCampaignEvent(message).toString();

        return (result != null) ? result : "Not match found!";
    }
}

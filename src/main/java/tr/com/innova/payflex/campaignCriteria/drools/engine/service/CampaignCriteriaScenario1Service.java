package tr.com.innova.payflex.campaignCriteria.drools.engine.service;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;
import tr.com.innova.payflex.campaignCriteria.drlGenerator.entity.DroolsCampaignEvent;
import tr.com.innova.payflex.campaignCriteria.drlGenerator.entity.DroolsCampaignMatch;

import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.List;

/**
 * Created by mehmet on 22.01.2017.
 */

@Service
@Slf4j
public class CampaignCriteriaScenario1Service {

    private final KieContainer kieContainer;

    private KieSession kieSession;

    private KieRuntimeLogger kieRuntimeLogger;

    public CampaignCriteriaScenario1Service(KieContainer kieContainer) {
        this.kieContainer = kieContainer;

        kieSession = kieContainer.newKieSession("PayflexCampaignCriteria-Scenario1-KS");

        kieRuntimeLogger = KieServices.Factory.get().getLoggers().newConsoleLogger(kieSession);
    }

    public DroolsCampaignMatch getCampaignEvent(String segment) {

        DroolsCampaignMatch match = new DroolsCampaignMatch();

        DroolsCampaignEvent droolsEvent = new DroolsCampaignEvent();
        droolsEvent.setValue("Timestamp", "2016-12-27 16:34:21");

        droolsEvent.setEndUserField("Segment", segment); // previously hardcoded as "VIP"
        droolsEvent.setEndUserField("Gender", "F");
        droolsEvent.setEndUserField("Birthdate", "1980-12-27 00:00:00");

        kieSession.insert(droolsEvent);
        kieSession.fireAllRules();

        List<DroolsCampaignMatch> droolsCampaignMatches = droolsEvent.getDroolsCampaignMatches();
        kieRuntimeLogger.close();

        for(DroolsCampaignMatch matchItem : droolsCampaignMatches) {
            log.info(matchItem.toString());
            match = matchItem;
        }

        return match;
    }

    @PreDestroy
    public void destroy() {
        kieSession.dispose();
    }
}

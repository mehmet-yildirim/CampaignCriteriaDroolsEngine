package tr.com.innova.payflex.campaignCriteria.drools.engine.service;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;
import tr.com.innova.payflex.campaignCriteria.drlGenerator.entity.DroolsCampaignEvent;

import java.util.Collection;

/**
 * Created by mehmet on 22.01.2017.
 */

@Service
@Slf4j
public class CampaignCriteriaService {

    private final KieContainer kieContainer;

    private KieSession kieSession;

    public CampaignCriteriaService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;

        kieSession = kieContainer.newKieSession("PayflexCampaignScenariosKS");

    }

    public void getCampaignEvent() {
        DroolsCampaignEvent droolsEvent = new DroolsCampaignEvent();
        droolsEvent.setValue("Timestamp", "2016-12-27 16:34:21");

        droolsEvent.setEndUserField("Segment", "VIP");
        droolsEvent.setEndUserField("Gender", "F");
        droolsEvent.setEndUserField("Birthdate", "1980-12-27 00:00:00");

        kieSession.insert(droolsEvent);
        kieSession.fireAllRules();
    }
}

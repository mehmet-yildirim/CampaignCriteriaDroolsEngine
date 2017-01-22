package tr.com.innova.payflex.campaignCriteria.drools.engine.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.payflex.campaignCriteria.drools.engine.service.CampaignCriteriaService;

/**
 * Created by meyildirim on 20-Jan-17.
 */
@RestController
@Slf4j
public class CampaignScenarioController {

    private final CampaignCriteriaService campaignCriteriaService;

    public CampaignScenarioController(CampaignCriteriaService campaignCriteriaService) {
        this.campaignCriteriaService = campaignCriteriaService;
    }

    @RequestMapping(value = "/getCampaign")
    public void getAllFacts() {

        campaignCriteriaService.getCampaignEvent();
    }

}

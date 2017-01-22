package tr.com.innova.payflex.campaignCriteria.drools.engine.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.payflex.campaignCriteria.drlGenerator.entity.DroolsCampaignMatch;
import tr.com.innova.payflex.campaignCriteria.drools.engine.service.CampaignCriteriaScenario1Service;

/**
 * Created by meyildirim on 20-Jan-17.
 */
@RestController
@Slf4j
public class CampaignScenarioController {

    private final CampaignCriteriaScenario1Service campaignCriteriaScenario1Service;

    public CampaignScenarioController(CampaignCriteriaScenario1Service campaignCriteriaScenario1Service) {
        this.campaignCriteriaScenario1Service = campaignCriteriaScenario1Service;
    }

    @RequestMapping(value = "/getCampaign")
    public @ResponseBody
    DroolsCampaignMatch getAllFacts(@RequestParam(value = "segment") String segment) {

        return campaignCriteriaScenario1Service.getCampaignEvent(segment);
    }

}

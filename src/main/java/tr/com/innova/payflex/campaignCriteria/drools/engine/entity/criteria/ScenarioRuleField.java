package tr.com.innova.payflex.campaignCriteria.drools.engine.entity.criteria;

import lombok.Data;

/**
 * Created by meyildirim on 20-Jan-17.
 */
@Data
public class ScenarioRuleField {

    private String fieldName;

    private String fieldValue;

    private String condition;

    private String fieldType;

}

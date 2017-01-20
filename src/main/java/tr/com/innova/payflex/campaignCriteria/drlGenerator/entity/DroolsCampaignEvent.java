package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DroolsCampaignEvent {

    private HashMap<String, Object> _properties;
    private HashMap<String, Object> _endUserFields;
    private HashMap<String, Object> _currentEventFields;
    private List<HashMap<String, Object>> _pastEvents;
    private List<HashMap<String, Object>> _pastBenefits;
    private List<DroolsCampaignMatch> _droolsCampaignMatches;
    private Map<Integer, DroolsCampaignAudit> _droolsCampaignAudit;

    public DroolsCampaignAudit getDroolsCampaignAuditByRuleNo(Integer ruleNo) {
        if (_droolsCampaignAudit.get(ruleNo) != null)
            return _droolsCampaignAudit.get(ruleNo);
        else
            return DroolsCampaignAudit.NewEventReceived;
    }

    public Map<Integer, DroolsCampaignAudit> getDroolsCampaignAudit() {
        return _droolsCampaignAudit;
    }

    public void updateDroolsCampaignAuditByRuleNo(Integer ruleNo, DroolsCampaignAudit audit) {
        this._droolsCampaignAudit.put(ruleNo, audit);
    }

    public void addDroolsCampaignAudit(Integer ruleNo, DroolsCampaignAudit audit) {
        this._droolsCampaignAudit.put(ruleNo, audit);
    }

    public DroolsCampaignEvent() {
        _properties = new HashMap<>();
        _endUserFields = new HashMap<>();
        _currentEventFields = new HashMap<>();
        _pastEvents = new ArrayList<HashMap<String, Object>>();
        _pastBenefits = new ArrayList<HashMap<String, Object>>();
        _droolsCampaignMatches = new ArrayList<DroolsCampaignMatch>();
        _droolsCampaignAudit = new HashMap<Integer, DroolsCampaignAudit>();
    }

    public void addDroolsCampaignMatch(String campaignId, String ruleNo, String priority) {
        _droolsCampaignMatches.add(new DroolsCampaignMatch(campaignId, ruleNo, priority));
    }

    public List<DroolsCampaignMatch> getDroolsCampaignMatches() {
        return this._droolsCampaignMatches;
    }

    public void addPastEvent(HashMap<String, Object> fields) {
        _pastEvents.add(fields);
    }

    public void addPastBenefit(HashMap<String, Object> fields) {
        _pastBenefits.add(fields);
    }

    public void setCurrentEventField(String key, Object value) {
        this._currentEventFields.put(key, value);
    }

    public List<HashMap<String, Object>> getPastBenefits() {
        return _pastBenefits;
    }

    public Object getPastEvents() {
        return _pastEvents;
    }

    public Object getCurrentEventField(String key) {
        return _currentEventFields.get(key);
    }

    public Object getEndUserField(String key) {
        return _endUserFields.get(key);
    }


    public void setEndUserField(String key, Object value) {
        this._endUserFields.put(key, value);
    }

    public Object getValue(String key) {
        return this._properties.get(key);
    }

    public void setValue(String key, Object value) {
        this._properties.put(key, value);
    }
}

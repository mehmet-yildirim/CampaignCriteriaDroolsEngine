package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class DroolsCampaignMatch {
    private int _campaignId;

    public int getCampaignId() {
        return _campaignId;
    }

    public void setCampaignId(int campaignId) {
        this._campaignId = campaignId;
    }

    public int getRuleNo() {
        return _ruleNo;
    }

    public void setRuleNo(int ruleNo) {
        this._ruleNo = ruleNo;
    }

    public int getPriority() {
        return _priority;
    }

    public void setPriority(int priority) {
        this._priority = priority;
    }

    private int _ruleNo;
    private int _priority;

    public DroolsCampaignMatch() {

    }

    public DroolsCampaignMatch(String campaignId, String ruleNo, String priority) {
        _campaignId = Integer.parseInt(campaignId);
        _ruleNo = Integer.parseInt(ruleNo);
        _priority = Integer.parseInt(priority);
    }
}

package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public enum DroolsCampaignAudit {
    NewEventReceived,
    DroolsCampaignEventValidated,
    EndUserCriteriasAreSatisfied,
    CurrentEventCriteriasAreSatisfied,
    PastEventsCriteriasAreSatisfied,
    PastBenefitsCriteriasAreSatisfied,
    BenefitLimitCriteriasAreSatisfied,
    RecurrenceCriteriasAreSatisfied,
    AllRuleCriteriasAreSatisfied;

    public DroolsCampaignAudit getNext() {
        return this.ordinal() < DroolsCampaignAudit.values().length - 1
                ? DroolsCampaignAudit.values()[this.ordinal() + 1]
                : null;
    }

    
}

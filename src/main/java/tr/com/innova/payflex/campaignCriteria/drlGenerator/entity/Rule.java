package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class Rule
{
    private EndUserCriterias EndUserCriterias;

    private String RuleNo;
    
    private String Priority;

    public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	private CurrentEventCriterias CurrentEventCriterias;

    public EndUserCriterias getEndUserCriterias ()
    {
        return EndUserCriterias;
    }

    public void setEndUserCriterias (EndUserCriterias EndUserCriterias)
    {
        this.EndUserCriterias = EndUserCriterias;
    }

    public String getRuleNo ()
    {
        return RuleNo;
    }

    public void setRuleNo (String RuleNo)
    {
        this.RuleNo = RuleNo;
    }

    public CurrentEventCriterias getCurrentEventCriterias ()
    {
        return CurrentEventCriterias;
    }

    public void setCurrentEventCriterias (CurrentEventCriterias CurrentEventCriterias)
    {
        this.CurrentEventCriterias = CurrentEventCriterias;
    }
    
    private PastEventCriterias[] PastEventCriterias;

    public PastEventCriterias[] getPastEventCriterias() {
		return PastEventCriterias;
	}

	public void setPastEventCriterias(PastEventCriterias[] pastEvents) {
		PastEventCriterias = pastEvents;
	}
	
	private PastBenefitCriterias[] PastBenefitCriterias;

    public PastBenefitCriterias[] getPastBenefitCriterias() {
		return PastBenefitCriterias;
	}

	public void setPastBenefitCriterias(PastBenefitCriterias[] pastBenefits) {
		PastBenefitCriterias = pastBenefits;
	}
	
	private BenefitLimit BenefitLimit;

	public BenefitLimit getBenefitLimit() {
		return BenefitLimit;
	}

	public void setBenefitLimit(BenefitLimit benefitLimit) {
		BenefitLimit = benefitLimit;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [EndUserCriterias = "+EndUserCriterias+", RuleNo = "+RuleNo+", CurrentEventCriterias = "+CurrentEventCriterias+"]";
    }
}
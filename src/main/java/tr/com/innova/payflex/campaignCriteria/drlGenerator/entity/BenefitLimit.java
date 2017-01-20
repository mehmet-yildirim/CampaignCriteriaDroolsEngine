package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class BenefitLimit {
	public int getLimitValue() {
		return LimitValue;
	}
	public void setLimitValue(int limitValue) {
		LimitValue = limitValue;
	}
	public String getLimitPeriod() {
		return LimitPeriod;
	}
	public void setLimitPeriod(String limitPeriod) {
		LimitPeriod = limitPeriod;
	}
	private int LimitValue;
	private String LimitPeriod;
}

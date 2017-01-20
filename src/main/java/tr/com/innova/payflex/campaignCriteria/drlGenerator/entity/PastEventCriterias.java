package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class PastEventCriterias
{
	private int MaxCount;
	
	public int getMaxCount() {
		return MaxCount;
	}

	public void setMaxCount(int maxCount) {
		MaxCount = maxCount;
	}

	private int MinCount;
	
    public int getMinCount() {
		return MinCount;
	}

	public void setMinCount(int count) {
		MinCount = count;
	}

	private Fields[] Fields;

    public Fields[] getFields ()
    {
        return Fields;
    }

    public void setFields (Fields[] Fields)
    {
        this.Fields = Fields;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Fields = "+Fields+"]";
    }
}

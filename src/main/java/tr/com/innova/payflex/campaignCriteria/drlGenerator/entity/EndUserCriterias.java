package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class EndUserCriterias {
    private Fields[] Fields;

    public Fields[] getFields() {
        return Fields;
    }

    public void setFields(Fields[] Fields) {
        this.Fields = Fields;
    }

    @Override
    public String toString() {
        return "ClassPojo [Fields = " + Fields + "]";
    }
}

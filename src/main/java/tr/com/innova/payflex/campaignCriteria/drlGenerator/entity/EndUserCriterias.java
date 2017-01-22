package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

import java.util.Arrays;

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
        return "EndUserCriterias{" +
                "Fields=" + Arrays.toString(Fields) +
                '}';
    }
}

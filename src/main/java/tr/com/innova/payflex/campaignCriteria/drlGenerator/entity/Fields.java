package tr.com.innova.payflex.campaignCriteria.drlGenerator.entity;

public class Fields {
    private String Condition;

    private String FieldValue;

    private String FieldType;

    private String FieldName;

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public String getFieldValue() {
        return FieldValue;
    }

    public void setFieldValue(String FieldValue) {
        this.FieldValue = FieldValue;
    }

    public String getFieldType() {
        return FieldType;
    }

    public void setFieldType(String FieldType) {
        this.FieldType = FieldType;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    @Override
    public String toString() {
        return "ClassPojo [Condition = " + Condition + ", FieldValue = " + FieldValue + ", FieldType = " + FieldType + ", FieldName = " + FieldName + "]";
    }
}

package mti.com.telegram.model;

public enum NumberType {
    DECIMAL("Decimal"),
    DOUBLE("Double"),
    FLOAT("Float"),
    INT("Integer"),
    LONG("Long"),
    SHORT("Short");

    String numberType;

    NumberType(String paramString1) {
        this.numberType = paramString1;
    }

    public String getNumberType() {
        return this.numberType;
    }
}

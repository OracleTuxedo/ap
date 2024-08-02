package mti.com.telegram.model;

public enum FieldType {
    STRING("String"),
    CHAR("Char"),
    BYTES("Bytes"),
    NUMBER("Number"),
    LIST("List"),
    VO("VO");

    String typeName;

    FieldType(String paramString1) {
        this.typeName = paramString1;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
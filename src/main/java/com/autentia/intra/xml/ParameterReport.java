package com.autentia.intra.xml;


public class ParameterReport {

    private String name;
    private String description;
    private String defaultValue;

    public ParameterReport() {

    }

    public ParameterReport(String name, String description, String defaultValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Report Details - ");
        sb.append("Name:" + getName());
        sb.append(", ");
        sb.append("Description:" + getDescription());
        sb.append(", ");
        sb.append("Default Value:" + getDefaultValue());
        sb.append(".");

        return sb.toString();
    }

}

package com.message.processor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productType",
        "value",
        "sales",
        "operation"
})
public class Message {
    @JsonProperty("productType")
    private String productType;

    @JsonProperty("value")
    private Float value;

    @JsonProperty("sales")
    private Integer sales;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("productType")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("productType")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("value")
    public Float getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Float value) {
        this.value = value;
    }

    @JsonProperty("sales")
    public Integer getSales() {
        return sales;
    }

    @JsonProperty("sales")
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getProductType(), message.getProductType()) &&
                Objects.equals(getValue(), message.getValue()) &&
                Objects.equals(getSales(), message.getSales()) &&
                Objects.equals(getOperation(), message.getOperation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getValue(), getSales(), getOperation());
    }

    @Override
    public String toString() {
        return "Message{" +
                "productType='" + productType + '\'' +
                ", value=" + value +
                ", sales=" + sales +
                ", operation='" + operation + '\'' +
                '}';
    }
}
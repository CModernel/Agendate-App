package com.example.agendate_app.Database;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "rubroNom",
})
public class Variedad {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("VariedadId")
    private String variedadId;
    @JsonProperty("VariedadDsc")
    private String variedadDsc;
    @JsonProperty("VariedadModDT")
    private String variedadModDT;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Variedad() {
    }

    /**
     * @param empId
     * @param variedadDsc
     * @param variedadId
     * @param variedadModDT
     */
    public Variedad(Integer empId, String variedadId, String variedadDsc, String variedadModDT) {
        super();
        this.empId = empId;
        this.variedadId = variedadId;
        this.variedadDsc = variedadDsc;
        this.variedadModDT = variedadModDT;
    }

    @JsonProperty("EmpId")
    public Integer getEmpId() {
        return empId;
    }

    @JsonProperty("EmpId")
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @JsonProperty("VariedadId")
    public String getVariedadId() {
        return variedadId;
    }

    @JsonProperty("VariedadId")
    public void setVariedadId(String variedadId) {
        this.variedadId = variedadId;
    }

    @JsonProperty("VariedadDsc")
    public String getVariedadDsc() {
        return variedadDsc;
    }

    @JsonProperty("VariedadDsc")
    public void setVariedadDsc(String variedadDsc) {
        this.variedadDsc = variedadDsc;
    }

    @JsonProperty("VariedadModDT")
    public String getVariedadModDT() {
        return variedadModDT;
    }

    @JsonProperty("VariedadModDT")
    public void setVariedadModDT(String variedadModDT) {
        this.variedadModDT = variedadModDT;
    }

    @Override
    public String toString() {
        return variedadDsc;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(empId).append(variedadId).append(variedadDsc).append(variedadModDT).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Variedad)) {
            return false;
        }
        Variedad rhs = ((Variedad) other);
        return new EqualsBuilder().append(empId, rhs.empId).append(variedadId, rhs.variedadId).append(variedadDsc, rhs.variedadDsc).append(variedadModDT, rhs.variedadModDT).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
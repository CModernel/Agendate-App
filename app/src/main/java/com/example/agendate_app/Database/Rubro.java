package com.example.agendate_app.Database;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "rubroNom",
})
public class Rubro {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("rubroNom")
    private String rubroNom;

    /**
     * No args constructor for use in serialization
     */
    public Rubro() {
    }

    /**
     * @param id
     * @param rubroNom
     */
    public Rubro(Integer id, String rubroNom) {
        super();
        this.id = id;
        this.rubroNom = rubroNom;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("rubroNom")
    public String getRubroNom() {
        return rubroNom;
    }

    @JsonProperty("rubroNom")
    public void setRubroNom(String rubroNom) {
        this.rubroNom = rubroNom;
    }


    @Override
    public String toString() {
        return rubroNom;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(rubroNom).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Rubro)) {
            return false;
        }
        Rubro rhs = ((Rubro) other);
        return new EqualsBuilder().append(id, rhs.id).append(rubroNom, rhs.rubroNom).isEquals();
    }

}
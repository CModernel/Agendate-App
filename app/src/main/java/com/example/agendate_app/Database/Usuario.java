package com.example.agendate_app.Database;

import com.example.agendate_app.Fragments.AltaDeUsuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "first_name",
        "last_name",
        "email",
})
public class Usuario {

    @JsonProperty("username")
    private String username;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("email")
    private String email;

    /**
     * No args constructor for use in serialization
     */
    public Usuario() {
    }

    /**
     * @param username
     * @param first_name
     * @param last_name
     * @param email
     */
    public Usuario(String username, String first_name, String last_name, String email) {
        super();
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("first_name")
    public String getFirst_name() {
        return first_name;
    }

    @JsonProperty("first_name")
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @JsonProperty("last_name")
    public String getLast_name() {
        return last_name;
    }

    @JsonProperty("last_name")
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(username).append(first_name).append(last_name).append(email).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Usuario)) {
            return false;
        }
        Usuario rhs = ((Usuario) other);
        return new EqualsBuilder().append(username, rhs.username).append(first_name, rhs.first_name).append(last_name, rhs.last_name).append(email, rhs.email).isEquals();
    }

    public void register(AltaDeUsuario altaDeUsuario, String username, String name, String lastname, String email, String password1, String password2) {
    }
}
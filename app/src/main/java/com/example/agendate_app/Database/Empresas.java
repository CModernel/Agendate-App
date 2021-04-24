package com.example.agendate_app.Database;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "EmpId",
        "EmpRUT",
        "EmpRazonSocial",
        "EmpDirCalle",
        "EmpDirEsquina",
        "EmpDirNum",
        "EmpDirEmail",
        "EmpTelefono",
        "EmpDescripcion",
        "EmpActivo",
        "EmpImagen",
        "EmpRubro1",
        "EmpRubro2"
})
public class Empresas {

    @JsonProperty("EmpId")
    private Integer EmpId;
    @JsonProperty("EmpRUT")
    private Integer EmpRUT;
    @JsonProperty("EmpRazonSocial")
    private String EmpRazonSocial;
    @JsonProperty("EmpDirCalle")
    private String EmpDirCalle;
    @JsonProperty("EmpDirEsquina")
    private String EmpDirEsquina;
    @JsonProperty("EmpDirNum")
    private Integer EmpDirNum;
    @JsonProperty("EmpDirEmail")
    private String EmpDirEmail;
    @JsonProperty("EmpTelefono")
    private String EmpTelefono;
    @JsonProperty("EmpDescripcion")
    private String EmpDescripcion;
    @JsonProperty("EmpActivo")
    private String EmpActivo;
    @JsonProperty("EmpImagen")
    private String EmpImagen; // la imagen seria string?
    @JsonProperty("EmpRubro1")
    private Integer EmpRubro1;
    @JsonProperty("EmpRubro2")
    private Integer EmpRubro2;

    public Empresas(Integer EmpId, Integer EmpRUT, String EmpRazonSocial, String EmpDirCalle,
                    String EmpDirEsquina, Integer EmpDirNum, String EmpDirEmail, String EmpTelefono,
                    String EmpDescripcion, String EmpActivo, String EmpImagen, Integer EmpRubro1,
                    Integer EmpRubro2) {
        super();
        this.EmpId = EmpId;
        this.EmpRUT = EmpRUT;
        this.EmpRazonSocial = EmpRazonSocial;
        this.EmpDirCalle = EmpDirCalle;
        this.EmpDirEsquina = EmpDirEsquina;
        this.EmpDirNum = EmpDirNum;
        this.EmpDirEmail = EmpDirEmail;
        this.EmpTelefono = EmpTelefono;
        this.EmpDescripcion = EmpDescripcion;
        this.EmpActivo = EmpActivo;
        this.EmpImagen = EmpImagen;
        this.EmpRubro1 = EmpRubro1;
        this.EmpRubro2 = EmpRubro2;


    }

    public Empresas() {

    }


    @JsonProperty("EmpId")
    public Integer getEmpId() {
        return EmpId;
    }
    @JsonProperty("EmpId")
    public void setEmpId(Integer empId) {
        EmpId = empId;
    }
    @JsonProperty("EmpRUT")
    public Integer getEmpRUT() {
        return EmpRUT;
    }
    @JsonProperty("EmpRUT")
    public void setEmpRUT(Integer empRUT) {
        EmpRUT = empRUT;
    }
    @JsonProperty("EmpRazonSocial")
    public String getEmpRazonSocial() {
        return EmpRazonSocial;
    }
    @JsonProperty("EmpRazonSocial")
    public void setEmpRazonSocial(String empRazonSocial) {
        EmpRazonSocial = empRazonSocial;
    }
    @JsonProperty("EmpDirCalle")
    public String getEmpDirCalle() {
        return EmpDirCalle;
    }
    @JsonProperty("EmpDirCalle")
    public void setEmpDirCalle(String empDirCalle) {
        EmpDirCalle = empDirCalle;
    }
    @JsonProperty("EmpDirEsquina")
    public String getEmpDirEsquina() {
        return EmpDirEsquina;
    }
    @JsonProperty("EmpDirEsquina")
    public void setEmpDirEsquina(String empDirEsquina) {
        EmpDirEsquina = empDirEsquina;
    }
    @JsonProperty("EmpDirNum")
    public Integer getEmpDirNum() {
        return EmpDirNum;
    }
    @JsonProperty("EmpDirNum")
    public void setEmpDirNum(Integer empDirNum) {
        EmpDirNum = empDirNum;
    }
    @JsonProperty("EmpDirEmail")
    public String getEmpDirEmail() {
        return EmpDirEmail;
    }
    @JsonProperty("EmpDirEmail")
    public void setEmpDirEmail(String empDirEmail) {
        EmpDirEmail = empDirEmail;
    }
    @JsonProperty("EmpTelefono")
    public String getEmpTelefono() {
        return EmpTelefono;
    }
    @JsonProperty("EmpTelefono")
    public void setEmpTelefono(String empTelefono) {
        EmpTelefono = empTelefono;
    }
    @JsonProperty("EmpDescripcion")
    public String getEmpDescripcion() {
        return EmpDescripcion;
    }
    @JsonProperty("EmpDescripcion")
    public void setEmpDescripcion(String empDescripcion) {
        EmpDescripcion = empDescripcion;
    }
    @JsonProperty("EmpActivo")
    public Boolean getEmpActivo() {
        return Boolean.valueOf(EmpActivo);
    }
    @JsonProperty("EmpActivo")
    public void setEmpActivo(String empActivo) {
        EmpActivo = empActivo;
    }
    @JsonProperty("EmpImagen")
    public String getEmpImagen() {
        return EmpImagen;
    }
    @JsonProperty("EmpImagen")
    public void setEmpImagen(String empImagen) {
        EmpImagen = empImagen;
    }
    @JsonProperty("EmpRubro1")
    public Integer getEmpRubro1() {
        return EmpRubro1;
    }
    @JsonProperty("EmpRubro1")
    public void setEmpRubro1(Integer empRubro1) {
        EmpRubro1 = empRubro1;
    }
    @JsonProperty("EmpRubro2")
    public Integer getEmpRubro2() {
        return EmpRubro2;
    }
    @JsonProperty("EmpRubro2")
    public void setEmpRubro2(Integer empRubro2) {
        EmpRubro2 = empRubro2;
    }

    /*@Override
    public String toString() {
        return getEmpRazonSocial;
    } que funcion cumpliria esta linea de codigo?*/

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(EmpId).append(EmpRUT).append(EmpRazonSocial).
                append(EmpDirCalle).append(EmpDirEsquina).append(EmpDirNum).append(EmpDirEmail).
                append(EmpTelefono).append(EmpDescripcion).append(EmpActivo).append(EmpImagen).
                append(EmpRubro1).append(EmpRubro2).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Rubro)) {
            return false;
        }
        Empresas rhs = ((Empresas) other);
        return new EqualsBuilder().append(EmpId, rhs.EmpId).append(EmpRUT, rhs.EmpRUT).
                append(EmpRazonSocial, rhs.EmpRazonSocial).append(EmpDirCalle, rhs.EmpDirCalle).
                append(EmpDirEsquina, rhs.EmpDirEsquina).append(EmpDirEsquina, rhs.EmpDirEsquina).
                append(EmpDirNum, rhs.EmpDirNum).append(EmpDirEmail, rhs.EmpDirEmail).
                append(EmpTelefono, rhs.EmpTelefono).append(EmpDescripcion, rhs.EmpDescripcion).
                append(EmpActivo, rhs.EmpActivo).append(EmpImagen, rhs.EmpImagen).
                append(EmpRubro1, rhs.EmpRubro1).append(EmpRubro2, rhs.EmpRubro2).isEquals();
    }


}

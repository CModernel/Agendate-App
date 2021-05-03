package com.example.agendate_app.Database;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "FechaSolicitud",
        "HoraSolicitud",
        "SeConcreto",
        "ComentarioAdmin",
        "ComentarioUsuario",
        "SolicitudActivo",
        "UsuId",
        "EmpId",
        "UsuAdminResponsable"})

public class Agenda {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("FechaSolicitud")
    private String FechaSolicitud;
    @JsonProperty("HoraSolicitud")
    private String HoraSolicitud;
    @JsonProperty("SeConcreto")
    private String SeConcreto;
    @JsonProperty("ComentarioAdmin")
    private String ComentarioAdmin;
    @JsonProperty("ComentarioUsuario")
    private String ComentarioUsuario;
    @JsonProperty("SolicitudActivo")
    private boolean SolicitudActivo;
    @JsonProperty("UsuId")
    private Integer UsuId;
    @JsonProperty("EmpId")
    private Integer EmpId;
    @JsonProperty("UsuAdminResponsable")
    private Integer UsuAdminResponsable;

    private String EmpRazonSocial;
    private String EmpTelefono;
    private String EmpRubro1;

    public Agenda(Integer id, String FechaSolicitud, String HoraSolicitud,
                  String SeConcreto, String ComentarioAdmin, String ComentarioUsuario,
                  boolean SolicitudActivo, Integer UsuId, Integer EmpId,
                  Integer UsuAdminResponsable, String EmpRazonSocial, String EmpTelefono, String EmpRubro1) {
            super();
            this.id = id;
            this.FechaSolicitud = FechaSolicitud;
            this.HoraSolicitud = HoraSolicitud;
            this.SeConcreto = SeConcreto;
            this.ComentarioAdmin = ComentarioAdmin;
            this.ComentarioUsuario = ComentarioUsuario;
            this.SolicitudActivo = SolicitudActivo;
            this.UsuId = UsuId;
            this.EmpId = EmpId;
            this.UsuAdminResponsable = UsuAdminResponsable;
            this.EmpRazonSocial = EmpRazonSocial;
            this.EmpTelefono = EmpTelefono;
            this.EmpRubro1 = EmpRubro1;
        }

    public Agenda() {

    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("FechaSolicitud")
    public String getFechaSolicitud() {
        return FechaSolicitud;
    }
    @JsonProperty("FechaSolicitud")
    public void setFechaSolicitud(String fechaSolicitud) {
        FechaSolicitud = fechaSolicitud;
    }
    @JsonProperty("HoraSolicitud")
    public String getHoraSolicitud() {
        return HoraSolicitud;
    }
    @JsonProperty("HoraSolicitud")
    public void setHoraSolicitud(String horaSolicitud) {
        HoraSolicitud = horaSolicitud;
    }
    @JsonProperty("SeConcreto")
    public String getSeConcreto() {
        return SeConcreto;
    }
    @JsonProperty("SeConcreto")
    public void setSeConcreto(String seConcreto) {
        SeConcreto = seConcreto;
    }
    @JsonProperty("ComentarioAdmin")
    public String getComentarioAdmin() {
        return ComentarioAdmin;
    }
    @JsonProperty("ComentarioAdmin")
    public void setComentarioAdmin(String comentarioAdmin) {
        ComentarioAdmin = comentarioAdmin;
    }
    @JsonProperty("ComentarioUsuario")
    public String getComentarioUsuario() {
        return ComentarioUsuario;
    }
    @JsonProperty("ComentarioUsuario")
    public void setComentarioUsuario(String comentarioUsuario) {
        ComentarioUsuario = comentarioUsuario;
    }
    @JsonProperty("SolicitudActivo")
    public boolean getSolicitudActivo() {
        return SolicitudActivo;
    }
    @JsonProperty("SolicitudActivo")
    public void setSolicitudActivo(boolean solicitudActivo) {
        SolicitudActivo = solicitudActivo;
    }
    @JsonProperty("UsuId")
    public Integer getUsuId() {
        return UsuId;
    }
    @JsonProperty("UsuId")
    public void setUsuId(Integer usuId) {
        UsuId = usuId;
    }
    @JsonProperty("EmpId")
    public Integer getEmpId() {
        return EmpId;
    }
    @JsonProperty("EmpId")
    public void setEmpId(Integer empId) {
        EmpId = empId;
    }
    @JsonProperty("UsuAdminResponsable")
    public Integer getUsuAdminResponsable() {
        return UsuAdminResponsable;
    }
    @JsonProperty("UsuAdminResponsable")
    public void setUsuAdminResponsable(Integer usuAdminResponsable) {
        UsuAdminResponsable = usuAdminResponsable;
    }

    public boolean isSolicitudActivo() {
        return SolicitudActivo;
    }

    public String getEmpRazonSocial() {
        return EmpRazonSocial;
    }

    public void setEmpRazonSocial(String empRazonSocial) {
        EmpRazonSocial = empRazonSocial;
    }

    public String getEmpTelefono() {
        return EmpTelefono;
    }

    public void setEmpTelefono(String empTelefono) {
        EmpTelefono = empTelefono;
    }

    public String getEmpRubro1() {
        return EmpRubro1;
    }

    public void setEmpRubro1(String empRubro1) {
        EmpRubro1 = empRubro1;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(FechaSolicitud).append(HoraSolicitud).
                append(SeConcreto).append(ComentarioAdmin).append(ComentarioUsuario).append(SolicitudActivo).
                append(UsuId).append(EmpId).append(UsuAdminResponsable).append(EmpRazonSocial).append(EmpTelefono).append(EmpRubro1).toHashCode();
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Rubro)) {
            return false;
        }
        Agenda rhs = ((Agenda) other);
        return new EqualsBuilder().append(id, rhs.id).append(FechaSolicitud, rhs.FechaSolicitud).
                append(HoraSolicitud, rhs.HoraSolicitud).append(SeConcreto, rhs.SeConcreto).
                append(ComentarioAdmin, rhs.ComentarioAdmin).append(ComentarioUsuario, rhs.ComentarioUsuario).
                append(SolicitudActivo, rhs.SolicitudActivo).append(UsuId, rhs.UsuId).
                append(EmpId, rhs.EmpId).append(UsuAdminResponsable, rhs.UsuAdminResponsable).
                append(EmpRazonSocial, rhs.EmpRazonSocial).append(EmpTelefono, rhs.EmpTelefono).append(EmpRubro1, rhs.EmpRubro1).isEquals();
    }

}

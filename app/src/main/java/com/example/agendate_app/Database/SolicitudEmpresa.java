package com.example.agendate_app.Database;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Horario",
        "Solicitudes",
        "HorariosVencidos",})

public class SolicitudEmpresa implements Serializable {

    @JsonProperty("Horario")
    private String[] Horario;
    @JsonProperty("Solicitudes")
    private String[] Solicitudes;
    @JsonProperty("HorariosVencidos")
    private String[] HorariosVencidos;

    private Integer EmpId;
    private Date Fecha;
    private Time HorarioSolicitud;


    public SolicitudEmpresa(Integer EmpId, Date Fecha, Time HorarioSolicitud) {
        super();
        this.EmpId = EmpId;
        this.Fecha = Fecha;
        this.HorarioSolicitud = HorarioSolicitud;

    }

/*
    public SolicitudEmpresa(String[] Horario, String[] Solicitudes, String[] HorariosVencidos) {
        super();
        this.Horario = Horario;
        this.Solicitudes = Solicitudes;
        this.HorariosVencidos = HorariosVencidos;

    }
*/
    public SolicitudEmpresa() {

    }

    public static int size() {
        return 0;
    }

    public static SolicitudEmpresa get(int position) {
        return null;
    }

    @JsonProperty("Horario")
    public String[] getHorario() {
        return Horario;
    }
    @JsonProperty("Horario")
    public void setHorario(String[] Horario) {
        this.Horario = Horario;
    }


    @JsonProperty("Solicitudes")
    public String[] getSolicitudes() {
        return Solicitudes;
    }
    @JsonProperty("Solicitudes")
    public void setSolicitudes(String[] Solicitudes) {
        this.Solicitudes = Solicitudes;
    }

    @JsonProperty("HorariosVencidos")
    public String[] getHorariosVencidos() {
        return HorariosVencidos;
    }
    @JsonProperty("HorariosVencidos")
    public void setHorariosVencidos(String[] HorariosVencidos) {
        this.HorariosVencidos = HorariosVencidos;
    }

    public Integer getEmpId() {
        return EmpId;
    }

    public void setEmpId(Integer empId) {
        EmpId = empId;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Time getHorarioSolicitud() {
        return HorarioSolicitud;
    }

    public void setHorarioSolicitud(Time horarioSolicitud) {
        HorarioSolicitud = horarioSolicitud;
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(EmpId).append(Fecha).append(HorarioSolicitud).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SolicitudEmpresa)) {
            return false;
        }
        SolicitudEmpresa rhs = ((SolicitudEmpresa) other);
        return new EqualsBuilder().append(EmpId, rhs.EmpId).append(Fecha, rhs.Fecha).
                append(HorarioSolicitud, rhs.HorarioSolicitud).isEquals();
    }


}

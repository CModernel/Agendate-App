package com.example.agendate_app.Database;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Time;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "EmpId",
        "Fecha",
        "HorarioSolicitud",})

public class SolicitudEmpresa {

    @JsonProperty("EmpId")
    private Integer EmpId;
    @JsonProperty("Fecha")
    private Date Fecha;
    @JsonProperty("HorarioSolicitud")
    private Time HorarioSolicitud;


    public SolicitudEmpresa(Integer EmpId, Date Fecha, Time HorarioSolicitud) {
        super();
        this.EmpId = EmpId;
        this.Fecha = Fecha;
        this.HorarioSolicitud = HorarioSolicitud;

    }

    public SolicitudEmpresa() {

    }

    public static int size() {
        return 0;
    }

    public static SolicitudEmpresa get(int position) {
        return null;
    }

    @JsonProperty("EmpId")
    public Integer getEmpId() {
        return EmpId;
    }
    @JsonProperty("EmpId")
    public void setEmpId(Integer empId) {
        EmpId = empId;
    }
    @JsonProperty("Fecha")
    public Date getFecha() {
        return Fecha;
    }
    @JsonProperty("Fecha")
    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
    @JsonProperty("HorarioSolicitud")
    public Time getHorarioSolicitud() {
        return HorarioSolicitud;
    }
    @JsonProperty("HorarioSolicitud")
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

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
        "UsuAdminResponsable",})

public class VerAgenda {

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
        private String SolicitudActivo;
        @JsonProperty("UsuId")
        private Integer UsuId;
        @JsonProperty("EmpId")
        private Integer EmpId;
        @JsonProperty("UsuAdminResponsable")
        private Integer UsuAdminResponsable;

        public VerAgenda(Integer id, String FechaSolicitud, String HoraSolicitud,
                    String SeConcreto, String ComentarioAdmin, String ComentarioUsuario,
                    String SolicitudActivo, Integer UsuId, Integer EmpId,
                        Integer UsuAdminResponsable) {
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

        }

    public VerAgenda() {

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
        public String getSolicitudActivo() {
            return SolicitudActivo;
        }
        @JsonProperty("SolicitudActivo")
        public void setSolicitudActivo(String solicitudActivo) {
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

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(id).append(FechaSolicitud).append(HoraSolicitud).
                    append(SeConcreto).append(ComentarioAdmin).append(ComentarioUsuario).append(SolicitudActivo).
                    append(UsuId).append(EmpId).append(UsuAdminResponsable).toHashCode();
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Rubro)) {
                return false;
            }
            VerAgenda rhs = ((VerAgenda) other);
            return new EqualsBuilder().append(id, rhs.id).append(FechaSolicitud, rhs.FechaSolicitud).
                    append(HoraSolicitud, rhs.HoraSolicitud).append(SeConcreto, rhs.SeConcreto).
                    append(ComentarioAdmin, rhs.ComentarioAdmin).append(ComentarioUsuario, rhs.ComentarioUsuario).
                    append(SolicitudActivo, rhs.SolicitudActivo).append(UsuId, rhs.UsuId).
                    append(EmpId, rhs.EmpId).append(UsuAdminResponsable, rhs.UsuAdminResponsable).isEquals();
        }

}

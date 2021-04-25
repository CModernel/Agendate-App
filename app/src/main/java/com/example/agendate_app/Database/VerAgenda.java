package com.example.agendate_app.Database;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
        "UsuAdminResponsable",

public class VerAgenda {

}

package com.example.agendate_app.Database;

public class _SyncableGetResponse {
    public String tag;
    public String out;

    public String identificador1;
    public String identificador2;
    public String identificador3;
    public String identificador4;
    public String identificador5;

    public Object objeto1;

    public _SyncableGetResponse() {
    }

    public _SyncableGetResponse(Object objeto1, String identificador1, String identificador2, String identificador3, String tag, String out) {
        this.objeto1 = objeto1;
        this.identificador1 = identificador1;
        this.identificador2 = identificador2;
        this.identificador3 = identificador3;
        this.tag = tag;
        this.out = out;
    }

    public _SyncableGetResponse(Object objeto1, String identificador1, String identificador2, String identificador3, String identificador4, String identificador5, String tag, String out) {
        this.objeto1 = objeto1;
        this.identificador1 = identificador1;
        this.identificador2 = identificador2;
        this.identificador3 = identificador3;
        this.identificador4 = identificador4;
        this.identificador5 = identificador5;
        this.tag = tag;
        this.out = out;
    }
}

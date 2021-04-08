package com.example.agendate_app.Interfaces;

import com.example.agendate_app.Database._SyncableGetResponse;

public interface _SyncableGet {

    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr);

}
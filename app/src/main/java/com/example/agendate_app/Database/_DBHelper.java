package com.sd.solidatec.wms.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sd.solidatec.wms.Utils._Log;
import com.sd.solidatec.wms.Utils._LoginStatus;

public class _DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "com.solidatec.wms.db";
    private static final int DATABASE_VERSION = 64;

    public _DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Probar esto, se supone que hace un close de la DB cuando el GC agarra al objeto, esto ayuda a evitar el memory leak, igual lo correcto es que se cierre luego de abrir y usar
     * https://stackoverflow.com/a/30352464/1259763
     * */
    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    // Tabla Categorias
    private static final String CREATE_Categorias = "CREATE TABLE Categorias (" +
            "EmpId INTEGER NOT NULL, " +
            "CategoriaId TEXT NOT NULL, " +
            "CategoriaDsc TEXT, " +
            "CategoriaModDT TEXT, " +
            "CategoriaActiva TEXT, " +
            "PRIMARY KEY (EmpId, CategoriaId)" +
            ");";

    // Tabla ClasesProductos
    private static final String CREATE_ClasesProductos = "CREATE TABLE ClasesProductos (" +
            "EmpId INTEGER NOT NULL, " +
            "ClaseProdId TEXT NOT NULL, " +
            "ClaseProdDsc TEXT, " +
            "ClaseProdModDt TEXT, " +
            "ClaseProdActivo TEXT, " +
            "PRIMARY KEY (EmpId, ClaseProdId)" +
            ");";

    // Tabla Clientes
    private static final String CREATE_Clientes = "CREATE TABLE Clientes (" +
            "EmpId INTEGER NOT NULL, " +
            "CliId TEXT NOT NULL, " +
            "CliCodDist TEXT, " +
            "CliCodCorp TEXT, " +
            "CliImpNro TEXT, " +
            "CliNroSegSoc TEXT, " +
            "CliNom TEXT, " +
            "CliNom2 TEXT, " +
            "CliApe2 TEXT, " +
            "CliRazSoc TEXT, " +
            "CliNomAbr TEXT, " +
            "CliPerJud TEXT, " +
            "CliDirFis TEXT, " +
            "RamoId TEXT, " +
            "CanalId TEXT, " +
            "SubcanalId TEXT, " +
            "PaisId TEXT, " +
            "RegionId TEXT, " +
            "LocalidadId TEXT, " +
            "BarrioId TEXT, " +
            "ZonaId TEXT, " +
            "CadenaId TEXT, " +
            "CliCalle TEXT, " +
            "CliNroPuerta INTEGER, " +
            "CliEntreCalle1 TEXT, " +
            "CliEntreCalle2 TEXT, " +
            "CliTel TEXT, " +
            "CliContacto TEXT, " +
            "CliEmail TEXT, " +
            "CondVtaId TEXT, " +
            "CliApe TEXT, " +
            "CliNomCompleto TEXT, " +
            "CliLisPreId1 TEXT, " +
            "CliLisPreId2 TEXT, " +
            "CliModDT TEXT, " +
            "CliLat TEXT, " +
            "CliLong TEXT, " +
            "MedpagoId TEXT, " +
            "CliMedPagoEstricto TEXT, " +
            "CliMaxPlazo INTEGER, " +
            "CliMonedaId TEXT, " +
            "CliDirCom TEXT, " +
            "CliDirComplemento TEXT, " +
            "CliClase TEXT, " +
            "CliIdCtc TEXT, " +
            "CliIdGpo TEXT, " +
            "CliActivo TEXT, " +
            "CliTpoDocDGI TEXT, " +
            "CliCreditoLimite REAL, " +
            "CliCreditoDisponible REAL, " +
            "CliCreditoAtraso TEXT, " +
            "CliCreditoAtrasoImporte REAL, " +
            "CliCreditoAtrasoCantDocs INTEGER, " +
            "CliCreditoMonedaId TEXT, " +
            "CliObservaciones TEXT, " +
            "CliTpoDocId TEXT, " +
            "CliCreditoPendiente REAL, " +
            "CliPerfImpId TEXT, " +
            "CategoriaClienteId TEXT, " +
            "CliEstado TEXT, " +
            "CliCreditoUsado INTEGER, " +
            "CliLisPreId3 TEXT, " +
            "CliLisPreId4 TEXT, " +
            "CliLisPreId5 TEXT, " +
            "PRIMARY KEY (EmpId, CliId)" +
            ");";

    // Tabla ClientesLocales
    private static final String CREATE_ClientesLocales = "CREATE TABLE ClientesLocales (" +
            "EmpId INTEGER NOT NULL, " +
            "CliId TEXT NOT NULL, " +
            "CliLocId TEXT NOT NULL, " +
            "CliLocDir TEXT, " +
            "CliLocDirNro INTEGER, " +
            "CliLocModDT TEXT, " +
            "CliLocContacto TEXT, " +
            "CliLocNom TEXT, " +
            "CliLocTelefono TEXT, " +
            "CliLocMail TEXT, " +
            "CliLocActivo TEXT, " +
            "CliLocPaisId TEXT, " +
            "CliLocLocalidadId TEXT, " +
            "CliLocBarrioId TEXT, " +
            "CliLocLat TEXT, " +
            "CliLocLong TEXT, " +
            "CliLocLatLongSyncFlag TEXT, " +
            "CliLocRadioAccion INTEGER, " +
            "PRIMARY KEY (EmpId, CliId, CliLocId)" +
            ");";

    // Tabla CondicionesVenta
    private static final String CREATE_CondicionesVenta = "CREATE TABLE CondicionesVenta (" +
            "EmpId INTEGER NOT NULL, " +
            "CondVtaId TEXT NOT NULL, " +
            "CondVtaDsc TEXT NOT NULL, " +
            "CondVtaNivel INTEGER NOT NULL, " +
            "CondVtaPzo INTEGER NOT NULL, " +
            "CondVtaPzoMoro INTEGER NOT NULL, " +
            "CondVtaModDT TEXT, " +
            "CondVtaActiva TEXT NOT NULL, " +
            "TipoDocIdVta TEXT, " +
            "TipoDocIdDev TEXT, " +
            "TipoDocIdVtaFin TEXT, " +
            "TipoDocIdDevFin TEXT, " +
            "TipoDocIdVtaParcial TEXT, " +
            "TipoDocIdDevParcial TEXT, " +
            "PRIMARY KEY (EmpId, CondVtaId)" +
            ");";

    // Tabla Conteos
    private static final String CREATE_Conteos = "CREATE TABLE Conteos (" +
            "EmpId INTEGER NOT NULL, " +
            "ContId TEXT NOT NULL, " +
            "DepId TEXT, " +
            "ContSts TEXT, " +
            "ContObs TEXT, " +
            "ContFchCongelado TEXT, " +
            "ContTipo TEXT, " +
            "ContUbiId TEXT, " +
            "ContActivo TEXT, " +
            "ContModDt TEXT, " +
            "ContUsuId TEXT, " +
            "ContSyncError TEXT, " +
            "ContSyncIntentos INTEGER, " +
            "ContSyncDt TEXT, " +
            "ContSyncFlg INTEGER, " +
            "ContUltNum INTEGER, " +
            "ContNro INTEGER, " +
            "PRIMARY KEY (EmpId, ContId, DepId)" +
            ");";

    // Tabla ConteosLineas
    private static final String CREATE_ConteosLineas = "CREATE TABLE ConteosLineas (" +
            "EmpId INTEGER NOT NULL, " +
            "ContId TEXT NOT NULL, " +
            "DepId TEXT, " +
            "ContLinId TEXT, " +
            "ProdId TEXT, " +
            "UbiId TEXT, " +
            "ContLinUniMedId TEXT, " +
            "ContLinCntStk TEXT, " +
            "ContLinCntCon TEXT, " +
            "ContLinMovStkId TEXT, " +
            "ContLinLoteId TEXT, " +
            "ContLinSyncError TEXT, " +
            "ContLinSyncIntentos INTEGER, " +
            "ContLinSyncDt TEXT, " +
            "ContLinSyncFlg TEXT, " +
            "ContLinActivo TEXT, " +
            "PRIMARY KEY (EmpId, ContId, DepId, ContLinId)" +
            ");";

    // Tabla Documentos
    private static final String CREATE_Documentos = "CREATE TABLE Documentos (" +
            "EmpId INTEGER NOT NULL, " +
            "TpoDocId TEXT NOT NULL, " +
            "DocUsuId TEXT NOT NULL, " +
            "DocId TEXT NOT NULL, " +
            "DocIdMobile TEXT NOT NULL, " +
            "DocNro TEXT, " +
            "DocSerie TEXT, " +
            "DocFHIns TEXT, " +
            "DocFechaEmi TEXT, " +
            "DocFechaEntrega TEXT, " +
            "DocRUT TEXT, " +
            "DocTitNom TEXT, " +
            "DocTitRazSoc TEXT, " +
            "DocTitDir TEXT, " +
            "DocTitTel TEXT, " +
            "DocTitObs TEXT, " +
            "DocConsFin TEXT, " +
            "DocVto TEXT, " +
            "MonedaId TEXT, " +
            "DocTipoCamb REAL, " +
            "DocCondVtaId TEXT, " +
            "DocImpSDSI REAL, " +
            "DocImpCDSI REAL, " +
            "DocImpCDCI REAL, " +
            "CliId TEXT, " +
            "CliLocId TEXT, " +
            "DocModDT TEXT, " +
            "DocActivo TEXT, " +
            "DocSyncFlg TEXT, " +
            "DocSyncDT TEXT, " +
            "DocSyncIntentos INTEGER, " +
            "DocNroOriginal TEXT, " +
            "DocCI TEXT, " +
            "DocTipoIdent TEXT, " +
            "DocErrorSyncSet TEXT, " +
            "DocOrdenDeCompra TEXT, " +
            "DocSerieOriginal TEXT, " +
            "DocFlagRel TEXT, " +
            "DocAnulado TEXT, " +
            "DocFacturado TEXT, " +
            "DocRelPrimDocId TEXT, " +
            "DocRelPrimDocUsuId TEXT, " +
            "DocRelPrimTpoDocId TEXT, " +
            "DocSyncError TEXT, " +
            "DocFlagSaldoMes TEXT, " +
            "DocFlgEstado TEXT, " +
            "DocFlgRetira TEXT, " +
            "DocDepId TEXT, " +
            "DocDepId_OfcCom TEXT, " +
            "DocVenId TEXT, " +
            "DocVenId_Sup1 TEXT, " +
            "DocVenId_Sup2 TEXT, " +
            "DocVenId_Sup3 TEXT, " +
            "DocVenId_Sup4 TEXT, " +
            "DocVenId_Sup5 TEXT, " +
            "DocVenId_Sup6 TEXT, " +
            "DocVenId_Sup7 TEXT, " +
            "DocVenId_Sup8 TEXT, " +
            "DocVenId_Sup9 TEXT, " +
            "DocPlaNro TEXT, " +
            "DocRepId TEXT, " +
            "DocFlgReparto TEXT, " +
            "DocFlgExportacion TEXT, " +
            "DocPolEsManual TEXT, " +
            "DocDtoCabezal REAL, " +
            "DocDtoCabezalExcluyente TEXT, " +
            "DocMotDevId TEXT, " +
            "PRIMARY KEY (EmpId, TpoDocId, DocUsuId, DocId, DocIdMobile)" +
            ");";

    // Tabla Documentos
    private static final String CREATE_DocumentosFirmas = "CREATE TABLE DocumentosFirmas (" +
            "EmpId INTEGER NOT NULL, " +
            "TpoDocId TEXT NOT NULL, " +
            "DocUsuId TEXT NOT NULL, " +
            "DocId TEXT NOT NULL, " +
            "DocFirmaId TEXT NOT NULL, " +
            "DocFirma BLOB, " +
            "DocFirmaModDT TEXT, " +
            "DocFirmaFlgSync TEXT, " +
            "DocFirmaActivo TEXT, " +
            "DocFirmaAclaracion TEXT, " +
            "DocFirmaCI TEXT, " +
            "DocFirmaImagen TEXT, " +
            "DocFirmaDsc TEXT, " +
            "PRIMARY KEY (EmpId, TpoDocId, DocUsuId, DocId, DocFirmaId)" +
            ");";

    // Tabla Empresas
    private static final String CREATE_Empresas = "CREATE TABLE Empresas (" +
            "EmpId INTEGER NOT NULL, " +
            "UsuId INTEGER, " +
            "EmpDsc TEXT, " +
            "EmpRazSoc TEXT, " +
            "EmpDevSep INTEGER, " +
            "EmpDtosSep INTEGER, " +
            "EmpImpNro TEXT, " +
            "EmpSegSocNum TEXT, " +
            "EmpresaModDT TEXT, " +
            "EmpresaActiva TEXT, " +
            "EmpTZDiff INTEGER, " +
            "PRIMARY KEY (EmpId)" +
            ");";

    // Tabla Libretas
    private static final String CREATE_Libretas = "CREATE TABLE Libretas (" +
            "EmpId INTEGER NOT NULL, " +
            "TpoDocId TEXT NOT NULL, " +
            "LibId TEXT NOT NULL, " +
            "LibSerie TEXT NOT NULL, " +
            "LibNumDesde INTEGER NOT NULL, " +
            "LibNumHasta INTEGER NOT NULL, " +
            "LibUltNum INTEGET, " +
            "LibVto TEXT NOT NULL, " +
            "LibModDt TEXT, " +
            "LibActiva TEXT, " +
            "UsuId TEXT, " +
            "LibFlgSync TEXT, " +
            "LibSyncIntentos TEXT, " +
            "LibSyncError TEXT, " +
            "PRIMARY KEY (EmpId, TpoDocId, LibId)" +
            ");";

    // Tabla Lineas
    private static final String CREATE_Lineas = "CREATE TABLE Lineas (" +
            "EmpId INTEGER NOT NULL, " +
            "LineaId TEXT NOT NULL, " +
            "LineaDsc TEXT, " +
            "LineaModDT TEXT, " +
            "LineaActiva TEXT, " +
            "PRIMARY KEY (EmpId, LineaId)" +
            ");";

    // Tabla ListasDePreciosBORRAR
    private static final String CREATE_ListasDePrecios = "CREATE TABLE ListasDePrecios (" +
            "EmpId INTEGER NOT NULL, " +
            "LisPreId TEXT NOT NULL, " +
            "LisPreDsc TEXT NOT NULL, " +
            "LisPreDscAbr TEXT NOT NULL, " +
            "LisPreConImp TEXT NOT NULL, " +
            "LisPreModDT TEXT, " +
            "LisPreActiva TEXT NOT NULL, " +
            "LisPreAplicaDesc TEXT, " +
            "PRIMARY KEY (EmpId, LisPreId)" +
            ");";

    // Tabla ListaPreVersion
    private static final String CREATE_ListasPreVersion = "CREATE TABLE ListasPreVersiones (" +
            "EmpId INTEGER NOT NULL, " +
            "LisPreId TEXT NOT NULL, " +
            "LisPreVerId TEXT NOT NULL, " +
            "LisPreVerDsc TEXT NOT NULL, " +
            "LisPreVerAbr TEXT NOT NULL, " +
            "LisPreVerVig TEXT NOT NULL, " +
            "LisPreVerModDT TEXT, " +
            "LisPreVerActiva TEXT NOT NULL, " +
            "PRIMARY KEY (EmpId, LisPreId, LisPreVerId)" +
            ");";

    // Tabla Marcas
    private static final String CREATE_Marcas = "CREATE TABLE Marcas (" +
            "EmpId INTEGER NOT NULL, " +
            "MarcaId TEXT NOT NULL, " +
            "MarcaDsc TEXT, " +
            "MarcaModDT TEXT, " +
            "MarcaActiva TEXT, " +
            "PRIMARY KEY (EmpId, MarcaId)" +
            ");";

    // Tabla Monedas
    private static final String CREATE_Monedas = "CREATE TABLE Monedas (" +
            "EmpId INTEGER NOT NULL, " +
            "MonedaId TEXT NOT NULL, " +
            "MonedaDsc TEXT, " +
            "MonedaSimb TEXT, " +
            "MonedaModDT TEXT, " +
            "TpoCamCot REAL, " +
            "MonedaBase TEXT, " +
            "PRIMARY KEY (EmpId, MonedaId)" +
            ");";

    // Tabla Pallet
    private static final String CREATE_Pallet = "CREATE TABLE Pallet (" +
            "EmpId INTEGER NOT NULL, " +
            "PalletId TEXT NOT NULL, " +
            "PalletDsc TEXT, " +
            "PalletActivo TEXT, " +
            "PalletModDt TEXT, " +
            "PalletFecha TEXT, " +
            "PalletBultos INTEGER, " +
            "PalletPeso REAL, " +
            "PalletVol REAL, " +
            "RepId TEXT, " +
            "UsuId TEXT, " +
            "PalletEstado TEXT, " +
            "PalletSyncFlg INTEGER, " +
            "TipoPalletId TEXT, " +
            "TipoPalletDsc TEXT, " +
            "PalletDepId TEXT, " +
            "PalletDepIdDestino TEXT, " +
            "PRIMARY KEY (EmpId, PalletId)" +
            ");";

    // Tabla Pallet Productos
    private static final String CREATE_PalletProductos = "CREATE TABLE PalletProductos (" +
            "EmpId INTEGER NOT NULL, " +
            "PalletId TEXT NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "PalletProdModDt TEXT, " +
            "PalletProdPeso REAL, " +
            "PalletProdVol REAL, " +
            "PalletProdCnt INTEGER, " +
            "PalletProdSyncFlg REAL, " +
            "PalletProdUM TEXT, " +
            "PalletProdActivo TEXT, " +
            "PRIMARY KEY (EmpId, PalletId, ProdId)" +
            ");";

    // Tabla Parametros
    private static final String CREATE_Parametros = "CREATE TABLE Parametros (" +
            "EmpId INTEGER NOT NULL, " +
            "ParId TEXT NOT NULL, " +
            "ParDsc TEXT, " +
            "ParNum INTEGER, " +
            "ParDate TEXT, " +
            "ParTxt TEXT, " +
            "ParTipo TEXT, " +
            "ParModDT TEXT, " +
            "GrupoParmId TEXT, " +
            "ParDateTime TEXT, " +
            "PRIMARY KEY (EmpId, ParId)" +
            ");";

    private static final String CREATE_Planillas = "CREATE TABLE Planillas (" +
            "EmpId INTEGER NOT NULL, " +
            "DepId TEXT NOT NULL, " +
            "PlaNro TEXT NOT NULL, " +
            "PlaTxt TEXT NOT NULL, " +
            "PlaNroRef TEXT NOT NULL, " +
            "PlaFch TEXT NOT NULL, " +
            "PlaFchIng TEXT NOT NULL, " +
            "PlaFchSal TEXT, " +
            "PlaFchEnt TEXT, " +
            "PlaFchLiq TEXT, " +
            "PlaFchCer TEXT, " +
            "PlaEstado TEXT NOT NULL," +
            "PlaTipo TEXT NOT NULL, " +
            "FleteroId TEXT, " +
            "CamId TEXT, " +
            "CamDsc TEXT, " +
            "PlaIdCam TEXT, " +
            "PlaTotLiq REAL, " +
            "PlaTotVal REAL, " +
            "PlaTotPeso REAL, " +
            "PlaFlgFlete TEXT NOT NULL, " +
            "PlafltTot REAL, " +
            "PlaDpsOrigen TEXT, " +
            "PlaDpsDestino TEXT, " +
            "PlaFlgRetPla TEXT, " +
            "PlaTpoFlt INTEGER, " +
            "PlaNroVje INTEGER, " +
            "PlaModDt TEXT, " +
            "PlaCajaId TEXT, " +
            "PlaFchCaja TEXT, " +
            "PlaUsrCaja TEXT, " +
            "SyncFlag TEXT, " +
            "ErrorSyncSet TEXT, " +
            "SyncIntentos TEXT, " +
            "PRIMARY KEY (EmpId, DepId, PlaNro)" +
            ");";

    // Tabla Portafolios
    private static final String CREATE_Portafolios = "CREATE TABLE Portafolios (" +
            "EmpId INTEGER NOT NULL, " +
            "PortafolioId TEXT NOT NULL, " +
            "PortafolioDsc TEXT NOT NULL, " +
            "UninegId TEXT NOT NULL, " +
            "PortafolioModDT TEXT, " +
            "PRIMARY KEY (EmpId, PortafolioId)" +
            ");";

    // Tabla Precios
    private static final String CREATE_Precios = "CREATE TABLE Precios (" +
            "EmpId INTEGER NOT NULL, " +
            "LisPreId TEXT NOT NULL, " +
            "LisPreVerId TEXT NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "PrecioImp REAL NOT NULL, " +
            "MonedaIdPrecio TEXT NOT NULL, " +
            "PrecioModDT TEXT, " +
            "PrecioActivo TEXT, " +
            "PRIMARY KEY (EmpId, LisPreId, LisPreVerId, ProdId)" +
            ");";

    // Tabla Presentaciones
    private static final String CREATE_Presentaciones = "CREATE TABLE Presentaciones (" +
            "EmpId INTEGER NOT NULL, " +
            "PresentaId TEXT NOT NULL, " +
            "PresentaDsc TEXT, " +
            "PresentaDscAbr TEXT, " +
            "PresentaModDT TEXT, " +
            "PresentaActiva TEXT, " +
            "PRIMARY KEY (EmpId, PresentaId)" +
            ");";

    // Tabla Productos
    private static final String CREATE_ProductosDeposito = "CREATE TABLE ProductosDeposito (" +
            "EmpId INTEGER NOT NULL, " +
            "DepId TEXT NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "LoteId TEXT NOT NULL, " +
            "ProdDepPtoCarga TEXT, " +
            "ProdDepUbiId TEXT, " +
            "ProdDepPosId TEXT, " +
            "ProdDepVtaPromDia REAL, " +
            "ProdDepDiasStkMax REAL, " +
            "ProdDepDiasStkMin REAL, " +
            "ProdDepCntMin REAL, " +
            "ProdDepCntMax REAL, " +
            "ProdDepActivo TEXT, " +
            "ProdDepFechaEstado TEXT, " +
            "ProdDepModDt TEXT, " +
            "ProdDepClaseUbiId TEXT, " +
            "ProdDepClaseUbiId2 TEXT, " +
            "ProdDepCntStk REAL, " +
            "PRIMARY KEY (EmpId, DepId, ProdId, LoteId)" +
            ");";

    // Tabla Productos
    private static final String CREATE_Productos = "CREATE TABLE Productos (" +
            "EmpId INTEGER NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "ProdDistId TEXT, " +
            "ProdCorpId TEXT, " +
            "ProdDsc TEXT, " +
            "ProdDscAbr TEXT, " +
            "ProdObs TEXT, " +
            "CategoriaId TEXT, " +
            "LineaId TEXT, " +
            "MarcaId TEXT, " +
            "PresentaId TEXT, " +
            "VariedadId TEXT, " +
            "PortafolioId TEXT, " +
            "ProdTipo TEXT, " +
            "ProdLleStock TEXT, " +
            "UnimedId TEXT, " +
            "ProdStkMin REAL, " +
            "ProdStkCrit REAL, " +
            "ProdStkMax REAL, " +
            "ProdAplComEsp TEXT, " +
            "ProdPrjComEsp INTEGER, " +
            "ProdMargenPrcCompra REAL, " +
            "ProdLoteable TEXT, " +
            "UninegId TEXT, " +
            "ProdDscCorpId TEXT, " +
            "ProdModDT TEXT, " +
            "ProdHabCanjes INTEGER, " +
            "ProdGpoCanjes TEXT, " +
            "ProdAlcohol TEXT, " +
            "ProdContabilidad TEXT, " +
            "ProdActivo TEXT, " +
            "txtGruposProductosRel TEXT, " +
            "PerfImpId TEXT, " +
            "PerfImpDsc TEXT, " +
            "ProdStkCnt REAL, " +
            "PerfImpValor REAL, " +
            "ProdCodEanCSV TEXT, " +
            "ProdImagenURL TEXT, " +
            "ProdDscHTML TEXT, " +
            "ProdFlgEnvase TEXT, " +
            "PropietarioId TEXT, " +
            "PropietarioDsc TEXT, " +
            "ClaseProdId TEXT, " +
            "ClaseProdDsc TEXT, " +
            "SubClaseDsc TEXT, " +
            "SubClaseProdId TEXT, " +
            "ProdSyncFlg TEXT, " +
            "ProdManVencimiento TEXT, " +
            "PRIMARY KEY (EmpId, ProdId)" +
            ");";

    // Tabla Productos Lotes
    private static final String CREATE_ProductosLotes = "CREATE TABLE ProductosLotes (" +
            "EmpId INTEGER NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "LoteId TEXT NOT NULL, " +
            "LoteDsc TEXT, " +
            "LoteFecha TEXT, " +
            "LoteFechaVto TEXT, " +
            "LoteProvID TEXT, " +
            "LoteCntDiasVto INTEGER, " +
            "LoteCantidad INTEGER, " +
            "LoteSaldo INTEGER, " +
            "LoteImporte REAL, " +
            "LoteValCst REAL, " +
            "LoteFechaIng TEXT, " +
            "LoteUsuId TEXT, " +
            "LoteActivo TEXT, " +
            "LoteObs TEXT, " +
            "LoteModDt TEXT, " +
            "LoteIdProveedor TEXT, " +
            "PRIMARY KEY (EmpId, ProdId, LoteId)" +
            ");";

    // Tabla ProdUnidadesMedida
    private static final String CREATE_ProdUnidadesMedida = "CREATE TABLE ProdUnidadesMedida (" +
            "EmpId INTEGER NOT NULL, " +
            "ProdId TEXT NOT NULL, " +
            "ProdUniMedId TEXT NOT NULL, " +
            "ProdUniMedDsc TEXT, " +
            "ProdUniFcConv REAL, " +
            "ProdIdEnv TEXT, " +
            "ProdUniBase TEXT, " +
            "ProdUniVta TEXT, " +
            "ProdUniComp TEXT, " +
            "ProdUniOrden INTEGER, " +
            "ProdCodEan TEXT, " +
            "ProdPesoNeto REAL, " +
            "ProdPesoBruto REAL, " +
            "ProdVolumen REAL, " +
            "ProdCapacidad REAL, " +
            "ProdUniMedModDT TEXT, " +
            "ProdUniMedActiva TEXT, " +
            "ProdUniMedMultiplo INTEGER, " +
            "ProdUniSyncflg INTEGER, " +
            "PRIMARY KEY (EmpId, ProdId, ProdUniMedId)" +
            ");";

    // Tabla Propietarios
    private static final String CREATE_Propietarios = "CREATE TABLE Propietarios (" +
            "EmpId INTEGER NOT NULL, " +
            "PropietarioId TEXT NOT NULL, " +
            "PropietarioDsc TEXT, " +
            "PropietarioModDT TEXT, " +
            "PropietarioActivo TEXT NOT NULL, " +
            "PRIMARY KEY (EmpId, PropietarioId)" +
            ");";

    // Tabla Repartos
    private static final String CREATE_Repartos = "CREATE TABLE Repartos (" +
            "EmpId INTEGER NOT NULL, " +
            "DepId TEXT NOT NULL, " +
            "RepId TEXT NOT NULL, " +
            "RepNom TEXT, " +
            "RepAbv TEXT, " +
            "RepFch TEXT, " +
            "RepIng TEXT, " +
            "RepSts TEXT, " +
            "RepModDt TEXT, " +
            "PlaNro TEXT, " +
            "RepFleteroId TEXT, " +
            "RepCamId TEXT, " +
            "RepCntContado REAL, " +
            "RepCntBultos REAL, " +
            "RepCntVol REAL, " +
            "RepCntPeso REAL, " +
            "RepCntCli REAL, " +
            "RepFlgRetPla TEXT, " +
            "RepFlgSync TEXT, " +
            "RepSyncError TEXT, " +
            "RepSyncModDt TEXT, " +
            "RepSyncIntentos INTEGER, " +
            "RepDepIdDestino TEXT, " +
            "RepCntPedidos INTEGER, " +
            "PRIMARY KEY (EmpId, DepId, RepId)" +
            ");";

    // Tabla RepartosProductos
    private static final String CREATE_RepartosProductos = "CREATE TABLE RepartosProductos (" +
            "EmpId INTEGER NOT NULL, " +
            "DepId TEXT NOT NULL, " +
            "RepId TEXT NOT NULL, " +
            "TpoDocId TEXT, " +
            "DocUsuId TEXT, " +
            "DocId TEXT, " +
            "ProdId TEXT, " +
            "LoteId TEXT, " +
            "FrescuraId TEXT, " +
            "ProdUniMedId TEXT, " +
            "RepPrdCnt REAL, " +
            "RepCntCrg REAL, " +
            "RepProductoModDt TEXT, " +
            "RepProductoFlgSync TEXT, " +
            "RepProductoFlgEstado TEXT, " +
            "RepProductoSyncError TEXT, " +
            "RepProductoSyncDt TEXT, " +
            "RepProductoSyncIntentos INTEGER, " +
            "RepProductoDocNro TEXT, " +
            "RepCntDesc REAL, " +
            "PRIMARY KEY (EmpId, DepId, RepId, TpoDocId, DocUsuId, DocId, ProdId, LoteId, FrescuraId)" +
            ");";

    // Tabla SubClasesProductos
    private static final String CREATE_SubClasesProductos = "CREATE TABLE SubClasesProductos (" +
            "EmpId INTEGER NOT NULL, " +
            "ClaseProdId TEXT NOT NULL, " +
            "SubClaseProdId TEXT NOT NULL, " +
            "SubClaseDsc TEXT, " +
            "SubClaseModDT TEXT, " +
            "SubClaseActivo TEXT NOT NULL, " +
            "PRIMARY KEY (EmpId, ClaseProdId, SubClaseProdId)" +
            ");";

    // Tabla Propietarios
    private static final String CREATE_TiposDePallet = "CREATE TABLE TiposDePallet (" +
            "EmpId INTEGER NOT NULL, " +
            "TipoPalletId TEXT NOT NULL, " +
            "TipoPalletDsc TEXT, " +
            "TipoPalletFecha TEXT, " +
            "TipoPalletMaxPeso REAL, " +
            "TipoPalletMaxVol REAL, " +
            "TipoPalletFlgSync TEXT, " +
            "TipoPalletSyncError TEXT, " +
            "TipoPalletActivo TEXT, " +
            "TipoPalletModDt TEXT, " +
            "PRIMARY KEY (EmpId, TipoPalletId)" +
            ");";

    // Tabla TipoDocumentos
    private static final String CREATE_TiposDocumentos = "CREATE TABLE TiposDocumentos (" +
            "EmpId INTEGER NOT NULL, " +
            "TpoDocId TEXT NOT NULL, " +
            "TpoDocDsc TEXT, " +
            "TpoDocRotulo TEXT, " +
            "TpoDocCtaCte INTEGER, " +
            "TpoDocStck INTEGER, " +
            "TpoDocCaja INTEGER, " +
            "CodContId TEXT, " +
            "TpoDocImprime TEXT, " +
            "TpoDocNumDoc TEXT, " +
            "TipoTdocId TEXT, " +
            "TpoDocEmite TEXT, " +
            "TpoDocModDT TEXT, " +
            "GrpProdId TEXT, " +
            "TpoDocExento TEXT, " +
            "TpoDocMonId TEXT, " +
            "TpoDocVisibleMobile TEXT, " +
            "TpoDocIdAnulacion TEXT, " +
            "TpoDocAnulacion TEXT, " +
            "TpoDocReqAuto TEXT, " +
            "TpoDocPolPermiteEliminar TEXT, " +
            "TpoDocPolPermiteModificar TEXT, " +
            "TpoDocPolPermiteAgregar TEXT, " +
            "PRIMARY KEY (EmpId, TpoDocId)" +
            ");";

    // Tabla Trazas
    private static final String CREATE_Trazas = "CREATE TABLE Trazas (" +
            "EmpId INTEGER NOT NULL, " +
            "TrazaId TEXT NOT NULL, " +
            "TrazaFecha TEXT, " +
            "TrazaSts TEXT, " +
            "TrazaUpdDT TEXT, " +
            "TrazaDow INTEGER, " +
            "TrazaRutaFecha TEXT, " +
            "TrazaModDT TEXT, " +
            "TrazaOrigen TEXT, " +
            "TrazaActiva TEXT NOT NULL, " +
            "TrazaSyncFlg TEXT, " +
            "TrazaSyncDT TEXT, " +
            "TrazaSyncIntentos INTEGER, " +
            "TrazaUsuId TEXT NOT NULL, " +
            "TrazaHoraInicio TEXT, " +
            "TrazaHoraFin TEXT, " +
            "PRIMARY KEY (EmpId, TrazaId)" +
            ");";

    // Tabla UnidadesDeMedida
    private static final String CREATE_Ubicaciones = "CREATE TABLE Ubicacion (" +
            "EmpId INTEGER NOT NULL, " +
            "DepId TEXT NOT NULL, " +
            "UbiId TEXT NOT NULL, " +
            "UbiDsc TEXT, " +
            "UbiModDT TEXT, " +
            "UbiCapAcum REAL, " +
            "UbiCapMax REAL, " +
            "UbiCodBarra TEXT, " +
            "EstInvId TEXT, " +
            "SectorId TEXT, " +
            "UbiLote TEXT, " +
            "UbiClaseUbiId TEXT, " +
            "UbiUniMed TEXT, " +
            "UbiOrden INTEGER, " +
            "PRIMARY KEY (EmpId, DepId, UbiId)" +
            ");";

    // Tabla UnidadesDeMedida
    private static final String CREATE_UnidadesDeMedida = "CREATE TABLE UnidadesDeMedida (" +
            "EmpId INTEGER NOT NULL, " +
            "UniMedId TEXT NOT NULL, " +
            "UniMedDsc TEXT, " +
            "UniMedDscAbr TEXT, " +
            "UniMedModDT TEXT, " +
            "UniMedActiva TEXT, " +
            "UniMedTipo TEXT, " +
            "PRIMARY KEY (EmpId, UniMedId)" +
            ");";

    // Tabla Usuarios
    private static final String CREATE_Usuarios = "CREATE TABLE Usuarios (" +
            "UsuId TEXT NOT NULL, " +
            "UsuNom TEXT, " +
            "UsuPass TEXT, " +
            "UsuMail TEXT, " +
            "UsuActivo INTEGER, " +
            "UsuTelefono TEXT, " +
            "loggedIn TEXT, " +
            "loggedInDT TEXT, " +
            "loggedInEmpId INTEGER, " +
            "UsuSeguridadesCSV TEXT, " +
            "ImpresoraId TEXT, " +
            "UsuDepIdDft TEXT, " +
            "UsuEmpIdDft TEXT, " +
            "PRIMARY KEY (UsuId)" +
            ");";

    // Tabla UsuariosDepositos
    private static final String CREATE_UsuariosDepositos = "CREATE TABLE UsuariosDepositos (" +
            "UsuId TEXT NOT NULL, " +
            "EmpId INTEGER NOT NULL, " +
            "UsuDepId TEXT NOT NULL, " +
            "UsuDepActivo TEXT, " +
            "UsuDepModDt INTEGER, " +
            "PRIMARY KEY (UsuId, EmpId, UsuDepId)" +
            ");";

    // Tabla Variedades
    private static final String CREATE_Variedades = "CREATE TABLE Variedades (" +
            "EmpId INTEGER NOT NULL, " +
            "VariedadId TEXT NOT NULL, " +
            "VariedadDsc TEXT, " +
            "VariedadModDT TEXT, " +
            "PRIMARY KEY (EmpId, VariedadId)" +
            ");";

    // Tabla Vendedores
    private static final String CREATE_Vendedores = "CREATE TABLE Vendedores (" +
            "EmpId INTEGER NOT NULL, " +
            "VenId TEXT NOT NULL, " +
            "VenNombre TEXT, " +
            "VenApellido TEXT, " +
            "VenMovil TEXT, " +
            "VenMail TEXT, " +
            "VenDireccion TEXT, " +
            "ZonaId TEXT, " +
            "VenHabSD TEXT, " +
            "GrpSupVenId TEXT, " +
            "VenNomCompleto TEXT, " +
            "VenUsuId TEXT, " +
            "VenFoto TEXT, " +
            "VenModDT TEXT, " +
            "VenActivo TEXT, " +
            "VenPolPermiteEliminar TEXT, " +
            "VenPolPermiteModificar TEXT, " +
            "VenPolPermiteAgregar TEXT, " +
            "VenDepId_OfcCom TEXT, " +
            "VenCategoria TEXT, " +
            "VenSemanaCiclo INTEGER, " +
            "PRIMARY KEY (EmpId, VenId)" +
            ");";


    // Tabla _LoginStatus
    private static final String CREATE__LoginStatus = "CREATE TABLE _LoginStatus (" +
            "UsuId TEXT NOT NULL, " +
            "EmpId INTEGER NOT NULL, " +
            "UsuPass TEXT, " +
            "UsuPassHash TEXT, " +
            "loggedIn TEXT, " +
            "loggedInDT TEXT, " +
            "token TEXT, " +
            "trazaId TEXT, " +
            "ultima_sincronizacion TEXT, " +
            "ambiente TEXT, " +
            "url_servicios TEXT, " +
            "PRIMARY KEY (UsuId)" +
            ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Categorias);
        db.execSQL(CREATE_ClasesProductos);
        db.execSQL(CREATE_Clientes);
        db.execSQL(CREATE_ClientesLocales);
        db.execSQL(CREATE_CondicionesVenta);
        db.execSQL(CREATE_Conteos);
        db.execSQL(CREATE_ConteosLineas);
        db.execSQL(CREATE_Documentos);
        db.execSQL(CREATE_DocumentosFirmas);
        db.execSQL(CREATE_Empresas);
        db.execSQL(CREATE_Libretas);
        db.execSQL(CREATE_Lineas);
        db.execSQL(CREATE_ListasDePrecios);
        db.execSQL(CREATE_ListasPreVersion);
        db.execSQL(CREATE_Marcas);
        db.execSQL(CREATE_Monedas);
        db.execSQL(CREATE_Pallet);
        db.execSQL(CREATE_PalletProductos);
        db.execSQL(CREATE_Parametros);
        db.execSQL(CREATE_Planillas);
        db.execSQL(CREATE_Portafolios);
        db.execSQL(CREATE_Precios);
        db.execSQL(CREATE_Presentaciones);
        db.execSQL(CREATE_Productos);
        db.execSQL(CREATE_ProductosDeposito);
        db.execSQL(CREATE_ProductosLotes);
        db.execSQL(CREATE_ProdUnidadesMedida);
        db.execSQL(CREATE_Propietarios);
        db.execSQL(CREATE_Repartos);
        db.execSQL(CREATE_RepartosProductos);
        db.execSQL(CREATE_SubClasesProductos);
        db.execSQL(CREATE_TiposDocumentos);
        db.execSQL(CREATE_TiposDePallet);
        db.execSQL(CREATE_Trazas);
        db.execSQL(CREATE_Ubicaciones);
        db.execSQL(CREATE_UnidadesDeMedida);
        db.execSQL(CREATE_Usuarios);
        db.execSQL(CREATE_UsuariosDepositos);
        db.execSQL(CREATE_Variedades);
        db.execSQL(CREATE_Vendedores);
        db.execSQL(CREATE__LoginStatus);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        _Log.w(_DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS Clientes;");
        db.execSQL("DROP TABLE IF EXISTS ClientesLocales;");
        db.execSQL("DROP TABLE IF EXISTS Categorias;");
        db.execSQL("DROP TABLE IF EXISTS ClasesProductos;");
        db.execSQL("DROP TABLE IF EXISTS CondicionesVenta;");
        db.execSQL("DROP TABLE IF EXISTS Conteos;");
        db.execSQL("DROP TABLE IF EXISTS ConteosLineas;");
        db.execSQL("DROP TABLE IF EXISTS Documentos;");
        db.execSQL("DROP TABLE IF EXISTS DocumentosFirmas;");
        db.execSQL("DROP TABLE IF EXISTS Empresas;");
        db.execSQL("DROP TABLE IF EXISTS Libretas");
        db.execSQL("DROP TABLE IF EXISTS Lineas;");
        db.execSQL("DROP TABLE IF EXISTS ListasDePrecios;");
        db.execSQL("DROP TABLE IF EXISTS ListasPreVersiones;");
        db.execSQL("DROP TABLE IF EXISTS Marcas;");
        db.execSQL("DROP TABLE IF EXISTS Monedas;");
        db.execSQL("DROP TABLE IF EXISTS Pallet;");
        db.execSQL("DROP TABLE IF EXISTS PalletProductos;");
        db.execSQL("DROP TABLE IF EXISTS Parametros;");
        db.execSQL("DROP TABLE IF EXISTS Planillas;");
        db.execSQL("DROP TABLE IF EXISTS Portafolios;");
        db.execSQL("DROP TABLE IF EXISTS Precios;");
        db.execSQL("DROP TABLE IF EXISTS Presentaciones;");
        db.execSQL("DROP TABLE IF EXISTS Productos;");
        db.execSQL("DROP TABLE IF EXISTS ProductosDeposito;");
        db.execSQL("DROP TABLE IF EXISTS ProductosLotes;");
        db.execSQL("DROP TABLE IF EXISTS ProdUnidadesMedida;");
        db.execSQL("DROP TABLE IF EXISTS Propietarios;");
        db.execSQL("DROP TABLE IF EXISTS Repartos;");
        db.execSQL("DROP TABLE IF EXISTS RepartosProductos;");
        db.execSQL("DROP TABLE IF EXISTS SubClasesProductos;");
        db.execSQL("DROP TABLE IF EXISTS TiposDocumentos;");
        db.execSQL("DROP TABLE IF EXISTS TiposDePallet;");
        db.execSQL("DROP TABLE IF EXISTS Trazas;");
        db.execSQL("DROP TABLE IF EXISTS Ubicacion;");
        db.execSQL("DROP TABLE IF EXISTS UnidadesDeMedida;");
        db.execSQL("DROP TABLE IF EXISTS Usuarios;");
        db.execSQL("DROP TABLE IF EXISTS UsuariosDepositos;");
        db.execSQL("DROP TABLE IF EXISTS Variedades;");
        db.execSQL("DROP TABLE IF EXISTS Vendedores;");
        db.execSQL("DROP TABLE IF EXISTS _LoginStatus;");
        onCreate(db);
    }

    public void truncate(SQLiteDatabase db) {
        _Log.w(_DBHelper.class.getName(), "Vaciando tablas...");
        db.execSQL("DELETE FROM Categorias;");
        db.execSQL("DELETE FROM ClasesProductos;");
        db.execSQL("DELETE FROM Clientes;");
        db.execSQL("DELETE FROM ClientesLocales;");
        db.execSQL("DELETE FROM CondicionesVenta;");
        db.execSQL("DELETE FROM Conteos;");
        db.execSQL("DELETE FROM ConteosLineas;");
        db.execSQL("DELETE FROM Documentos;");
        db.execSQL("DELETE FROM DocumentosFirmas;");
        db.execSQL("DELETE FROM Empresas;");
        db.execSQL("DELETE FROM Libretas;");
        db.execSQL("DELETE FROM Lineas;");
        db.execSQL("DELETE FROM ListasDePrecios;");
        db.execSQL("DELETE FROM ListasPreVersiones;");
        db.execSQL("DELETE FROM Marcas;");
        db.execSQL("DELETE FROM Monedas;");
        db.execSQL("DELETE FROM Pallet;");
        db.execSQL("DELETE FROM PalletProductos;");
        db.execSQL("DELETE FROM Parametros;");
        db.execSQL("DELETE FROM Planillas;");
        db.execSQL("DELETE FROM Portafolios;");
        db.execSQL("DELETE FROM Precios;");
        db.execSQL("DELETE FROM Presentaciones;");
        db.execSQL("DELETE FROM Productos;");
        db.execSQL("DELETE FROM ProductosDeposito;");
        db.execSQL("DELETE FROM ProductosLotes;");
        db.execSQL("DELETE FROM ProdUnidadesMedida;");
        db.execSQL("DELETE FROM Propietarios;");
        db.execSQL("DELETE FROM Repartos;");
        db.execSQL("DELETE FROM RepartosProductos;");
        db.execSQL("DELETE FROM SubClasesProductos;");
        db.execSQL("DELETE FROM TiposDocumentos;");
        db.execSQL("DELETE FROM TiposDePallet;");
        db.execSQL("DELETE FROM Trazas;");
        db.execSQL("DELETE FROM Ubicacion;");
        db.execSQL("DELETE FROM UnidadesDeMedida;");
        db.execSQL("DELETE FROM Usuarios;");
        db.execSQL("DELETE FROM UsuariosDepositos;");
        db.execSQL("DELETE FROM Variedades;");
        db.execSQL("DELETE FROM Vendedores;");
        db.execSQL("DELETE FROM _LoginStatus;");

        // borro login status
        _LoginStatus.truncate();

        // evaluar vacuum
        // db.execSQL("VACUUM;");   // https://www.tutorialspoint.com/sqlite/sqlite_vacuum.htm
    }
}
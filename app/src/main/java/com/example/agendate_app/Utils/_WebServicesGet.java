package com.sd.solidatec.wms.Utils;

import android.os.AsyncTask;

import com.sd.solidatec.wms.Database.CategoriaDS;
import com.sd.solidatec.wms.Database.ClaseProductoDS;
import com.sd.solidatec.wms.Database.ClienteDS;
import com.sd.solidatec.wms.Database.ConteoDS;
import com.sd.solidatec.wms.Database.LibretaDS;
import com.sd.solidatec.wms.Database.LineaDS;
import com.sd.solidatec.wms.Database.MarcaDS;
import com.sd.solidatec.wms.Database.MonedaDS;
import com.sd.solidatec.wms.Database.PalletDS;
import com.sd.solidatec.wms.Database.PalletProductosDS;
import com.sd.solidatec.wms.Database.ParametroDS;
import com.sd.solidatec.wms.Database.PlanillaDS;
import com.sd.solidatec.wms.Database.PortafolioDS;
import com.sd.solidatec.wms.Database.PresentacionDS;
import com.sd.solidatec.wms.Database.ProdUnidadMedidaDS;
import com.sd.solidatec.wms.Database.ProductoDS;
import com.sd.solidatec.wms.Database.ProductosDepositoDS;
import com.sd.solidatec.wms.Database.ProductosLotesDS;
import com.sd.solidatec.wms.Database.PropietarioDS;
import com.sd.solidatec.wms.Database.RepartosDS;
import com.sd.solidatec.wms.Database.RepartosProductosDS;
import com.sd.solidatec.wms.Database.SubClaseProductoDS;
import com.sd.solidatec.wms.Database.TipoDocumentoDS;
import com.sd.solidatec.wms.Database.TiposDePallet;
import com.sd.solidatec.wms.Database.TiposDePalletDS;
import com.sd.solidatec.wms.Database.UbicacionDS;
import com.sd.solidatec.wms.Database.UnidadDeMedidaDS;
import com.sd.solidatec.wms.Database.UsuarioDepositosDS;
import com.sd.solidatec.wms.Database.VariedadDS;
import com.sd.solidatec.wms.Database.VendedorDS;
import com.sd.solidatec.wms.Database._SyncableGet;
import com.sd.solidatec.wms.Database._SyncableGetResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import static java.lang.Thread.sleep;


public class _WebServicesGet extends AsyncTask<String, Void, String> implements _SyncableGet {

    // private static String base = "http://192.168.1.217:8080/SD1JavaEnvironment/servlet/";
    // private static String base = "https://solidatec.com/sd/servlet/";
    //private static String base = _LoginStatus.getUrl_servicios();

    //v1
    //public static String _getEmpresas = "sd.aws_get_empresas_v1";
    public static String _getCategorias = "sd.aws_get_categorias_v1";
    public static String _getClasesProductos = "sd.aws_get_clasesproductos_v1?";
    public static String _getConteos = "sd.aws_get_conteos_v1";
    public static String _getCondicionesVenta = "sd.aws_get_condicionventa_v1";
    public static String _getClientesLocalesHorarios = "sd.aws_get_clienteslocaleshorarios";
    public static String _getLibretas = "sd.aws_get_libretas_v1";
    public static String _getLineas = "sd.aws_get_lineas_v1";
    //public static String _getListasDePrecios = "sd.aws_get_listasdeprecios_v1";
    //public static String _getListasPreVersiones = "sd.aws_get_listapreversion_v1";
    public static String _getMarcas = "sd.aws_get_marcas_v1";
    public static String _getMonedas = "sd.aws_get_monedas_v1";
    public static String _getPallet = "sd.aws_get_pallet_v1";
    public static String _getPalletProductos = "sd.aws_get_pallet_productos_v1";
    public static String _getParametros = "sd.aws_get_parametros_v1";
    public static String _getPortafolios = "sd.aws_get_portafolios_v1";
    //public static String _getPrecios = "sd.aws_get_precios_v1";
    public static String _getPresentaciones = "sd.aws_get_presentacion_v1";
    public static String _getProductoStock = "sd.aws_get_productostock_v1";
    public static String _getProductosLotes = "sd.aws_get_productoslote_v1";
    public static String _getProductosDeposito = "sd.aws_get_productos_deposito_v1";
    public static String _getPropietarios = "sd.aws_get_propietarios_v1";
    //public static String _getRepartosProductos = "sd.aws_get_repartosproductos_v2";
    public static String _getSubClasesProductos = "sd.aws_get_subclasesproductos_v1";
    public static String _getTiposDePallet = "sd.aws_get_tiposdepallet_v1";
    public static String _getUbicaciones = "sd.aws_get_ubicaciones_v1";
    //public static String _getUsuariosConDepositos = "sd.aws_get_usuarios_con_deposito_v1";
    public static String _getUsuariosDepositos = "sd.aws_get_usuariosdepositos_v1";
    public static String _getVariedades = "sd.aws_get_variedades_v1";
    public static String _getClientes = "sd.aws_get_clientes_wms_v1";
    //public static String _getClientes = "sd.aws_get_clientes_v5";
    public static String _getDocumentosFirmas = "sd.aws_get_documentosfirmas_v1";

    // v2
    public static String _getClientesLocales = "sd.aws_get_clienteslocales_v2";
    public static String _getPlanillas = "sd.aws_get_planillas_v2";
    public static String _getRepartos = "sd.aws_get_repartos_v2";
    public static String _getTrazas = "sd.aws_get_trazas_v2";
    public static String _getUnidadesDeMedida = "sd.aws_get_unidadesdemedida_v2";
    public static String _getUsuariosConDepositos = "sd.aws_get_usuarios_con_deposito_v2";

    // v3
    public static String _getTiposDocumentos = "sd.aws_get_tiposdocumentos_v3";
    public static String _getProductos = "sd.aws_get_productos_v3";
    public static String _getVendedores = "sd.aws_get_vendedores_v3";
    public static String _getProdUnidadesMedida = "sd.aws_get_produnidadmedida_v3";
    public static String _getUsuarios = "sd.aws_get_usuarios_v3";
    public static String _getRepartosProductos = "sd.aws_get_repartosproductos_v3";


    //v8
    public static String _getDocumentos = "sd.aws_get_documentos_v8";

    private String urlIn;               // url de donde voy a sincronizar
    private _SyncableGet syncableAll;   // si estoy sincronizando todos los tipos, a quien le doy el retorno
    private _SyncableGet syncableUnico; // si estoy sincronizando un solo tipo, a quien le doy el retorno
    private String tag;                 // tag identificando el tipo de objeto que sincronizo
    private String dtOper;              // fecha desde la ultima sincronizacion
    private String dtUsuario;           // fecha desde del usuario

    private String identificador1;      // primer identificador, opcional
    private String identificador2;      // segundo identificador, opcional
    private String identificador3;      // tercer identificador, opcional
    private String identificador4;      // cuarto identificador, opcional
    private String identificador5;      // quinto identificador, opcional
    private String jsonBodyIn;          // opcional, body del request, en JSON, opcional

    // auxiliares
    private Object objeto1;

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag) {
        // constructor para objetos que no tienen/requieren moddt
        super();
        this.urlIn = _LoginStatus.getUrl_servicios() + urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        this.dtOper = "";
        this.identificador1 = "";
        this.identificador2 = "";
        this.identificador3 = "";
        this.identificador4 = "";
        this.identificador5 = "";
        this.jsonBodyIn = "";
        this.dtUsuario = _Utils.ahora();
    }

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag, String dtOper) {
        // constructor para objetos que tienen/requieren moddt
        super();
        this.urlIn = _LoginStatus.getUrl_servicios() + urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        this.dtOper = dtOper;
        this.identificador1 = "";
        this.identificador2 = "";
        this.identificador3 = "";
        this.identificador4 = "";
        this.identificador5 = "";
        this.jsonBodyIn = "";
        this.objeto1 = null;
        this.dtUsuario = _Utils.ahora();
    }

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag, String dtOper, String identificador1, String identificador2, String identificador3, String identificador4, String identificador5) {
        // constructor para objetos que tienen/requieren moddt o identificadores adicionales
        super();
        this.urlIn = _LoginStatus.getUrl_servicios() + urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        this.dtOper = dtOper;
        this.identificador1 = identificador1;
        this.identificador2 = identificador2;
        this.identificador3 = identificador3;
        this.identificador4 = identificador4;
        this.identificador5 = identificador5;
        this.jsonBodyIn = "";
        this.objeto1 = null;
        this.dtUsuario = _Utils.ahora();
    }

    public _WebServicesGet(String urlIn, _SyncableGet syncable, String tag, String dtOper, String identificador1, String identificador2, String identificador3, String identificador4, String identificador5, String jsonBodyIn, Object objeto1) {
        // constructor para objetos que tienen/requieren moddt o identificadores adicionales
        super();
        this.urlIn = _LoginStatus.getUrl_servicios() + urlIn;
        this.syncableUnico = syncable;
        this.tag = tag;
        this.dtOper = dtOper;
        this.identificador1 = identificador1;
        this.identificador2 = identificador2;
        this.identificador3 = identificador3;
        this.identificador4 = identificador4;
        this.identificador5 = identificador5;
        this.jsonBodyIn = jsonBodyIn;
        this.objeto1 = objeto1;
        this.dtUsuario = _Utils.ahora();
    }

    @Override
    protected String doInBackground(String... args) {

        // TODO: chequear
        //// android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_FOREGROUND);

        String strOut = "";

        // 1
        //_Log.i("SYNCRETURN Intento 1", tag);
        strOut = httpPost().trim();

        // 2
        if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
            try {
                sleep(1000);
                _Log.i("SYNCRETURN Intento 2", tag);
                _ProgressBar.update(tag + " - Error\nIntento 2 de 10...");
                strOut = httpPost().trim();
            } catch (InterruptedException e) {
                _Log.d("WebServicesGet : Lin198", e.getMessage());
                e.printStackTrace();
            }
        }

        if(!identificador1.equalsIgnoreCase("ACTUALIZAR")){
            // 3
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(2000);
                    _Log.i("SYNCRETURN Intento 3", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 3 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin212", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 4
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(3000);
                    _Log.i("SYNCRETURN Intento 4", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 4 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin225", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 5
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(4000);
                    _Log.i("SYNCRETURN Intento 5", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 5 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin238", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 6
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(5000);
                    _Log.i("SYNCRETURN Intento 6", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 6 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin251", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 7
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(5000);
                    _Log.i("SYNCRETURN Intento 7", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 7 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin264", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 8
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(5000);
                    _Log.i("SYNCRETURN Intento 8", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 8 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin277", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 9
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(5000);
                    _Log.i("SYNCRETURN Intento 9", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 9 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin290", e.getMessage());
                    e.printStackTrace();
                }
            }

            // 10
            if (strOut == null || strOut.length() == 0 || strOut.equalsIgnoreCase("SocketTimeoutException")) {
                try {
                    sleep(5000);
                    _Log.i("SYNCRETURN Intento 10", tag);
                    _ProgressBar.update(tag + " - Error\nIntento 10 de 10...");
                    strOut = httpPost().trim();
                } catch (InterruptedException e) {
                    _Log.d("WebServicesGet : Lin303", e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        _SyncableGetResponse sgr = new _SyncableGetResponse();
        sgr.tag = tag;
        sgr.out = strOut;
        sgr.identificador1 = identificador1;
        sgr.identificador2 = identificador2;
        sgr.identificador3 = identificador3;
        sgr.identificador4 = identificador4;
        sgr.identificador5 = identificador5;
        sgr.objeto1 = objeto1;

        _ProgressBar.update("Actualizando " + tag + "...");

        syncableUnico.syncGetReturn(tag, strOut, sgr);
        return strOut;
    }

    public String httpPost() {
        BufferedReader in = null;
        HttpURLConnection huc = null;
        String inputLine;
        String strOut;

        // TODO: ir a buscar a donde corresponda: TOKEN
        String UsuId = _LoginStatus.getUsuId();
        String EmpId = Integer.toString(_LoginStatus.getEmpId());
        String Pass = _LoginStatus.getUsuPass();
        String DeviceId = "1";
        String Imei = _Utils.getImei();
        String Token = "lite.1234";
        String Plataforma = "M";
        String ModuloMobile = _Utils.moduloMobile;

        _ProgressBar.update("Descargando " + tag + "...");

        try {
            URL url = new URL(urlIn);
            strOut = "";
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Connection", "Close");
            huc.setRequestProperty("UsuId", UsuId);
            huc.setRequestProperty("EmpId", EmpId);
            huc.setRequestProperty("Pass", Pass);
            huc.setRequestProperty("DeviceId", DeviceId);
            huc.setRequestProperty("Imei", Imei);
            huc.setRequestProperty("Token", Token);
            huc.setRequestProperty("Plataforma", Plataforma);
            huc.setRequestProperty("ModuloMobile", ModuloMobile);
            if (dtOper != null && !dtOper.equals(""))
                huc.setRequestProperty("DtOper", dtOper);
            if (dtUsuario != null && !dtUsuario.equals(""))
                huc.setRequestProperty("dtUsuario", dtUsuario);
            if (identificador1 != null && !identificador1.equals(""))
                huc.setRequestProperty("Id1", identificador1);
            if (identificador2 != null && !identificador2.equals(""))
                huc.setRequestProperty("Id2", identificador2);
            if (identificador3 != null && !identificador3.equals(""))
                huc.setRequestProperty("Id3", identificador3);
            if (identificador4 != null && !identificador4.equals(""))
                huc.setRequestProperty("Id4", identificador4);
            if (identificador5 != null && !identificador5.equals(""))
                huc.setRequestProperty("Id5", identificador5);

            if (tag.equals("Repartos")) {
                String planillasRef = new PlanillaDS().getAllPlanillasDeReferenciaPlaNro("", "");
                //huc.setRequestProperty("Id1", planillasRef.get(0).getPlaNro());
                if (planillasRef != null && !planillasRef.equalsIgnoreCase("")) {
                    huc.setRequestProperty("Id1", planillasRef);
                }
            }
            if (jsonBodyIn != null && jsonBodyIn.length() > 0) {
                // antes se mandaba como header, pero Tomcat rechaza headers muy largos, se pasa al body
                // https://stackoverflow.com/q/46674695/1259763
                // huc.setRequestProperty(jsonNameIn, jsonBodyIn);
                //_Log.d("_WebServicesGet JSONBody", jsonBodyIn);

                // send data
                huc.setRequestProperty("Content-Type", "application/json");
                huc.setRequestProperty("Content-Length", Integer.toString(jsonBodyIn.getBytes().length));
                huc.setDoInput(true);
                huc.setDoOutput(true);
                DataOutputStream dos = new DataOutputStream(huc.getOutputStream());
                dos.write(jsonBodyIn.getBytes("UTF-8"));
                dos.flush();
                dos.close();
            }

            huc.setConnectTimeout(10 * 1000);

            in = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                if (strOut.equalsIgnoreCase("")) {
                    strOut = inputLine;
                } else {
                    // strOut = strOut + "\n" + inputLine;
                    strOut = strOut + inputLine;
                }
            }
            _LoginStatus.setServidorEnLinea(true);
        } catch (ConnectException ce) {
            _LoginStatus.setServidorEnLinea(false);
            strOut = "ConnectException";
            _Log.d("WebServicesGet : Lin413", ce.getMessage());
            ce.printStackTrace();
        } catch (SocketTimeoutException ste) {
            _LoginStatus.setServidorEnLinea(false);
            strOut = "SocketTimeoutException";
            _Log.d("WebServicesGet : Lin413", ste.getMessage());
            ste.printStackTrace();
        } catch (Exception e) {
            _LoginStatus.setServidorEnLinea(false);
            strOut = "";
            _Log.d("WebServicesGet : Lin423", e.getMessage());
            e.printStackTrace();
        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            _Log.d("WebServicesGet : Lin431", e.getMessage());
            e.printStackTrace();
        }
        if (huc != null) huc.disconnect();
        in = null;
        huc = null;

        return strOut;
    }
    /*
     *   doModDT definie si debe o no mandarse el ultimo moddt de cada tabla
     *   la funcion es que en la sincronizacion automatica de 6am no se mande el modDT asi se piden todos los datos
     * */
    public int syncAll(_SyncableGet syncable, boolean doModDT) {
        syncableAll = syncable;
        int iRet = 0;

        // elimino el contenido de UsuVendedoresIn, para que se refresque por si hubo un cambio en licencias
        _LoginStatus.setUsuVendedoresIn("");

        // Categorias
        _Log.d("_WebServicesGet:syncAll", "disparo Categorias");
        CategoriaDS mCategoriaDS = new CategoriaDS();
        mCategoriaDS.syncGet(this, doModDT);
        mCategoriaDS = null;
        iRet++;

        // ClasesProductos
        _Log.d("_WebServicesGet:syncAll", "disparo ClasesProductos");
        new ClaseProductoDS().syncGet(this, doModDT);
        iRet++;

        // Conteos
        _Log.d("_WebServicesGet:syncAll", "disparo Conteos");
        new ConteoDS().syncGet(this, doModDT);
        iRet++;

        // Clientes
        _Log.d("_WebServicesGet:syncAll", "disparo Clientes");
        ClienteDS mClienteDS = new ClienteDS();
        mClienteDS.syncGet(this, doModDT);
        mClienteDS = null;
        iRet++;

        // Lineas
        _Log.d("_WebServicesGet:syncAll", "disparo Lineas");
        LineaDS mLineaDS = new LineaDS();
        mLineaDS.syncGet(this, doModDT);
        mLineaDS = null;
        iRet++;

        // Lineas
        _Log.d("_WebServicesGet:syncAll", "disparo Libretas");
        LibretaDS mLibretaDS = new LibretaDS();
        mLibretaDS.syncGet(this, doModDT);
        mLibretaDS = null;
        iRet++;

        // Marcas
        _Log.d("_WebServicesGet:syncAll", "disparo Marcas");
        MarcaDS mMarcaDS = new MarcaDS();
        mMarcaDS.syncGet(this, doModDT);
        mMarcaDS = null;
        iRet++;

        // Monedas
        _Log.d("_WebServicesGet:syncAll", "disparo Monedas");
        MonedaDS mMonedaDs = new MonedaDS();
        mMonedaDs.syncGet(this, doModDT);
        iRet++;

        // Parametros
        _Log.d("_WebServicesGet:syncAll", "disparo Parametros");
        ParametroDS mParametroDS = new ParametroDS();
        mParametroDS.syncGet(this, doModDT);
        mParametroDS = null;
        iRet++;

        // Pallet
        _Log.d("_WebServicesGet:syncAll", "disparo Pallet");
        PalletDS mPalletDS = new PalletDS();
        mPalletDS.syncGet(this, doModDT);
        mPalletDS = null;
        iRet++;

        // PalletProductos
        _Log.d("_WebServicesGet:syncAll", "disparo PalletProductos");
        PalletProductosDS mPalletProductosDS = new PalletProductosDS();
        mPalletProductosDS.syncGet(this, doModDT);
        mPalletProductosDS = null;
        iRet++;

        // Planillas
        _Log.d("_WebServicesGet:syncAll", "disparo Planillas");
        PlanillaDS mPlanillasDS = new PlanillaDS();
        mPlanillasDS.syncGet(this, doModDT);
        mPlanillasDS = null;
        iRet++;

        // Portafolios
        _Log.d("_WebServicesGet:syncAll", "disparo Portafolios");
        PortafolioDS mPortafolioDS = new PortafolioDS();
        mPortafolioDS.syncGet(this, doModDT);
        iRet++;

        // Presentacion
        _Log.d("_WebServicesGet:syncAll", "disparo Presentacion");
        new PresentacionDS().syncGet(this, doModDT);
        iRet++;

        // Productos
        _Log.d("_WebServicesGet:syncAll", "disparo Productos");
        ProductoDS mProductoDS = new ProductoDS();
        mProductoDS.syncGet(this, doModDT);
        mProductoDS = null;
        iRet++;

        // Productos Deposito
        _Log.d("_WebServicesGet:syncAll", "disparo Productos Deposito");
        ProductosDepositoDS mProductosDepositoDS = new ProductosDepositoDS();
        mProductosDepositoDS.syncGet(this, doModDT);
        mProductosDepositoDS = null;
        iRet++;

        // Productos Lotes
        _Log.d("_WebServicesGet:syncAll", "disparo Productos Lotes");
        ProductosLotesDS mProductosLotesDS = new ProductosLotesDS();
        mProductosLotesDS.syncGet(this, doModDT);
        mProductosLotesDS = null;
        iRet++;


        // ProdUnidadesMedida
        _Log.d("_WebServicesGet:syncAll", "disparo ProdUnidadMedida");
        ProdUnidadMedidaDS mProdUnidadMedidaDS = new ProdUnidadMedidaDS();
        mProdUnidadMedidaDS.syncGet(this, doModDT);
        mProdUnidadMedidaDS = null;
        iRet++;

        // Propietarios
        _Log.d("_WebServicesGet:syncAll", "disparo Propietarios");
        new PropietarioDS().syncGet(this, doModDT);
        iRet++;

        // Repartos
        _Log.d("_WebServicesGet:syncAll", "disparo Repartos");
        RepartosDS mRepartosDS = new RepartosDS();
        mRepartosDS.syncGet(this, doModDT);
        mRepartosDS = null;
        iRet++;

        // RepartosProductos
        _Log.d("_WebServicesGet:syncAll", "disparo RepartosProductos");
        RepartosProductosDS mRepartosProductosDS = new RepartosProductosDS();
        mRepartosProductosDS.syncGet(this, doModDT);
        mRepartosProductosDS = null;
        iRet++;

        // SubClasesProductos
        _Log.d("_WebServicesGet:syncAll", "disparo SubClasesProductos");
        new SubClaseProductoDS().syncGet(this, doModDT);
        iRet++;

        // TiposDocumentos
        _Log.d("_WebServicesGet:syncAll", "disparo TiposDocumentos");
        TipoDocumentoDS mtipoDocumentoDS = new TipoDocumentoDS();
        mtipoDocumentoDS.syncGet(this, doModDT);
        mtipoDocumentoDS = null;
        iRet++;

        // TiposDePallet
        _Log.d("_WebServicesGet:syncAll", "disparo TiposDePallet");
        TiposDePalletDS mTiposDePalletDS = new TiposDePalletDS();
        mTiposDePalletDS.syncGet(this, doModDT);
        mTiposDePalletDS = null;
        iRet++;

        // Ubicaciones
        _Log.d("_WebServicesGet:syncAll", "disparo Ubicaciones");
        UbicacionDS mUbicacionesDS = new UbicacionDS();
        mUbicacionesDS.syncGet(this, doModDT);
        mUbicacionesDS = null;
        iRet++;

        // UnidadesDeMedida
        _Log.d("_WebServicesGet:syncAll", "disparo UnidadesDeMedida");
        UnidadDeMedidaDS mUnidadDeMedidaDS = new UnidadDeMedidaDS();
        mUnidadDeMedidaDS.syncGet(this, doModDT);
        mUnidadDeMedidaDS = null;
        iRet++;

        // UnidadesDeMedida
        _Log.d("_WebServicesGet:syncAll", "disparo UsuariosDepositos");
        UsuarioDepositosDS mUsuarioDepositosDS = new UsuarioDepositosDS();
        mUsuarioDepositosDS.syncGet(this, doModDT);
        mUsuarioDepositosDS = null;
        iRet++;

        // Variedades
        _Log.d("_WebServiceGet:syncAll", " disparo Varieades");
        new VariedadDS().syncGet(this, doModDT);
        iRet++;

        // Vendedores
        _Log.d("_WebServicesGet:syncAll", "disparo Vendedores");
        VendedorDS mVendedorDS = new VendedorDS();
        mVendedorDS.syncGet(this, doModDT);
        mVendedorDS = null;
        iRet++;

        return iRet;
    }

    @Override
    public boolean syncGetReturn(String tag, String out, _SyncableGetResponse sgr) {
        syncableAll.syncGetReturn(tag, out, sgr);
        return true;
    }
}
package com.example.agendate_app.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class _Utils {

    static public String moduloMobile = "PREVENTA";
    static Context context;
    static Activity activity;
    static public String fragmentCurrentString;
    static public Fragment fragmentCurrent;
    static public Fragment fragmentLast;

    public static String CERRAR = "cerrar";
    public static String ATRAS = "atras";
    public static String FRAGMENT = "fragment";
    public static String NADA = "nada";

    private static Boolean upflag = true;
    private static Bitmap bitmap, bitmapRotate;
    private static String newFileName = "";
    static String imagepath = "";
    static File file;

    static List<Rubro> mRubros  = new ArrayList<Rubro>();

    public static List<Rubro> getmRubros() {
        return mRubros;
    }

    public static void setmRubros(List<Rubro> mRubros) {
        _Utils.mRubros = mRubros;
    }

    public static void addRubro(Rubro nuevoRubro){
        _Utils.mRubros.add(nuevoRubro);
    }

    public static String appFolderName = Environment.getExternalStorageDirectory() + "/solidatec/pv";

    public static int getAppVersionCode() {
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            // String version = pInfo.versionName;
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Utils : Lin68", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }


    public static String getAppVersionName() {
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            // String version = pInfo.versionName;
            return pInfo.versionName;
        } catch (Exception e) {
            Log.d("Utils : Lin80", e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    // getter y setter de context
    public static Context getContext() {
        return context;
    }

    public static void setContext(Context ctx) {
        context = ctx;
    }

    // getter y setter de activity
    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity act) {
        activity = act;
    }

    public static MainActivity getActivityMain() {
        return (MainActivity) activity;
    }

    public static void setTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(title);
    }

    private static boolean mantenerFiltro;

    public static boolean getMantenerFiltro() {
        return mantenerFiltro;
    }


    private static boolean mantenerEstadoAbierto = true;

    public static boolean getMantenerEstadoAbierto() {
        return mantenerEstadoAbierto;
    }


    private static boolean mantenerEstadoCerrado = false;

    public static boolean getMantenerEstadoCerrado() {
        return mantenerEstadoCerrado;
    }

    // fecha y hora
    /*public static String hoy() {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtFormatter.print(DateTime.now());
    }

    public static String ahora() {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dtFormatter.print(ahoraDateTime());
    }

    public static DateTime ahoraDateTime() {
        return DateTime.now();
    }

    public static String hoyYYYYMMDD() {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyyMMdd");
        return dtFormatter.print(DateTime.now());
    }

    public static DateTime stringToDateTime(String sDateTime) {
        // String sDateTime en formato yyyy-MM-dd HH:mm:ss
        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        String mDateTime = sDateTime.trim();
        mDateTime = mDateTime.replace("T", " ");

        return DateTime.parse(mDateTime, DateTimeFormat.forPattern(dateFormat));
    }*/

    public static Boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void fragment(Fragment f) {
        fragment(f, null);
    }

    public static void fragment(Fragment f, Bundle b) {
        FragmentManager fm = getActivityMain().getSupportFragmentManager();
        fragmentLast = fm.findFragmentById(R.id.content_frame);

        fragmentCurrentString = f.getClass().getCanonicalName();
        fragmentCurrent = f;

        if (b != null) f.setArguments(b);

        // mando un toast con el nombre del fragment, solo en validacion
        //if (_LoginStatus.getAmbiente() != null && _LoginStatus.getAmbiente().contains("VALIDACION"))
            //com.sd.solidatec.wms.Utils.
                    _Utils.toast(f.getClass().getSimpleName());

        fm.beginTransaction().replace(R.id.content_frame, f).setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).commitAllowingStateLoss();
    }

    // que hacer en el onBackPressed
    public static void setBackAction(View view, final String action) {
        // action: cerrar, atras, fragment
        setBackAction(view, action, null, true, null);
    }

    // que hacer en el onBackPressed con Bundle
    public static void setBackAction(View view, final String action, Bundle bundle) {
        // action: cerrar, atras, fragment
        setBackAction(view, action, null, true, bundle);
    }

    public static void setBackAction(View view, final Fragment fragment) {
        // action: cerrar, atras, fragment
        setBackAction(view, FRAGMENT, fragment, true, null);
    }

    public static void setBackAction(View view, final Fragment fragment, Bundle bundle) {
        // action: cerrar, atras, fragment
        setBackAction(view, FRAGMENT, fragment, true, bundle);
    }

    private static void setBackAction(View view, final String action, @Nullable final Fragment fragment, boolean doSubViews, final Bundle bundle) {
        // action: cerrar, atras, fragment
        // para el fragment
        view.setFocusableInTouchMode(true);
        if (doSubViews)
            view.requestFocus(); // el request focus solo se lo aplico al fragment, que es el unico que viene con doSubViews en True
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) return false;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (action.equalsIgnoreCase(CERRAR)) {
                        // minimizo la aplicacion
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(startMain);
                    } else if (action.equalsIgnoreCase(ATRAS)) {
                        // voy un fragment para atras
                        _Utils.fragment(_Utils.fragmentLast, bundle);
                    } else if (action.equalsIgnoreCase(FRAGMENT)) {
                        // voy a un fragment especifico
                        if (fragment == null) {
                            // si me mandan null me voy a FragmentMain
                            //_Utils.fragment(new MainFragment(), bundle);
                        } else {
                            // sino voy al que me pasaron
                            _Utils.fragment(fragment, bundle);
                        }
                    } else if (action.equalsIgnoreCase(NADA)) {
                        // no hago nada
                    }
                }
                return false;
            }
        });

        // para todos los text con contentdescription = text
        if (doSubViews) {
            ArrayList<View> views = new ArrayList<View>();
            view.findViewsWithText(views, "text", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
            for (View v : views) {
                setBackAction(v, action, fragment, false, bundle);
            }
        }
    }

    /*
    public static void drawerEnable() {

        getActivityMain().drawerEnable();
    }

    public static void drawerDisable() {
        getActivityMain().drawerDisable();
    }
    */
    public static void keyboardShow() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    public static void keyboardHide() {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("Utils : Lin303", e.getMessage());
             e.printStackTrace();
        }
    }

    public static void toast(final String msg) {
        toast(msg, Toast.LENGTH_SHORT);
    }

    public static void toast(final String msg, final int ToastLength) {
        _Utils.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), msg, ToastLength).show();
            }
        });
    }

    // https://stackoverflow.com/questions/7485114/how-to-zip-and-unzip-the-files
    public static void zip(String file, String zipFile) throws IOException {
        String[] files = {file};
        zip(files, zipFile);
    }

    private static void zip(String[] files, String zipFile) throws IOException {
        int BUFFER_SIZE = 8192;
        BufferedInputStream origin;

        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));

        try {
            byte data[] = new byte[BUFFER_SIZE];

            for (String file : files) {
                FileInputStream fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(file.substring(file.lastIndexOf("/") + 1));
                    zos.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        zos.write(data, 0, count);
                    }

                } catch (IOException e) {
                    Log.d("Utils : Lin354", e.getMessage());
                    e.printStackTrace();

                } finally {
                    origin.close();
                }
            }
        } finally {
            zos.close();
        }
    }

    public static String getImei() {
        String imei = "SINIMEI";

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // no tengo permiso para acceder al IMEI, retorno sin imei
            return "SINIMEI";
        }

        // caso que tenga permiso lo busco
        try {
            TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                imei = tm.getDeviceId();
            }
            if (imei == null || imei.length() == 0 || imei.equalsIgnoreCase("SINIMEI")) {
                imei = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            return imei;
        } catch (Exception e) {
            return imei;
        }
    }

    public static Double doubleValueOf(String sDouble) {
        try {
            //TODO: tercer posicion de derecha a izquierda
            if (sDouble.length() > 6) {
                int i = sDouble.length() - 1;
                if (String.valueOf(sDouble.charAt(i - 2)).equalsIgnoreCase(".")) {
                    sDouble = sDouble.replace(",", "");
                } else if (String.valueOf(sDouble.charAt(i - 2)).equalsIgnoreCase(",")) {
                    sDouble = sDouble.replace(".", "");
                    sDouble = sDouble.replace(",", ".");
                }
            } else {
                sDouble = sDouble.replace(",", ".");
            }
            if (sDouble.equalsIgnoreCase("") || sDouble.length() == 0) {
                sDouble = "0";
            }
        } catch (Exception e) {
            Log.e("_Utils.doubleValueOf", sDouble);
            Log.e("_Utils.doubleValueOf", String.valueOf(e));
            sDouble = "0";
        }

        return Double.valueOf(sDouble);
    }

/*
    public static String dateToString(DateTime dt) {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtFormatter.print(dt);
    }

    public static String dateTimeToString(DateTime dt) {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dtFormatter.print(dt);
    }

    public static DateTime stringToDate(String sDate) {
        // String sDate en formato yyyy-MM-dd
        String dateFormat = "yyyy-MM-dd";
        return DateTime.parse(sDate, DateTimeFormat.forPattern(dateFormat));
    }

    public static String doubleFormat_Importe(double d) {
        return new DecimalFormat("#,##0.00").format(new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static String ahoraMilisegundos() {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS");
        return dtFormatter.print(DateTime.now());
    }

    public static LoadingThread getLoading(){
        return loading;
    }

    ////////////////////////////////////////-Metodos para envio de fotos por SFTP-////////////////////////////////////////

    public static void enviarFoto(String docId, String tpoDocId, String docUsuId, String docFirmaId){
        try{
            FirmaASubir = new DocumentoFirmaDS().getDocumentoFirma(docId, tpoDocId, docUsuId, docFirmaId);

            byte[] byteArray = FirmaASubir.getDocFirma();
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

            if (Float.valueOf(getImageOrientation()) >= 0) {
                bitmapRotate = rotateImage(bitmap, Float.valueOf(getImageOrientation()));
            } else {
                bitmapRotate = bitmap;
                bitmap.recycle();
            }

            File loaclPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String root = loaclPath.getAbsolutePath();
            File myDir = new File(root);
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;

            newFileName = FirmaASubir.getDocId();
            imagepath = root + "/" + newFileName+ ".jpeg";
            file = new File(myDir, newFileName+ ".jpeg");

            saveFile(bitmapRotate, file);
            generarRegistroArchivo("N");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }

    //    In some mobiles image will get rotate so to correting that this code will help us
    private static int getImageOrientation() {
        final String[] imageColumns = {MediaStore.Images.Media._ID, MediaStore.Images.ImageColumns.ORIENTATION};
        final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";
        Cursor cursor = com.sd.solidatec.wms.Utils._Utils.getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imageColumns, null, null, imageOrderBy);

        if (cursor.moveToFirst()) {
            int orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION));
            System.out.println("orientation===" + orientation);
            cursor.close();
            return orientation;
        } else {
            return 0;
        }
    }

    //    Saving file to the mobile internal memory
    private static void saveFile(Bitmap sourceUri, File destination) {
        if (destination.exists()) destination.delete();
        saveFileOnly(sourceUri, destination);
        ConnectionDetector cd = new ConnectionDetector(com.sd.solidatec.wms.Utils._Utils.getContext());
        if (cd.isConnectingToInternet()) {

            new DoFileUpload().execute();
        } else {
            Toast.makeText(com.sd.solidatec.wms.Utils._Utils.getContext(), "No Internet Connection..", Toast.LENGTH_LONG).show();
        }
    }

    private static void saveFileOnly(Bitmap sourceUri, File destination){
        try {
            FileOutputStream out = new FileOutputStream(destination);
            sourceUri.compress(Bitmap.CompressFormat.JPEG, 20, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarRegistroArchivo(String estadoSync){
        try{
            // Obtengo URL y Path del FTP
            String hostFTP = new ParametroDS().getParTxt(_LoginStatus.getEmpId(), ParametroDS._HOST_FTP);

            // Obtengo carpeta del FTP
            String carpetaFTP = new ParametroDS().getParTxt(_LoginStatus.getEmpId(), ParametroDS._CARPETA_CLIENTE_FTP);

            String puerto = "/";
            if(_LoginStatus.getAmbiente().equalsIgnoreCase(_LoginStatus._AMBIENTE_VALIDACION)){
                puerto = ":8080/";
            }

            String archPathFile = "";
            if(_LoginStatus.getAmbiente().equalsIgnoreCase(_LoginStatus._AMBIENTE_VALIDACION)){
                archPathFile = "http://" + hostFTP + puerto + _LoginStatus._FOLDERPATH_FTP + _LoginStatus._PATH_ARCHIVOS + carpetaFTP + newFileName + ".jpeg";
            }else{
                archPathFile = "http://" + hostFTP + puerto + _LoginStatus._FOLDERPATH_FTP_PROD +  _LoginStatus._PATH_ARCHIVOS + carpetaFTP + newFileName + ".jpeg";
            }

            //Grabo firma en BDD
            FirmaASubir.setDocFirmaModDT(com.sd.solidatec.wms.Utils._Utils.hoy());
            FirmaASubir.setDocFirmaFlgSync("P");
            FirmaASubir.setDocFirmaActivo("S");
            FirmaASubir.setDocFirmaImagen(archPathFile);
            FirmaASubir.setDocFirmaDsc(newFileName+ ".jpeg");

            if(estadoSync.equalsIgnoreCase("N")){
                new DocumentoFirmaDS().createDocumentoFirma(FirmaASubir);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static class DoFileUpload extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
//            pDialog = new ProgressDialog(_Utils.getContext());
//            pDialog.setMessage("Subiendo imagen, Por favor espere..");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Set your file path here
                FileInputStream fstrm = new FileInputStream(imagepath);
                // Set your server page url (and the file title/description)
                FTPFileUpload hfu = new FTPFileUpload();
                //upflag = hfu.upload("www-root","S0l1daRePr0!",newFileName,file);
                upflag = hfu.upload("root","S0l1daRet3st!",newFileName,file);
            } catch (FileNotFoundException e) {
                // Error: File not found
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (upflag) {
                Toast.makeText(com.sd.solidatec.wms.Utils._Utils.getContext(), "Imagen enviada!", Toast.LENGTH_LONG).show();
                _Log.d("Imagen enviada: ", "_Utils lin.918");

                generarRegistroArchivo("S");

                FirmaASubir.setDocFirmaFlgSync("S");
                new DocumentoFirmaDS().createDocumentoFirma(FirmaASubir);
                //new ArchivoDS().syncSet(syncSet, nuevaFoto);
            } else {
                Toast.makeText(com.sd.solidatec.wms.Utils._Utils.getContext(), "La imagen no se pudo enviar..", Toast.LENGTH_LONG).show();
                _Log.d("Imagen no enviada: ", "_Utils lin.927");
            }
        }
    }

    public static void initLoading(String mensaje) {
        loading = new LoadingThread(mensaje);
        loading.run();
    }

    public static void hideLoading() {
        loading.interrupt();
    }

    public static int convertKeycode(int keyCode){
        int value;
        switch(keyCode){
            case KeyEvent.KEYCODE_0:
                value = 0;
                break;
            case KeyEvent.KEYCODE_1:
                value = 1;
                break;
            case KeyEvent.KEYCODE_2:
                value = 2;
                break;
            case KeyEvent.KEYCODE_3:
                value = 3;
                break;
            case KeyEvent.KEYCODE_4:
                value = 4;
                break;
            case KeyEvent.KEYCODE_5:
                value = 5;
                break;
            case KeyEvent.KEYCODE_6:
                value = 6;
                break;
            case KeyEvent.KEYCODE_7:
                value = 7;
                break;
            case KeyEvent.KEYCODE_8:
                value = 8;
                break;
            case KeyEvent.KEYCODE_9:
                value = 9;
                break;
            default:
                value = -1;
                break;
        }
        return value;
    }

    public static String convertKeycode2(int keyCode){
        String value;
        switch(keyCode){
            case KeyEvent.KEYCODE_0:
                value = "0";
                break;
            case KeyEvent.KEYCODE_1:
                value = "1";
                break;
            case KeyEvent.KEYCODE_2:
                value = "2";
                break;
            case KeyEvent.KEYCODE_3:
                value = "3";
                break;
            case KeyEvent.KEYCODE_4:
                value = "4";
                break;
            case KeyEvent.KEYCODE_5:
                value = "5";
                break;
            case KeyEvent.KEYCODE_6:
                value = "6";
                break;
            case KeyEvent.KEYCODE_7:
                value = "7";
                break;
            case KeyEvent.KEYCODE_8:
                value = "8";
                break;
            case KeyEvent.KEYCODE_9:
                value = "9";
                break;
            case KeyEvent.KEYCODE_A:
                value = "A";
                break;
            case KeyEvent.KEYCODE_B:
                value = "B";
                break;
            case KeyEvent.KEYCODE_C:
                value = "C";
                break;
            case KeyEvent.KEYCODE_D:
                value = "D";
                break;
            case KeyEvent.KEYCODE_E:
                value = "E";
                break;
            case KeyEvent.KEYCODE_F:
                value = "F";
                break;
            case KeyEvent.KEYCODE_G:
                value = "G";
                break;
            case KeyEvent.KEYCODE_H:
                value = "H";
                break;
            case KeyEvent.KEYCODE_I:
                value = "I";
                break;
            case KeyEvent.KEYCODE_J:
                value = "J";
                break;
            case KeyEvent.KEYCODE_K:
                value = "K";
                break;
            case KeyEvent.KEYCODE_L:
                value = "L";
                break;
            case KeyEvent.KEYCODE_M:
                value = "M";
                break;
            case KeyEvent.KEYCODE_N:
                value = "N";
                break;
            case KeyEvent.KEYCODE_O:
                value = "O";
                break;
            case KeyEvent.KEYCODE_P:
                value = "P";
                break;
            case KeyEvent.KEYCODE_Q:
                value = "Q";
                break;
            case KeyEvent.KEYCODE_R:
                value = "R";
                break;
            case KeyEvent.KEYCODE_S:
                value = "S";
                break;
            case KeyEvent.KEYCODE_T:
                value = "T";
                break;
            case KeyEvent.KEYCODE_U:
                value = "U";
                break;
            case KeyEvent.KEYCODE_V:
                value = "V";
                break;
            case KeyEvent.KEYCODE_W:
                value = "W";
                break;
            case KeyEvent.KEYCODE_X:
                value = "X";
                break;
            case KeyEvent.KEYCODE_Y:
                value = "Y";
                break;
            case KeyEvent.KEYCODE_Z:
                value = "Z";
                break;

            default:
                value = "-1";
                break;
        }
        return value;
    }
    */
}

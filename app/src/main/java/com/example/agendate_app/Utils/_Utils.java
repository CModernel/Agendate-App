package com.example.agendate_app.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.agendate_app.Database.Agenda;
import com.example.agendate_app.Database.Empresas;
import com.example.agendate_app.Database.Rubro;
import com.example.agendate_app.Database.SolicitudEmpresa;
import com.example.agendate_app.Fragments.MenuPrincipalFragment;
import com.example.agendate_app.MainActivity;
import com.example.agendate_app.R;
import java.util.ArrayList;
import java.util.Calendar;

public class _Utils {

    static Context context;
    static Activity activity;
    static public String fragmentCurrentString;
    static public Fragment fragmentCurrent;
    static public Fragment fragmentLast;
    public static String CERRAR = "cerrar";
    public static String ATRAS = "atras";
    public static String FRAGMENT = "fragment";

    public static String _URL_AGENDATE = "http://agendate.pythonanywhere.com";
    public static String _PATH_STATIC = "/static";

    private static Rubro rubroSeleccionado;
    private static Empresas empresaSeleccionada;
    private static SolicitudEmpresa solicitudesEmpresa;

    // Usuario ADMIN, id=1
    public static String Username = "admin";
    public static String Password = "admin";
    public static int UsuId = 1;
    // Fecha Seleccionada
    public static String FechaSeleccionada = "2021-04-26";

    public static String getFechaSeleccionada() {
        return FechaSeleccionada;
    }

    public static void setFechaSeleccionada(String fechaSeleccionada) {
        FechaSeleccionada = fechaSeleccionada;
    }

    public static SolicitudEmpresa getSolicitudesEmpresa() {
        return solicitudesEmpresa;
    }

    public static void setSolicitudesEmpresa(SolicitudEmpresa solicitudesEmpresa) {
        _Utils.solicitudesEmpresa = solicitudesEmpresa;
    }

    public static Empresas getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public static void setEmpresaSeleccionada(Empresas empresaSeleccionada) {
        _Utils.empresaSeleccionada = empresaSeleccionada;
    }

    public static Rubro getRubroSeleccionado() {
        return rubroSeleccionado;
    }

    public static void setRubroSeleccionado(Rubro rubroSeleccionado) {
        _Utils.rubroSeleccionado = rubroSeleccionado;
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

        if (b != null)
            f.setArguments(b);

        // Toast para ver fragment actual
        //_Utils.toast(f.getClass().getSimpleName());

        fm.beginTransaction().replace(R.id.content_frame, f).setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).commitAllowingStateLoss();
    }

    public static void setAccionAtras(View view, @Nullable final Fragment fragment, @Nullable final Bundle bundle) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if (event.getAction() == KeyEvent.ACTION_UP) return false;
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    _Utils.fragment(fragment, bundle);
                    return true;
                }
                return false;
            }
        } );
    }

    public static String getFechaHoy(){
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes +1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return crearFechaString(dia, mes ,año);
    }

    public static String crearFechaString(int dia, int mes, int año){
        String fecha = año+"-"+agregarDigito(mes)+"-"+agregarDigito(dia);
        _Utils.FechaSeleccionada = fecha;
        return fecha;
    }

    public static String agregarDigito(int digit){
        if(digit<10)
            return "0" + Integer.toString(digit);

        return Integer.toString(digit);
    }

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

}

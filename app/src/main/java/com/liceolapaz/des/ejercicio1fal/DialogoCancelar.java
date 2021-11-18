package com.liceolapaz.des.ejercicio1fal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoCancelar extends DialogFragment {

    private NuevoUsuario nuevoUsuario;

    public DialogoCancelar(NuevoUsuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public NuevoUsuario getDatosUsers() {
        return nuevoUsuario;
    }

    public void setNuevoUsuarios(NuevoUsuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("Los datos no se guardarán. ¿Está seguro?")
                .setTitle("Cancelar")
                .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Aceptada.");
                        System.out.println(id);
                        DialogoCancelar.this.getDatosUsers().cancelar();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Denegada.");
                        System.out.println(id);
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}


package com.liceolapaz.des.ejercicio1fal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoEliminar  extends DialogFragment {

        private NuevoUsuario nuevoUsuario;

        public DialogoEliminar(NuevoUsuario nuevoUsuario) {
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

            builder.setMessage("Los datos se eliminaran de la base de datos.¿Está seguro?")
                    .setTitle("Eliminar")
                    .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("Dialogos", "Confirmacion Aceptada.");
                            System.out.println(id);
                            DialogoEliminar.this.getDatosUsers().eliminarUsuario();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("Dialogos", "Confirmacion Denegada.");
                            System.out.println(id);
                            dialog.cancel();
                        }
                    })
                    .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("Dialogos", "Confirmacion Cancelada.");
                            System.out.println(id);
                            dialog.cancel();
                        }
                    });
            return builder.create();
        }

    }



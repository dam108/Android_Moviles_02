package com.example.disfruta;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MessageDialog extends DialogFragment {

    public String titulo;
    public String msg;

    MessageDialog(String titulo, String msg){
        this.titulo = titulo;
        this.msg = msg;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(titulo).setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(msg)
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Código asociado al boton Aceptar
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        // Código asociado al botón Cancelar
                    }
                });

        return builder.create();
    }
}

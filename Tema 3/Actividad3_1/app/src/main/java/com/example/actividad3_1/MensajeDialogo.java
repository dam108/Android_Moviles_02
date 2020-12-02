package com.example.actividad3_1;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
//import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class MensajeDialogo extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("JADF08 No se pudo iniciar la sesión").setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("JADF08 El login o el password que has introducido son incorrectos. Compruébalos y vuelve a intentarlo de nuevo.")
                .setPositiveButton("ACEPTAR", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Código asociado al botón Aceptar. Por ejemplo:
                        //Toast.makeText(getActivity(), "PULSADA OPCION BOA", Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }
}
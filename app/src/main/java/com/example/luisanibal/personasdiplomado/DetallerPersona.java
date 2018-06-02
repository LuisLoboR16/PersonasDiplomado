package com.example.luisanibal.personasdiplomado;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallerPersona extends AppCompatActivity {
    private TextView lblCedula,lblNombre,lblApellido,lblSexo;
    private String[] sexo;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String ced,nomb,apell,id;
    private int fot,sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaller_persona);

        lblCedula = findViewById(R.id.lblCedulaDatos);
        lblNombre = findViewById(R.id.lblNombreDatos2);
        lblApellido = findViewById(R.id.lblApellidoDatos);
        lblSexo = findViewById(R.id.lblSexoDatos3);
        foto = findViewById(R.id.fotoPersona);
        collapsingToolbarLayout = findViewById(R.id.layout);
        sexo = getResources().getStringArray(R.array.sexo);

        i = getIntent();
        bundle = i.getBundleExtra("datos");

        ced = bundle.getString("cedula");
        nomb = bundle.getString("nombre");
        apell = bundle.getString("apellido");
        fot = bundle.getInt("foto");
        sex = bundle.getInt("sexo");
        id = bundle.getString("id");

        lblCedula.setText(ced);
        lblNombre.setText(nomb);
        lblApellido.setText(apell);
        lblSexo.setText(sexo[sex]);


        foto.setImageResource(fot);
        collapsingToolbarLayout.setTitle(nomb+ " " +apell);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.mensajeeliminar));
        positivo = getResources().getString(R.string.eliminarsi);
        negativo = getResources().getString(R.string.eliminarno);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Persona p = new Persona(id);
                p.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent (DetallerPersona.this, Principal.class);
        startActivity(i);
    }

    public void modificar(View v){
        Intent i = new Intent(DetallerPersona.this, Modificar.class);
        i.putExtra("datos",bundle);
        startActivity(i);
    }
}

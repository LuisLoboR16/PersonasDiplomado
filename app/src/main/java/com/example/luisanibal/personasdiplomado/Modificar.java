package com.example.luisanibal.personasdiplomado;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Modificar extends AppCompatActivity {
    private EditText txtCedula, txtNombre, txtApellido;
    private Spinner cmbSexo;
    private ArrayAdapter<String> adapter;
    private String opc[];
    private String ced,nomb,apell,id;
    private int sex;
    private ArrayList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtCedula = findViewById(R.id.txtCedulamodificar);
        txtNombre = findViewById(R.id.txtNombremodificar);
        txtApellido = findViewById(R.id.txtApellidomodificar);
        cmbSexo = findViewById(R.id.cmbSexomodificar);

        opc = this.getResources().getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        cmbSexo.setAdapter(adapter);

    }

    public void guardar(View v){
        String ced, nomb,apell,id;
        int sexo, foto;

        id=Datos.getId();
        foto = Metodos.fotoAleatoria(fotos);
        ced = txtCedula.getText().toString();
        nomb = txtNombre.getText().toString();
        apell = txtApellido.getText().toString();
        sexo = cmbSexo.getSelectedItemPosition();

        Persona p = new Persona(id,foto,ced,nomb,apell,sexo);
        p.guardar();

        Snackbar.make(v,getResources().getString(R.string.mensaje_guardado),Snackbar.LENGTH_SHORT)
                .setAction("Action",null).show();
        limpiar();

    }

    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cmbSexo.setSelection(0);
        txtCedula.requestFocus();
    }

    public void limpiar(View v){
        limpiar();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(Modificar.this,Principal.class);
        startActivity(i);
    }
}

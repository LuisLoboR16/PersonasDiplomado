package com.example.luisanibal.personasdiplomado;

import java.util.ArrayList;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Datos {
    //Nombre del elemento raiz en la bd
    private static String db = "Personas";
    public static ArrayList<Persona> personas = new ArrayList<>();

    // Objeto DatabeReference que permite la conexión con Firabase
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static void agregar(Persona p){
        //Busca el nodo con nombre Personas posteriormente busca el nodo
        //con ese id y le pasa el objeto p (si no existe lo crea).
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static String getId(){

        return databaseReference.push().getKey();
    }


    public static  ArrayList<Persona> obtener(){
        return personas;
    }

    public static void setPersonas(ArrayList<Persona> personas) {
        Datos.personas = personas;
    }

    public static void eliminarPersona(Persona p){
        databaseReference.child(db).child(p.getId()).removeValue();
    }

    public static void modificarPersona (Persona p){
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static boolean validar_existencia(ArrayList<Persona> personas,String cedula){
        for (int i = 0; i<personas.size();i++) {
            if (personas.get(i).getCedula().equals(cedula))
                return true;
        }
                return false;
    }

}

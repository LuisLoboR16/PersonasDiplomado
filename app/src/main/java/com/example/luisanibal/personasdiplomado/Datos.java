package com.example.luisanibal.personasdiplomado;

import java.util.ArrayList;

public class Datos {
    public static ArrayList<Persona> personas;

    public static void agregar(Persona p){
        personas.add(p);
    }

    public ArrayList<Persona> obtener(){
        return personas;
    }
}

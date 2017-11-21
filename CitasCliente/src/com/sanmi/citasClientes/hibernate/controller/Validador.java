package com.sanmi.citasClientes.hibernate.controller;

public class Validador {

    public boolean isNotaValid(String $cadena) {
        char[] cadena = $cadena.toLowerCase().toCharArray();

        for (int i = 0; i < cadena.length; i++) {

            if ("=+-:;{}[]|#$*".indexOf(cadena[i]) != -1)
                return false;

        }

        return true;
    }

    public boolean isNameValid(String $cadena) {
        char[] cadena = $cadena.toLowerCase().toCharArray();

        for (int i = 0; i < cadena.length; i++) {

            if ("qwertyuiopasdfghjklñzxcvbnmçàáäâèéëêíìïîòóöôùúüû-·' ".indexOf(cadena[i]) == -1) {
                return false;
            }

        }
        return true;
    }
}

package com.opencart.models;

public record DatosRegistro(
        String nombre,
        String apellido,
        String email,
        String telefono,
        String contrasena) {

    /**
     * El e-mail incluye la hora actual en milisegundos: la tienda no admite dos
     * cuentas con el mismo e-mail, y asi el caso se puede ejecutar muchas veces.
     */
    public static DatosRegistro deEjemplo() {
        return new DatosRegistro(
                "Federico",
                "Valverde",
                "federico.valverde." + System.currentTimeMillis() + "@example.com",
                "5981234567",
                "Prueba#2026");
    }
}

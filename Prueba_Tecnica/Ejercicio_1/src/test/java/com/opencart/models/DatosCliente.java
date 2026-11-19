package com.opencart.models;

public record DatosCliente(
        String nombre,
        String apellido,
        String email,
        String telefono,
        String direccion,
        String ciudad,
        String codigoPostal,
        String pais,
        String region) {

    public static DatosCliente deEjemplo() {
        return new DatosCliente(
                "Federico",
                "Valverde",
                "federico.valverde@example.com",
                "5981234567",
                "Calle Pruebas 123",
                "Maldonado",
                "20200",
                "Uruguay",
                "Maldonado");
    }
}

package com.opencart.models;

public record DatosGiftCertificate(
        String nombreDestinatario,
        String emailDestinatario,
        String nombreRemitente,
        String emailRemitente,
        String tema,
        String mensaje,
        String monto) {

    public static DatosGiftCertificate deEjemplo() {
        return new DatosGiftCertificate(
                "Lucia Fernandez",
                "lucia.fernandez@example.com",
                "Federico Valverde",
                "federico.valverde@example.com",
                "General",
                "Gift Certificate creado por la prueba automatizada",
                "25");
    }

    /** Importe tal como lo muestra el carrito, por ejemplo "$25.00". */
    public String montoEnCarrito() {
        return "$" + monto + ".00";
    }
}

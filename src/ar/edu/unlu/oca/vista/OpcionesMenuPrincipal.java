package ar.edu.unlu.oca.vista;

enum OpcionesMenuPrincipal {
    INICIO (0, "[*] Bienvenido al Juego de la Oca!\n"),
    ENTRAR_PARTIDA (1, "1) Entrar partida"),
    HISTORIAL_PARTIDAS (2, "2) Historial de partidas"),
    SALIR (3, "3) Salir");

    public final int opcion;
    public final String label;

    private OpcionesMenuPrincipal(int opcion, String label) {
        this.opcion = opcion;
        this.label = label;
    }
}

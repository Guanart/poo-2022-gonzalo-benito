package ar.edu.unlu.oca.vista;

enum OpcionesMenuNuevaPartida {
    INICIO (0, "[*] Menú de creación de nueva partida\n"),
    INICIAR (1, "1) Iniciar"),
    CARGAR_JUGADORES (2, "2) Cargar jugadores"),
    MOSTRAR_JUGADORES (3, "3) Mostrar jugadores"),
	SALIR (4, "4) Salir");

    public final int opcion;
    public final String label;

    private OpcionesMenuNuevaPartida(int opcion, String label) {
        this.opcion = opcion;
        this.label = label;
    }

}

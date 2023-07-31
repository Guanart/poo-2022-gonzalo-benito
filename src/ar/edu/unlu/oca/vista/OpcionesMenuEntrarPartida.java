package ar.edu.unlu.oca.vista;

enum OpcionesMenuEntrarPartida {
    INICIO (0, "[*] Menú de ingreso a partida\n"),
    INICIAR (1, "1) Iniciar"),
    CARGAR_NOMBRE (2, "2) Cargar nombre"),
    SELECCIONAR_FICHA (3, "3) Seleccionar ficha"),
	SALIR (4, "4) Salir");

    public final int opcion;
    public final String label;

    private OpcionesMenuEntrarPartida(int opcion, String label) {
        this.opcion = opcion;
        this.label = label;
    }

}

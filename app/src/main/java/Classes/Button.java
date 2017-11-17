package Classes;

/**
 * Created by smayor on 15/11/2017.
 */

public class Button {
    int order;
    String label;
    String funcion;
    String imagen;
    String dato;
    String over;
    String enabled;

    public Button(int order, String label, String funcion, String imagen, String dato, String over, String enabled) {
        this.order = order;
        this.label = label;
        this.funcion = funcion;
        this.imagen = imagen;
        this.dato = dato;
        this.over = over;
        this.enabled = enabled;
    }
}

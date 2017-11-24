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
    String categorias;

    public Button(int order, String label, String funcion, String imagen, String dato, String over, String enabled, String categorias) {
        this.order = order;
        this.label = label;
        this.funcion = funcion;
        this.imagen = imagen;
        this.dato = dato;
        this.over = over;
        this.enabled = enabled;
        this.categorias = categorias;
    }

    public int getOrder() {
        return order;
    }

    public String getLabel() {
        return label;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDato() {
        return dato;
    }

    public String getOver() {
        return over;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getCategorias() {return categorias; }
}

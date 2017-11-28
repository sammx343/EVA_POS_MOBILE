package Classes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by smayor on 17/11/2017.
 */

public class XMLButtonParser {

    public ArrayList getXMLfromResource(XmlPullParser parser, String keyboardId) throws IOException, XmlPullParserException {
        ArrayList<Button> buttonsParser = new ArrayList<>();
        int eventType = -1;
        boolean requiredKeyButton = false;
        while(eventType != XmlPullParser.END_DOCUMENT){
            if(eventType == XmlPullParser.START_TAG){
                String elementName = parser.getName();

                if(elementName.equals("teclado")){//Revisa que el tag inicial sea teclado
                    requiredKeyButton = (parser.getAttributeValue(null, "id").equals(keyboardId))? true : false;
                }

                if(elementName.equals("boton") && requiredKeyButton){//Empieza el ciclo de lectura de XML en el botón
                    System.out.println("Boton : ");
                    eventType = parser.next();//Pasa a la siguiente linea inmediatamente
                    elementName = parser.getName();//Cambia el atributo attr por el nombre del siguiente atributo
                    int order = 0;
                    String label = "";
                    String funcion = "";
                    String imagen = "";
                    String dato = "";
                    String over = "";
                    String enabled = "";
                    String categoria = "";
                    while(!elementName.equals("boton")){//Se queda en el ciclo mientras no vuelva a repetirse la etiquieta boton, que sería la de finalizacion
                        if(eventType == XmlPullParser.START_TAG){//Solo compara con aquellos elementos que contengan la etiqueta de inicio
                            switch (parser.getName()){
                                case "label":
                                    parser.next();
                                    //System.out.println(parser.getText());
                                    label = parser.getText();
                                    break;
                                case "accion":
                                    funcion = parser.getAttributeValue(null, "funcion");
                                    parser.next();
                                    break;
                                case "dato":
                                    parser.next();
                                    dato = parser.getText();
                                    break;
                                case "imagen":
                                    parser.next();
                                    imagen = parser.getText();
                                    break;
                                case "over":
                                    parser.next();
                                    over = parser.getText();
                                    break;
                                case "Enabled":
                                    parser.next();
                                    enabled = parser.getText();
                                    break;
                                case "Categoria":
                                    parser.next();
                                    categoria = parser.getText();
                                    break;
                            }
                        }
                        eventType = parser.next(); //Se termina con una etiqueta de cierre, nunca se compara, pero se sabe que es un TAG_END por la estructura
                        if(eventType != XmlPullParser.TEXT) //Se necesita comparar el nombre de la siguiente etiqueta, solo se guarda si no es texto, ya que marcaría error
                            elementName = parser.getName();
                    }
                    buttonsParser.add(new Button(order, label, funcion,imagen, dato, over, enabled, categoria ));
                }
            }
            eventType = parser.next();
        }
        return buttonsParser;
    }
}

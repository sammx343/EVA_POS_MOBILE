package com.example.smayor.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigurationScreen extends AppCompatActivity {
    EditText rows, columns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_screen);

        rows = (EditText) findViewById(R.id.rows);
        columns = (EditText) findViewById(R.id.columns);

        //Método para crear los SharedPreferences, o los métodos configuración por default
        setTextConfigurationDefault();
    }

    public void setTextConfigurationDefault(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Orientation", "");// SharedPreferences para la orientación del dispositivo
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        String rows, columns;
        if (preferences.getString("Rows", "").equals(""))// SharedPreferences para definir las filas en la botonera
            rows = "8";
        else
            rows = preferences.getString("Rows", "");

        if (preferences.getString("Columns", "").equals(""))// SharedPreferences para definir las columnas en la botonera
            columns = "8";
        else
            columns = preferences.getString("Columns", "");

        this.rows.setText(rows);
        this.columns.setText(columns);
    }

    public void changeScreenConfiguration(View view) {//Botón que cambia la configuración de orientación de pantalla
        String screenConfigurationType = view.getTag().toString();
        Toast.makeText(this, "Configuración de pantalla cambiada a " + screenConfigurationType, Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Orientation",screenConfigurationType);
        editor.apply();
    }

    public void changeButtonsPrincipalRowColumn(View view) {//Botón que cambia la configuración de filas y columnas
        String rows = this.rows.getText().toString();
        String columns = this.columns.getText().toString();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Rows", rows);
        editor.putString("Columns", columns);
        editor.apply();
        Toast.makeText(this, "Configuración de botones cambiada a " + rows + "x" + columns, Toast.LENGTH_SHORT).show();
    }
}

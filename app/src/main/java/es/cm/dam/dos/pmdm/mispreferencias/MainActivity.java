package es.cm.dam.dos.pmdm.mispreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edNombre;
    private EditText edEmail;
    private EditText edEmpresa;
    private EditText edEdad;
    private EditText edSueldo;
    private SharedPreferences misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edNombre=findViewById(R.id.edNombre);
        edEmail=findViewById(R.id.edEmail);
        edEmpresa=findViewById(R.id.edEmpresa);
        edEdad=findViewById(R.id.edEdad);
        edSueldo=findViewById(R.id.edSueldo);

        misPreferencias=getSharedPreferences("prefs_cli",MODE_PRIVATE);

        String nombre=misPreferencias.getString("nombre","");
        String empresa=misPreferencias.getString("empresa","Claudio Moyano");
        String email=misPreferencias.getString("email","cambiame@claudiomoyano.es");
        int edad=misPreferencias.getInt("edad",18);
        float sueldo=misPreferencias.getFloat("sueldo",15000);

        edNombre.setText(nombre);
        edEmpresa.setText(empresa);
        edEmail.setText(email);
        edSueldo.setText(String.valueOf(sueldo));
        edEdad.setText(String.valueOf(edad));

        Button btnGuardar=findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            SharedPreferences.Editor editor=misPreferencias.edit();
            editor.putString("nombre",edNombre.getText().toString());
            editor.putString("empresa",edEmpresa.getText().toString());
            editor.putString("email",edEmail.getText().toString());
            try {
                editor.putInt("edad", Integer.parseInt(edEdad.getText().toString()));
                editor.putFloat("sueldo", Float.parseFloat(edSueldo.getText().toString()));
            }catch (Exception e){
                //Error de conversión
            }
            if(!editor.commit())
                Toast.makeText(getApplicationContext(), "Error al grabar las preferencias", Toast.LENGTH_SHORT).show();

        });

        Button btnSiguiente=findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(i);
        });


        var preferences = PreferenceManager.getDefaultSharedPreferences(this).getAll();
        preferences.forEach((key, value) ->{
            Log.d("MIS PREFERENCIAS", String.format("%s -> %s", key, value));
        });

    }
}
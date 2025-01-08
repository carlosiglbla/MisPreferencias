package es.cm.dam.dos.pmdm.mispreferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences misPreferencias= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        misPreferencias.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            if ("feeback".equals(key) || "tipoEmpleado".equals(key)) {
                Toast.makeText(this, "Preferencia " + key + " actualizada: " + sharedPreferences.getString(key, ""), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Preferencia " + key + " actualizada: " + sharedPreferences.getBoolean(key, false), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package br.marcusmoreira.dgua;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class ClienteActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private TextView lblQuantidade;
    private Button btnQuantidadeMais;
    private Button btnQuantidadeMenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        lblQuantidade = findViewById(R.id.lblQuantidade);
        btnQuantidadeMais = findViewById(R.id.btnQuantidadeMais);
        btnQuantidadeMenos = findViewById(R.id.btnQuantidadeMenos);

        btnQuantidadeMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantidade = Integer.parseInt(lblQuantidade.getText().toString());

                if (quantidade < 10)
                    lblQuantidade.setText(String.valueOf(quantidade + 1));
            }
        });

        btnQuantidadeMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantidade = Integer.parseInt(lblQuantidade.getText().toString());
                if (quantidade > 1)
                    lblQuantidade.setText(String.valueOf(quantidade - 1));
            }
        });
    }

}

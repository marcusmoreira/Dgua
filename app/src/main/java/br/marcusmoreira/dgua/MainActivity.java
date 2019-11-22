package br.marcusmoreira.dgua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    private Button btnAcessar;
    private TextView lblMensagemAcesso;
    private TextView lblCadastro;
    private TextView lblCadastroOu;
    private int contador = 3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnAcessar = findViewById(R.id.btnAcessar);
        lblMensagemAcesso = findViewById(R.id.lblMensagemLogin);
        lblCadastro = findViewById(R.id.lblCadastro);
        lblCadastroOu = findViewById(R.id.lblCadastroOu);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnAcessar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validaLogin(edtUsuario.getText().toString(), edtSenha.getText().toString());
            }

        });

        lblCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cadastroUsuario();
            }
        });

        lblCadastroOu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cadastroUsuario();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser userAuth) {
        if (userAuth != null){
            //
        }

    }

    private void validaLogin(String usuario, String senha){
        if (usuario.equals("Dgua") && senha.equals("1")){
            Intent intent = new Intent(MainActivity.this, DistribuidorActivity.class);
            startActivity(intent);
        } else if (usuario.equals("Dgua") && senha.equals("2")) {
            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
            startActivity(intent);
        } else if (usuario.equals("x") && senha.equals("y")) {
            //
        } else {
            contador--;

            lblMensagemAcesso.setText("Número de tentativas: " + String.valueOf(contador));
            lblMensagemAcesso.setVisibility(View.VISIBLE);

            if (contador == 0){
                lblMensagemAcesso.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void cadastroUsuario(){
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}

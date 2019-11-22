package br.marcusmoreira.dgua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private Switch swtDistribuidor;
    private TextView lblTipoCadastro;
    private TextView lblMensagemDistribuidor;
    private Button btnCadastrar;

    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtDistribuidorCNPJ;
    private EditText edtDistribuidorEndereco;
    private EditText edtDistribuidorResponsavel;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        swtDistribuidor = findViewById(R.id.swtDistribuidor);
        lblTipoCadastro = findViewById(R.id.lblTipoCadastro);
        lblMensagemDistribuidor = findViewById(R.id.lblMensagemDistribuidor);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        edtNome = findViewById(R.id.edtNome);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEmail = findViewById(R.id.edtEmail);

        edtDistribuidorCNPJ = findViewById(R.id.edtDistribuidorCNPJ);
        edtDistribuidorEndereco = findViewById(R.id.edtDistribuidorEndereco);
        edtDistribuidorResponsavel = findViewById(R.id.edtDistribuidorReponsavel);

        swtDistribuidor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    lblTipoCadastro.setText("distribuidor");

                    edtNome.setHint("Razão Social");

                    lblMensagemDistribuidor.setVisibility(View.VISIBLE);

                    btnCadastrar.setVisibility(View.INVISIBLE);
                    edtDistribuidorCNPJ.setVisibility(View.VISIBLE);
                    edtDistribuidorEndereco.setVisibility(View.VISIBLE);
                    edtDistribuidorResponsavel.setVisibility(View.VISIBLE);

                    //9

                    btnCadastrar.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(btnCadastrar, "translationY", 550f);
                    animation.setDuration(1000);
                    animation.start();
                } else {
                    lblTipoCadastro.setText("cliente");

                    edtNome.setHint("Nome");

                    edtDistribuidorResponsavel.setVisibility(View.INVISIBLE);
                    edtDistribuidorEndereco.setVisibility(View.INVISIBLE);
                    edtDistribuidorCNPJ.setVisibility(View.INVISIBLE);

                    lblMensagemDistribuidor.setVisibility(View.INVISIBLE);

                    ObjectAnimator animation = ObjectAnimator.ofFloat(btnCadastrar, "translationY", -10f);
                    animation.setDuration(1000);
                    animation.start();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new user with a first, middle, and last name
                Map<String, Object> user = new HashMap<>();
                user.put("email", edtEmail);
                user.put("nome", edtNome);
                user.put("telefone", edtTelefone);
                user.put("tipo", 0);

                // Add a new document with a generated ID
                db.collection("usuarios")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });
    }
}

package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ADMPrincipalActivity extends AppCompatActivity {

    Button btnLogout, btnADMUsuarios, btnADMTurma, btnADMAlunos, btnADMProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admprincipal);

        btnLogout = (Button) findViewById(R.id.btnlogout);
        btnADMUsuarios = (Button) findViewById(R.id.btnADMUsuarios);
        btnADMProfessores = (Button) findViewById(R.id.btnProfADM);
        btnADMAlunos = (Button) findViewById(R.id.btnAlunosADM);
        btnADMTurma = (Button) findViewById(R.id.btnTurmaADM);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ADMPrincipalActivity.this);
                builder.setMessage("Deseja realmente sair do sistema?");
                builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                builder.show();
            }
        });

        btnADMUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaUsuarios = new Intent(ADMPrincipalActivity.this, UsuariosActivity.class);
                startActivity(telaUsuarios);
            }
        });

        btnADMTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaTurma = new Intent(ADMPrincipalActivity.this, TurmaActivity.class);
                startActivity(telaTurma);
            }
        });

        btnADMAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaAlunos = new Intent(ADMPrincipalActivity.this, AlunosActivity.class);
                startActivity(telaAlunos);
            }
        });

        btnADMProfessores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaProfessores = new Intent(ADMPrincipalActivity.this, ProfessorActivity.class);
                startActivity(telaProfessores);
            }
        });


//        imageButtonUsuarios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent telaUsuarios = new Intent(ADMPrincipalActivity.this, UsuariosActivity.class);
//                startActivity(telaUsuarios);
//            }
//        });
    }
}

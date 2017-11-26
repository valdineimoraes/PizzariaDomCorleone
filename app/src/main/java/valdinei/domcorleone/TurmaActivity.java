package valdinei.domcorleone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.DAO.ProfessorDAO;
import Model.DAO.TurmaDAO;
import Model.DAO.UsuarioDAO;
import Model.Professor;
import Model.Turma;
import Model.Usuario;

public class TurmaActivity extends AppCompatActivity {

    TextView nomeTurma;
    EditText salaNumero, totalAlunos;
    Spinner spinnerProfessor;
    Button btnSalvarTurma, btnEditarTurma, btnVoltarTurma, btnExcluirTurma;
    ListView lsViewTurma;
    ArrayList<Turma> turmaArrayList;
    ArrayList<Professor> optionsProfessores;
    ArrayList<Professor> arrayProf;

    Turma turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);

        nomeTurma = (TextView) findViewById(R.id.txtNomeTurma);
        salaNumero = (EditText) findViewById(R.id.edtSalaNumeroTurma);
        totalAlunos = (EditText) findViewById(R.id.edtTotalAlunosSala);

        spinnerProfessor = (Spinner) findViewById(R.id.spinnerProfessores);

        btnSalvarTurma = (Button) findViewById(R.id.btnSalvarTurma);
        btnEditarTurma = (Button) findViewById(R.id.btnEditarTurma);
        btnVoltarTurma = (Button) findViewById(R.id.btnVoltarTurma);
        btnExcluirTurma = (Button) findViewById(R.id.btnExcluirTurma);
        lsViewTurma = (ListView) findViewById(R.id.listViewTurma);

        btnVoltarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turma = new Turma();

                turma.setNome(nomeTurma.getText().toString());
                turma.setTotalAlunos(Integer.parseInt(totalAlunos.getText().toString()));
                turma.setSala(Integer.parseInt(salaNumero.getText().toString()));
                // turma.setProfessor(Integer.parseInt(spinnerProfessor.getSelectedItem()));

                TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                if (nomeTurma.length() == 0 || salaNumero.length() == 0 || totalAlunos.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao cadastrar turma");
                    builder.setMessage("O campo nome ou numero da sala ou total de alunos nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(TurmaActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if (turmaDAO.insertTurma(turma)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Cadastro da Turma");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    limparCampos();
                    turma = null;

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao cadastrar turma");
                    builder.setMessage("Nao foi possivel cadastrar turma");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(TurmaActivity.this, "Cadastro Negado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (turma==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setMessage("Nenhuma turma selecionada.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else {

                    if (nomeTurma.length() == 0 || salaNumero.length() == 0 || totalAlunos.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                        builder.setTitle("Erro ao atualizar turma");
                        builder.setMessage("O campo nome ou numero da sala ou total de alunos nao podem estar vazio");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Toast.makeText(TurmaActivity.this, "Atualização Negada", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }else {
                        TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                        turma.setNome(nomeTurma.getText().toString());
                        turma.setTotalAlunos(Integer.parseInt(totalAlunos.getText().toString()));
                        turma.setSala(Integer.parseInt(salaNumero.getText().toString()));
                        //turma.setProfessor(spinnerProfessor.getSelectedItem().toString());

                        turmaDAO.updateTurma(turma);

                        AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                        builder.setTitle("Cadastro da Turma");
                        builder.setMessage("Atualização realizada com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();

                    }
                }

            }
        });

        btnExcluirTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                if (turma == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao excluir turma");
                    builder.setMessage("Nenhuma turma selecionada para excluir");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(TurmaActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else{
                    turmaDAO.deleteTurma(turma);
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Excluir Turma");
                    builder.setMessage("Exclusao realizada com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    limparCampos();
                    turma = null;

                }
            }
        });

        //============================================ spinner Professor populado

        ProfessorDAO professorDAO = new ProfessorDAO(this);
        optionsProfessores = professorDAO.getProfessoresSpinner();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_professores, optionsProfessores);

        spinnerProfessor.setAdapter(adapter);
//
//        ListAdapterProfessor listAdapterProfessor = new ListAdapterProfessor(this,optionsProfessores);
//        spinnerProfessor.setAdapter(listAdapterProfessor);



        //============================================

        TurmaDAO turmaDAO = new TurmaDAO(this);
        turmaArrayList = turmaDAO.getTurmas();

        lsViewTurma.setAdapter(
                new ListAdapterTurma(this, turmaArrayList)
        );

        lsViewTurma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void populaCampos(int i){
        turma = turmaArrayList.get(i);

        nomeTurma.setText(turma.getNome());
        salaNumero.setText(turma.getSala());
        totalAlunos.setText(turma.getTotalAlunos());
        spinnerProfessor.setSelection(indiceProfessor(turma));
    }

    public void limparCampos(){

        nomeTurma.setText("");
        salaNumero.setText("");
        totalAlunos.setText("");
        spinnerProfessor.setSelection(0);
    }
//
    public int indiceProfessor(Turma turma){
        int idProfessor = turma.getProfessor();

        for (int i = 0; i < optionsProfessores.size(); i++) {
            if (idProfessor == optionsProfessores.get(i).getId()){
                return i;
            }
        }
        return 0;
    }

}

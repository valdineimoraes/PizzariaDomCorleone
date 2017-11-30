package Model.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Aluno;
import Model.Usuario;
import bd.CriaBanco;

public class AlunoDAO {

    private static final String NOME_TABELA = "Alunos";
    private Context context;


    public AlunoDAO(Context context) {
        this.context=context;
    }

    public boolean insertAluno(Aluno aluno){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("nome",aluno.getNome());
            values.put("telefone", aluno.getTelefone());
            values.put("responsavel", aluno.getResponsavel());
            values.put("endereco", aluno.getEndereco());
            values.put("turma", aluno.getTurma());
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateAluno(Aluno aluno){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+aluno.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",aluno.getNome());
            values.put("telefone", aluno.getTelefone());
            values.put("responsavel", aluno.getResponsavel());
            values.put("endereco", aluno.getEndereco());
            values.put("turma", aluno.getTurma());
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public ArrayList<Aluno> getAlunos(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Aluno> alunoArray = new ArrayList<>();
        String getAluno = "SELECT * FROM "+ NOME_TABELA + " ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getAluno, null);

            if (cursor.moveToFirst()){

                do {
                    Aluno u = new Aluno();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setTelefone(cursor.getString(2));
                    u.setResponsavel(cursor.getString(3));
                    u.setEndereco(cursor.getString(4));
                    u.setTurma(cursor.getInt(5));
                    alunoArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        Log.v("Qtde", String.valueOf(alunoArray.size()));
        return alunoArray;
    }


    public boolean deleteAluno(Aluno aluno){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteAluno = " _id = '"+aluno.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteAluno, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
}

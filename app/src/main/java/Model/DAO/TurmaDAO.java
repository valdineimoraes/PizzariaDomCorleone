package Model.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Model.Turma;
import bd.CriaBanco;

public class TurmaDAO {

    private static final String NOME_TABELA = "Turmas";
    private Context context;


    public TurmaDAO(Context context) {
        this.context=context;
    }

    public boolean insertTurma(Turma turma){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("nome",turma.getNome());
            values.put("total_alunos", turma.getTotalAlunos());
            values.put("sala", turma.getSala());
            values.put("professor", String.valueOf(turma.getProfessor()));
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateTurma(Turma turma){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+turma.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",turma.getNome());
            values.put("total_alunos", turma.getTotalAlunos());
            values.put("sala", turma.getSala());
            values.put("professor", String.valueOf(turma.getProfessor()));
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public ArrayList<Turma> getTurmas(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Turma> turmaArray = new ArrayList<>();
        String getTurma = "SELECT t.*, p.nome as professor_nome FROM "+ NOME_TABELA + " t join Professores p ON p._id = t.professor ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getTurma, null);

            if (cursor.moveToFirst()){

                do {
                    Turma u = new Turma();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setTotalAlunos(cursor.getInt(2));
                    u.setSala(cursor.getInt(3));
                    u.setProfessor(cursor.getInt(4));
                    u.setProfessorNome(cursor.getString(5));
                    turmaArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return turmaArray;
    }


    public boolean deleteTurma(Turma turma){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteTurma = "_id = '"+turma.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteTurma, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
}

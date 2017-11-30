package Model.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.Professor;
import Model.Turma;
import bd.CriaBanco;

public class ProfessorDAO {

    private static final String NOME_TABELA = "Professores";
    private Context context;


    public ProfessorDAO(Context context) {
        this.context=context;
    }

    public boolean insertProfessor(Professor professor){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("nome",professor.getNome());
            values.put("telefone", professor.getTelefone());
            values.put("formacao", professor.getFormacao());
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateProfessor(Professor professor){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+professor.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",professor.getNome());
            values.put("telefone", professor.getTelefone());
            values.put("formacao", professor.getFormacao());
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }


    public ArrayList<Professor> getProfessores(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Professor> profArray = new ArrayList<>();
        String getProf = "SELECT * FROM " + NOME_TABELA + " ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getProf, null);

            if (cursor.moveToFirst()){

                do {
                    Professor u = new Professor();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setTelefone(cursor.getString(2));
                    u.setFormacao(cursor.getString(3));
                    profArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        Log.v("pregQtde", String.valueOf(profArray.size()));
        return profArray;
    }

    public List<Professor> getProfessoSpinner(){

        List<Professor> listProf = new ArrayList<Professor>();

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+ NOME_TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                listProf.add(new Professor(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.v("profSpi", "tamanho"+listProf.size());
        return listProf;

    }

//    public ArrayList<Professor> getProfessoresSpinner(){
//
//        CriaBanco criaBanco = new CriaBanco(context);
//        SQLiteDatabase db = criaBanco.getReadableDatabase();
//
//        ArrayList<Professor> profArraySpinner = new ArrayList<>();
//        String getProf = "SELECT nome FROM " + NOME_TABELA + " ORDER by nome ASC";
//
//        try {
//            Cursor cursor = db.rawQuery(getProf, null);
//
//            if (cursor.moveToFirst()){
//                do {
//                    Professor prof = new Professor();
//                    prof.setNome(cursor.getString(0));
//                    profArraySpinner.add(prof);
//
//                }while (cursor.moveToNext());
//
//            }
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return null;
//        }finally {
//
//            db.close();
//        }
//        Log.v("profSpQtde", String.valueOf(profArraySpinner.size()));
//        return profArraySpinner;
//    }



    public boolean deleteProfessor(Professor professor){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteProfessor = "_id = '"+professor.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteProfessor, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
}

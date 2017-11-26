package Model.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Usuario;
import bd.CriaBanco;
import bd.ScriptSQL;

public class UsuarioDAO {

    private static final String NOME_TABELA = "Usuarios";
    private Context context;


    public UsuarioDAO(Context context) {
        this.context=context;
    }

    public boolean insertUsuario(Usuario usuario){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("nome",usuario.getNome());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("cargo", usuario.getCargo());
            values.put("telefone", usuario.getTelefone());
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateUsuario(Usuario usuario){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+usuario.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",usuario.getNome());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("cargo", usuario.getCargo());
            values.put("telefone", usuario.getTelefone());
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean checkLogin(String usuario, String senha) {

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        boolean check = false;

        String buscarLogin = "SELECT * FROM "+NOME_TABELA+" where usuario = ? and senha = ? ";
        Cursor cursor = db.rawQuery(buscarLogin, new String[] {usuario, senha});

        if(cursor.moveToFirst())
            check = true;

        db.close();

        return check;
    }

    public ArrayList<Usuario> getUsuarios(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Usuario> userArray = new ArrayList<>();
        String getUser = "SELECT * FROM "+ NOME_TABELA+" ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getUser, null);

            if (cursor.moveToFirst()){

                do {
                    Usuario u = new Usuario();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setUsuario(cursor.getString(2));
                    u.setSenha(cursor.getString(3));
                    u.setCargo(cursor.getString(4));
                    u.setTelefone(cursor.getString(5));
                    userArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        //Log.v("Qtde", String.valueOf(userArray.size()));
        return userArray;
    }


    public boolean deleteUsuario(Usuario usuario){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteUsuario = "_id = '"+usuario.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteUsuario, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
}

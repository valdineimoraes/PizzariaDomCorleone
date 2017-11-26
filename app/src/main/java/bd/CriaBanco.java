package bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.Usuario;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "system_schools";
    private static final int VERSAO = 2;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateUsuarios());
        db.execSQL(ScriptSQL.getCreateAlunos());
        db.execSQL(ScriptSQL.getCreateProfessores());
        db.execSQL(ScriptSQL.getCreateTurmas());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

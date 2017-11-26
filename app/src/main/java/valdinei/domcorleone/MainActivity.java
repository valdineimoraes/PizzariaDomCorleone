package valdinei.domcorleone;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import Model.DAO.UsuarioDAO;
import Model.Usuario;
import bd.CriaBanco;
import android.database.sqlite.*;
import android.database.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button bntLogarSistema;
    AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntLogarSistema = (Button) findViewById(R.id.bntLogarSistema);

        bntLogarSistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(abreLogin);
            }
        });


//        ImageView imageView = (ImageView) findViewById(R.id.animacao);
//        imageView.setBackgroundResource(R.drawable.animacao);
//
//        AnimationDrawable animation = (AnimationDrawable) imageView.getBackground();
//        animation.start();


        //==================================================================
        //mostra os usuarios cadastrados no banco em Log
//        UsuarioDAO dao = new UsuarioDAO(MainActivity.this);
//        ArrayList<Usuario> arrayList = dao.getUsuarios();//
//        for (Usuario usuario:arrayList){
//            String valor = "Usuario: "+usuario.getUsuario()+" senha:"+usuario.getSenha();
//            Log.v("Usuarios", valor);
//        }
        //==================================================================



    }
}

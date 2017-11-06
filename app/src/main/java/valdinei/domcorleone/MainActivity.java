package valdinei.domcorleone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button bntLogarSistema;

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
    }
}

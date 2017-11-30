package valdinei.domcorleone;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by valdinei on 29/11/17.
 */

public class ServiceSample extends IntentService {

    private boolean running = true;

    public ServiceSample() {
        super("Service Sample");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this){
            for (int i = 10; i < 100; i++) {
                Log.d("pdmLog", "uploading: "+i+"%");
                try {
                    wait(600);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (!running) break;
            }
        }
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }
}

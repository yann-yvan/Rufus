package corp.ny.com.tuttifrutti;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class HomeActivityActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        findViewById(R.id.btn_launch).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_launch:
                User.getInstance().save();
                System.out.println(new User().find(1).toJson());
                for (int i = 0; i < 10; i++) {
                    Message.getInstance(i).save();
                }
                break;
        }
    }
}

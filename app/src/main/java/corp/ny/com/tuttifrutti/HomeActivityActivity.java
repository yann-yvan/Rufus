package corp.ny.com.tuttifrutti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
                    Message message = new Message()
                            .where("receiverId", User.getInstance().getId())
                            .where("id", i)
                            .first();
                    if (message != null)
                        System.out.println(message.toJson());
                }

                Message message = new Message()
                        .where("receiverId", User.getInstance().getId())
                        .where("id", 5)
                        .first();
                if (message != null) {
                    System.out.printf("Before  ==> %s", message.toJson());
                    message.setOpen(true);
                    message = message.save();
                    System.out.printf("After  ==> %s", message.toJson());
                }

                break;
        }
    }
}

package corp.ny.com.tuttifrutti;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import corp.ny.com.rufus.database.exceptions.TableException;
import corp.ny.com.rufus.system.RufusApp;

import static corp.ny.com.rufus.system.RufusApp.getDataBaseInstance;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        buildDB();
    }

    public void buildDB() {
       // Log.e("Db version",String.format("%s", RufusApp.getDataBaseInstance().getVersion()));
        RufusApp.setTableBuilder(new RufusApp.TableBuilder() {
            @Override
            public void build(SQLiteDatabase db) {
                try {

                    db.execSQL(new User().genTable());
                } catch (TableException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade() {
                try {
                    RufusApp.addOnUpgradeSchema(6,
                            new Message().getSchema(),
                            new Conversation().getSchema()
                    );
                } catch (TableException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void downUpgrade() {

            }
        });
        RufusApp.getTableBuilder().build(getDataBaseInstance());
    }

    @Override
    public void onTerminate() {
        getDataBaseInstance().close();
        super.onTerminate();
    }
}

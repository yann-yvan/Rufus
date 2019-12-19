package corp.ny.com.rufus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import corp.ny.com.rufus.system.RufusApp;
import corp.ny.com.rufus.utils.ManifestReader;


/**
 * Created by yann-yvan on 04/12/17.
 */

public class Migrations extends SQLiteOpenHelper {
    private SparseArray<Schema> schemas = new SparseArray<>();

    public Migrations(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        RufusApp.getTableBuilder().build(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(ManifestReader.getMetadataBoolean("FOREIGN_KEY_CONSTRAINTS_ENABLED"));
        }
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < schemas.size() ; i++) {
            int currentVersion = schemas.keyAt(0);
            if (currentVersion>oldVersion)
                db.execSQL(schemas.get(currentVersion).toString());
        }
    }

    public void add(Schema schema,int version){
        schemas.put(version,schema);
    }

}

package corp.ny.com.rufus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import corp.ny.com.rufus.system.RufusApp;
import corp.ny.com.rufus.utils.ManifestReader;


/**
 * Created by yann-yvan on 04/12/17.
 */

public class Migrations extends SQLiteOpenHelper {
    private SparseArray<ArrayList<Schema>> schemas = new SparseArray<>();

    public Migrations(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (RufusApp.getTableBuilder() != null) {
            RufusApp.getTableBuilder().build(db);
            RufusApp.getTableBuilder().onUpgrade();
        }
        for (int i = 0; i < schemas.size() ; i++) {
                for (Schema s : schemas.get(schemas.keyAt(0))) {
                    db.execSQL(s.toString());
                }
        }
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
        if (RufusApp.getTableBuilder() != null)
            RufusApp.getTableBuilder().onUpgrade();
        for (int i = 0; i < schemas.size() ; i++) {
            int currentVersion = schemas.keyAt(0);
            if (currentVersion>oldVersion)
                for (Schema s : schemas.get(currentVersion)) {
                    db.execSQL(s.toString());
                }
        }
    }

    public void add(ArrayList<Schema> schema, int version) {
        schemas.put(version,schema);
    }

}

package sv.com.udb.prueba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import sv.com.udb.prueba.R;

class SQLOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pruebapractica";
    private static final int DB_VERSION = 1;
    private String queries;

    public SQLOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
       final InputStream inputStream = context.getResources().openRawResource(R.raw.db);
        try{
            this.queries = IOUtils.toString(inputStream);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to read file");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String query: queries.split(";")){
            try {
                db.execSQL(query);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed to execute query: "+query);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Useless right now
    }
}

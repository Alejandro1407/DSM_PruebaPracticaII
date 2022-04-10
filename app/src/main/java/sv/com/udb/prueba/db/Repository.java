package sv.com.udb.prueba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Id;
import sv.com.udb.prueba.exceptions.InvalidClassException;

public abstract class Repository<T,K> {

    private static volatile SQLiteDatabase database;
    private Context context;

    public Repository(Context c){
        this.context = c;
    }

    private SQLiteDatabase getInstance(){
        if (database == null) {
            database = new SQLOpenHelper(context).getWritableDatabase();
        }
        return database;
    }

     public final T getOne(K key){
        return null;
     }

     public final void insert(T payload) throws Exception{
        String tableName = payload.getClass().getAnnotation(Entity.class).name();
        Map<String,Object> hashMap = process(payload);
        String keys = "";
        String values = "";
        for (Map.Entry<String,Object> entry : hashMap.entrySet()){
            Object val = entry.getValue();
            keys += entry.getKey() + ",";
            values += (val.getClass() == Integer.class ? val : "\"" + val + "\"") + ",";
        }
        String query = String.format("INSERT INTO %s (%s) VALUES (%s)",tableName,keys.substring(0,keys.length()-1),values.substring(0,values.length()-1));
        getInstance().execSQL(query);

     }

     public final void update(T payload) throws Exception{
         String tableName = payload.getClass().getAnnotation(Entity.class).name();
         Map<String,Object> hashMap = process(payload);
         String values = "";
         for (Map.Entry<String,Object> entry : hashMap.entrySet()){
             Object val = entry.getValue();
             values += entry.getKey() + " = " + (val.getClass() == Integer.class ? val : "\"" + val + "\"") + ", ";
         }
         Object id = null;
         for(Field f : payload.getClass().getDeclaredFields()){
             if(f.isAnnotationPresent(Id.class)){
                 f.setAccessible(true);
               id = f.get(payload);
             }
         }
         String query = String.format("UPDATE %s SET %s WHERE id = %s",tableName,values.substring(0,values.length()-1),id);
         getInstance().execSQL(query);
     }

     public final void delete(T payload) throws Exception{
         String tableName = payload.getClass().getAnnotation(Entity.class).name();
         Object id = null;
         for(Field f : payload.getClass().getDeclaredFields()){
             if(f.isAnnotationPresent(Id.class)){
                 f.setAccessible(true);
                 id = f.get(payload);
             }
         }
         String query = String.format("DELETE FROM %s WHERE id = %s",tableName,id);
         getInstance().execSQL(query);
     }

     private Map<String, Object> process(T payload) throws InvalidClassException, IllegalAccessException {
        if(!payload.getClass().isAnnotationPresent(Entity.class)){
            throw new InvalidClassException();
        }
        Field[] declaredFields = payload.getClass().getDeclaredFields();
        if(declaredFields.length == 0) {
            throw new InvalidClassException();
        }
        List<Field> fields = new ArrayList<>(declaredFields.length);
        int idCount = 0;
        for(Field f : payload.getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(Id.class)){
                idCount++;
            }
            if (f.isAnnotationPresent(Column.class)){
                fields.add(f);
            }
        }
        if(idCount != 1 ){
            throw new InvalidClassException();
        }
         Map<String,Object> hashMap = new HashMap<>();
         for (Field f : fields){
             String fieldName = f.getAnnotation(Column.class).name();
             if(null == fieldName || "".equals(fieldName)){
                 fieldName = f.getName();
             }
             f.setAccessible(true);
             hashMap.put(fieldName,f.get(payload));
         }
         return hashMap;
     }
}

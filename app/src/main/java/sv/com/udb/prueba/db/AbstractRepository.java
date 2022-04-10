package sv.com.udb.prueba.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Id;
import sv.com.udb.prueba.data.model.Usuario;
import sv.com.udb.prueba.exceptions.EntityNotFoundException;
import sv.com.udb.prueba.exceptions.InvalidClassException;
import sv.com.udb.prueba.exceptions.UnsupportedTypeException;

public abstract class AbstractRepository<T,K> {

    private static volatile SQLiteDatabase database;
    private Context context;
    private Class<T> genericType;

    public AbstractRepository(Context c){
        this.context = c;
        this.genericType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private SQLiteDatabase getInstance(){
        if (database == null) {
            database = new SQLOpenHelper(context).getWritableDatabase();
        }
        return database;
    }

    public final List<T> findAll() throws IllegalAccessException, InstantiationException {
        String tableName = genericType.getAnnotation(Entity.class).name();
        Field[] fields = genericType.getDeclaredFields();
        String query = String.format("SELECT %s FROM %s",getFieldNames(fields),tableName);
        Cursor c = getInstance().rawQuery(query,null);
        List<T> genericInstances = new ArrayList<>();
        T genericInstance = genericType.newInstance();
        if(c.moveToFirst()){
            do{
                for(Field f : fields ){
                    getValue(f,c,genericInstance);
                }
                genericInstances.add(genericInstance);
            }while (c.moveToNext());
        }else {
            throw new EntityNotFoundException(tableName);
        }
        return genericInstances;
    }

     public final T getOne(K key) throws IllegalAccessException, InstantiationException {
        String tableName = genericType.getAnnotation(Entity.class).name();
        Field[] fields = genericType.getDeclaredFields();
        String query = String.format("SELECT %s FROM %s WHERE id = %s",getFieldNames(fields),tableName,key);
        Cursor c = getInstance().rawQuery(query,null);
        T genericInstance = genericType.newInstance();
        if(c.moveToFirst()){
            for(Field f : fields ){
                getValue(f,c,genericInstance);
            }
        }else {
            throw new EntityNotFoundException(tableName);
        }
        return genericInstance;
     }

     @SuppressLint("Range")
     private void getValue(Field field, Cursor cursor, T instance) throws IllegalAccessException {
        String fieldName =  getFieldName(field);
        Class<?> fieldType = field.getType();
        field.setAccessible(true);
        if (fieldType.equals(String.class)){
            field.set(instance,cursor.getString(cursor.getColumnIndex(fieldName)));
        }else if (fieldType.equals(Integer.class)){
             field.set(instance,cursor.getInt(cursor.getColumnIndex(fieldName)));
        }else if(fieldType.equals(Float.class)){
            field.set(instance,cursor.getFloat(cursor.getColumnIndex(fieldName)));
        }else{
            throw new UnsupportedTypeException(fieldType);
        }
     }

     private String getFieldName(Field field){
         String fieldName;
        if(field.isAnnotationPresent(Column.class)) {
            fieldName = field.getAnnotation(Column.class).name();
         }else if(field.isAnnotationPresent(Id.class)){
            fieldName = "id";
         }else {
            throw new RuntimeException("This shouldn't happen");
        }
         if(null == fieldName || "".equals(fieldName)){
             fieldName = field.getName();
         }
         return  fieldName;
     }

     private String getFieldNames(Field[] fields){
        if(fields.length == 0){
            throw new InvalidClassException();
        }
         String keys = "id,";
         for (Field f : fields){
             if(f.isAnnotationPresent(Column.class)){
                 String fieldName = getFieldName(f);
                 keys += fieldName + ",";
             }
         }
         return keys.substring(0,keys.length() -1);
     }

     public final void insert(T payload) throws Exception{
        String tableName = payload.getClass().getAnnotation(Entity.class).name();
        Map<String,Object> hashMap = process(payload);
        String keys = "";
        String values = "";
        for (Map.Entry<String,Object> entry : hashMap.entrySet()){
            Object val = entry.getValue();
            keys += entry.getKey() + ",";
            values += ((val.getClass() == Integer.class  || val.getClass() == Float.class)
                    ? val : "\"" + val + "\"") + ",";
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

     public final void delete(K payload) throws Exception{
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

     public final void deleteById(K key) {
         String tableName = genericType.getAnnotation(Entity.class).name();
         String query = String.format("DELETE FROM %s WHERE id = %s",tableName,key);
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
             String fieldName = getFieldName(f);
             f.setAccessible(true);
             hashMap.put(fieldName,f.get(payload));
         }
         return hashMap;
     }
}

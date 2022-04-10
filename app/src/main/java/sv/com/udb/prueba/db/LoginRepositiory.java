package sv.com.udb.prueba.db;

import android.content.Context;

import sv.com.udb.prueba.data.model.Usuario;

public class LoginRepositiory extends Repository<Usuario,Integer>{
    public LoginRepositiory(Context context){
        super(context);
    }
}

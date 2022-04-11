package sv.com.udb.prueba.repositories;

import android.content.Context;

import sv.com.udb.prueba.model.Usuario;

public final class LoginRepositiory extends AbstractRepository<Usuario,Integer> {
    public LoginRepositiory(Context context){
        super(context);
    }
}

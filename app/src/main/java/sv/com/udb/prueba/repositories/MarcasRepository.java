package sv.com.udb.prueba.repositories;

import android.content.Context;

import sv.com.udb.prueba.model.Marcas;

public final class MarcasRepository extends AbstractRepository<Marcas,Integer> {
    public MarcasRepository(Context context) {
        super(context);
    }
}

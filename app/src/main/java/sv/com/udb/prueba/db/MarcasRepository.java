package sv.com.udb.prueba.db;

import android.content.Context;

import sv.com.udb.prueba.data.model.Marcas;

public class MarcasRepository extends AbstractRepository<Marcas,Integer> {
    public MarcasRepository(Context c) {
        super(c);
    }
}

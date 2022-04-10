package sv.com.udb.prueba.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String tableName){
        super("Entity could not be found in "+ tableName);
    }
}

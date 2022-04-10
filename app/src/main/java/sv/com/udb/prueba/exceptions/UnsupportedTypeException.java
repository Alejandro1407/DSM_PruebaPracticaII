package sv.com.udb.prueba.exceptions;

public class UnsupportedTypeException extends RuntimeException{

    public UnsupportedTypeException(Class type){
        super(type.getName() + " is currenly unsupported");
    }
}

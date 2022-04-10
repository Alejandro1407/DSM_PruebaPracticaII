package sv.com.udb.prueba.exceptions;

public class InvalidClassException extends RuntimeException{

    public InvalidClassException(){
        super("Class must be annotated with @Entity and have at least one @Field with only one @Id");
    }
}

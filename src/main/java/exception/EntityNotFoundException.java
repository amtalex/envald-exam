package exception;


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(Class clazz, String id) {
        super(clazz.getSimpleName() + "[" + id + "] was not found");
    }
}

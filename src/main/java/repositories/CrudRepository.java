package repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    void add(T entity);

    List<T> selectAll();

    Optional<?> findBy(String param, String value);

    void update(T entity);

    void delete(T entity);

}

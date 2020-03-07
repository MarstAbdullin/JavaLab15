package repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    Optional<T> create(T model);

    Optional<T> read(ID id);

    void update(T model);

    void delete(ID id);

}

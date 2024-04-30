package repository;

public interface Repository<T> {
    void saveEntity(T entity);
}

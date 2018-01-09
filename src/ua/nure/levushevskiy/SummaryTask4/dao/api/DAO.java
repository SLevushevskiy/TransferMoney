package ua.nure.levushevskiy.SummaryTask4.dao.api;

import java.util.List;

/**
 * An interface that defines the general logic of all DAO classes.
 *
 * @param <T>  - object.
 * @param <ID> - id.
 */
public interface DAO<T, ID> {

    /**
     * A method that saves the specified object.
     *
     * @param t - object name.
     * @return - this object.
     */
    T save(T t);

    /**
     * The method that gets the object by the identifier.
     *
     * @param id - ID.
     * @return - object by this ID.
     */
    T getById(ID id);

    /**
     * Method to delete an object.
     *
     * @param t - object to be deleted.
     * @return - true (if object was removed).
     */
    boolean delete(T t);

    /**
     * Method for updating the state of an object in a table.
     *
     * @param t - object to be updated.
     * @return - true (if object was updated).
     */
    boolean update(T t);

    /**
     * Method for retrieving all table objects.
     *
     * @return - got objects.
     */
    List<T> getAll();

}
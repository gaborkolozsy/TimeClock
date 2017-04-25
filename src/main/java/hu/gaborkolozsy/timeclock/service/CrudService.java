/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service;

/**
 * The basic <strong>C.R.U.D.</strong> generic service interface.
 * <blockquote>
 * <table border=2 style=background-color:rgb(220,220,220)>
 *  <tr>
 *   <th>C.R.U.D.</th>
 *   <th>&#060;&#062;</th>
 *   <th>Meaning</th>
 *  </tr>
 *  <tr>
 *   <td><center><strong>C</strong></center></td>
 *   <td><center>==</center></td>
 *   <td><strong>&nbsp;<u><font color=red>C</font></u></strong>reate</td>
 *  </tr>
 *  <tr>
 *   <td><center><strong>R</strong></center></td>
 *   <td><center>==</center></td>
 *   <td><strong>&nbsp;<u><font color=red>R</font></u></strong>ead</td>
 *  </tr>
 *  <tr>
 *   <td><center><strong>U</strong></center></td>
 *   <td><center>==</center></td>
 *   <td><strong>&nbsp;<u><font color=red>U</font></u></strong>pdate</td>
 *  </tr>
 *  <tr>
 *   <td><center><strong>D</strong></center></td>
 *   <td><center>==</center></td>
 *   <td><<strong>&nbsp;<u><font color=red>D</font></u></strong>elete</td>
 *  </tr>
 * </table>
 * </blockquote>
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <K> type of primary key
 * @since 0.0.1-SNAPSHOT
 */
public interface CrudService<T, K> {

    /**
     * Make an instance, managed and persistent.
     * @param entity entity instance
     */
    void save(T entity);

    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param primaryKey entity's primary key(ID)
     * @return the found entity instance or null if the entity does not exist
     */
    T get(K primaryKey);

    /**
     * Returns an iterable collection of the {@code TimeClock} entity.
     * @return an iterable collection of entity
     */
    Iterable<T> getAll();
    
    /**
     * Merge the state of the given entity into the current persistence context.
     * Return the updated entity.
     * @param <S> the extended entity
     * @param entity entity instance
     * @return the updated entity instance
     */
    <S extends T> S update(S entity);
    
    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    void remove(T entity);
    
    /**
     * Remove all entity instance.
     */
    void removeAll();
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primaryKey entity's primary key(ID)
     * @return boolean indicating if entity is in persistence context
     */
    boolean isExist(K primaryKey);
    
    /**
     * Clear persistence context.
     */
    void clear();
    
    /**
     * Close the entity manager.
     */
    void close();
    
}

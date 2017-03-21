/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

/**
 * Order some common methode for {@code TimeClok} entity's builder classes.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @since 0.0.1-SNAPSHOT
 */
public interface Builder<E> {

    /**
     * Build the specified {@code TimeClock} entity <strong>(E)</strong>  
     * and return it.
     * @return {@code E} entity
     */
    E build();
}

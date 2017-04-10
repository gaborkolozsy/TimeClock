/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.embedded.Address;
import hu.gaborkolozsy.timeclock.model.embedded.Address.AddressBuilder;

/**
 * Order some abstract methode for {@link Address} entity's 
 * {@link AddressBuilder} class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractAddressBuilder<E, B> extends AbstractEntity<E, B> implements Builder<E> {

    /**
     * Set the country.
     * @param country country
     * @return builder implement class
     */
    public abstract B setCountry(String country);

    /**
     * Set the region.
     * @param region region
     * @return builder implement class
     */
    public abstract B setRegion(String region);

    /**
     * Set the city.
     * @param city city
     * @return builder implement class
     */
    public abstract B setCity(String city);

    /**
     * Set the address.
     * @param address address
     * @return builder implement class
     */
    public abstract B setAddress(String address);

    /**
     * Set the zip.
     * @param zip zip
     * @return builder implement class
     */
    public abstract B setZip(int zip);

    /**
     * Set the pob.
     * @param pob pob
     * @return builder implement class
     */
    public abstract B setPob(int pob);

    /**
     * Set the phone number.
     * @param phoneNumber phone number
     * @return builder implement class
     */
    public abstract B setPhoneNumber(String phoneNumber);

    /**
     * Set the email address
     * @param emailAddress
     * @return builder implement class
     */
    public abstract B setEmailAddress(String emailAddress);
    
}

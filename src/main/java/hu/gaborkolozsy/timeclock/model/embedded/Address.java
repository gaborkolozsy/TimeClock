/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.embedded;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractAddressBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Defines an {@code Address} class whose instances are stored as an intrinsic
 * part of an owning entity and share the identity of the entity.<br>
 * Each of the persistent properties or fields of the embedded
 * object is mapped to the database table for the entity.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractAddressBuilder
 * @see Column
 * @see Embeddable
 */
@Embeddable
@SuppressWarnings("SerializableClass")
public class Address {

    @Column(name = "Country")
    private String country;
    
    @Column(name = "Region")
    private String region;
    
    @Column(name = "City")
    private String city;
    
    @Column(name = "Address")
    private String address;
    
    @Column(name = "Zip")
    private int zip;
    
    @Column(name = "POB")
    private int pob;
    
    @Column(name = "Phone")
    private String pnoneNumber;
    
    @Column(name = "Email")
    private String emailAddress;

    /**
     * Returns the country.
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the region.
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Returns the city.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the address.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the zip code.
     * @return zip code
     */
    public int getZip() {
        return zip;
    }

    /**
     * Returns the PO Box.
     * @return po box
     */
    public int getPob() {
        return pob;
    }

    /**
     * Returns the phone number.
     * @return phone number
     */
    public String getPnoneNumber() {
        return pnoneNumber;
    }

    /**
     * Returns the email address.
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
    * {@code AddressBuilder} is used to build instances of 
    * {@link Addresst} from values configured by the setters.
    * 
    * <p>The class is achieves the Build design pattern.
    *
    * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
    * @since 0.0.1-SNAPSHOT
    * @see Builder
    * @see AbstractAddressBuilder
    */
    public static class AddressBuilder extends AbstractAddressBuilder<Address, AddressBuilder> {
        
        /**
         * Constructor without parameter.
         */
        public AddressBuilder() {
            super.entity = new Address();
        }
        
        /**
         * Constructor with one parameter.
         * @param address {@code Address}
         */
        public AddressBuilder(Address address) {
            super.entity = address;
        }
        
        /**
         * Create a new {@code AddressBuilder} instance.
         * @return a new {@code AddressBuilder} instance
         */
        public static AddressBuilder create() {
            return new AddressBuilder();
        }

        /**
         * Set the country.
         * @param country country
         * @return this
         */
        @Override
        public AddressBuilder setCountry(String country) {
            super.entity.country = country;
            return this;
        }

        /**
         * Set the region.
         * @param region region
         * @return this
         */
        @Override
        public AddressBuilder setRegion(String region) {
            super.entity.region = region;
            return this;
        }

        /**
         * Set the city.
         * @param city city
         * @return this
         */
        @Override
        public AddressBuilder setCity(String city) {
            super.entity.city = city;
            return this;
        }

        /**
         * Set the address.
         * @param address address
         * @return this
         */
        @Override
        public AddressBuilder setAddress(String address) {
            super.entity.address = address;
            return this;
        }

        /**
         * Set the zip code.
         * @param zip zip code
         * @return this
         */
        @Override
        public AddressBuilder setZip(int zip) {
            super.entity.zip = zip;
            return this;
        }

        /**
         * Set the PO Box.
         * @param pob PO Box
         * @return this
         */
        @Override
        public AddressBuilder setPob(int pob) {
            super.entity.pob = pob;
            return this;
        }

        /**
         * Set the phone number.
         * @param phoneNumber phone number
         * @return this
         */
        @Override
        public AddressBuilder setPhoneNumber(String phoneNumber) {
            super.entity.pnoneNumber = phoneNumber;
            return this;
        }

        /**
         * Set the email address.
         * @param emailAddress email address
         * @return this
         */
        @Override
        public AddressBuilder setEmailAddress(String emailAddress) {
            super.entity.emailAddress = emailAddress;
            return this;
        }

        /**
         * Return a new {@code Address} set instance.
         * @return {@code Address}
         */
        @Override
        public Address build() {
            return entity;
        }        
        
    }
    
}

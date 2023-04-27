package com.happypet.model;

import java.io.Serializable;
/** Pet Store class is a data model for the pet stores in our database, stores the name, address,
 * and database key for each store*/
public class PetStore implements Serializable {
    private String name;
    private String storeAddress;
    private String Key;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public PetStore(String name, String storeAddress) {
        this.name = name;
        this.storeAddress = storeAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public PetStore() {
    }
}

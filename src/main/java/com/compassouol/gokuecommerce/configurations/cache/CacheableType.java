package com.compassouol.gokuecommerce.configurations.cache;

public enum CacheableType {
    ANNONYMOUS("EnableKeyCache");

    String value;

    CacheableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

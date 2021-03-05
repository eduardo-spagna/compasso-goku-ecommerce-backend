package com.compassouol.gokuecommerce.enums;

public enum RoleEnum {
    ROLE_ADMIN(1), ROLE_COMMON_USER(2);

    private final int value;

    RoleEnum(int value) {
        this.value = value;
    }

    public int getRoleId() {
        return value;
    }
}

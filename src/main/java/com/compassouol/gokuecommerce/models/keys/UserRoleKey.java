package com.compassouol.gokuecommerce.models.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;
}
package com.example;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@MappedEntity("a_friend")
public class Friend {
    @Id
    @AutoPopulated
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Integer id;

    private String name;

    private Date dob;

    public Friend() {
    }

    public Friend(String name, Date dob) {
        this();

        this.name = name;
        this.dob = dob;
    }
}

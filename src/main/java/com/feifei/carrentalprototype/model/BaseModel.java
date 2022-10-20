package com.feifei.carrentalprototype.model;

import java.util.UUID;

public class BaseModel {
    public String id;

    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }
}

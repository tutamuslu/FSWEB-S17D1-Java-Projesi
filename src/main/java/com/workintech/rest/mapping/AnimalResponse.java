package com.workintech.rest.mapping;

import com.workintech.rest.entity.Animal;

public class AnimalResponse {
    private int status;
    private String message;
    private Animal animal;

    public AnimalResponse(int status, String message, Animal animal) {
        this.status = status;
        this.message = message;
        this.animal = animal;
    }
}

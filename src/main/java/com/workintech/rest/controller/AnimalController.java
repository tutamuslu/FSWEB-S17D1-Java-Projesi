package com.workintech.rest.controller;

import com.workintech.rest.entity.Animal;
import com.workintech.rest.mapping.AnimalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("workintech/animal")
public class AnimalController {
    @Value("${global.name}")
    private String name;
    private Map<Integer, Animal> animals;

    public AnimalController() {
        this.animals = new HashMap<>();
    }

    @GetMapping("")
    public List<Animal> get(){
        return animals.values().stream().toList();
    }

    @GetMapping("{id}")
    public AnimalResponse get(@PathVariable int id){
        if(id < 1){
            return new AnimalResponse(1, "Id 1 den küçük girilemez.", null);
        }
        if(!animals.containsKey(id)){
            return new AnimalResponse(2, "Bu id ile kayıt mevcut.",null);
        }
        return new AnimalResponse(0, "", animals.get(id));
    }

    @PostMapping("")
    public AnimalResponse save(@RequestBody Animal animal){
        if(animals.containsKey(animal.getId())){
            return new AnimalResponse(2, "Bu id ile kayıt mevcut.",null);
        }
        if(animal.getId() < 0 || animal.getName() == null || animal.getName().isEmpty()){
            return new AnimalResponse(3, "Id veya isim hatalı girildi.",null);
        }
        animals.put(animal.getId(), animal);
        return new AnimalResponse(0, "", animal);
    }

    @PutMapping("{id}")
    public AnimalResponse update(@PathVariable int id, @RequestBody Animal animal){
        if(!animals.containsKey(id)){
            return new AnimalResponse(4, "Güncellenecek kayıt bulunamadı",null);
        }
        if(animal.getId() < 0 || animal.getName() == null || animal.getName().isEmpty()){
            return new AnimalResponse(3, "Id veya isim hatalı girildi.",null);
        }
        animals.put(id, new Animal(id, animal.getName()));
        return new AnimalResponse(0, "", animal);
    }

    @DeleteMapping("{id}")
    public AnimalResponse delete(@PathVariable int id){
        if(!animals.containsKey(id)){
            return new AnimalResponse(5, "Silinecek kayıt bulunamadı",null);
        }
        Animal animal = animals.get(id);
        animals.remove(animal);
        return new AnimalResponse(0, "", animal);
    }

    @GetMapping("welcome")
    public String welcome(){
        return "Selamm " + name;
    }
}

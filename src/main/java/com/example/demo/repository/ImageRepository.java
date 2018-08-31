package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

}

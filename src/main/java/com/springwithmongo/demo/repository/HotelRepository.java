package com.springwithmongo.demo.repository;

import com.springwithmongo.demo.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    List<Hotel> findHotelsByPricePerNightIsLessThanEqual(int maxPrice);

    @Query(value = "{'address.city':'?0'}")
    List<Hotel> findByCity(String city);

}

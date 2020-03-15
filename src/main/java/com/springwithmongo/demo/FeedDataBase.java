package com.springwithmongo.demo;


import com.springwithmongo.demo.domain.Address;
import com.springwithmongo.demo.domain.Hotel;
import com.springwithmongo.demo.domain.Review;
import com.springwithmongo.demo.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FeedDataBase implements CommandLineRunner {

    private HotelRepository hotelRepository;

    public FeedDataBase(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Hotel marriot = new Hotel();
        marriot.setName("Marriot");
        marriot.setPricePerNight(150);
        marriot.setAddress(new Address("Paris", "France"));
        marriot.setReviews(   Arrays.asList(
                new Review("John", 8, false),
                new Review("Mary", 7, true)
        ));

        Hotel sofitel = new Hotel();
        sofitel.setName("Sofitel");
        sofitel.setPricePerNight(200);
        sofitel.setAddress(new Address("Rome", "Italy"));
        sofitel.setReviews(new ArrayList<>());

        // drop all hotels
        this.hotelRepository.deleteAll();

        //add our hotels to the database
        List<Hotel> hotels = Arrays.asList(marriot, sofitel);
        this.hotelRepository.saveAll(hotels);
    }

}

package com.springwithmongo.demo.service;

import com.google.gson.Gson;
import com.springwithmongo.demo.domain.Hotel;
import com.springwithmongo.demo.repository.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public ResponseEntity<String> getAllHotels() {

        try{

            Gson gson = new Gson();
            String json = gson.toJson(hotelRepository.findAll());

            return new ResponseEntity<>(json, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> getHotel(String id) {

        Gson gson = new Gson();

        try{

            Optional<Hotel> hotelOption = hotelRepository.findById(id);

            if (hotelOption.isPresent()) return new ResponseEntity<>(gson.toJson(hotelOption), HttpStatus.OK);
            else return new ResponseEntity<>("Any hotel could be found with this id.", HttpStatus.NOT_FOUND);

        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> insertHotel(String json) {

        Gson gson = new Gson();

        try{

            Hotel hotel = gson.fromJson(json, Hotel.class);
            hotelRepository.insert(hotel);

            return ResponseEntity.ok().build();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body("Impossible to insert object Hotel.");
        }

    }

    public ResponseEntity<Object> deleteHotel(String id) {

        try{
            Optional<Hotel> hotel = hotelRepository.findById(id);
            if (hotel.isPresent()) {
                hotelRepository.delete(hotel.get());
                return ResponseEntity.ok().build();
            } else {
                return new ResponseEntity<>("There are no hotels with this id. Nothing to delete", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Any hotel could be deleted with this id.", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> getHotelsByPrice(int maxPrice) {

        try{

            List<Hotel> hotels = hotelRepository.findHotelsByPricePerNightIsLessThanEqual(maxPrice);

            if (!hotels.isEmpty()) {
                Gson gson = new Gson();
                String json = gson.toJson(hotels);

                return new ResponseEntity<>(json, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There's no hotels with price equal or less than " + maxPrice, HttpStatus.NOT_FOUND);
            }
            

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<String> getHotelsByCity(String cityName) {

        try{

            List<Hotel> hotels = hotelRepository.findByCity(cityName);

            System.out.println(hotels);

            if (!hotels.isEmpty()) {
                Gson gson = new Gson();
                String json = gson.toJson(hotels);

                return new ResponseEntity<>(json, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There's no hotels with city name " + cityName, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}

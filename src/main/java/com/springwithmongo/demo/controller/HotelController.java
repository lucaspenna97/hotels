package com.springwithmongo.demo.controller;

import com.springwithmongo.demo.service.HotelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/byPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByPrice(@RequestParam(required = true) int price) {
        return hotelService.getHotelsByPrice(price);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable("id") String id) {
        return hotelService.getHotel(id);
    }

    @GetMapping(value = "/byCityName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByCityName(@RequestParam(required = true) String name) {
        return hotelService.getHotelsByCity(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insertHotel(@RequestBody String json) {
        return hotelService.insertHotel(json);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteHotel (@RequestParam(required = true) String id){
        return hotelService.deleteHotel(id);
    }



}

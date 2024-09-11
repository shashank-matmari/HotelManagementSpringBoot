package com.example.demo;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Amenity;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.services.AmenityServiceImp;

public class AmenityServiceImpTest {

    @InjectMocks
    private AmenityServiceImp amenityService;

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);;;
    }

    @Test
    public void testCreateAmenity() { 
        Amenity amenity = new Amenity();
        SuccessResponse response = amenityService.createAmenity(amenity);
        assertNotNull(response);
        assertEquals("POSTSUCCESS", response.getCode());

        verify(amenityRepository, times(1)).save(amenity);
    }

    @Test
    public void testGetAllAmenities() throws AmenityNotFoundException {
        Amenity amenity = new Amenity();
        when(amenityRepository.findAll()).thenReturn(Arrays.asList(amenity));

        List<Amenity> amenities = amenityService.getAllAmenities();
        assertNotNull(amenities);
        assertEquals(1, amenities.size());
    }

    @Test
    public void testGetAllAmenitiesEmpty() {
        when(amenityRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.getAllAmenities();
        });
    }

    @Test
    public void testGetAmenityById() throws AmenityNotFoundException {
        Amenity amenity = new Amenity();
        when(amenityRepository.findById(1)).thenReturn(Optional.of(amenity));

        Amenity foundAmenity = amenityService.getAmenityById(1);
        assertNotNull(foundAmenity);
    }

    @Test
    public void testGetAmenityByIdNotFound() {
        when(amenityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.getAmenityById(1);
        });
    }

    @Test
    public void testGetAmenitByHotelId() throws AmenityNotFoundException, HotelNotFoundException {
        Amenity amenity = new Amenity();
        when(amenityRepository.getAmenitByHotelId(1)).thenReturn(Arrays.asList(amenity));

        List<Amenity> amenities = amenityService.getAmenitByHotelId(1);
        assertNotNull(amenities);
        assertEquals(1, amenities.size());
    }

    @Test
    public void testGetAmenitByHotelIdNotFound() {
        when(amenityRepository.getAmenitByHotelId(1)).thenReturn(Arrays.asList());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.getAmenitByHotelId(1);
        });
    }

    @Test
    public void testGetAmenitByRoomId() throws AmenityNotFoundException, RoomNotFoundException {
        Amenity amenity = new Amenity();
        when(amenityRepository.getAmenitByRoomId(1)).thenReturn(Arrays.asList(amenity));

        List<Amenity> amenities = amenityService.getAmenitByRoomId(1);
        assertNotNull(amenities);
        assertEquals(1, amenities.size());
    }

    @Test
    public void testGetAmenitByRoomIdNotFound() {
        when(amenityRepository.getAmenitByRoomId(1)).thenReturn(Arrays.asList());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.getAmenitByRoomId(1);
        });
    }

    @Test
    public void testDeleteAmenityById() {
        when(amenityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.deleteAmenityById(1);
        });
    }

    @Test
    public void testUpdateAmenityById() throws AmenityNotFoundException {
        Amenity amenity = new Amenity();
        amenity.setAmenity_id(1);
        when(amenityRepository.findById(1)).thenReturn(Optional.of(amenity));

        SuccessResponse response = amenityService.updateAmenityById(amenity);
        assertNotNull(response);
        assertEquals("UPDATESUCCESS", response.getCode());

        verify(amenityRepository, times(1)).save(amenity);
    }

    @Test
    public void testUpdateAmenityByIdNotFound() {
        Amenity amenity = new Amenity();
        amenity.setAmenity_id(1);
        when(amenityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AmenityNotFoundException.class, () -> {
            amenityService.updateAmenityById(amenity);
        });
    }
}

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

import com.example.demo.dto.RoomTypeDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.RoomTypeNotFoundException;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.services.RoomTypeServiceImpl;

public class RoomTypeServiceImplTest {

    @InjectMocks
    private RoomTypeServiceImpl roomTypeService;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRoomType() throws RoomTypeNotFoundException {
        RoomType roomType = new RoomType();
        when(roomTypeRepository.findAll()).thenReturn(Arrays.asList(roomType));

        List<RoomType> roomTypes = roomTypeService.getAllRoomType();
        assertNotNull(roomTypes);
        assertEquals(1, roomTypes.size());
    }

    @Test
    public void testGetById() throws RoomTypeNotFoundException {
        RoomType roomType = new RoomType();
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        RoomType foundRoomType = roomTypeService.getById(1);
        assertNotNull(foundRoomType);
    }

    @Test
    public void testDeleteById() throws RoomTypeNotFoundException {
        RoomType roomType = new RoomType();
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        SuccessResponse response = roomTypeService.deleteById(1);
        assertNotNull(response);
        assertEquals("DELETESUCCESS", response.getCode());
    }

    @Test
    public void testCreateRoomType() throws RoomTypeNotFoundException {
        RoomTypeDTO roomTypeDto = new RoomTypeDTO();
        roomTypeDto.setType_name("Deluxe");
        roomTypeDto.setDescription("A deluxe room");
        roomTypeDto.setMax_occupancy(2);
        roomTypeDto.setPrice_per_night(2000);

        when(roomTypeRepository.getRoomTypeByName("Deluxe")).thenReturn(Arrays.asList());

        SuccessResponse response = roomTypeService.createRoomType(roomTypeDto);
        assertNotNull(response);
        assertEquals("POSTSUCCESS", response.getCode());
    }

    @Test
    public void testUpdateRoomTypeById() throws RoomTypeNotFoundException {
        RoomTypeDTO roomTypeDto = new RoomTypeDTO();
        roomTypeDto.setType_name("Deluxe");
        roomTypeDto.setDescription("Updated description");
        roomTypeDto.setMax_occupancy(2);
        roomTypeDto.setPrice_per_night(2500);

        RoomType roomType = new RoomType();
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        SuccessResponse response = roomTypeService.updateRoomTypeById(roomTypeDto, 1);
        assertNotNull(response);
        assertEquals("UPDATESUCCESS", response.getCode());
    }
}

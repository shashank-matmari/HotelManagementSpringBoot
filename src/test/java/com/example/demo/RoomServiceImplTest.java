package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Amenity;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.RoomTypeNotFoundException;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.services.RoomServiceImpl;

public class RoomServiceImplTest {

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private RoomTypeRepository roomTypeRepository;
    
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewRoom_Success() throws RoomTypeNotFoundException {
        // Arrange
        int hotelId = 1;
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_number(101);
        roomDTO.setRoom_type_id(1);
        roomDTO.setIs_available(true);
        
        RoomType roomType = new RoomType();
        Hotel hotel = new Hotel();

        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(roomRepository.save(any(Room.class))).thenReturn(new Room());

        // Act
        SuccessResponse response = roomService.createNewRoom(hotelId, roomDTO);

        // Assert
        assertEquals("POSTSUCCESS", response.getCode());
        assertEquals("Room added successfully", response.getMessage());
        verify(roomTypeRepository).findById(1);
        verify(hotelRepository).findById(hotelId);
        verify(roomRepository).save(any(Room.class));
    }
    
    @Test
    void testCreateNewRoom_RoomTypeNotFound() {
        // Arrange
        int hotelId = 1;
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_number(101);
        roomDTO.setRoom_type_id(1);
        roomDTO.setIs_available(true);

        when(roomTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RoomTypeNotFoundException thrown = assertThrows(
            RoomTypeNotFoundException.class,
            () -> roomService.createNewRoom(hotelId, roomDTO)
        );
        assertEquals("Roomtype doesnot exist", thrown.getMessage());
        assertEquals("ADDFAILS", thrown.getCode()); // Adjust this to match your actual exception handling
    }

    @Test
    public void testGetAllRooms() {
        Room room = new Room();
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));

        List<Room> rooms = roomService.getAllRooms();
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }

    @Test
    public void testGetRoomById() throws RoomNotFoundException {
        Room room = new Room();
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));

        Room foundRoom = roomService.getRoomById(1);
        assertNotNull(foundRoom);
    }

    @Test
    public void testGetAvailableRoomsByType() throws RoomTypeNotFoundException {
        Room room = new Room();
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(new RoomType()));
        when(roomRepository.getAvailableRoomsByType(1)).thenReturn(Arrays.asList(room));

        List<Room> rooms = roomService.getAvailableRoomsByType(1);
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }

    @Test
    public void testGetRoomsWithSpecificAmenityId() throws AmenityNotFoundException {
        Room room = new Room();
        when(amenityRepository.findById(1)).thenReturn(Optional.of(new Amenity()));
        when(roomRepository.getRoomsWithSpecificAmenityId(1)).thenReturn(Arrays.asList(room));

        List<Room> rooms = roomService.getRoomsWithSpecificAmenityId(1);
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }

    @Test
    public void testUpdateRoom() throws RoomNotFoundException, RoomTypeNotFoundException {
        RoomDTO roomDto = new RoomDTO();
        roomDto.setRoom_number(101);
        roomDto.setIs_available(true);
        roomDto.setRoom_type_id(1);

        Room room = new Room();
        RoomType roomType = new RoomType();
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        SuccessResponse response = roomService.updateRoom(1, roomDto);
        assertNotNull(response);
        assertEquals("UPDATESUCCESS", response.getCode());
    }

    @Test
    public void testDeleteRoomById() {
        assertThrows(RoomNotFoundException.class, () -> {
            roomService.deleteRoomById(1);
        });
    }

    @Test
    public void testGetRoomsAvailableByDateRange() throws ParseException {
        Room room = new Room();
        when(roomRepository.getAvailableRoomsInHotelByDateRange(anyInt(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Arrays.asList(room));

        List<Room> rooms = roomService.getRoomsAvailableByDateRange(1, "2023-09-01", "2023-09-10");
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }

    @Test
    public void testAddAmenityToRoom() throws RoomNotFoundException, AmenityNotFoundException {
        Room room = new Room();
        room.setAmenities(new ArrayList<>()); 
        Amenity amenity = new Amenity();
        amenity.setRoom(new ArrayList<>()); 
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(amenityRepository.findById(1)).thenReturn(Optional.of(amenity));
        SuccessResponse response = roomService.addAmenityToRoom(1, 1);
        assertNotNull(response);
        assertEquals("POSTSUCCESS", response.getCode());
        assertTrue(room.getAmenities().contains(amenity));
        assertTrue(amenity.getRoom().contains(room));
        verify(roomRepository, times(1)).save(room);
        verify(amenityRepository, times(1)).save(amenity);
    }

}

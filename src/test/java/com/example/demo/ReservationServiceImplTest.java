package com.example.demo;

import com.example.demo.dto.DetailsDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.services.ReservationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;
    
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private LocalDate parseDate(String dateString) throws ParseException {
        return LocalDate.parse(dateString, dateFormatter);

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation_Valid() throws ReservationNotFoundException, ParseException {
    	Reservation reservation = new Reservation();
        reservation.setReservation_id(54);
        reservation.setGuest_name("Surya");
        reservation.setGuest_email("surya@email.com");
        reservation.setGuest_phone("9998887776");

        // Parse the date-time strings
        reservation.setCheck_in_date(parseDate("09-06-2024"));
        reservation.setCheck_out_date(parseDate("09-07-2024"));

        // Mock repository behavior
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Call the service method
        Reservation result = reservationService.createReservation(reservation);

        // Verify the result
        assertNotNull(result);
        assertEquals(54, result.getReservation_id());
        assertEquals("Surya", result.getGuest_name());
        assertEquals("surya@email.com", result.getGuest_email());
        
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testCreateReservation_InvalidDetails() throws ParseException {
    	 Reservation reservation = new Reservation();
         reservation.setReservation_id(54);
         reservation.setGuest_name("Surya");
         reservation.setGuest_email("surya@email.com");
         reservation.setGuest_phone("9998887776");

         // Parse invalid dates
         reservation.setCheck_in_date(parseDate("09-07-2024"));
         reservation.setCheck_out_date(parseDate("09-06-2024"));

         // Expect an exception to be thrown
         ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
             reservationService.createReservation(reservation);
         });

         assertEquals("ADDFAILS", thrown.getCode());
         assertEquals("Invalid reservation details", thrown.getMessage());
    }

    @Test
    void testGetAllReservations() throws ReservationNotFoundException {
        List<Reservation> reservations = new ArrayList<>();
        // Add some reservations to the list
        reservations.add(new Reservation());
        reservations.add(new Reservation());

        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();

//        System.out.println(result.size());
        assertNotNull(result);
        assertEquals(reservations.size(), result.size());
        verify(reservationRepository, times(2)).findAll();
    }

    @Test
    void testGetAllReservations_Empty() {
        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());

        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getAllReservations();
        });

        assertEquals("GETALLFAILS", thrown.getCode());
        assertEquals("reservation list is empty", thrown.getMessage());
    }

    @Test
    void testGetReservationById_Exists() throws ReservationNotFoundException {
        Reservation reservation = new Reservation();
        // Set reservation details
        reservation.setReservation_id(19);

        when(reservationRepository.findById(19)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationById(19);
//        System.out.println("*This is Exists*\n"+result);

        assertNotNull(result);
        assertEquals(19, result.getReservation_id());
        verify(reservationRepository, times(2)).findById(19);
    }

    @Test
    void testGetReservationById_NotFound() {
        when(reservationRepository.findById(1000)).thenReturn(Optional.empty());

        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationById(1000);
        });
        
//        System.out.println(thrown);
        assertEquals("GETFAILS", thrown.getCode());
        assertEquals("Reservation doesn't exist", thrown.getMessage());
    }

    @Test
    void testDeleteReservationById_Exists() throws ReservationNotFoundException {
        when(reservationRepository.findById(1)).thenReturn(Optional.of(new Reservation()));

        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.deleteReservationById(1);
        });
        
        assertEquals("DLTFAILS", thrown.getCode());
        assertEquals("Reservation cannot be deleted", thrown.getMessage());
    }

    @Test
    void testDeleteReservationById_NotFound() {
        when(reservationRepository.findById(100)).thenReturn(Optional.empty());

        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.deleteReservationById(100);
        });

//        System.out.println(thrown);
        assertEquals("DLTFAILS", thrown.getCode());
        assertEquals("Reservation doesn't exist", thrown.getMessage());
    }
    
    @Test
    void testUpdateReservationById_Exists() throws ReservationNotFoundException {
        // Create a Reservation object
        Reservation reservation = new Reservation();
        reservation.setReservation_id(1);

        // Mock repository behavior
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Call the service method
        SuccessResponse result = reservationService.updateReservationById(reservation);

        // Verify the result
        assertNotNull(result);
        assertEquals("UPDATESUCCESS", result.getCode());
        assertEquals("Reservation updated successfully", result.getMessage());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservationById_NotExists() {
        // Create a Reservation object
        Reservation reservation = new Reservation();
        reservation.setReservation_id(1);

        // Mock repository behavior
        when(reservationRepository.findById(1)).thenReturn(Optional.empty());

        // Expect an exception to be thrown
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.updateReservationById(reservation);
        });

        assertEquals("UPDTFAILS", thrown.getCode());
        assertEquals("Reservation doesn't exist exist", thrown.getMessage());
    }

    @Test
    void testGetReservationInDateRange_Exists() throws ReservationNotFoundException, ParseException {
        // Create a list of ReservationDTOs
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations.add(new ReservationDTO());

        // Mock repository behavior
        when(reservationRepository.getReservationsInDateRange(parseDate("09-01-2024"), parseDate("09-30-2024")))
                .thenReturn(reservations);

        // Call the service method
        List<ReservationDTO> result = reservationService.getReservationInDateRange(parseDate("09-01-2024"), parseDate("09-30-2024"));

        // Verify the result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservationRepository, times(2)).getReservationsInDateRange(parseDate("09-01-2024"), parseDate("09-30-2024"));
    }

    @Test
    void testGetReservationInDateRange_NotExists() throws ParseException {
        // Mock repository behavior
        when(reservationRepository.getReservationsInDateRange(parseDate("09-01-2024"), parseDate("09-30-2024")))
                .thenReturn(new ArrayList<>());

        // Expect an exception to be thrown
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationInDateRange(parseDate("09-01-2024"), parseDate("09-30-2024"));
        });

        assertEquals("GETFAILS", thrown.getCode());
        assertEquals("Reservations doesn't exist in specified range", thrown.getMessage());
    }

    @Test
    void testGetAllReservationsWithRoom_Exists() throws ReservationNotFoundException {
        // Create a list of ReservationDTOs
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations.add(new ReservationDTO());

        // Mock repository behavior
        when(reservationRepository.getAllReservationswithRoom()).thenReturn(reservations);

        // Call the service method
        List<ReservationDTO> result = reservationService.getAllReservationsWithRoom();

        // Verify the result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservationRepository, times(2)).getAllReservationswithRoom();
    }

    @Test
    void testGetAllReservationsWithRoom_EmptyList() {
        // Mock repository behavior
        when(reservationRepository.getAllReservationswithRoom()).thenReturn(new ArrayList<>());

        // Expect an exception to be thrown
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getAllReservationsWithRoom();
        });

        assertEquals("GETFAILS", thrown.getCode());
        assertEquals("Reservation list is empty", thrown.getMessage());
    }

    @Test
    void testGetReservationwithRoom_Exists() throws ReservationNotFoundException {
        // Create a ReservationDTO object
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservation_id(19);
        reservationDTO.setRoom_id(19);

//        System.out.println(reservationDTO);
        // Mock repository behavior
        when(reservationRepository.getReservationwithRoom(19)).thenReturn(reservationDTO);

        // Call the service method
        ReservationDTO result = reservationService.getReservationwithRoom(19);

//        System.out.println(result);
        // Verify the result
        assertNotNull(result);
        assertEquals(19, result.getReservation_id());
        verify(reservationRepository, times(2)).getReservationwithRoom(19);
    }

    @Test
    void testGetReservationwithRoom_NotExists() {
        // Mock repository behavior
        when(reservationRepository.getReservationwithRoom(1000)).thenReturn(null);

        // Expect an exception to be thrown
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationwithRoom(1000);
        });

        assertEquals("GETFAILS", thrown.getCode());
        assertEquals("Reservation doesn't exist", thrown.getMessage());
    }

    @Test
    void testGetAllDetailsByReservationId_Exists() throws ReservationNotFoundException {
        // Create a list of DetailsDTOs
        List<DetailsDTO> details = new ArrayList<>();
        DetailsDTO abc = new DetailsDTO();
        abc.setReservation_id(5);
        details.add(abc);

//        System.out.println(details);

        // Mock repository behavior
        when(reservationRepository.getDetailsByReservationIdWithoutReview(5)).thenReturn(details);

        // Call the service method
        List<DetailsDTO> result = reservationService.getAllDetailsByReservationId(5);

        // Verify the result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(5, result.get(0).getReservation_id());
//        verify(reservationRepository, times(2)).getAllDetailsByReservationId(1);
    }

    @Test
    void testGetAllDetailsByReservationId_NotExists() {
        // Mock repository behavior
        when(reservationRepository.findById(100)).thenReturn(Optional.empty());

        // Expect an exception to be thrown
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getAllDetailsByReservationId(100);
        });

        assertEquals("GETFAILS", thrown.getCode());
        assertEquals("Reservation doesn't exist", thrown.getMessage());
    }

}

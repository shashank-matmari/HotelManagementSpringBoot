package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.RoomTypeDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.RoomTypeNotFoundException;
import com.example.demo.repository.RoomTypeRepository;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;
    
    // Method to get all room type details
    @Override
    public List<RoomType> getAllRoomType() throws RoomTypeNotFoundException{
    	Optional<List<RoomType>> roomTypeList=Optional.of(roomTypeRepository.findAll());
    	if(roomTypeList.isEmpty()) {
    		throw new RoomTypeNotFoundException("GETFAILS","RoomType List is empty");
    	}else {
    		return roomTypeList.get();
    	}
    }

    // Method to get room_type by id
	@Override
	public RoomType getById(int room_type_id) throws RoomTypeNotFoundException {
		
		if(roomTypeRepository.findById(room_type_id).isEmpty()) {
			throw new RoomTypeNotFoundException("GETFAILS","roomType doesn't exist");
		}
		else
			return roomTypeRepository.findById(room_type_id).get();
	}

	// Method to delete a room_type
    @Override
    public SuccessResponse deleteById(int room_type_id) throws RoomTypeNotFoundException{
    	if(roomTypeRepository.findById(room_type_id).isEmpty()) {
			throw new RoomTypeNotFoundException("DLTFAILS","roomType doesn't exist");
		}
		else {
			roomTypeRepository.deleteById(room_type_id);
			return new SuccessResponse("DELETESUCCESS","roomType deleted successfully");
		}
    }
    
    // Method to create room_type
    @Override
	public SuccessResponse createRoomType(RoomTypeDTO roomtype) throws RoomTypeNotFoundException{
    	
    	if(roomtype.getMax_occupancy()<=0 || roomtype.getPrice_per_night()<=0 || roomTypeRepository.getRoomTypeByName(roomtype.getType_name()).size()>0) {
    		throw new RoomTypeNotFoundException("ADDFAILS","Invalid Roomtype details entered");
    	}
    	else {
    		RoomType newroomtype=new RoomType(roomtype.getType_name(),roomtype.getDescription(), roomtype.getMax_occupancy(), roomtype.getPrice_per_night());
			roomTypeRepository.save(newroomtype);
			return new SuccessResponse("POSTSUCCESS","roomType added successfully");
		}    		

	}
    
    // Method to update a room_type details
	@Override
	public SuccessResponse updateRoomTypeById(RoomTypeDTO roomtype, int room_type_id) throws RoomTypeNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<RoomType>newroomtype=roomTypeRepository.findById(room_type_id);
		if(newroomtype.isEmpty() ) {
			throw new RoomTypeNotFoundException("UPDTFAILS","roomType doesn't exist");
		}
		else {
			RoomType roomtype1=newroomtype.get();
			roomtype1.setRoom_type_id(room_type_id);
			roomtype1.setType_name(roomtype.getType_name());
			roomtype1.setDescription(roomtype.getDescription());
			roomtype1.setMax_occupancy(roomtype.getMax_occupancy());
			roomtype1.setPrice_per_night(roomtype.getPrice_per_night());
			roomTypeRepository.save(roomtype1);
			return new SuccessResponse("UPDATESUCCESS","roomType updated successfully");
		}
	}
}


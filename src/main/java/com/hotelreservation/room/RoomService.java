package com.hotelreservation.room;

import com.hotelreservation.system.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if (!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Room theRoom = roomRepository.findById(roomId)
                .orElseThrow(()->new ObjectNotFoundException("room", roomId));

        Blob photoBlob = theRoom.getPhoto();
        if(photoBlob != null){
            return photoBlob.getBytes(1, (int) photoBlob.length());//A vérifier plutard
        }
        return null;
    }
    public Room getRoomById(Long roomId) {

        return roomRepository.findById(roomId)
                .orElseThrow(()->new ObjectNotFoundException("room", roomId));
    }

    public void deleteRoom(Long roomId) {
        roomRepository.findById(roomId)
                .orElseThrow(()->new ObjectNotFoundException("room", roomId));
        roomRepository.deleteById(roomId);
    }
    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return roomRepository.findAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType);
    }

    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
      return roomRepository.findById(roomId)
               .map(oldroom -> {
                   oldroom.setRoomType(roomType);
                   oldroom.setRoomPrice(roomPrice);
                   if (photoBytes != null && photoBytes.length > 0) {
                       try {
                           oldroom.setPhoto(new SerialBlob(photoBytes));
                           return this.roomRepository.save(oldroom);
                       } catch (SQLException e) {
                           throw new RuntimeException(e);
                       }
                   }
                   return this.roomRepository.save(oldroom);
               })
                .orElseThrow(()->new ObjectNotFoundException("room", roomId));

    }



}

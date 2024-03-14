package com.hotelreservation.room;

import com.hotelreservation.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    /**
     *
     * @return la liste des types de chambre, sans dupblication
     */
    @Query("SELECT DISTINCT r.roomType FROM Room r")
    List<String> findDistinctRoomTypes();

    /**
     * Récupère les chambre disponible par date et type
     * <p/>
     * si (date d'entrer <= date de sortie et date sortie >= date d'entree), il ya réservation
     * @param checkInDate
     * @param checkOutDate
     * @param roomType
     * @return
     */
    @Query(" SELECT r FROM Room r " +
            " WHERE r.roomType LIKE %:roomType% " +
            " AND r.id NOT IN (" +
            "  SELECT br.room.id FROM BookedRoom br " +
            "  WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" +
            ")")
    List<Room> findAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}

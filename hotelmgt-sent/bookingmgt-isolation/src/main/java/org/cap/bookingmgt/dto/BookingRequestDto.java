package org.cap.bookingmgt.dto;

import java.util.List;

public class BookingRequestDto {

    private int customerId;

    private int roomsCount;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    /**
     * requested rooms id
     */
    private List<Integer>requestedRooms;

    public List<Integer>getRequestedRooms(){
        return requestedRooms;
    }
    public  void setRequestedRooms(List<Integer>rooms){
        this.requestedRooms=rooms;
    }

}

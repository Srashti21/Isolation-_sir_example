package org.cap.bookingmgt.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private int id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    private int customerId;

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId=customerId;
    }

    private int roomsCount;

    public int getRoomsCount(){
        return roomsCount;
    }

    public void setRoomsCount(int count){
        this.roomsCount=count;
    }

    @ElementCollection
    private List<Integer>roomsId;

    public List<Integer>getRoomsId(){
        return roomsId;
    }
    public void setRoomsId(List<Integer>roomsId){
        this.roomsId=roomsId;
    }

}

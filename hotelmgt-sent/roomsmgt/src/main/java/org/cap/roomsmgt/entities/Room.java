package org.cap.roomsmgt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue
    private int id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    private int floorNo;

    public int getFloorNo(){
        return floorNo;
    }

    public void setFloorNo(int floorNo){
        this.floorNo=floorNo;
    }

    private int roomNo;

    public int getRoomNo(){
        return roomNo;
    }

    public void setRoomNo(int roomNo){
        this.roomNo=roomNo;
    }

    private boolean available;

    public boolean getAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available=available;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

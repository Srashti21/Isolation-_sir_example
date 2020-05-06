package org.cap.roomsmgt.dtos;

public class RoomDetailsDto {

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


}

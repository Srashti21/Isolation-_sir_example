package org.cap.roomsmgt.exceptions;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(String msg){
        super(msg);
    }

}

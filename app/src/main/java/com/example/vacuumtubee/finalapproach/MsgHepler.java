package com.example.vacuumtubee.finalapproach;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class MsgHepler {

    String date;
    String message;

    String sendername;
    String senderId;

    @Override
    public String toString() {
        return "MsgHepler{" +
                "date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", sendername='" + sendername + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }

    public MsgHepler(String date, String message, String sendername, String senderId) {
        this.date = date;
        this.message = message;
        this.sendername = sendername;
        this.senderId = senderId;
    }
}


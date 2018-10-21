package models;

import enums.PaymentStatus;

import java.util.Date;
import java.util.List;

public class Booking {

   private int bookingId;
   private int userId;
   private int hotelId;
   private List<Room> bookedRooms;
   private int amount;
   private PaymentStatus status_of_payment;
   private Date bookingTime;
   private Duration duration;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public List<Room> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(List<Room> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus_of_payment() {
        return status_of_payment;
    }

    public void setStatus_of_payment(PaymentStatus status_of_payment) {
        this.status_of_payment = status_of_payment;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}

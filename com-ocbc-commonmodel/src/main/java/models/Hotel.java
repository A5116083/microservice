package models;

import java.util.List;

public class Hotel {
   private int hotelId;
   private String hotelName;
   private Address adress;
   private List<Room> rooms;
   private float rating;
   private Facilities facilities;
   private List<Duration> blockedDates;
}

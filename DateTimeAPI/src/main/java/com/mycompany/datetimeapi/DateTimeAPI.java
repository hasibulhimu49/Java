package com.mycompany.datetimeapi;
import java.util.*;
import java.time.*;
import java.time.temporal.*;

public class DateTimeAPI {
    public static void main(String[] args) {
     LocalTime dl=LocalTime.parse("2022-03-09");
         LocalTime d=dl.plusMinutes(20);
        System.out.println(d);
      
    }
}


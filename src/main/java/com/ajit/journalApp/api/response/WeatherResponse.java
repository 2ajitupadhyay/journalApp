package com.ajit.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Getter
@Setter
public class WeatherResponse{
    // public Location location;
    private Current current;

    @Getter
    @Setter
    public class Current{
        // public int last_updated_epoch;
        // public String last_updated;
        private double temp_c;
        // public double temp_f;
        // private int is_day;
        // public Condition condition;
        // public double wind_mph;
        // public double wind_kph;
        // public int wind_degree;
        // public String wind_dir;
        // public double pressure_mb;
        // public double pressure_in;
        // public double precip_mm;
        // public double precip_in;
        // public int humidity;
        // public int cloud;
        private double feelslike_c;
        // public double feelslike_f;
        // public double windchill_c;
        // public double windchill_f;
        // public double heatindex_c;
        // public double heatindex_f;
        // public double dewpoint_c;
        // public double dewpoint_f;
        // public double vis_km;
        // public double vis_miles;
        // public double uv;
        // public double gust_mph;
        // public double gust_kph;
        // public int short_rad;
        // public int diff_rad;
        // public int dni;
        // public int gti;

        // public class Condition{
        //     public String text;
        //     public String icon;
        //     public int code;
        // }
    }
    
    // public class Location{
    //     public String name;
    //     public String region;
    //     public String country;
    //     public double lat;
    //     public double lon;
    //     public String tz_id;
    //     public int localtime_epoch;
    //     public String localtime;
    // }
}

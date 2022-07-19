package cl.ucn.disc.dsm.pictwin.frontend.model;

import java.time.ZonedDateTime;

public class Pic {

    private long id;
    private String timestamp;
    private Integer dislikes;
    private double latitude;
    private double longitude;
    private double error;
    private Integer views;
    private String name;
    private byte[] picture;

    public byte[] getImage() {
        return picture;
    }

    public long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getDisliked() {
        return dislikes;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getError() {
        return error;
    }

    public String getName() {
        return name;
    }

    public Integer getViews() {
        return views;
    }





}

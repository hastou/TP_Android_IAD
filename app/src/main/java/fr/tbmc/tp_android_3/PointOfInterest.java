package fr.tbmc.tp_android_3;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tbmc on 25/09/2017.
 */

public class PointOfInterest implements Serializable
{

    private String label, description;
    private double latitude, longitude;
    private Date visitedDate;
    private float score;

    public PointOfInterest() {
        this("", "", 0, 0);
    }

    public PointOfInterest(String label, String description, double latitude, double longitude) {
        this(label, description, latitude, longitude, new Date(), 0);
    }

    public PointOfInterest(String label, String description, double latitude, double longitude, Date visitedDate, float score)
    {
        this.label = label;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitedDate = visitedDate;
        this.score = score;
    }

    @Override
    public String toString() {
        return label + " : " + score;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public Date getVisitedDate()
    {
        return visitedDate;
    }

    public void setVisitedDate(Date visitedDate)
    {
        this.visitedDate = visitedDate;
    }

    public float getScore()
    {
        return score;
    }

    public void setScore(float score)
    {
        this.score = score;
    }

}

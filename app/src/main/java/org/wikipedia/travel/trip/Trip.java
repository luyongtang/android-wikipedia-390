package org.wikipedia.travel.trip;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.wikipedia.json.annotations.Required;
import org.wikipedia.travel.database.DeprecatedDateAdapter;
import org.wikipedia.travel.database.DestinationDatabaseTable;
import org.wikipedia.travel.database.TripDatabaseTable;
import org.wikipedia.travel.landmarkpicker.LandmarkCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//This class constitutes a basic Trip class with a list of destinations (also implemented as an inner class below)
//and a departure date
public class Trip {

    //Initialization of database table for trips with single destinations
    public static final TripDatabaseTable DATABASE_TABLE = new TripDatabaseTable();
    //Initialization of database table for destinations with single destinations
    public static final DestinationDatabaseTable DESTINATION_DATABASE_TABLE = new DestinationDatabaseTable();
    //trip id for database storage
    private long id;
    //trip title, also to be used to signfiy the germane list in the database
    @NonNull
    private String title;
    @SuppressWarnings("unused")
    @Nullable
    private List<Destination> destinations;
    @SuppressWarnings("unused")
    @Nullable
    private Destination singleDestination;
    @SuppressWarnings("unused")
    @Nullable
    private Date departureDate;

    //Parametrized constructor for a trip
    public Trip(String title, List<Destination> destinations, Date departureDate) {
        this.title = title;
        this.destinations = destinations;
        this.departureDate = departureDate;
    }

    //Parametrized constructor for a trip, only accounting for one destination (also will be constructor used for storage in database)
    public Trip(String title, Destination singleDestination, Date departureDate) {
        this.title = title;
        this.singleDestination = singleDestination;
        this.departureDate = departureDate;
    }

    //Parametrized constructor for a trip, only accounting for one destination (also will be constructor used for storage in destination database)
    public Trip(Destination singleDestination) {
        this.singleDestination = singleDestination;
    }

    public Trip() {
        this("", new Destination(""), new DeprecatedDateAdapter());
    }

    //Setters and getters for the trip's id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Setters and getters for the trip's title
    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    //Getter method for the list of destinations that the trip will entail
    @NonNull
    public List<Destination> getTripDestinations() {
        return (this.destinations != null) ? this.destinations : Collections.emptyList();
    }

    //Getter method for a single destination that the trip will entail (again this will be used for database storage for the time being)
    @NonNull
    public Destination getDestination() {
        return (this.singleDestination != null) ? this.singleDestination : null;
    }

    //setter method for a single destination
    public void setDestination(Destination singleDestination) {
        this.singleDestination = singleDestination;
    }

    //Method to add a destination to the list of destinations that the trip will entail
    public void addDestination(Destination destination) {
        if (destination != null) {
            destinations.add(destination);
        } else {
            System.out.println("The destination object could not be added.");
        }
    }

    //Getter method for the trip's departure date
    public Date getTripDepartureDate() {
        if (departureDate == null)
            System.out.println("No date has been set.");
        return this.departureDate;
    }

    //Setter method for the trip's departure date
    public void setTripDepartureDate(int year, int month, int date) {
        if (departureDate != null) {
            this.departureDate.setYear(year);
            this.departureDate.setMonth(month);
            this.departureDate.setDate(date);
        } else {
            System.out.println("A valid depature date has not been passed.");
        }
    }

    //Checking if the trip actually has destinations
    public boolean areThereDestinationsSelectedForTrip() {
        return (!(this.destinations.isEmpty()) && this.destinations != null);
    }

    public void setDestinationName(String destinationName) {
        if (this.destinations == null) {
            this.singleDestination = new Destination();
        }
        this.getDestination().setDestinationName(destinationName);
    }

    //Inner Destination Class
    public static class Destination {
        @SuppressWarnings("unused,NullableProblems")
        @Required
        @Nullable
        private List<LandmarkCard> landmarks;
        @SuppressWarnings("unused,NullableProblems")
        @Required
        @NonNull
        private String destinationName;

        public Destination() {
            this(new ArrayList<>(), "");
        }

        //Parametrized constructor for a destination
        public Destination(List<LandmarkCard> placesToVisit, String destinationName) {
            this.landmarks = placesToVisit;
            this.destinationName = destinationName;
        }

        //Parametrized constructor for a destination, for storage in database
        public Destination(String destinationName) {
            this.destinationName = destinationName;
        }

        //Getter method for the list of landmarks that a destination has
        @NonNull
        public List<LandmarkCard> getDestinationPlacesToVisit() {
            return (this.landmarks != null) ? this.landmarks : Collections.emptyList();
        }

        //Method to add a landmark to the list of landmarks that a destination has
        public void addLandmark(LandmarkCard location) {
            if (location != null) {
                landmarks.add(location);
            } else {
                System.out.println("The landmark could not be added.");
            }
        }

        //Getter method for the trip's departure date
        @NonNull
        public String getDestinationName() {
            return this.destinationName;
        }

        //Setter method for the trip's destination name
        public void setDestinationName(@NonNull String destinationName) {
            this.destinationName = destinationName;
        }

        //Checking if the destination actually has places to visit
        public boolean areTherePlacesToVisitForDestination() {
            return (!(this.landmarks.isEmpty()) && this.landmarks != null);
        }

    }

}

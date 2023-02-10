package org.example.train;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int customerId;
    private long totalCostInCents;
    private List<Trip> trips=new ArrayList<>();

    public Customer(int customerId, long totalCostInCents, List<Trip> trips) {
        this.customerId = customerId;
        this.totalCostInCents = totalCostInCents;
        this.trips = trips;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getTotalCostInCents() {
        return totalCostInCents;
    }

    public void setTotalCostInCents(long totalCostInCents) {
        this.totalCostInCents = totalCostInCents;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", totalCostInCents=" + totalCostInCents +
                ", trips=" + trips +
                '}';
    }
}

package org.example.train;

import java.util.Arrays;
import java.util.List;

public enum Zone {
    ZONE1(1,Arrays.asList(Station.A, Station.B)),
    ZONE2(2,Arrays.asList(Station.C, Station.D,Station.E)),
    ZONE3(3,Arrays.asList(Station.C, Station.E,Station.F)),
    ZONE4(4,Arrays.asList(Station.F, Station.G,Station.H,Station.I));
    private List<Station> stations;
    private int numzone;

    Zone(int numzone,List<Station> stations){
        this.stations=stations;
        this.numzone=numzone;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public int getNumzone() {
        return numzone;
    }

    public void setNumzone(int numzone) {
        this.numzone = numzone;
    }
}

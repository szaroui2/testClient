package org.example.train;

public class Trip {
    private String stationStart;
    private String stationEnd;
    private int startedJourneyAt;
    private long  costInCents;
    private int  zoneFrom;
    private int zoneTo;

    public int getPrice(int zoneFrom,int zoneTo){
       if(zoneFrom == 1 && (zoneTo == 2 || zoneTo == 1) || (zoneFrom == 2 && (zoneTo == 2 || zoneTo == 1)))
           return 240;
        if(zoneFrom == 3 && (zoneTo == 4 || zoneTo == 3 ) || (zoneFrom == 4 && (zoneTo == 4 || zoneTo == 3 ) ))
            return 200;
        if((zoneFrom == 3 && (zoneTo == 1 ||  zoneTo == 2)) || ((zoneFrom == 1 || zoneFrom == 2) && zoneTo == 3))
            return 280;
        if((zoneFrom == 4 && (zoneTo == 1 ||  zoneTo == 2)) || ((zoneFrom == 1 || zoneFrom == 2) && zoneTo == 4))
            return 300;
        return 0;
    }



    @Override
    public String toString() {
        return "Trip{" +
                "stationStart='" + stationStart + '\'' +
                ", stationEnd='" + stationEnd + '\'' +
                ", startedJourneyAt=" + startedJourneyAt +
                ", costInCents=" + costInCents +
                ", zoneFrom=" + zoneFrom +
                ", zoneTo=" + zoneTo +
                '}';
    }

    public String getStationStart() {
        return stationStart;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public void setStationEnd(String stationEnd) {
        this.stationEnd = stationEnd;
    }

    public int getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public void setStartedJourneyAt(int startedJourneyAt) {
        this.startedJourneyAt = startedJourneyAt;
    }

    public long getCostInCents() {
        return costInCents;
    }

    public void setCostInCents(long costInCents) {
        this.costInCents = costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(int zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(int zoneTo) {
        this.zoneTo = zoneTo;
    }
}

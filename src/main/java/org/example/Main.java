package org.example;

import org.example.Parser.JsonParser;
import org.example.train.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilePath, outputFilePath;

        if (args.length < 2) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter file path for input data...");
            inputFilePath = scanner.nextLine();

            System.out.println("Enter file path for result output...");

            outputFilePath = scanner.nextLine();
        } else {
            inputFilePath = args[0];
            outputFilePath = args[1];
        }
        Input input = JsonParser.fromFile(inputFilePath);
        Map<Integer, List<Tap>> tapsByCustomer = input.groupTapsByCustomerId(input.getTaps());
        Output output = new Output();
        output.setCustomerSummaries(generateOutputList(tapsByCustomer));
        JsonParser.toFile(outputFilePath, output);
    }

    private static List<Customer> generateOutputList(Map<Integer, List<Tap>> input) {
        List<Customer> outputs = new ArrayList<>();
        for (Map.Entry<Integer, List<Tap>> customerEntry : input.entrySet()) {

            List<Trip> trips = new ArrayList<>();
            int journey = 0;
            String stationStart = "";
            int startedJourneyAt = 0;
            long costInCents = 0;
            int zoneFrom = 0;
            int zoneTo = 0;
            String stationEnd = "";
            long total = 0;
            long entryTime = 0;
            long exitTime = 0;

            for (Tap t : customerEntry.getValue()) {

                if (journey == 0) {
                    stationStart = t.getStation();
                    entryTime = t.getUnixTimestamp();
                    startedJourneyAt = (int) t.getUnixTimestamp();
                    journey++;
                } else if (journey == 1) {
                    exitTime = t.getUnixTimestamp();
                    Trip trip = new Trip();
                    stationEnd = t.getStation();
                    zoneFrom = getNumZoneByStation(Station.valueOf(stationStart)).get(0);
                    zoneTo = getNumZoneByStation(Station.valueOf(stationEnd)).get(0);

                    int nbrZonesStartStation = getNumZoneByStation(Station.valueOf(stationStart)).size();
                    List<Integer> startZones = getNumZoneByStation(Station.valueOf(stationStart));
                    int nbrZonesEndStation = getNumZoneByStation(Station.valueOf(stationEnd)).size();
                    List<Integer> endZones = getNumZoneByStation(Station.valueOf(stationEnd));

                    if (zoneTo > zoneFrom && nbrZonesStartStation > 1) {
                        zoneFrom = startZones.get(1);
                    } else if (zoneTo > zoneFrom && nbrZonesStartStation == 1) {
                        zoneFrom = startZones.get(0);
                    } else if (zoneTo < zoneFrom && nbrZonesEndStation > 1) {
                        zoneTo = endZones.get(1);
                    } else if (zoneTo < zoneFrom && nbrZonesEndStation == 1) {
                        zoneTo = endZones.get(0);
                    }

                    if (NonvalidEntries(customerEntry, stationStart, stationEnd, entryTime, exitTime)) break;

                    costInCents = trip.getPrice(zoneFrom, zoneTo);
                    total += costInCents;
                    trip.setStationStart(stationStart);
                    trip.setStationEnd(stationEnd);
                    trip.setCostInCents(costInCents);
                    trip.setZoneFrom(zoneFrom);
                    trip.setZoneTo(zoneTo);
                    trip.setStartedJourneyAt(startedJourneyAt);
                    trips.add(trip);
                    stationStart = "";
                    startedJourneyAt = 0;
                    costInCents = 0;
                    zoneFrom = 0;
                    zoneTo = 0;
                    stationEnd = "";
                    journey = 0;
                }
            }

            Customer c = new Customer(customerEntry.getKey(), total, trips);
            outputs.add(c);
        }
        return outputs;
    }

    private static boolean NonvalidEntries(Map.Entry<Integer, List<Tap>> customerEntry, String stationStart, String stationEnd, long entryTime, long exitTime) {
        if (stationStart.equals(stationEnd)) {
            System.out.println(String.format("For customer %s the start station %s is equal to the end station %s", customerEntry.getKey(), stationStart, stationEnd));
            return true;
        }
        if (entryTime == exitTime) {
            System.out.println(String.format("For customer %s enty time %s is equal to exit time %s", customerEntry.getKey(), entryTime, exitTime));
            return true;
        }
        if (entryTime > exitTime) {
            System.out.println(String.format("For customer %s enty time %s is greater than exit time %s", customerEntry.getKey(), entryTime, exitTime));
            return true;
        }
        return false;
    }

    public static List<Integer> getNumZoneByStation(Station station) {
        List<Integer> zoneIds = new ArrayList<>();
        for (Zone zone : Zone.values()) {
            if (zone.getStations().contains(station)) {
                zoneIds.add(zone.getNumzone());
            }
        }
        return zoneIds;
    }


}

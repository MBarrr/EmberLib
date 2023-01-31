package mbarrr.github.emberlib.Util;

import org.bukkit.Location;

public class LocationUTIL {

    /**
     * Return whether a location is between two locations
     * @param firstBound First bound
     * @param secondBound Second bound
     * @param location Location to test
     * @return true if location is between the two points, otherwise false
     */
    public static boolean isLocationInBound(Location firstBound, Location secondBound, Location location){

        double x1 = firstBound.getX();
        double y1 = firstBound.getY();
        double z1 = firstBound.getZ();

        double x2 = secondBound.getX();
        double y2 = secondBound.getY();
        double z2 = secondBound.getZ();

        return (location.getX() > x1) && (location.getY() > y1) && (location.getZ() > z1) && (location.getX() < x2) && (location.getY() < y2) && (location.getZ() < z2);
    }

}

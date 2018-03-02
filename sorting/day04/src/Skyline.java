import java.util.ArrayList;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        public int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        Building[] sorted = sortSkyline(B);
        List<Point> p = new ArrayList<>();
        for(Building b : sorted){
            p.add(new Point(b.l, b.h));
            System.out.println(Integer.toString(b.l) + ' ' + Integer.toString(b.h));
        }
        p.add(new Point(sorted[sorted.length-1].r,0));
        return p;
    }

    public static Building[] sortSkyline(Building[] B){
        if (B.length <= 1){
            return B;
        }
        Building[] left = new Building[B.length/2];
        Building[] right = new Building[B.length - left.length];
        System.arraycopy(B, 0, left, 0, left.length);
        System.arraycopy(B, left.length, right, 0, right.length);
        Building[] leftSorted = sortSkyline(left);
        Building[] rightSorted = sortSkyline(right);
        if (leftSorted[0].l <= rightSorted[0].l) {
            return mergeSkylines(leftSorted, rightSorted);
        } else {
            return mergeSkylines(rightSorted, leftSorted);
        }
    }

    public static Building[] mergeSkylines(Building[] left, Building[] right){
        if (left.length == 0){
            return right;
        } else if (right.length == 0) {
            return left;
        }
        List<Building> added = new ArrayList<>();
        int overlapped = 0;
        if(left[left.length - 1].r < right[0].l){
            added.add(new Building(left[left.length - 1].r, right[0].l, 0));
        } else if (left[left.length - 1].r >= right[0].l){
            while(overlapped < right.length && left[left.length - 1].r > right[overlapped].l){
                added.add(right[overlapped]);
                overlapped++;
            }
        }
        ArrayList<Building> mid = new ArrayList<>();
        Building prev = left[left.length - 1];
        int baseH = prev.h;
        int leftMax = prev.r;
        for (Building building : added) {
            if (building.h <= baseH) {
                if (building.r > leftMax) {
                    if (building.h == baseH) {
                        prev.r = building.r;
                    } else {
                        Building b = building;
                        b.l = leftMax;
                        mid.add(b);
                    }
                }
            } else { // building on right is taller than baseH
                prev.r = building.l;
                Building next = new Building(building.r, leftMax, baseH);
                mid.add(building);
                mid.add(next);
                prev = next;
            }
        }
        Building[] merged = new Building[left.length + right.length + mid.size() - overlapped];
        System.arraycopy(left, 0, merged, 0, left.length);
        for (int i = 0; i < mid.size(); i++){
            merged[left.length+i] = mid.get(i);
        }
        System.arraycopy(right, overlapped, merged, left.length+mid.size(), right.length - overlapped);
        List<Building> checked = new ArrayList<>();
        for(Building b : merged){
            if (b.l < b.r){
                checked.add(b);
            }
        }
        return checked.toArray(new Building[checked.size()]);
    }
}

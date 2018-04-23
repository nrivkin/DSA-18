public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long upperLimit = n;
        long lowerLimit = 1;
        while (upperLimit != lowerLimit){
            long check = (lowerLimit + upperLimit) / 2;
            if(isBadVersion.isFailingVersion(check)) upperLimit = check;
            else lowerLimit = check+1;
        }
        return upperLimit;
    }
}

public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {

        return check(0,n,isBadVersion);
    }

    public static long check(long start, long end, IsFailingVersion isBadVersion) {
        long mid = (start + end) / 2;
        if (!isBadVersion.isFailingVersion(mid - 1) && isBadVersion.isFailingVersion(mid)) {
            return mid;
        } else if (isBadVersion.isFailingVersion(mid)) {
            return check(start, mid, isBadVersion);
        } else {
            return check(mid, end, isBadVersion);
        }
    }
}
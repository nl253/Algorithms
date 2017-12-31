package strings;

/**
 * @author nl253
 */

public final class Levenshtein extends BaseEditDistanceAlgorithm {

    public Levenshtein(final String a, final String b) {
        super(a, b);
    }

    @Override
    public int editDistance() {
        return editDistance(a.length(), b.length());
    }

    private int editDistance(final int aLen, final int bLen) {

        if (Math.min(aLen, bLen) == 0) return Math.max(aLen, bLen);

        int cost = 1;

        if (a.charAt(aLen - 1) != b.charAt(bLen - 1)) cost--;

        // @formatter:off
        return Math.min(
                    Math.min(
                        editDistance(aLen - 1, bLen) + 1,
                        editDistance(aLen, bLen - 1) + 1),
                    editDistance(aLen - 1, bLen - 1) + cost
                       );
        // @formatter:on
    }
}

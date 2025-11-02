package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST_PRIZE(6, false, 2_000_000_000L),
    SECOND_PRIZE(5, true, 30_000_000L),
    THIRD_PRIZE(5, false, 1_500_000L),
    FOURTH_PRIZE(4, false, 50_000L),
    FIFTH_PRIZE(3, false, 5_000L),
    NO_PRIZE(0, false, 0L);

    private final int matchCount;
    private final boolean matchBonus;
    private final long prizeMoney;

    Rank(int matchCount, boolean matchBonus, long prizeMoney) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Rank of(int matchCount, boolean matchBonus) {
        for (Rank rank : getValidRanks()) {
            if (rank.matchCount != matchCount) {
                continue;
            }
            if (rank.matchBonus == matchBonus) {
                return rank;
            }
        }

        return NO_PRIZE;
    }

    private static List<Rank> getValidRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != NO_PRIZE)
                .toList();
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

}
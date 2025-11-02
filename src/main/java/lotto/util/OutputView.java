package lotto.util;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    private OutputView() {
    }

    public static void printPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottos(List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.%n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public static void printStatistics(List<Rank> ranks, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + countRank(ranks, Rank.FIFTH_PRIZE) + "개");
        System.out.println("4개 일치 (50,000원) - " + countRank(ranks, Rank.FOURTH_PRIZE) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + countRank(ranks, Rank.THIRD_PRIZE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + countRank(ranks, Rank.SECOND_PRIZE) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + countRank(ranks, Rank.FIRST_PRIZE) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.%n", calculateReturnRate(ranks, purchaseAmount));
    }

    private static long countRank(List<Rank> ranks, Rank target) {
        return ranks.stream()
                .filter(rank -> rank == target)
                .count();
    }

    private static double calculateReturnRate(List<Rank> ranks, int purchaseAmount) {
        long totalPrize = ranks.stream()
                .mapToLong(Rank::getPrizeMoney)
                .sum();

        return (double) totalPrize / purchaseAmount * 100;
    }

}

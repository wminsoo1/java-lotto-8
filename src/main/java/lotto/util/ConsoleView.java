package lotto.util;

import java.util.List;

public class ConsoleView {

    private ConsoleView() {
    }

    public static int readPurchaseAmount() {
        OutputView.printPurchaseAmount();

        while (true) {
            try {
                return InputView.getPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Integer> readWinningNumbers() {
        OutputView.printWinningNumbers();

        while (true) {
            try {
                return InputView.getWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int readBonusNumber(List<Integer> winningNumbers) {
        OutputView.printBonusNumber();

        while (true) {
            try {
                return InputView.getBonusNumber(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

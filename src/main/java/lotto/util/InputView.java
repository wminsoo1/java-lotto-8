package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private InputView() {
    }

    public static int getPurchaseAmount() {
        String input = Console.readLine();
        int amount = parseInteger(input, "[ERROR] 구입 금액은 숫자여야 합니다.");

        validatePurchaseAmount(amount);

        return amount;
    }

    public static List<Integer> getWinningNumbers() {
        String input = Console.readLine();

        List<String> winningNumberStrings = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();

        validateCount(winningNumberStrings);

        return convertAndValidateNumbers(winningNumberStrings);
    }

    public static int getBonusNumber(List<Integer> winningNumbers) {
        String input = Console.readLine();

        int bonusNumber = parseInteger(input, "[ERROR] 보너스 번호는 숫자여야 합니다.");

        validateRange(bonusNumber);
        validateBonusNumberDuplicate(winningNumbers, bonusNumber);

        return bonusNumber;
    }


    private static int parseInteger(String numStr, String errorMessage) {
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validatePurchaseAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은" + LOTTO_PRICE + "으로 나누어 떨어져야 합니다.");
        }
    }

    private static void validateCount(List<String> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + LOTTO_SIZE + "개여야 합니다.");
        }
    }

    private static List<Integer> convertAndValidateNumbers(List<String> numberStrings) {
        List<Integer> numbers = new ArrayList<>();
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (String numStr : numberStrings) {
            int number = parseInteger(numStr, "[ERROR] 당첨 번호는 숫자여야 합니다.");
            validateRange(number);
            validateDuplicate(uniqueNumbers, number);
            numbers.add(number);
        }

        return numbers;
    }

    private static void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이의 숫자여야 합니다.");
        }
    }

    private static void validateDuplicate(Set<Integer> uniqueNumbers, int number) {
        if (uniqueNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        uniqueNumbers.add(number);
    }

    private static void validateBonusNumberDuplicate(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

}

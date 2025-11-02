package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.generator.FixedNumberGenerator;
import lotto.generator.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    private LottoService lottoService;

    private static final List<Integer> WINNING_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    private static final int BONUS_NUMBER = 7;
    private static final int LOTTO_PRICE = 1000;

    @BeforeEach
    void setUp() {
        NumberGenerator fixedGenerator = new FixedNumberGenerator(List.of(10, 20, 30, 40, 41, 42));
        lottoService = new LottoService(fixedGenerator);
    }

    @Test
    @DisplayName("구입 금액에 따라 정확한 수량의 로또를 발행해야 한다")
    void 구입금액에_맞게_로또_발행() {
        int purchaseAmount = 5000;
        int expectedCount = purchaseAmount / LOTTO_PRICE;

        List<Lotto> purchasedLottos = lottoService.purchaseLottos(purchaseAmount);

        assertThat(purchasedLottos).hasSize(expectedCount);
    }

    @Test
    @DisplayName("구매한 로또들을 당첨 번호와 비교하여 정확한 Rank 리스트를 반환해야 한다")
    void 로또_당첨_등수_확인() {
        Lotto lottoThird = new Lotto(List.of(1, 2, 3, 4, 5, 10));
        Lotto lottoFifth = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        Lotto lottoNone = new Lotto(List.of(40, 41, 42, 43, 44, 45));

        List<Lotto> purchasedLottos = List.of(lottoThird, lottoFifth, lottoNone);

        List<Rank> ranks = lottoService.checkLottos(purchasedLottos, BONUS_NUMBER, WINNING_NUMBERS);

        assertThat(ranks).containsExactly(Rank.THIRD_PRIZE, Rank.FIFTH_PRIZE, Rank.NO_PRIZE);
    }

    private Lotto createMockLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}
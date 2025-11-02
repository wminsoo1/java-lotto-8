package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.generator.NumberGenerator;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;

    private final NumberGenerator numberGenerator;

    public LottoService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchaseLottos(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        int count = amount / LOTTO_PRICE;
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(numberGenerator.generate()));
        }
        return lottos;
    }

    public List<Rank> checkLottos(List<Lotto> lottos, int bonusNumber, List<Integer> winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            ranks.add(lotto.match(bonusNumber, winningNumbers));
        }
        return ranks;
    }

}

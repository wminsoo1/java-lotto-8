package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.util.ConsoleView;
import lotto.util.OutputView;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        int amount = ConsoleView.readPurchaseAmount();

        List<Lotto> lottos = lottoService.purchaseLottos(amount);
        OutputView.printLottos(lottos);

        List<Integer> winningNumbers = ConsoleView.readWinningNumbers();

        int bonusNumber = ConsoleView.readBonusNumber(winningNumbers);

        List<Rank> ranks = lottoService.checkLottos(lottos, bonusNumber, winningNumbers);

        OutputView.printStatistics(ranks, amount);
    }

}

package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.LottoSystem;
import lotto.domain.WinningLotto;
import lotto.dto.PurchasedLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoSystem lottoSystem = new LottoSystem(new Buyer(), new WinningLotto());

    public void start() {
        buyLotto();
        generateWinningLotto();
    }

    private void buyLotto() {
        repeatExecutionOnFailure(() -> {
            int purchaseAmount = InputView.enterLottoPurchaseAmount();
            lottoSystem.buyLotto(purchaseAmount);

            PurchasedLotto purchasedLotto = lottoSystem.getPurchasedLotto();
            OutputView.printPurchasedLotto(purchasedLotto);
        });
    }

    private void generateWinningLotto() {
        repeatExecutionOnFailure(() -> {
            List<Integer> winningLottoNumbers = InputView.enterWinningLottoNumbers();
            lottoSystem.generateWinningLotto(winningLottoNumbers);
        });
    }

    private void repeatExecutionOnFailure(Runnable runnable) {
        do {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException exception) {
                exception.printStackTrace(System.out);
            }
        } while (true);
    }
}

package lotto.domain;

import lotto.dto.LottoNumbers;
import lotto.dto.PurchasedLotto;
import lotto.dto.WinningResult;
import lotto.dto.WinningStatus;
import lotto.utils.Calculator;
import lotto.utils.LottoIssuer;
import lotto.utils.WinningJudge;
import lotto.validator.LottoValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LottoSystem {

    private final Buyer buyer;
    private final WinningLotto winningLotto;

    public LottoSystem(Buyer buyer, WinningLotto winningLotto) {
        this.buyer = buyer;
        this.winningLotto = winningLotto;
    }

    public void buyLotto(int purchaseAmount) {
        LottoValidator.validatePurchaseAmount(purchaseAmount);
        int purchasesNumber = getPurchasesNumber(purchaseAmount);
        List<Lotto> lottos = LottoIssuer.issue(purchasesNumber);
        buyer.buyLotto(purchaseAmount, lottos);
    }

    private int getPurchasesNumber(int purchaseAmount) {
        return purchaseAmount / Lotto.PRICE;
    }

    public PurchasedLotto getPurchasedLotto() {
        List<Lotto> purchasedLottos = buyer.getLottos();
        List<LottoNumbers> lottos = purchasedLottos.stream()
                .map(lotto -> new LottoNumbers(lotto.getNumbers()))
                .toList();
        return new PurchasedLotto(purchasedLottos.size(), lottos);
    }

    public void generateWinningLotto(List<Integer> winningLottoNumbers) {
        winningLotto.generate(winningLottoNumbers);
    }

    public void inputBonusNumber(int bonusNumber) {
        LottoValidator.validateBonusNumber(bonusNumber, winningLotto.getNumbers());
        winningLotto.updateBonusNumber(bonusNumber);
    }

    public WinningResult getWinningResult() {
        List<WinningStatus> winningStatuses = getWinningStatus();
        double returnRate = Calculator.calculateReturnRate(winningStatuses, buyer.getPurchaseAmount());
        return new WinningResult(winningStatuses, returnRate);
    }

    private List<WinningStatus> getWinningStatus() {
        Map<WinningType, Integer> winningTypeCount = extractWinningTypeCount();
        Set<WinningType> winningTypes = winningTypeCount.keySet();
        return winningTypes.stream()
                .map(winningType -> new WinningStatus(winningType, winningTypeCount.get(winningType)))
                .toList();
    }

    private Map<WinningType, Integer> extractWinningTypeCount() {
        Map<WinningType, Integer> winningTypeCount = initializeWinningCountMap();

        buyer.getLottos().stream()
                .map(lotto -> WinningJudge.judgeWinningType(lotto, winningLotto))
                .flatMap(Optional::stream)
                .forEach(winningType -> winningTypeCount.put(winningType, winningTypeCount.get(winningType) + 1));
        return winningTypeCount;
    }

    private static Map<WinningType, Integer> initializeWinningCountMap() {
        Map<WinningType, Integer> winningCount = new HashMap<>();
        for (WinningType winningType : WinningType.values()) {
            winningCount.put(winningType, 0);
        }
        return winningCount;
    }
}

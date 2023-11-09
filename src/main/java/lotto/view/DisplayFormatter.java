package lotto.view;

import lotto.domain.WinningType;
import lotto.dto.LottoNumbers;
import lotto.dto.WinningStatus;

import java.util.stream.Collectors;

public class DisplayFormatter {

    private DisplayFormatter() {
    }

    public static String formatLottoNumbers(LottoNumbers lottoNumbers) {
        return lottoNumbers.numbers()
                .stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static String formatWinningStatus(WinningStatus winningStatus) {
        WinningType winningType = winningStatus.winningType();
        int matchesCount = winningType.getMatchesCount();
        int reward = winningType.getReward();
        boolean isBonus = winningType.isBonus();
        int count = winningStatus.count();

        return String.format("%d개 일치%s (%,d원) - %d개", matchesCount, bonusMatchMessage(isBonus), reward, count);
    }

    private static String bonusMatchMessage(boolean isBonus) {
        if (isBonus) {
            return ", 보너스 볼 일치";
        }
        return "";
    }
}

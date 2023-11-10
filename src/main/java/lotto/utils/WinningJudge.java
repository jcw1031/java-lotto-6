package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningType;

import java.util.List;
import java.util.Optional;

public class WinningJudge {

    private WinningJudge() {
    }

    public static Optional<WinningType> judgeWinningType(Lotto lotto, WinningLotto winningLotto) {
        List<Integer> numbers = lotto.getNumbers();
        int matchesCount = countMatches(numbers, winningLotto);
        return Optional.ofNullable(
                WinningType.of(matchesCount, isBonusMatch(numbers, winningLotto.getBonusNumber()))
        );
    }

    private static int countMatches(List<Integer> numbers, WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private static boolean isBonusMatch(List<Integer> numbers, int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}

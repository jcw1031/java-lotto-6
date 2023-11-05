package lotto.domain;

import lotto.MethodSourceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoSystemTest extends MethodSourceTest {

    Buyer buyer;
    WinningLotto winningLotto;
    LottoSystem lottoSystem;

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
        winningLotto = new WinningLotto();
        lottoSystem = new LottoSystem(buyer, winningLotto);
    }

    @DisplayName("사용자가 입력한 구매 금액에 맞는 개수만큼 로또를 발행한다.")
    @ParameterizedTest
    @ValueSource(ints = {1_000, 5_000, 10_000, 100_000})
    void buyLottos(int purchaseAmount) {
        // given & when
        lottoSystem.buyLotto(purchaseAmount);
        List<Lotto> lottos = buyer.getLottos();

        // then
        assertThat(lottos).isNotEmpty();
        assertThat(lottos).hasSize(purchaseAmount / Lotto.PRICE);
    }

    @DisplayName("당첨 로또를 생성한다.")
    @Test
    void generateWinningLotto() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        lottoSystem.generateWinningLotto(numbers);

        // when
        List<Integer> winningLottoNumbers = winningLotto.numbers();

        // then
        assertThat(winningLottoNumbers).isNotEmpty();
        assertThat(winningLottoNumbers).isEqualTo(numbers);
    }

    @DisplayName("보너스 번호를 입력한다.")
    @Test
    void updateBonusNumber() {
        // given
        int number = 10;
        lottoSystem.generateWinningLotto(List.of(1, 2, 3, 4, 5, 6));
        lottoSystem.inputBonusNumber(number);

        // when
        int bonusNumber = winningLotto.bonusNumber();

        // then
        assertThat(bonusNumber).isEqualTo(number);
    }
}
package lotto;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호는 오름차순으로 정렬되어 저장된다.")
    @Test
    void 로또_번호는_정렬되어_저장된다() {
        Lotto lotto = new Lotto(List.of(6, 1, 3, 2, 5, 4));
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @DisplayName("당첨 번호와 보너스 번호를 비교하여 등수를 반환한다.")
    @Test
    void 당첨번호와_보너스번호_비교하여_Rank_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 7);
        int bonusNumber = 7;

        Rank rank = lotto.match(bonusNumber, winning);

        assertThat(rank).isEqualTo(Rank.SECOND_PRIZE);
    }

    @DisplayName("아무 번호도 일치하지 않으면 NO_PRIZE를 반환한다.")
    @Test
    void 일치하는_번호가_없으면_NO_PRIZE_반환한다() {
        Lotto lotto = new Lotto(List.of(10, 11, 12, 13, 14, 15));
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Rank rank = lotto.match(bonusNumber, winning);

        assertThat(rank).isEqualTo(Rank.NO_PRIZE);
    }

}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("6개 번호가 모두 일치하면 FIRST_PRIZE를 반환한다.")
    @Test
    void 여섯개_번호_일치하면_FIRST_PRIZE_반환() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST_PRIZE);
    }

    @DisplayName("5개 번호 + 보너스 번호 일치하면 SECOND_PRIZE를 반환한다.")
    @Test
    void 다섯개_번호_보너스_일치하면_SECOND_PRIZE_반환() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND_PRIZE);
    }

    @DisplayName("5개 번호만 일치하면 THIRD_PRIZE를 반환한다.")
    @Test
    void 다섯개_번호만_일치하면_THIRD_PRIZE_반환() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD_PRIZE);
    }

    @DisplayName("4개 번호 일치하면 FOURTH_PRIZE를 반환한다.")
    @Test
    void 네개_번호_일치하면_FOURTH_PRIZE_반환() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH_PRIZE);
    }

    @DisplayName("3개 번호 일치하면 FIFTH_PRIZE를 반환한다.")
    @Test
    void 세개_번호_일치하면_FIFTH_PRIZE_반환() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH_PRIZE);
    }

    @DisplayName("그 외에는 NO_PRIZE를 반환한다.")
    @Test
    void 기타_경우_NO_PRIZE_반환() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.NO_PRIZE);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.NO_PRIZE);
        assertThat(Rank.of(5, true)).isNotEqualTo(Rank.THIRD_PRIZE); // SECOND_PRIZE와 다른지 확인
    }

}
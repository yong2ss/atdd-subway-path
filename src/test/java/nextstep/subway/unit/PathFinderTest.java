package nextstep.subway.unit;

import nextstep.subway.domain.*;
import nextstep.subway.exception.CustomException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PathFinderTest {

    private Line 이호선;
    private Line 삼호선;

    private Section 강남역_역삼역;
    private Section 역삼역_선릉역;

    private Section 선정릉역_선릉역;

    private Station 강남역;
    private Station 역삼역;
    private Station 선릉역;
    private Station 선정릉역;

    @BeforeEach
    void init() {
        이호선 = new Line("2호선", "green");
        삼호선 = new Line("3호선", "blue");

        강남역 = new Station(1L, "강남역");
        역삼역 = new Station(2L, "역삼역");
        선릉역 = new Station(3L, "선릉역");
        선정릉역 = new Station(4L, "선정릉역");

        강남역_역삼역 = new Section(강남역, 역삼역, 10);
        역삼역_선릉역 = new Section(역삼역, 선릉역, 15);
        선정릉역_선릉역 = new Section(선정릉역, 선릉역, 7);

        이호선.addSection(강남역_역삼역);
        이호선.addSection(역삼역_선릉역);
        삼호선.addSection(선정릉역_선릉역);
    }

    /**
     *                 선정릉역
     *                  |
     * 강남역 -- 역삼역 -- 선릉역
     */
    @Test
    void getShortestPath_with_DijkstraPathFinder() {
        PathFinder pathFinder = new DijkstraPathFinder(Lists.newArrayList(이호선, 삼호선));

        var graph = pathFinder.getPath(역삼역.getId(), 선정릉역.getId());

        assertThat(graph.getVertexList()).hasSize(3);
        assertThat((int)graph.getWeight()).isEqualTo(22);
    }

    /**
     *                 선정릉역
     *                  |
     * 강남역 -- 역삼역 -- 선릉역
     */
    @Test
    void getShortestPath_with_KshortestPathFinder() {
        PathFinder pathFinder = new KshortestPathFinder(Lists.newArrayList(이호선, 삼호선));

        var graph = pathFinder.getPath(역삼역.getId(), 선정릉역.getId());

        assertThat(graph.getVertexList()).hasSize(3);
        assertThat((int)graph.getWeight()).isEqualTo(22);
    }
}
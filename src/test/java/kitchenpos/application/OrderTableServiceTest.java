package kitchenpos.application;

import kitchenpos.domain.OrderRepository;
import kitchenpos.domain.OrderStatus;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.OrderTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static kitchenpos.fixture.TestFixture.TEST_ORDER_TABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class OrderTableServiceTest {
    @InjectMocks
    private OrderTableService orderTableService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderTableRepository orderTableRepository;

    @Test
    void 새로운_주문_테이블을_등록한다() {
        // given
        OrderTable orderTableRequest = TEST_ORDER_TABLE();
        given(orderTableRepository.save(any(OrderTable.class))).willReturn(orderTableRequest);

        // when
        OrderTable orderTable = orderTableService.create(orderTableRequest);

        // then
        verify(orderTableRepository, times(1)).save(any(OrderTable.class));
        assertThat(orderTable.getNumberOfGuests()).isZero();
        assertThat(orderTable.isOccupied()).isFalse();
    }

    @Test
    void 이름은_비어있을_수_없다() {
        // given
        OrderTable nameNull = TEST_ORDER_TABLE();
        OrderTable nameEmpty1 = TEST_ORDER_TABLE();

        // when
        nameNull.setName(null);
        nameEmpty1.setName("");

        // then
        assertThatThrownBy(() -> orderTableService.create(nameNull))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> orderTableService.create(nameEmpty1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 테이블에_손님을_채운다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();
        given(orderTableRepository.findById(orderTableId)).willReturn(Optional.of(orderTable));

        // when
        OrderTable sitTable = orderTableService.sit(orderTableId);

        // then
        verify(orderTableRepository, times(1)).findById(orderTableId);
        assertThat(sitTable.isOccupied()).isTrue();
    }

    @Test
    void 테이블에_있던_손님이_나가고_다시_손님을_받을_수_있는_상태로_변경한다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();
        given(orderTableRepository.findById(orderTableId)).willReturn(Optional.of(orderTable));
        given(orderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED))
                .willReturn(false);

        // when
        OrderTable clearedTable = orderTableService.clear(orderTableId);

        // then
        verify(orderTableRepository, times(1)).findById(orderTableId);
        verify(orderRepository, times(1))
                .existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED);
        assertThat(clearedTable.isOccupied()).isFalse();
        assertThat(clearedTable.getNumberOfGuests()).isZero();
    }

    @Test
    void 주문이_완료되지_않았다면_테이블을_정리할_수_없다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();
        given(orderTableRepository.findById(orderTableId)).willReturn(Optional.of(orderTable));


        // when
        given(orderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED))
                .willReturn(true);
        // then
        assertThatThrownBy(() -> orderTableService.clear(orderTableId))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 테이블에_인원_수를_변경한다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();
        given(orderTableRepository.findById(orderTableId)).willReturn(Optional.of(orderTable));

        // when
        orderTable.setOccupied(true);
        orderTable.setNumberOfGuests(15);
        OrderTable clearedTable = orderTableService.changeNumberOfGuests(orderTableId, orderTable);

        // then
        verify(orderTableRepository, times(1)).findById(orderTableId);
        assertThat(clearedTable.isOccupied()).isTrue();
        assertThat(clearedTable.getNumberOfGuests()).isEqualTo(15);
    }

    @Test
    void 변경할_인원_수는_0명_이상이어야_한다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();

        // when
        orderTable.setNumberOfGuests(-1);

        // then
        assertThatThrownBy(() -> orderTableService.changeNumberOfGuests(orderTableId, orderTable))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사용가능한_테이블만_인원_수를_변경_할_수_있다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        UUID orderTableId = orderTable.getId();
        given(orderTableRepository.findById(orderTableId)).willReturn(Optional.of(orderTable));

        // when
        orderTable.setNumberOfGuests(13);
        orderTable.setOccupied(false);

        // then
        assertThatThrownBy(() -> orderTableService.changeNumberOfGuests(orderTableId, orderTable))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 모든_주문_테이블_정보를_가져온다() {
        // given
        OrderTable orderTable = TEST_ORDER_TABLE();
        given(orderTableRepository.findAll()).willReturn(List.of(orderTable, orderTable));

        // when
        List<OrderTable> orderTables = orderTableService.findAll();

        // then
        verify(orderTableRepository, times(1)).findAll();
        assertThat(orderTables).isNotNull();
        assertThat(orderTables).hasSize(2);
    }
}
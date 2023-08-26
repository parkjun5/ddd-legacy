# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```

## 요구 사항

- 메뉴 그룹
  - [ ] 새로운 메뉴 그룹를 등록한다.
    - [ ] 메뉴 그룹의 이름은 빈값일 수 없다.
  - [ ] 모든 메뉴 그룹 정보를 가져온다.

- 메뉴
    - [ ] 새로운 메뉴를 등록한다.
      - [ ] 메뉴의 가격은 0원 이상이여야 한다.
      - [ ] 메뉴 생성시 올바른 메뉴 그룹을 설정해주어야 한다.
      - [ ] 메뉴 생성시 상품을 등록해야한다.
      - [ ] 메뉴 생성시 상품들은 등록된 상품이어야 한다.
      - [ ] 메뉴의 상품들은 수량은 0개 이상이어야 한다.
      - [ ] 메뉴의 가격은 포함된 상품들의 각 금액(상품 가격 X 수량)의 합보다 높을 수 없다.
      - [ ] 이름은 비어있을 수 없다.
      - [ ] 메뉴의 이름은 외설적이거나 욕설이 포함된 영어 이름이면 안된다.
    - [ ] 기존 메뉴의 가격을 수정한다.
      - [ ] 변경할 가격은 0원 이상이어야 한다.
      - [ ] 변경할 메뉴의 가격은 포함된 상품들의 각 금액(상품 가격 X 수량)의 합보다 높을 수 없다.
    - [ ] 기존 메뉴가 사용자에게 보이도록 활성화한다.
      - [ ] 활성화 시킬 메뉴의 가격은 포함된 상품들의 각 금액(상품 가격 X 수량)의 합보다 높을 수 없다.
    - [ ] 기존 메뉴가 사용자에게 보이지 않도록 비활성화한다.
    - [ ] 모든 메뉴 정보를 가져온다.
  
- 주문 테이블
    - [ ] 새로운 주문 테이블을 등록한다.
      - [ ] 이름은 비어있을 수 없다.
    - [ ] 테이블에 손님을 채운다. 
      - [ ] 이 테이블은 더 이상 손님을 받을 수 없다.
    - [ ] 테이블에 있던 손님이 나가고 다시 손님을 받을 수 있는 상태로 변경한다.
      - [ ] 주문이 완료되지 않았다면 테이블을 정리할 수 없다.
    - [ ] 테이블에 인원 수를 변경한다.
      - [ ] 변경할 인원 수는 0명 이상이어야 한다.
      - [ ] 사용가능한 테이블만 인원 수를 변경 할 수 있다.
    - [ ] 모든 주문 테이블 정보를 가져온다.

- 주문
    - [ ] 새로운 주문을 등록한다.
      - [ ] 주문 타입은 배달, 먹고가기, 포장 중 하나이어야 한다.
      - [ ] 주문의 주문 내역은 비어있을 수 없다.
      - [ ] 주문 내역에 포함된 메뉴들은 존재하는 메뉴들이어야 한다.
      - [ ] 주문 타입이 먹고가는 것이 아니라면 주문 내역의 인원수가 0명 이상이어야 한다. 
      - [ ] 주문 내역들의 메뉴들은 활성화 된 메뉴들이어야 한다.
      - [ ] 주문 내역의 가격과 메뉴의 가격이 일치하여야 한다.
      - [ ] 주문 타입이 배달이라면 주소가 적혀있어야 한다.
      - [ ] 주문 타입이 먹고가는 것이라면 사용 가능한 테이블이 지정되어야 한다.
    - [ ] 주문을 수락한다.
      - [ ] 주문의 상태가 대기중이어야 한다.
      - [ ] 배달 주문이라면 배달을 요청한다.
    - [ ] 주문을 조리 완료하고 제공 상태로 변경한다.
      - [ ] 주문의 상태가 수락된 상태이어야 한다.
    - [ ] 주문의 배달을 시작한다.
      - [ ] 주문 타입이 배달이어야 한다.
      - [ ] 주문 상태가 제공 상태이어야 한다.
    - [ ] 주문의 배달을 완료한다.
      - [ ] 주문의 상태가 배달중이어야 한다.
    - [ ] 주문을 완료한다.
      - [ ] 주문이 배달이라면 주문 상태가 배달 완료이어야한다.
      - [ ] 주문이 먹고가는거나 포장이라면 주문 상태가 제공된 상태이여야한다.
      - [ ] 주문 타입이 먹고가는 타입이라면 테이블을 정리한다.
    - [ ] 모든 주문 정보를 가져온다.

- 상품
    - [ ] 새로운 상품을 등록한다.
      - [ ] 가격은 0원 이상이어야한다.
      - [ ] 이름은 비어있을 수 없으며, 외설적이거나 욕설이 포함된 이름은 사용 할 수 없다.
    - [ ] 상품의 가격을 수정한다.
      - [ ] 변경할 가격을 0원 이상이어야 한다.
      - [ ] 상품의 가격을 변경하면서 연관된 모든 메뉴에도 전부 변경된 가격이 적용되어야 한다
      - [ ] 가격 변경 후, 메뉴의 가격이 (메뉴에 포함된 상품들의 가격 x 개수) 총 합보다 높다면 메뉴를 비활성화 한다.
    - [ ] 모든 상품 정보를 가져온다.
  
## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | menu  | 메뉴는 노출 여부, 이름, 가격의 정보를 가지고 있으며, 메뉴 그룹 하위 타입이다.  |
| 메뉴 그룹 | menu_group  | 메뉴 그룹는 이름을 가지고 있으며, 메뉴 그룹 하위 타입이다.  |
| 주문 상태 | OrderStatus  | WAITING, ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED  |
| 주문 타입 | OrderType  | DELIVERY, TAKEOUT, EAT_IN  |
| 메뉴 상품 | menu_product  | 메뉴 상품은 메뉴에 포함된 상품 정보를 명시하고 있다.   |
| 주문 전체 아이템 | order_line_item  | 주문 전체 아이템은 주문에 포함된 아이템의 정보(메뉴, 수량, 메뉴 가격) 을 명시하고 있다. |
| 주문 테이블 | order_table  |주문 테이블(먹고 갈때 사용하는 듯 함). 테이블마다 최대 인원수와 꽉차있는지를 명시한다. |
| 주문 | orders  |주문 정보(배달 장소, 시각, 상태, 타입)을 명시하고 있다.|
| 상품 | product  | 상품 정보(이름, 단일 가격)을 명시하고 있다. |

## 모델링
![modeling drawio](https://github.com/next-step/ddd-legacy/assets/58926619/59ef3f82-8699-4401-a4d6-cd737515ade2)


# 🚀 1단계 - 문자열 덧셈 계산기

### 요구 사항

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw 한다.
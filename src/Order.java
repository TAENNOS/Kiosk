import java.util.*;

public class Order {
    private List<Product> orderList = new ArrayList<>(); // 장바구니
    private HashSet<Product> soldList = new HashSet<>(); // 판매목록
    private int waitlistnum = 0; // 대기번호
    private double income; // 총 수입

    public void addShoppingBag(Product product) throws InterruptedException { //장바구니 담기
        Scanner sc = new Scanner(System.in);
        if (product.getOptionPrice() != 0) {
            product.information();
            Product sizeUpProduct = new Product(product.getMenuName() + "(double)", product.getDescription(), product.getOptionPrice(), product.getCategory(), product.getOptionPrice());
            System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
            System.out.println("1. Single(W " + product.getPrice() + ")\t2. Double(W " + sizeUpProduct.getPrice() + ")");
            int optionInput = sc.nextInt();
            if (optionInput == 1) { // 기본 옵션
                product.information();
                addCart();

                int input = sc.nextInt();
                if (input == 1) {
                    orderList.add(product);
                    System.out.println(product.getMenuName() + " 가 장바구니에 추가되었습니다.");
                } else if (input == 2) {
                    System.out.println("장바구니에 추가하지 않았습니다.");
                } else {
                    Kiosk.wrongInput();
                }

            } else if (optionInput == 2) { // 추가 옵션
                sizeUpProduct.information();
                addCart();

                int input = sc.nextInt();
                if (input == 1) {
                    orderList.add(sizeUpProduct);
                    System.out.println(sizeUpProduct.getMenuName() + " 가 장바구니에 추가되었습니다.");
                } else if (input == 2) {
                    System.out.println("장바구니에 추가하지 않았습니다.");
                } else {
                    Kiosk.wrongInput();
                }
            } else {
                Kiosk.wrongInput();
            }

        } else { // 옵션 가격이 없는 경우
            product.information();
            addCart();

            int input = sc.nextInt();
            if (input == 1) {
                orderList.add(product);
                System.out.println(product.getMenuName() + " 가 장바구니에 추가되었습니다.");
            } else if (input == 2) {
                System.out.println("장바구니에 추가하지 않았습니다.");
            } else {
                Kiosk.wrongInput();
            }
        }
    }

    public void startOrder() throws InterruptedException { // 주문 시작
        if (!orderList.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            int confirmOrder;
            int EA;
            double totalprice = 0;
            System.out.println("아래와 같이 주문하시겠습니까?");
            System.out.println("[ Orders ]");
            HashSet<Product> orderListSet = new HashSet<>(orderList);
            for (Product product : orderListSet) {
                EA = Collections.frequency(orderList, product);
                product.information(EA);
                totalprice += product.getPrice() * EA;
            }
            totalprice = Math.round((totalprice * 100)) / 100.0;
            System.out.println("[ Total ]");
            System.out.println("W " + totalprice + "\n");
            System.out.println("1. 주문\t2. 메뉴판");

            confirmOrder = sc.nextInt();
            if (confirmOrder == 1) {
                income += totalprice;
                soldList.addAll(orderList);
                completeOrder(); // 주문 완료
            } else if (confirmOrder == 2) {
                System.out.println("주문이 완료되지 않았습니다.");
                System.out.println("(초기 메뉴판으로 돌아갑니다.)");
                Kiosk.countDown();
            } else {
                Kiosk.wrongInput();
            }

        } else {
            emptyCart();
        }
    }
    public void completeOrder() throws InterruptedException { // 주문 완료
        orderList.clear();
        waitlistnum++;
        System.out.println("주문이 완료되었습니다!");
        System.out.println("대기번호는 [ " + waitlistnum + " ] 번 입니다.");
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        Kiosk.countDown();
    }
    public void cancelOrder() throws InterruptedException { // 주문 취소
        Scanner sc = new Scanner(System.in);
        System.out.println("주문을 취소하겠습니까?");
        System.out.println("1. 예\t2. 아니오");

        int cancelInput = sc.nextInt();
        if (cancelInput == 1) {
            orderList.clear();
            System.out.println("진행하던 주문이 취소되었습니다. 이전 화면으로 돌아갑니다.");
            Kiosk.countDown();
        } else if (cancelInput == 2) {
            System.out.println("주문이 취소되지 않았습니다. 이전 화면으로 돌아갑니다.");
            Kiosk.countDown();
        } else {
            Kiosk.wrongInput();
        }
    }
    public void emptyCart() throws InterruptedException { // 추가하지 않은경우
        System.out.println("장바구니가 비었습니다.");
        System.out.println("(초기 화면으로 돌아갑니다.)");
        Kiosk.countDown();
    }
    public void addCart() { // 장바구니 추가
        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1.확인\t2.취소");
    }

    public HashSet<Product> getSoldList() {
        return soldList;
    }

}

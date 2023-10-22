public class Menu {
    private String MenuName;
    private String Description;

    public Menu(String menuName, String description) {
        this.MenuName = menuName;
        this.Description = description;
    }
    public void information() {
        System.out.printf("%-15s | %s\n ", MenuName, Description); // 상위 메뉴판 출력
    }

    public String getMenuName() {
        return MenuName;
    }

    public String getDescription() {
        return Description;
    }

}

import Menu.CreateMenuInit;
import Menu.CreateMenuList;

public class Main {
    public static void main(String[] args) {
        new CreateMenuList().createMenuList();
        new CreateMenuInit().initialize();
    }
}
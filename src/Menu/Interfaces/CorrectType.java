package Menu.Interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public interface CorrectType{
    default <T extends Number> T getValue(Scanner s, Class<T> type) {
        do {
            if (!s.hasNext()) {
                s.nextLine();
                System.out.println("Invalid input, please try again.");
                continue;
            }
            try {
                return type.getConstructor(String.class).newInstance(s.nextLine());
            } catch (
                    NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e
            ) {
                System.out.println("Invalid input, please try again.");
                s.nextLine();
            }
        } while (true);
    }
}

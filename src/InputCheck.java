import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class InputCheck {
    /**
     * Регулярное выражение для типа int
     */
    private static final String REGEX1 = "^[0-9]\\d*$";

    /**
     * Скомпилированный шаблон регулярного выражения для int
     */
    private static final Pattern PATTERN1 = Pattern.compile(REGEX1);

    /**
     * Метод, проверяющий содержимое строки на соответствие типу int
     * @param number Число в строковом представлении
     * @return Число int, соответствующее регулярному выражению
     */
    public static int checkInt(String number) {
        Matcher matcher = PATTERN1.matcher(number);
        if(!(matcher.matches())) {
            throw new Error("Некорректный ввод! Разрешено только целое положительное число");
        }
        try {
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e) {
            throw new Error("Введенное число превышает верхнюю границу int");
        }
    }

    /**
     * Метод проверяет вводимую границу на корректность
     * @param index Индекс одной из границ массива
     * @param size Размер старого массива, из которого формируется новый
     * @return Индекс границы, если она не находится за границами старого массива
     */
    public static int checkIndex(String index, int size) {
        Matcher matcher = PATTERN1.matcher(index);
        if(!(matcher.matches())){
            throw new Error("Некорректный ввод! Разрешено только целое положительное число");
        }
        try {
            if(Integer.parseInt(index) > size-1) {
                throw new Error("Некорректный ввод! Индекс находится за границами массива!");
            }
            return Integer.parseInt(index);
        }
        catch(NumberFormatException e) {
            throw new Error("Введенное число превышает верхнюю границу int");
        }
    }

    /**
     * Метод проверяет размерность массива на корректность
     * @param number Размерность массива в строковом представлении
     * @return Размерность массива
     */
    public static int correctSize(String number) {
        int size = checkInt(number);
        if (size==0) throw new Error("Размерность массива не может равняться 0!");
        return size;
    }
}

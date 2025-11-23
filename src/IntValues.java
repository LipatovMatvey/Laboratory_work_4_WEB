import java.util.*;

public class IntValues {
    /**
     * Целочисленный массив
     */
    private static ArrayList<IntValues> numbers = new ArrayList<>();

    /**
     * Объект компаратора для сравнения элементов списка
     */
    private static final Comparator<IntValues> byValue = Comparator.comparing(IntValues::getValue);

    /**
     * Числовое значение
     */
    private Integer value;

    /**
     * Экземпляр класса Random для генерации случайных чисел
     */
    private static Random random = new Random();

    /**
     * Конструктор по умолчанию
     */
    public IntValues() {
        this.value = 0;
    }

    /**
     * Конструктор с параметром
     * @param maxNumber Верхняя граница диапазона генерации чисел
     */
    public IntValues(int maxNumber) {
        this.value = random.nextInt(maxNumber);
        numbers.add(this);
    }

    /**
     * Метод заполняет массив значениями
     * @param size Размер массива
     * @param maxNumber Верхняя граница диапазона генерации чисел
     */
    public static void addToArray(int size, int maxNumber) {
        numbers.clear();
        for (int i = 0; i < size; i++) {
            new IntValues(maxNumber);
        }
    }

    /**
     * Геттер поля size
     * @return Значение поля size
     */
    public static int getSize() {
        return numbers.size();
    }

    /**
     * Метод переписывает часть элементов из массива в новый массив
     * @param firstIndex Индекс элемента, с которого будет переписано содержимое списка
     * @param lastIndex Индекс элемента, до которого будет переписано содержимое списка
     * @return Новый массив в виде строки
     */
    public static String newSubarray(int firstIndex, int lastIndex) {
        List<IntValues> subList = numbers.subList(firstIndex, lastIndex);
        String result = "";
        for (IntValues num : subList ) {
            result += num.getValue() + " ";
        }
        return result.trim();
    }

    /**
     * Геттер поля value
     * @return Поле value
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Метод определяет уникальные числа в массиве
     * @return Массив уникальных чисел в виде строки
     */
    public static String getUniqueNumbers() {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (IntValues number : numbers) {
            if (map.containsKey(number.getValue())) {
                map.put(number.getValue(), map.get(number.getValue()) + 1);
            }
            else {
                map.put(number.getValue(), 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        return list.toString();
    }

    /**
     * Метод определяет кол-во каждого неуникального числа в массиве
     * @return Строка, содержащая информацию о кол-ве повторений неуникальных чисел в массиве
     */
    public static String getNumberCounts() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (IntValues number : numbers) {
            if (map.containsKey(number.getValue())) {
                map.put(number.getValue(), map.get(number.getValue()) + 1);
            }
            else {
                map.put(number.getValue(), 1);
            }
        }
        String concatResult = "";
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                concatResult += "число: " + entry.getKey() + " кол-во повторений: " + entry.getValue() + "\n";
            }
        }
        if (concatResult.isEmpty()) {
            concatResult = "Повторяющихся чисел нет";
        }
        return concatResult.trim();
    }

    /**
     * Метод, собирающий строку с элементами массива для вывода в терминал
     * @return Строка с элементами массива
     */
    public static String printNumbers() {
        String concatResult = "";
        IntValuesIterator iterator = new IntValuesIterator(numbers);
        while (iterator.hasNext()) {
            IntValues number = iterator.next();
            concatResult += (number.getValue()) + (" ");
        }
        return concatResult;
    }

    /**
     * Метод сортировки массива
     */
    public static void sortList() {
        numbers.sort(byValue);
    }

    /**
     * Метод находит минимальное, максимальное и сумму всех чисел массива
     * @return Массив с 3 характеристиками в виде строки
     */
    public static String infoNumbers() {
        ArrayList<Integer> resultArray = new ArrayList<>();
        resultArray.add(Collections.min(numbers, byValue).getValue());
        resultArray.add(Collections.max(numbers, byValue).getValue());
        int sumArray = numbers.stream().mapToInt(IntValues::getValue).sum();
        resultArray.add(sumArray);
        String result = "";
        for (Integer num : resultArray) {
            result += num + " ";
        }
        return result.trim();
    }

    /**
     * Метод создает новый массив из четных чисел существующего
     * @return Массив с четными числами в виде строки
     */
    public static String evenNumbers() {
        ArrayList<Integer> resultArray = new ArrayList<>();
        for(IntValues n : numbers) {
            if (n.getValue() % 2 == 0) resultArray.add(n.getValue());
        }
        return resultArray.toString();
    }

    /**
     * Метод удаляет из массива все нечетные числа
     */
    public static void deleteOddNumbers() {
        numbers.removeIf(n -> n.getValue() % 2 != 0);
    }

    /**
     * Метод ищет заданное число в массиве
     * @param number Искомое число
     * @return Индекс числа в строковом представлении, если найдено, если не найдено - "Такого числа нет в массиве"
     */
    public static String findNumber(int number) {
        for (int i = 0; i<numbers.size(); i++) {
            if (numbers.get(i).getValue() == number) {
                return "Индекс искомого числа: " + i;
            }
        }
        return "Такого числа нет в массиве";
    }

    /**
     * Метод формирует HashMap с результатами работы с массивом для дальнейшей ее записи в файл
     * @return Map с результатами
     */
    public static HashMap<String, String> saveToMap(String fileName) {
        HashMap<String, String> results = new HashMap<>();
        results.put("Исходный массив", printNumbers());
        results.put("Уникальные числа", getUniqueNumbers());
        results.put("Количество каждого повторяющегося числа", getNumberCounts());
        String info = infoNumbers();
        String[] infoArray = info.split(" ");
        results.put("Статистика", "Минимум: " + infoArray[0] + ", Максимум: " + infoArray[1] + ", Сумма: " + infoArray[2]);
        results.put("Четные числа", evenNumbers());
        return results;
    }
}
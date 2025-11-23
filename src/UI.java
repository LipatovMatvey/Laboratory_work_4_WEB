import java.util.Scanner;

public class UI {

    /**
     * Объект класса Scanner для чтения ввода из консоли
     */
    Scanner in = new Scanner(System.in);

    /**
     * Метод запуска пользовательского интерфейса
     */
    public void run() {
        while(true) {
            System.out.println("=== МЕНЮ ===");
            System.out.println("1 -> Сгенерировать новый массив");
            System.out.println("2 -> Показать текущий массив");
            System.out.println("3 -> Создать новый массив, переписав в него часть элементов из первого массива");
            System.out.println("4 -> Показать уникальные числа");
            System.out.println("5 -> Показать количество каждого неуникального числа");
            System.out.println("6 -> Отсортировать массив");
            System.out.println("7 -> Показать максимальное число массива");
            System.out.println("8 -> Показать минимальное число массива");
            System.out.println("9 -> Показать сумму чисел массива");
            System.out.println("10 -> Создать массив, содержащий все чётные числа первого массива");
            System.out.println("11 -> Удалить из первого массива все нечетные числа");
            System.out.println("12 -> Найти число в массиве");
            System.out.println("13 -> Сохранить все результаты в файл");
            System.out.println("14 -> Прочитать файл");
            System.out.println("15 -> Создать файл");
            System.out.println("16 -> Очистить файл");
            System.out.println("17 -> Показать содержимое директории");
            System.out.println("0 -> Выход");

            System.out.print("Выберите команду: ");
            String input = in.nextLine().trim();

            switch (input) {
                case "1" -> generateArray();
                case "2" -> showArray();
                case "3" -> createNewArray();
                case "4" -> printPrimeNumber();
                case "5" -> printCountPrimeNumber();
                case "6" -> sortedMyArray();
                case "7" -> printMaxValue();
                case "8" -> printMinValue();
                case "9" -> printSumArray();
                case "10" -> createNewArray2();
                case "11" -> deleteOddValue();
                case "12" -> findValue();
                case "13" -> saveResults();
                case "14" -> readTheFile();
                case "15" -> createNewFile();
                case "16" -> clearFile();
                case "17" -> showAllFile();
                case "0" -> { System.out.println("Выход"); return; }
                default -> System.out.println("Неверная команда. Повторите ввод");
            }
        }
    }

    /**
     * Метод генерирует массив
     */
    private void generateArray() {
        boolean sizeCompleted = false;
        int size = 0;
        int maxValue = 0;
        while (!sizeCompleted) {
            try {
                System.out.print("Введите размер массива: ");
                size = InputCheck.correctSize(in.nextLine().trim());
                sizeCompleted = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        boolean maxValueCompleted = false;
        while (!maxValueCompleted) {
            try {
                System.out.print("Введите максимальное значение: ");
                maxValue = InputCheck.checkInt(in.nextLine().trim());
                IntValues.addToArray(size, maxValue);
                maxValueCompleted = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("Массив успешно сгенерирован!");
        showArray();
    }

    /**
     * Метод показывает массив
     */
    private void showArray() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        System.out.println("Текущий массив: " + IntValues.printNumbers());
    }

    /**
     * Метод создает массив, переписав в него часть элементов из первого массива
     */
    private void createNewArray() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        int firstIndex = 0;
        int lastIndex = 0;

        boolean firstIndexCompleted = false;
        while (!firstIndexCompleted) {
            try {
                System.out.print("Введите начальный индекс: ");
                firstIndex = InputCheck.checkIndex(in.nextLine().trim(), IntValues.getSize());
                firstIndexCompleted = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        boolean lastIndexCompleted = false;
        while (!lastIndexCompleted) {
            try {
                System.out.print("Введите конечный индекс: ");
                lastIndex = InputCheck.checkIndex(in.nextLine().trim(), IntValues.getSize());
                if (firstIndex > lastIndex) {
                    throw new Error("Некорректный ввод! Правая граница не может быть меньше левой!");
                }
                lastIndexCompleted = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("Новый массив: " + IntValues.newSubarray(firstIndex, lastIndex));
    }

    /**
     * Метод показывает уникальные числа
     */
    private void printPrimeNumber() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String uniqueNumbers = IntValues.getUniqueNumbers();
        if (uniqueNumbers.isEmpty()) {
            System.out.println("Уникальных чисел нет");
        } else {
            System.out.println("Уникальные числа: " + IntValues.getUniqueNumbers());
        }
    }

    /**
     * Метод показывает количество каждого уникального числа в массиве
     */
    private void printCountPrimeNumber() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String counts = IntValues.getNumberCounts();
        System.out.println("Неуникальные числа: ");
        System.out.println(counts);
    }

    /**
     * Метод сортирует массив
     */
    private void sortedMyArray() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        IntValues.sortList();
        System.out.println("Массив отсортирован!");
        showArray();
    }

    /**
     * Метод показывает максимальное число массива
     */
    private void printMaxValue() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String info = IntValues.infoNumbers();
        String[] infoArray = info.split(" ");
        System.out.println("Максимальное число: " + infoArray[1]);
    }

    /**
     * Метод показывает минимальное число массива
     */
    private void printMinValue() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String info = IntValues.infoNumbers();
        String[] infoArray = info.split(" ");
        System.out.println("Минимальное число: " + infoArray[0]);
    }

    /**
     * Метод показывает сумму чисел массива
     */
    private void printSumArray() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String info = IntValues.infoNumbers();
        String[] infoArray = info.split(" ");
        System.out.println("Сумма чисел массива: " + infoArray[2]);
    }

    /**
     * Метод создает массив, содержащий все чётные числа первого массива
     */
    private void createNewArray2() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        String evenNumbers = IntValues.evenNumbers();
        if (evenNumbers.isEmpty()) {
            System.out.println("Четных чисел нет в массиве");
        } else {
            System.out.println("Массив четных чисел: " + evenNumbers);
        }
    }

    /**
     * Метод удаляет все нечетные числа из первого массива
     */
    private void deleteOddValue() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        IntValues.deleteOddNumbers();
        System.out.println("Нечетные числа удалены!");
        showArray();
    }

    /**
     * Метод ищет число в массиве
     */
    private void findValue() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив не создан. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        int number = 0;
        boolean numberCompleted = false;
        while (!numberCompleted) {
            try {
                System.out.print("Введите число для поиска: ");
                number = InputCheck.checkInt(in.nextLine().trim());
                numberCompleted = true;
            } catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        String result = IntValues.findNumber(number);
        System.out.println("Результат поиска: " + result);
    }

    /**
     * Метод сохраняет все результаты в файл
     */
    private void saveResults() {
        if (IntValues.getSize() == 0) {
            System.out.println("Массив пуст. Сначала сгенерируйте массив (команда 1).");
            return;
        }
        boolean fileCompleted = false;
        while (!fileCompleted){
            try{
                System.out.print("Введите имя файла для чтения/напишите exit для выхода в меню: ");
                String fileName = in.nextLine().trim();
                if (fileName.equalsIgnoreCase("exit")) return;
                boolean flag = FileManager.saveToFile(fileName);
                if(flag) {
                    System.out.println("Результаты операций с массивом были сохранены в файл!");
                }
                else {
                    System.out.println("Результаты операций с массивом не были сохранены в файл! Возникла ошибка");
                }
                fileCompleted = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Метод выводит всё содержимое файла
     */
    private void readTheFile() {
        boolean correctFile = false;
        while (!correctFile) {
            try {
                System.out.print("Введите имя файла для чтения/напишите exit для выхода в меню: ");
                String fileName = in.nextLine().trim();
                if (fileName.equalsIgnoreCase("exit")) return;
                String res = FileManager.readTheFile(fileName);
                System.out.println("Содержимое файла: " + "\n");
                if(res.isEmpty()) {
                    System.out.println("Файл пуст!");
                }
                else {
                    System.out.println(res);
                }
                correctFile = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Метод создания файла
     */
    private void createNewFile() {
        System.out.print("Введите имя файла для создания: ");
        String fileName = in.nextLine().trim();
        boolean flag = FileManager.createFile(fileName);
        if (flag) {
            System.out.println("Файл создан!");
        }
        else {
            System.out.println("Файл с таким именем уже существует!");
        }
    }

    /**
     * Метод очищает файл
     */
    private void clearFile() {
        boolean correctFile = false;
        while (!correctFile) {
            System.out.print("Введите имя файла для чтения/напишите exit для выхода в меню: ");
            try {
                String filename = in.nextLine().trim();
                if (filename.equalsIgnoreCase("exit")) { return; }
                boolean res = FileManager.clearFile(filename);
                if (res) {
                    System.out.println("Файл очищен!");
                }
                else {
                    System.out.println("Файл не очищен! Возникла ошибка.");
                }
                correctFile = true;
            }
            catch (Error e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Метод показывает все файлы в директории
     */
    private void showAllFile() {
        try {
            String res = FileManager.showAllFiles();
            System.out.println(res);
        }
        catch (Error e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
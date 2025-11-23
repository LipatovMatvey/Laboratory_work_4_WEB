import java.io.*;
import java.util.*;

/**
 * Утилитный класс для работы с файлами
 */
public abstract class FileManager {

    /**
     * Метод читает содержимое файла
     */
    public static String readTheFile(String fileName) {
        String res = "";
        try {
            if (validateFile(fileName)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res += line + '\n';
                    }
                }
                return res;
            } else {
                throw new Error("Файл с именем " + fileName + " не существует");
            }
        } catch (IOException e) {
            return res;
        }
    }

    /**
     * Метод сохранения результатов операций в текстовый файл
     * @param fileName имя файла для сохранения
     */
    public static boolean saveToFile(String fileName) {
        if (!validateFile(fileName)) {
            throw new Error("Файл с именем " + fileName + " не существует");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            HashMap<String, String> results = IntValues.saveToMap(fileName);
            for (Map.Entry<String, String> entry : results.entrySet()) {
                writer.println(entry.getKey() + ":");
                writer.println(entry.getValue());
                writer.println();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Метод проверяет существование файла
     * @param filename имя файла для проверки
     * @return true, если файл существует, false - иначе
     */
    public static boolean validateFile(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile() ;
    }

    /**
     * Метод создает новый файл с указанным именем
     * @param fileName Имя создаваемого файла
     */
    public static boolean createFile(String fileName) {
        try {
            File file = new File(fileName);
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Метод очищает файл
     * @param fileName Имя файла, который необходимо очистить
     */
    public static boolean clearFile(String fileName) {
        if (!validateFile(fileName)) {
            throw new Error("Файл с именем " + fileName + " не существует");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Метод показывает все файлы в текущей директории
     */
    public static String showAllFiles() {
        String res = "";
        try {
            File currentDir = new File(".");
            File[] files = currentDir.listFiles();
            if (files == null || files.length == 0) {
                throw new Error("В текущей директории нет файлов.");
            }
            for (File file : files) {
                if (file.isFile()) {
                    res += "[ФАЙЛ] " + file.getName() + "\n";

                } else {
                    res += "[ПАПКА] " + file.getName() + "\n";

                }
            }
            return res;
        } catch (Exception e) {
            return res;
        }
    }
}
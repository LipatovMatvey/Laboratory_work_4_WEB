import java.util.*;

public class IntValuesIterator implements Iterator<IntValues> {

    /**
     * Коллекция Integer
     */
    private final ArrayList<IntValues> numbers;

    /**
     * Индекс элемента, на который указывает итератор
     */
    private int currentPosition;

    /**
     * Конструктор по умолчанию
     */
    public IntValuesIterator() {
        this.numbers = new ArrayList<>();
        this.currentPosition = 0;
    }

    /**
     * Конструктор с параметром
     * @param numbers Коллекция
     */
    public IntValuesIterator(ArrayList<IntValues> numbers) {
        this.numbers = numbers;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < numbers.size();
    }

    @Override
    public IntValues next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Элементов в массиве больше нет!");
        }
        return numbers.get(currentPosition++);
    }
}
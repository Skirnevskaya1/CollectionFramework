import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class MyIterator<T> implements Iterator<T> {

    private T[] iteratorArr;
    private int currentIndex;
    // флаг, указывающий, можно ли удалить элемент
    private boolean canRemove;

    public MyIterator(T[] array) {
        this.iteratorArr = array;
        this.currentIndex = 0;
        // удаление возможно только после вызова next()
        this.canRemove = false;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < iteratorArr.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Нет больше элементов");
        }
        canRemove = true;
        return iteratorArr[currentIndex++];
    }

    @Override
    public void remove() {
        if (!canRemove) {
            throw new IllegalStateException("Нельзя удалить элемент до вызова next() или элемент уже был удален");
        }
        // Поскольку массив фиксированного размера, сместим элементы влево после удаления
        System.arraycopy(iteratorArr, currentIndex, iteratorArr, currentIndex - 1,
                iteratorArr.length - currentIndex);
        // скорректируем индекс после удаления
        currentIndex--;
        // обнулим последний элемент, так как он теперь не нужен
        iteratorArr[iteratorArr.length - 1] = null;
        // после удаления следующего элемента нельзя сразу снова удалять
        canRemove = false;
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        while (hasNext()) {
            action.accept(next());
        }
    }
}
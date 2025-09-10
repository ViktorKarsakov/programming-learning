import java.util.*;

interface SortStrategy {
    List<Integer> sort(List<Integer> arr);
}

// Сортировка по возрастанию
class SortByAscend implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> arr) {
        //Collections.sort(arr);
        arr.sort(Comparator.naturalOrder());
        return arr;
    }
}

// Сортировка по убыванию
class SortByDescend implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> arr) {
        arr.sort(Comparator.reverseOrder());
        return arr;
    }
}

// Сортировка по четным и нечетным числам ( 3 1 4 2) -> (4 2 3 1)
class SortByOdd implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> arr) {
        arr.sort(Comparator.comparingInt(a -> a % 2));
        return arr;
    }
}

class SortPlace {
    private SortStrategy sortStrategy;

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void makeSort(List<Integer> arr) {
        if (sortStrategy != null) {
            sortStrategy.sort(arr);

            System.out.println("Отсортированный массив: " + arr.toString());
        } else {
            System.out.println("Способ сортировки не выбран.");
        }

    }
}

public class Main {
    public static void main(String[] args) {
        SortPlace cart = new SortPlace();

        cart.setSortStrategy(new SortByOdd());

        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(3,4,7,2,8,0,1,9));
        cart.makeSort(arr);

        System.out.println("Sorted: " + arr.toString());
    }
}

// 0 1 1 2   ->  2 1 1 0
// 3 4 7 2   ->  2 4 7 3
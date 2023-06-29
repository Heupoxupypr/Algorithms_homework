import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeapSort {
    private static Logger log = Logger.getLogger(HeapSort.class.getName());
    public static void main(String[] args) {


    }

    public static void heapsort(int arr[]) {
        int n = arr.length;

        log.logp(Level.INFO, HeapSort.class.getName(), new Throwable()
                .getStackTrace()[0]
                .getMethodName(), "Array to heapify: " + Arrays.toString(arr));
        // Строим кучу
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i); // Просеиваем кучу
        }

        // Извлекаем корни из кучи
        for (int i = n - 1; i >= 0; i--) {
            log.log(Level.INFO,"ROOT element: "+arr[0]);
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            log.logp(Level.INFO, HeapSort.class.getName(), new Throwable()
                    .getStackTrace()[0]
                    .getMethodName(), "Array to heapify: " + Arrays.toString(arr));

            // просеиваем кучу заново с новым-0-м элементом
            heapify(arr, i, 0);
        }
    }

    // Преобразование в двоичную кучу поддерева с корневым узлом i,
    public static void heapify(int arr[], int n, int i) {
        int largest = i; // Инициализируем наибольший
        int l = 2 * i + 1; // левый элемент
        int r = 2 * i + 2; // правый элемент

        // Если левый дочерний элемент больше корня
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Если правый дочерний элемент больше
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // Если самый большой элемент не корень, меняем местами
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            log.log(Level.INFO,"Change element number "+i+" with element number "+largest);

            log.log(Level.INFO,"Array to heapify: " + Arrays.toString(arr));

            // Рекурсивно просеиваем кучу при наличии дочерних элементов у дочерних
            // элементов корня
            heapify(arr, n, largest);
        }
    }
}

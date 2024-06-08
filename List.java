import java.util.Random;

public class List {
    private Node head;
    private Node originalListHead;
    private List randomList;
    private List sortedList;

    public List() {
        this.head = null;
        this.originalListHead = null;
        this.randomList = null;
        this.sortedList = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void addToOriginalList(int data) {
        Node newNode = new Node(data);
        if (originalListHead == null) {
            originalListHead = newNode;
        } else {
            Node current = originalListHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printList() {
        System.out.println("Lista original:");
        printOriginalList();
        System.out.println("Lista ordenada:");
        printSortedList();
    }

    private void printOriginalList() {
        Node current = originalListHead;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    private void printSortedList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void generateRandomList(int size) {
        Random random = new Random();
        randomList = new List(); // Inicializar randomList
        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(100);
            add(randomValue);
            addToOriginalList(randomValue);
            randomList.add(randomValue); // Agregar a randomList también
        }
    }
    
    public double bubbleSort() {
        if (head == null || head.next == null) {
            return 0;
        }

        long startTime = System.nanoTime();

        boolean swapped;
        do {
            Node current = head;
            Node previous = null;
            Node next = head.next;
            swapped = false;

            while (next != null) {
                if (current.data > next.data) {
                    swapped = true;
                    if (previous != null) {
                        Node temp = next.next;
                        previous.next = next;
                        next.next = current;
                        current.next = temp;
                    } else {
                        Node temp = next.next;
                        head = next;
                        next.next = current;
                        current.next = temp;
                    }

                    previous = next;
                    next = current.next;
                } else {
                    previous = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (swapped);

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public double selectionSort() {
        if (head == null || head.next == null) {
            return 0;
        }

        long startTime = System.nanoTime();

        Node current = head;
        while (current != null) {
            Node min = current;
            Node next = current.next;
            while (next != null) {
                if (next.data < min.data) {
                    min = next;
                }
                next = next.next;
            }

            // Intercambiar los datos
            int temp = current.data;
            current.data = min.data;
            min.data = temp;

            current = current.next;
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public double insertionSort() {
        if (head == null || head.next == null) {
            return 0;
        }

        long startTime = System.nanoTime();

        Node sorted = null; // Lista ordenada
        Node current = head; // Nodo actual en la lista original

        while (current != null) {
            Node next = current.next; // Guarda el siguiente nodo antes de desconectarlo

            // Insertar el nodo actual en la lista ordenada
            if (sorted == null || current.data <= sorted.data) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data < current.data) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }

            current = next; // Mover al siguiente nodo en la lista original
        }

        head = sorted; // Actualizar la cabeza de la lista original a la lista ordenada

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public double shellSort() {
        if (head == null || head.next == null) {
            return 0;
        }

        long startTime = System.nanoTime();

        int n = getSize();
        int gap = n / 2; // Inicializa el tamaño de la brecha

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = getNodeValue(i);
                int j = i;

                while (j >= gap && getNodeValue(j - gap) > temp) {
                    setNodeValue(j, getNodeValue(j - gap));
                    j -= gap;
                }

                setNodeValue(j, temp);
            }

            gap /= 2; // Reduce el tamaño de la brecha
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public double quickSort() {
        if (head == null || head.next == null) {
            return 0;
        }

        long startTime = System.nanoTime();
        head = quickSortRec(head);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000_000.0;
    }

    private Node quickSortRec(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pivot = head;
        Node less = null, equal = null, greater = null;
        Node current = head;

        while (current != null) {
            if (current.data < pivot.data) {
                less = appendNode(less, current.data);
            } else if (current.data == pivot.data) {
                equal = appendNode(equal, current.data);
            } else {
                greater = appendNode(greater, current.data);
            }
            current = current.next;
        }

        less = quickSortRec(less);
        greater = quickSortRec(greater);

        return concatenate(less, equal, greater);
    }

    private Node appendNode(Node list, int data) {
        Node newNode = new Node(data);
        if (list == null) {
            return newNode;
        } else {
            Node current = list;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            return list;
        }
    }

    private Node concatenate(Node less, Node equal, Node greater) {
        Node result = null, current = null;

        if (less != null) {
            result = less;
            current = less;
            while (current.next != null) {
                current = current.next;
            }
        }

        if (equal != null) {
            if (result == null) {
                result = equal;
            } else {
                current.next = equal;
            }
            current = equal;
            while (current.next != null) {
                current = current.next;
            }
        }

        if (greater != null) {
            if (result == null) {
                result = greater;
            } else {
                current.next = greater;
            }
        }

        return result;
    }

    public boolean sequentialSearch(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean binarySearch(int key) {
        // Convertir la lista a un array para realizar la búsqueda binaria
        int size = getSize();
        int[] array = new int[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == key) {
                return true;
            }
            if (array[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }

    private int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    private int getNodeValue(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
            current = current.next;
        }
        return current.data;
    }

    private void setNodeValue(int index, int value) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
            current = current.next;
        }
        current.data = value;
    }
}

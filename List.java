import java.util.Random;

public class List {
    private Node head;
    private Node originalListHead;

    public List() {
        this.head = null;
        this.originalListHead = null;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    // Método para agregar un nodo al final de la lista
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

    // Método para agregar un nodo al final de la lista original
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

    // Método para imprimir ambas listas
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

    // Método para generar dos listas aleatorias
    public void generateRandomList(int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(100);
            add(randomValue);
            addToOriginalList(randomValue);
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
        long startTime = System.nanoTime();

        head = quickSortRec(head);

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    private Node quickSortRec(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node pivot = partition(node);
        pivot.next = quickSortRec(pivot.next);
        node = quickSortRec(node);

        return node;
    }

    private Node partition(Node node) {
        Node pivot = node;
        Node current = node.next;
        Node smallerHead = new Node(0);
        Node smallerTail = smallerHead;
        Node greaterHead = new Node(0);
        Node greaterTail = greaterHead;

        while (current != null) {
            if (current.data < pivot.data) {
                smallerTail.next = current;
                smallerTail = current;
            } else {
                greaterTail.next = current;
                greaterTail = current;
            }
            current = current.next;
        }

        smallerTail.next = null;
        greaterTail.next = null;

        Node newHead = smallerHead.next != null ? partition(smallerHead.next) : smallerHead.next;
        smallerTail.next = pivot;
        pivot.next = greaterHead.next != null ? partition(greaterHead.next) : greaterHead.next;

        return newHead != null ? newHead : pivot;
    }

    public boolean sequentialSearch(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return true; // El elemento se encuentra en la lista
            }
            current = current.next;
        }
        return false; // El elemento no se encuentra en la lista
    }

    public boolean binarySearch(int key) {
        // Primero, asegúrate de que la lista esté ordenada
        quickSort();

        Node left = head;
        Node right = getLastNode();

        while (left != null && right != null && left != right && left != right.next) {
            int midValue = getMiddleValue(left, right);
            if (midValue == key) {
                return true; // El elemento se encuentra en la lista
            } else if (midValue < key) {
                left = left.next;
            } else {
                right = getPreviousNode(right);
            }
        }
        return false; // El elemento no se encuentra en la lista
    }

    private Node getLastNode() {
        Node current = head;
        while (current != null && current.next != null) {
            current = current.next;
        }
        return current;
    }

    private int getMiddleValue(Node left, Node right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Left and right nodes cannot be null.");
        }

        int leftValue = left.data;
        int rightValue = right.data;
        return leftValue + (rightValue - leftValue) / 2;
    }

    private Node getPreviousNode(Node node) {
        Node current = head;
        Node previous = null;

        while (current != null && current != node) {
            previous = current;
            current = current.next;
        }

        return previous;
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
                throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            current = current.next;
        }
        return current.data;
    }

    private void setNodeValue(int index, int value) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            current = current.next;
        }
        current.data = value;
    }
}

import java.util.Random;

public class List {
    private Node head;

    public List() {
        this.head = null;
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

    // Método para imprimir la lista

    public void printList() {
        System.out.println("Lista generada aleatoriamente:");
        printRandomList();
    }
    
    private void printRandomList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    

    // Método para generar una lista de tamaño aleatorio
    public void generateRandomList(int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            add(random.nextInt(100)); // Genera números aleatorios entre 0 y 99
        }
    }

    public void bubbleSort() {
        if (head == null || head.next == null) {
            return;
        }

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
    }

    public void selectionSort() {
        if (head == null || head.next == null) {
            return;
        }

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
    }

    public void insertionSort() {
        if (head == null || head.next == null) {
            return;
        }

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
    }

    public void shellSort() {
        if (head == null || head.next == null) {
            return;
        }

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
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        if (current != null) {
            return current.data;
        }
        throw new IndexOutOfBoundsException("Index out of range");
    }

    private void setNodeValue(int index, int value) {
        Node current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        if (current != null) {
            current.data = value;
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public void quickSort() {
        head = quickSortRec(head);
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
        if (node == null || node == head) {
            throw new IllegalArgumentException("Node cannot be null or head node.");
        }

        Node current = head;
        while (current != null && current.next != node) {
            current = current.next;
        }
        return current;
    }

}
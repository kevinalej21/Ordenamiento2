import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List list = new List();
        int option;

        do {
            System.out.println("1. Verificar si la lista está vacía");
            System.out.println("2. Imprimir la lista");
            System.out.println("3. Generar una lista aleatoria");
            System.out.println("4. ordernar Por intercambio");
            System.out.println("5. ordenar por seleccion");
            System.out.println("6. odernar por insercion");
            System.out.println("7. ordenar por metodo shell");
            System.out.println("8. ordenar por ordenacion rapida");
            System.out.println("9. ordenar por Busqueda secuencial");
            System.out.println("10.ordenar por busqueda binaria");
            System.out.println("11.Salir");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    if (list.isEmpty()) {
                        System.out.println("La lista está vacía.");
                    } else {
                        System.out.println("La lista no está vacía.");
                    }
                    break;
                case 2:

                    list.printList();

                    break;
                case 3:
                    System.out.print("Introduce el tamaño de la lista: ");
                    int size = scanner.nextInt();
                    list.generateRandomList(size);
                    System.out.println("Lista generada.");
                    break;
                case 4:
                    long startTimeBubble = System.currentTimeMillis();
                    list.bubbleSort();
                    long endTimeBubble = System.currentTimeMillis();
                    System.out.println("Lista ordenada en " + (endTimeBubble - startTimeBubble) + " milisegundos.");
                    break;

                case 5:
                    long startTimeSelection = System.currentTimeMillis();
                    list.selectionSort();
                    long endTimeSelection = System.currentTimeMillis();
                    System.out
                            .println("Lista ordenada en " + (endTimeSelection - startTimeSelection) + " milisegundos.");
                    break;

                case 6:
                    long startTimeInsertion = System.currentTimeMillis();
                    list.insertionSort();
                    long endTimeInsertion = System.currentTimeMillis();
                    System.out
                            .println("Lista ordenada en " + (endTimeInsertion - startTimeInsertion) + " milisegundos.");
                    break;

                case 7:
                    long startTimeShell = System.currentTimeMillis();
                    list.shellSort();
                    long endTimeShell = System.currentTimeMillis();
                    System.out.println("Lista ordenada en " + (endTimeShell - startTimeShell) + " milisegundos.");
                    break;

                case 8:
                    long startTimeQuick = System.currentTimeMillis();
                    list.quickSort();
                    long endTimeQuick = System.currentTimeMillis();
                    System.out.println("Lista ordenada en " + (endTimeQuick - startTimeQuick) + " milisegundos.");
                    break;

                case 9:
                    long startTimeSequentialSearch = System.currentTimeMillis();
                    System.out.print("Introduce el elemento a buscar: ");
                    int searchKey = scanner.nextInt();
                    if (list.sequentialSearch(searchKey)) {
                        System.out.println("El elemento está en la lista.");
                    } else {
                        System.out.println("El elemento no está en la lista.");
                    }
                    long endTimeSequentialSearch = System.currentTimeMillis();
                    System.out.println("Búsqueda secuencial realizada en "
                            + (endTimeSequentialSearch - startTimeSequentialSearch) + " milisegundos.");
                    break;

                case 10:
                    long startTimeBinarySearch = System.currentTimeMillis();
                    System.out.print("Introduce el elemento a buscar: ");
                    int searchKey1 = scanner.nextInt();
                    if (list.binarySearch(searchKey1)) {
                        System.out.println("El elemento está en la lista.");
                    } else {
                        System.out.println("El elemento no está en la lista.");
                    }
                    long endTimeBinarySearch = System.currentTimeMillis();
                    System.out.println("Búsqueda binaria realizada en " + (endTimeBinarySearch - startTimeBinarySearch)
                            + " milisegundos.");
                    break;

                case 11:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 11);

        scanner.close();
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List list = new List();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Generar lista aleatoria");
            System.out.println("2. Verificar si la lista está vacía");
            System.out.println("3. Imprimir listas");
            System.out.println("4. Ordenar lista por burbuja");
            System.out.println("5. Ordenar lista por selección");
            System.out.println("6. Ordenar lista por inserción");
            System.out.println("7. Ordenar lista por Shell Sort");
            System.out.println("8. Ordenar lista por Quick Sort");
            System.out.println("9. Buscar elemento por búsqueda secuencial");
            System.out.println("10. Buscar elemento por búsqueda binaria");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            double duration;

            switch (option) {
                case 1:
                    System.out.print("Ingrese el tamaño de la lista: ");
                    int size = scanner.nextInt();
                    list.generateRandomList(size);
                    System.out.println("Lista aleatoria generada.");
                    break;
                case 2:
                    System.out.println("La lista " + (list.isEmpty() ? "está vacía." : "no está vacía."));
                    break;
                case 3:
                    list.printList();
                    break;
                case 4:
                    duration = list.bubbleSort();
                    System.out.println("Lista ordenada por burbuja en " + duration + " segundos.");
                    break;
                case 5:
                    duration = list.selectionSort();
                    System.out.println("Lista ordenada por selección en " + duration + " segundos.");
                    break;
                case 6:
                    duration = list.insertionSort();
                    System.out.println("Lista ordenada por inserción en " + duration + " segundos.");
                    break;
                case 7:
                    duration = list.shellSort();
                    System.out.println("Lista ordenada por Shell Sort en " + duration + " segundos.");
                    break;
                case 8:
                    duration = list.quickSort();
                    System.out.println("Lista ordenada por Quick Sort en " + duration + " segundos.");
                    break;
                case 9:
                    System.out.print("Ingrese el elemento a buscar: ");
                    int keySequential = scanner.nextInt();
                    System.out.println("El elemento " + (list.sequentialSearch(keySequential) ? "se encuentra" : "no se encuentra") + " en la lista.");
                    break;
                case 10:
                    System.out.print("Ingrese el elemento a buscar: ");
                    int keyBinary = scanner.nextInt();
                    System.out.println("El elemento " + (list.binarySearch(keyBinary) ? "se encuentra" : "no se encuentra") + " en la lista.");
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}

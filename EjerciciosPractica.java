/**
 * EJERCICIOS DE PRÁCTICA - Resueltos
 * Análisis de complejidad mediante recurrencias
 */

public class EjerciciosPractica {
    
    /**
     * PRÁCTICA - Ejercicio 1
     * 
     * public static int fRecu(int n){
     *     if(n <= 1){
     *         return n * 6 + 1;
     *     }else{
     *         int suma = 2 * fRecu(n / 2);
     *         return suma + fRecu(n / 2);
     *     }
     * }
     * 
     * ANÁLISIS:
     * Recurrencia: T(n) = 2·fRecu(n/2) + fRecu(n/2) + O(1)
     *                    = 3·fRecu(n/2) + O(1)
     *                    = 3·T(n/2) + c
     * 
     * Usando Teorema Master: a = 3, b = 2, f(n) = O(1)
     * nˡᵒᵍᵇ(ᵃ) = n^log₂(3) = n^1.585
     * 
     * Como n^1.585 > n⁰ (Caso 1):
     * T(n) = O(n^log₂(3)) ≈ O(n^1.585)
     * 
     * Complejidad: O(n^1.585) - POLINÓMICA
     */
    private static long llamadasPractica1 = 0;
    
    public static int practica1(int n) {
        llamadasPractica1++;
        
        if (n <= 1) {
            return n * 6 + 1;
        } else {
            int suma = 2 * practica1(n / 2);
            return suma + practica1(n / 2);
        }
    }
    
    /**
     * PRÁCTICA - Ejercicio 2
     * 
     * public static int fRecu(int n){
     *     if(n <= 1){
     *         return n * 6 + 1;
     *     }else{
     *         return 2 + fRecu(n / 2);
     *     }
     * }
     * 
     * ANÁLISIS:
     * Recurrencia: T(n) = T(n/2) + O(1)
     * 
     * Usando Teorema Master: a = 1, b = 2, f(n) = O(1)
     * nˡᵒᵍᵇ(ᵃ) = n^log₂(1) = n⁰ = 1
     * 
     * Como f(n) = O(1) = O(n⁰) (Caso 2):
     * T(n) = O(n⁰ · log n) = O(log n)
     * 
     * Complejidad: O(log n) - LOGARÍTMICA
     * 
     * Explicación intuitiva:
     * En cada llamada, n se reduce a la mitad.
     * Número de niveles en el árbol de recursión: log₂(n)
     */
    private static long llamadasPractica2 = 0;
    
    public static int practica2(int n) {
        llamadasPractica2++;
        
        if (n <= 1) {
            return n * 6 + 1;
        } else {
            return 2 + practica2(n / 2);
        }
    }
    
    /**
     * PRÁCTICA - Ejercicio 3
     * 
     * public static int fRecu(int n){
     *     if(n <= 1){
     *         return n * 2;
     *     }else{
     *         return 2 + fRecu(n - 2);
     *     }
     * }
     * 
     * ANÁLISIS:
     * Recurrencia: T(n) = T(n-2) + O(1)
     * 
     * Resolviendo por iteración:
     * T(n) = T(n-2) + c
     *      = (T(n-4) + c) + c = T(n-4) + 2c
     *      = (T(n-6) + c) + 2c = T(n-6) + 3c
     *      ...
     *      = T(1) + (n/2)·c
     *      = O(1) + n/2
     * 
     * Número de pasos: n/2
     * 
     * T(n) = O(n)
     * 
     * Complejidad: O(n) - LINEAL
     * 
     * Explicación intuitiva:
     * La función se reduce por 2 en cada llamada, tardando n/2 pasos.
     * El coeficiente 1/2 no cambia la clase de complejidad O(n).
     */
    private static long llamadasPractica3 = 0;
    
    public static int practica3(int n) {
        llamadasPractica3++;
        
        if (n <= 1) {
            return n * 2;
        } else {
            return 2 + practica3(n - 2);
        }
    }
    
    /**
     * PRÁCTICA - Ejercicio 4
     * 
     * Análisis de Búsqueda Binaria
     * 
     * public static int busquedaBinaria(int[] arr, int objetivo) {
     *     int izq = 0, der = arr.length - 1;
     *     while (izq <= der) {
     *         int mid = (izq + der) / 2;
     *         if (arr[mid] == objetivo) return mid;
     *         if (arr[mid] < objetivo) izq = mid + 1;
     *         else der = mid - 1;
     *     }
     *     return -1; // No encontrado
     * }
     * 
     * ANÁLISIS:
     * Loop: while (izq <= der)
     * En cada iteración el espacio de búsqueda se divide entre 2:
     * - Iteración 1: n elementos
     * - Iteración 2: n/2 elementos
     * - Iteración 3: n/4 elementos
     * - Iteración k: n/2^k elementos
     * 
     * El bucle termina cuando n/2^k ≤ 1
     * Resolviendo: 2^k ≥ n → k ≥ log₂(n)
     * 
     * Recurrencia: T(n) = T(n/2) + O(1)
     * 
     * Usando Teorema Master: a = 1, b = 2, f(n) = O(1)
     * T(n) = O(log n)
     * 
     * Complejidad: O(log n) - LOGARÍTMICA
     * 
     * Ejemplo: Para n = 1,000,000
     * - Iteraciones necesarias: log₂(1,000,000) ≈ 20
     */
    private static long comparacionesBusqueda = 0;
    
    public static int busquedaBinaria(int[] arr, int objetivo) {
        int izq = 0, der = arr.length - 1;
        
        while (izq <= der) {
            comparacionesBusqueda++;
            int mid = (izq + der) / 2;
            
            if (arr[mid] == objetivo) {
                return mid;
            }
            if (arr[mid] < objetivo) {
                izq = mid + 1;
            } else {
                der = mid - 1;
            }
        }
        return -1; // No encontrado
    }
    
    // ============= MÉTODOS AUXILIARES =============
    
    public static void resetContadores() {
        llamadasPractica1 = 0;
        llamadasPractica2 = 0;
        llamadasPractica3 = 0;
        comparacionesBusqueda = 0;
    }
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║       EJERCICIOS DE PRÁCTICA - ANÁLISIS DE COMPLEJIDAD                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
        
        // ===== PRÁCTICA 1 =====
        System.out.println("┌─ PRÁCTICA 1: T(n) = 3·T(n/2) + O(1) ─────────────────────────────────┐");
        System.out.println("│ Complejidad: O(n^1.585) - POLINÓMICA                                  │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("n\t| Llamadas\t| log₂(n)\t| n^1.585\t| Relación");
        System.out.println("-".repeat(65));
        
        double log2_3 = Math.log(3) / Math.log(2);
        for (int n = 2; n <= 256; n *= 2) {
            resetContadores();
            practica1(n);
            double teorico = Math.pow(n, log2_3);
            double relacion = llamadasPractica1 / teorico;
            
            System.out.printf("%d\t| %d\t\t| %.2f\t\t| %.0f\t\t| %.4f%n", 
                            n, llamadasPractica1, Math.log(n) / Math.log(2), 
                            teorico, relacion);
        }
        
        // ===== PRÁCTICA 2 =====
        System.out.println("\n┌─ PRÁCTICA 2: T(n) = T(n/2) + O(1) ────────────────────────────────────┐");
        System.out.println("│ Complejidad: O(log n) - LOGARÍTMICA                                   │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("n\t| Llamadas\t| log₂(n)\t| Estado");
        System.out.println("-".repeat(50));
        
        for (int n = 2; n <= 1024; n *= 2) {
            resetContadores();
            practica2(n);
            double teorico = Math.log(n) / Math.log(2);
            
            System.out.printf("%d\t| %d\t\t| %.2f\t\t| ✓%n", 
                            n, llamadasPractica2, teorico);
        }
        
        // ===== PRÁCTICA 3 =====
        System.out.println("\n┌─ PRÁCTICA 3: T(n) = T(n-2) + O(1) ────────────────────────────────────┐");
        System.out.println("│ Complejidad: O(n) - LINEAL                                            │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("n\t| Llamadas\t| n/2\t\t| Estado");
        System.out.println("-".repeat(50));
        
        for (int n = 10; n <= 100; n += 10) {
            resetContadores();
            practica3(n);
            double teorico = n / 2.0;
            
            System.out.printf("%d\t| %d\t\t| %.1f\t\t| ✓%n", 
                            n, llamadasPractica3, teorico);
        }
        
        // ===== PRÁCTICA 4 =====
        System.out.println("\n┌─ PRÁCTICA 4: Búsqueda Binaria → O(log n) ────────────────────────────────┐");
        System.out.println("│ Complejidad: O(log n) - LOGARÍTMICA                                   │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("n\t| Comparaciones | log₂(n)\t| Diferencia");
        System.out.println("-".repeat(55));
        
        for (int size = 2; size <= 1024; size *= 2) {
            int[] arr = new int[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i * 2;
            }
            
            resetContadores();
            busquedaBinaria(arr, 999);
            double teorico = Math.log(size) / Math.log(2);
            
            System.out.printf("%d\t| %d\t\t| %.2f\t\t| %d%n", 
                            size, comparacionesBusqueda, teorico, 
                            comparacionesBusqueda - (int)teorico);
        }
        
        // ===== TABLA COMPARATIVA FINAL =====
        System.out.println("\n┌─ TABLA COMPARATIVA DE COMPLEJIDADES ───────────────────────────────────┐");
        System.out.println("│ Comparación de diferentes tipos de complejidad para n = 1000          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado (n=1000)");
        System.out.println("-".repeat(60));
        
        int n = 1000;
        System.out.printf("O(log n)\t| log₂(n)\t\t| %.1f%n", Math.log(n) / Math.log(2));
        System.out.printf("O(n)\t\t| n\t\t\t| %d%n", n);
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %.0f%n", n * Math.log(n) / Math.log(2));
        System.out.printf("O(n^1.585)\t| n^1.585\t\t| %.0f%n", Math.pow(n, log2_3));
        System.out.printf("O(n²)\t\t| n²\t\t\t| %d%n", n * n);
        System.out.printf("O(2^n)\t\t| 2^n\t\t\t| 2^%d (IMPRACTICABLE)%n", n);
        
        System.out.println("\n✓ Análisis completado exitosamente.");
    }
}

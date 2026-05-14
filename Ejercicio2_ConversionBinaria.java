/**
 * EJERCICIO 2: CONVERSIÓN BINARIA
 * 
 * Función Original:
 * public static void conversionBinaria(int n) {
 *     String binario = "";
 *     while (n > 0) {
 *         binario = (n % 2) + binario;
 *         n = n / 2;
 *     }
 * }
 * 
 * ANÁLISIS MATEMÁTICO:
 * 
 * Bucle while: while (n > 0)
 * - Iteración 1: n
 * - Iteración 2: n/2
 * - Iteración 3: n/4
 * - Iteración k: n/2^(k-1)
 * 
 * El bucle termina cuando: n/2^k ≤ 0
 * Resolviendo: 2^k ≥ n
 *             k ≥ log₂(n)
 *             k = ⌈log₂(n)⌉
 * 
 * Número de iteraciones ≈ log₂(n)
 * 
 * RECURRENCIA:
 * T(n) = T(n/2) + O(1)
 * 
 * Por Teorema Master:
 * a = 1, b = 2, f(n) = O(1)
 * log_b(a) = log₂(1) = 0
 * n^0 = 1
 * 
 * Como f(n) = O(1) = O(n^0) (Caso 2 del Teorema Master):
 * T(n) = O(n^log_b(a) · log n) = O(1 · log n) = O(log n)
 * 
 * COMPLEJIDAD: O(log n) - LOGARÍTMICA
 * 
 * EJEMPLO VISUAL:
 * n = 16 (binario = 10000)
 * Iteración 1: 16 / 2 = 8, resto 0
 * Iteración 2: 8 / 2 = 4, resto 0
 * Iteración 3: 4 / 2 = 2, resto 0
 * Iteración 4: 2 / 2 = 1, resto 0
 * Iteración 5: 1 / 2 = 0, resto 1
 * Total: 5 iteraciones = log₂(16) + 1 = 4 + 1 = 5 ✓
 */

public class Ejercicio2_ConversionBinaria {
    
    private static long operaciones = 0;
    
    /**
     * Convierte un número decimal a binario
     * Versión con contador de operaciones
     */
    public static String conversionBinariaConteo(int n) {
        operaciones = 0;
        String binario = "";
        
        while (n > 0) {
            operaciones++;
            binario = (n % 2) + binario;
            n = n / 2;
        }
        
        return binario;
    }
    
    public static void analizarComplejidad() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ANÁLISIS DE COMPLEJIDAD: DIVISIÓN SUCESIVA POR 2 O(log n)          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📊 Datos Empíricos vs Teóricos:");
        System.out.println("n\t| Iteraciones\t| log₂(n)\t| Binario\t\t| Status");
        System.out.println("-".repeat(75));
        
        int[] valores = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 1048576};
        
        for (int n : valores) {
            String binario = conversionBinariaConteo(n);
            double teorico = Math.log(n) / Math.log(2);
            String status = (Math.abs(operaciones - (teorico + 1)) <= 1) ? "✓" : "✗";
            
            System.out.printf("%d\t| %d\t\t| %.2f\t| %s\t| %s%n",
                            n, operaciones, teorico, binario, status);
        }
    }
    
    public static void demostrarArbolRecursion() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ÁRBOL DE RECURSIÓN: T(n) = T(n/2) + O(1)                          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nPara n = 32 (5 niveles de profundidad):");
        System.out.println("\n                       T(32)");
        System.out.println("                         |");
        System.out.println("                      T(16) + O(1)");
        System.out.println("                         |");
        System.out.println("                      T(8) + O(1)");
        System.out.println("                         |");
        System.out.println("                      T(4) + O(1)");
        System.out.println("                         |");
        System.out.println("                      T(2) + O(1)");
        System.out.println("                         |");
        System.out.println("                      T(1) + O(1)");
        System.out.println("                         |");
        System.out.println("                      T(0) → Caso base");
        
        System.out.println("\nProfundidad del árbol: log₂(32) = 5");
        System.out.println("Número de niveles: 5");
        System.out.println("Trabajo en cada nivel: O(1)");
        System.out.println("Trabajo total: 5 × O(1) = O(log n)");
    }
    
    public static void comparisonConOtraComplejidades() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   COMPARACIÓN: LOGARÍTMICA vs OTRAS COMPLEJIDADES (n = 1,000,000)   │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        long n = 1_000_000;
        
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado\t| Incremento");
        System.out.println("-".repeat(70));
        
        long log_n = (long)(Math.log(n) / Math.log(2));
        long sqrtn = (long)Math.sqrt(n);
        long n_val = n;
        long nlogn = (long)(n * Math.log(n) / Math.log(2));
        long n2 = n * n;
        
        System.out.printf("O(log n) ← MEJOR | log₂(n)\t\t| %d\t\t| ------- %n", log_n);
        System.out.printf("O(√n)\t\t| √n\t\t\t| %d\t\t| +%.0f%%  %n", sqrtn, (sqrtn - log_n) * 100.0 / log_n);
        System.out.printf("O(n)\t\t| n\t\t\t| %d\t| +%.0f%% %n", n_val, (n_val - log_n) * 100.0 / log_n);
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %d\t| +%.0f%% %n", nlogn, (nlogn - log_n) * 100.0 / log_n);
        System.out.printf("O(n²)\t\t| n²\t\t\t| %d\t| EXTREMO %n", n2);
    }
    
    public static void tablaViabilidad() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│              ANÁLISIS DE VIABILIDAD - O(log n) ES ÓPTIMO             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("n\t\t| log₂(n)\t| Viable?");
        System.out.println("-".repeat(50));
        
        long[] valores = {
            256L,
            1024L,
            1_000_000L,
            1_000_000_000L,
            1_000_000_000_000L,
            1_000_000_000_000_000L
        };
        
        for (long n : valores) {
            double log_n = Math.log(n) / Math.log(2);
            String viable = "✓ ÓPTIMO";
            
            System.out.printf("%,d\t| %.1f\t\t| %s%n", n, log_n, viable);
        }
    }
    
    public static void ejemploCompleto() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│          EJEMPLO COMPLETO: CONVERSIÓN DE ALGUNOS NÚMEROS             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("Decimal | Binario | Iteraciones | log₂(n) | Correctitud");
        System.out.println("-".repeat(65));
        
        int[] numeros = {0, 1, 5, 10, 15, 63, 64, 128, 255, 256, 1000};
        
        for (int num : numeros) {
            String binario = conversionBinariaConteo(num);
            double teorico = num > 0 ? Math.log(num) / Math.log(2) : 0;
            
            // Verificar que el binario es correcto
            int verificacion = Integer.parseInt(binario.isEmpty() ? "0" : binario, 2);
            String correctitud = (verificacion == num) ? "✓" : "✗";
            
            System.out.printf("%7d | %7s | %11d | %.2f   | %s%n",
                            num, binario, operaciones, teorico, correctitud);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 2: CONVERSIÓN BINARIA - DIVISIÓN SUCESIVA O(log n)        ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📌 INFORMACIÓN TEÓRICA:");
        System.out.println("   • Bucle con división por 2 en cada iteración");
        System.out.println("   • Número de iteraciones: log₂(n)");
        System.out.println("   • Recurrencia: T(n) = T(n/2) + O(1)");
        System.out.println("   • Complejidad: O(log n) - LOGARÍTMICA");
        System.out.println("   • Viabilidad: ✓ EXCELENTE (incluso para n = 10^18)");
        
        analizarComplejidad();
        demostrarArbolRecursion();
        comparisonConOtraComplejidades();
        tablaViabilidad();
        ejemploCompleto();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("✓ CONCLUSIÓN: Algoritmo logarítmico - O(log n)");
        System.out.println("✓ MUY EFICIENTE: Viable para cualquier tamaño de entrada");
        System.out.println("=".repeat(75));
    }
}

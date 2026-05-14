/**
 * EJERCICIO 5: ANÁLISIS DE RECURRENCIA CON BUCLE
 * 
 * Función Original:
 * public static int Func(int n){
 *     if(n < 1)
 *         return 1;
 *     else{
 *         int inicio=0, aux = n;
 *         while(inicio < n)
 *             aux = aux + (inicio++) * (n-1);
 *         return Func(n - 1) + aux;
 *     }
 * }
 * 
 * ANÁLISIS MATEMÁTICO:
 * 
 * Estructura:
 * - Condición base: if (n < 1) → O(1)
 * - Bucle while: while (inicio < n) → O(n) iteraciones
 * - Llamada recursiva: Func(n-1)
 * 
 * RECURRENCIA:
 * T(n) = T(n-1) + O(n)
 * 
 * RESOLUCIÓN POR ITERACIÓN:
 * 
 * T(n) = T(n-1) + c₁·n
 *      = (T(n-2) + c₁·(n-1)) + c₁·n
 *      = T(n-2) + c₁·(n-1) + c₁·n
 *      = (T(n-3) + c₁·(n-2)) + c₁·(n-1) + c₁·n
 *      = T(n-3) + c₁·(n-2) + c₁·(n-1) + c₁·n
 * 
 * Después de k iteraciones (hasta n-k = 0):
 * T(n) = T(0) + c₁·(1 + 2 + 3 + ... + n)
 *      = O(1) + c₁·Σ(i=1 hasta n) i
 *      = O(1) + c₁·(n·(n+1)/2)
 *      = O(1) + c₁·(n²/2 + n/2)
 *      = O(n²/2 + n/2)
 * 
 * Término dominante: n²/2
 * 
 * Por definición de O():
 * T(n) = O(n²)
 * 
 * COMPLEJIDAD: O(n²) - CUADRÁTICA
 * 
 * ANÁLISIS DE VIABILIDAD:
 * 
 * n = 100: 100²/2 = 5,000 operaciones ✓
 * n = 1,000: 1000²/2 = 500,000 operaciones ✓
 * n = 10,000: 10000²/2 = 50,000,000 operaciones ✓
 * n = 100,000: 100000²/2 = 5,000,000,000 operaciones ⚠️
 * n = 1,000,000: 1000000²/2 = 500,000,000,000 operaciones ✗ INVIABLE
 * 
 * Máximo recomendado: n ≤ 10,000
 * 
 * DIFERENCIA CON EJERCICIO 1:
 * Ambos son O(n²), pero:
 * - Ej. 1: Dos bucles anidados → n²/2 ≈ 500,000 (n=1000)
 * - Ej. 5: Recursión + bucle → n²/2 ≈ 500,000 (n=1000)
 * 
 * Ambos tienen el mismo orden de magnitud.
 */

public class Ejercicio5_RecurrenciaMixta {
    
    private static long operaciones = 0;
    
    public static int Func(int n) {
        if (n < 1) {
            return 1;
        } else {
            int inicio = 0, aux = n;
            
            while (inicio < n) {
                operaciones++;
                aux = aux + (inicio++) * (n - 1);
            }
            
            return Func(n - 1) + aux;
        }
    }
    
    public static void analizarComplejidad() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ANÁLISIS DE COMPLEJIDAD: RECURRENCIA T(n) = T(n-1) + O(n)          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📊 Datos Empíricos vs Teóricos:");
        System.out.println("n\t| Operaciones\t| n²/2\t\t| Relación\t| Tiempo (est.)");
        System.out.println("-".repeat(75));
        
        for (int n = 10; n <= 100; n += 10) {
            operaciones = 0;
            Func(n);
            
            double teorico = (double)(n * n) / 2;
            double relacion = operaciones / teorico;
            double tiempoMs = operaciones / 1_000_000.0;
            
            System.out.printf("%d\t| %d\t\t| %.0f\t\t| %.4f\t\t| %.3f ms%n",
                            n, operaciones, teorico, relacion, tiempoMs);
        }
    }
    
    public static void demostrarSumaIterativa() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│    DEMOSTRACIÓN: Suma de 1 + 2 + 3 + ... + n = n(n+1)/2             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nPara n = 10:");
        System.out.println("Bucle 1: 1 iteración con bucle interno de 1 paso");
        System.out.println("Bucle 2: 1 iteración con bucle interno de 2 pasos");
        System.out.println("Bucle 3: 1 iteración con bucle interno de 3 pasos");
        System.out.println("...");
        System.out.println("Bucle 10: 1 iteración con bucle interno de 10 pasos");
        System.out.println("\nTotal: 1 + 2 + 3 + ... + 10 = 10·11/2 = 55 pasos");
        
        System.out.println("\n┌─ Verificación con tabla ─────────────────────────────┐");
        System.out.println("│ Llamada | Bucle while | Total acumulado             │");
        System.out.println("├─────────────────────────────────────────────────────┤");
        
        int total = 0;
        for (int i = 1; i <= 10; i++) {
            total += i;
            System.out.printf("│ Func(%d) | %d iteraciones  | %d                  │%n", i, i, total);
        }
        System.out.println("└─────────────────────────────────────────────────────┘");
        System.out.printf("\nFórmula: n(n+1)/2 = 10·11/2 = %d ✓%n", (10 * 11) / 2);
    }
    
    public static void comparisonConOtraComplejidades() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│       COMPARACIÓN CON OTRAS COMPLEJIDADES (n = 1,000)                │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        long n = 1000;
        
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado\t| Tiempo (1 op/ns)");
        System.out.println("-".repeat(75));
        
        System.out.printf("O(log n)\t| log₂(n)\t\t| %.0f\t\t\t| %.2f µs%n",
                        Math.log(n) / Math.log(2),
                        (Math.log(n) / Math.log(2)) / 1000);
        
        System.out.printf("O(√n)\t\t| n^0.5\t\t\t| %.0f\t\t\t| %.2f µs%n",
                        Math.sqrt(n),
                        Math.sqrt(n) / 1000);
        
        System.out.printf("O(n)\t\t| n\t\t\t| %d\t\t\t| %.2f ms%n",
                        n,
                        n / 1_000_000.0);
        
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %.0f\t\t\t| %.2f ms%n",
                        n * Math.log(n) / Math.log(2),
                        (n * Math.log(n) / Math.log(2)) / 1_000_000.0);
        
        System.out.printf("O(n²) ← NUESTRO CASO | n²/2\t\t| %.0f\t\t\t| %.2f s%n",
                        (n * n) / 2.0,
                        ((n * n) / 2.0) / 1_000_000_000.0);
        
        System.out.printf("O(n³)\t\t| n³\t\t\t| %d\t\t| %.2E s%n",
                        n * n * n,
                        ((double)(n * n * n)) / 1_000_000_000.0);
    }
    
    public static void tablaViabilidad() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│              ANÁLISIS DE VIABILIDAD POR TAMAÑO DE ENTRADA             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("n\t| Operaciones\t| Tiempo (est.)\t| Viable?");
        System.out.println("-".repeat(65));
        
        long[] valores = {10, 100, 1000, 10000, 100000, 1000000};
        
        for (long n : valores) {
            double operaciones = (n * n) / 2.0;
            String viable;
            String tiempo;
            
            if (operaciones < 1_000_000) {
                tiempo = String.format("%.1f ms", operaciones / 1_000);
                viable = "✓ INMEDIATO";
            } else if (operaciones < 1_000_000_000) {
                tiempo = String.format("%.1f ms", operaciones / 1_000_000);
                viable = "✓ RÁPIDO";
            } else if (operaciones < 1_000_000_000_000.0) {
                tiempo = String.format("%.1f s", operaciones / 1_000_000_000);
                viable = "⚠️ LENTO";
            } else {
                tiempo = String.format("%.2E ops", operaciones);
                viable = "✗ INVIABLE";
            }
            
            System.out.printf("%d\t| %.0f\t| %s\t\t| %s%n",
                            n, operaciones, tiempo, viable);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 5: ANÁLISIS DE RECURRENCIA - T(n) = T(n-1) + O(n)         ║");
        System.out.println("║  Complejidad: O(n²) - CUADRÁTICA                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📌 INFORMACIÓN TEÓRICA:");
        System.out.println("   • Combinación de recursión + bucle");
        System.out.println("   • Reducción de n en 1 por cada llamada recursiva");
        System.out.println("   • Trabajo en cada nivel = n operaciones");
        System.out.println("   • Suma: 1 + 2 + 3 + ... + n = n(n+1)/2");
        System.out.println("   • Complejidad dominante: n²/2");
        
        analizarComplejidad();
        demostrarSumaIterativa();
        comparisonConOtraComplejidades();
        tablaViabilidad();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("✓ CONCLUSIÓN: Algoritmo cuadrático - O(n²)");
        System.out.println("✓ Máximo recomendado: n ≤ 10,000");
        System.out.println("=".repeat(75));
    }
}

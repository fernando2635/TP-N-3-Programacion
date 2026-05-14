/**
 * EJERCICIO 1: NÚMEROS PRIMOS
 * 
 * Función Original (simplificada):
 * public static void numeroPrimo(int n) {
 *     for (int i = 2; i < n; i++) {
 *         boolean esPrimo = true;
 *         for (int j = 2; j < i; j++) {
 *             if (i % j == 0) {
 *                 esPrimo = false;
 *                 break;
 *             }
 *         }
 *     }
 * }
 * 
 * ANÁLISIS MATEMÁTICO:
 * 
 * Bucle externo: i = 2 hasta n-1 → (n-2) iteraciones
 * Bucle interno: j = 2 hasta i-1 → (i-2) iteraciones (en el peor caso)
 * 
 * Trabajo total (peor caso, sin break):
 * T(n) = Σ(i=2 hasta n) Σ(j=2 hasta i) 1
 *      = Σ(i=2 hasta n) (i-1)
 *      = 1 + 2 + 3 + ... + (n-1)
 *      = Σ(i=1 hasta n-1) i
 *      = (n-1)·n / 2
 *      = (n² - n) / 2
 * 
 * Por definición de O():
 * T(n) = O(n²) - Término dominante es n²
 * 
 * COMPLEJIDAD: O(n²) - CUADRÁTICA
 */

import java.util.Scanner;

public class Ejercicio1_NumerosPrimos {
    
    private static long operaciones = 0;
    
    /**
     * Encuentra todos los números primos menores que n
     * Versión con contador de operaciones
     */
    public static void numerosPrimosConteo(int n) {
        operaciones = 0;
        
        for (int i = 2; i < n; i++) {
            boolean esPrimo = true;
            operaciones++; // Asignación de esPrimo
            
            for (int j = 2; j < i && esPrimo; j++) {
                operaciones++; // Comparación en el módulo
                if (i % j == 0) {
                    esPrimo = false;
                }
            }
        }
    }
    
    /**
     * Versión optimizada (para comparación)
     * Solo verificar hasta √n
     */
    public static void numerosPrimosOptimizado(int n) {
        operaciones = 0;
        
        for (int i = 2; i < n; i++) {
            boolean esPrimo = true;
            operaciones++;
            
            for (int j = 2; j <= Math.sqrt(i); j++) {
                operaciones++;
                if (i % j == 0) {
                    esPrimo = false;
                    break;
                }
            }
        }
    }
    
    public static void analizarComplejidad() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ANÁLISIS DE COMPLEJIDAD: DOS BUCLES ANIDADOS O(n²)                 │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📊 VERSIÓN SIMPLE (peor caso):");
        System.out.println("n\t| Operaciones\t| n²/2\t\t| Relación\t| Eficiencia");
        System.out.println("-".repeat(80));
        
        for (int n = 10; n <= 200; n += 10) {
            numerosPrimosConteo(n);
            double teorico = (double)(n * n) / 2;
            double relacion = operaciones / teorico;
            
            System.out.printf("%d\t| %d\t\t| %.0f\t\t| %.4f\t\t| %.1f%%%n",
                            n, operaciones, teorico, relacion, relacion * 100);
        }
        
        System.out.println("\n📊 VERSIÓN OPTIMIZADA (√n):");
        System.out.println("n\t| Operaciones\t| n·√n\t\t| Relación");
        System.out.println("-".repeat(65));
        
        for (int n = 10; n <= 200; n += 10) {
            numerosPrimosOptimizado(n);
            double teorico = n * Math.sqrt(n);
            double relacion = operaciones / teorico;
            
            System.out.printf("%d\t| %d\t\t| %.0f\t\t| %.4f%n",
                            n, operaciones, teorico, relacion);
        }
    }
    
    public static void demostrarSuma() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   DEMOSTRACIÓN: Suma de bucles anidados 1 + 2 + 3 + ... + n         │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nPara n = 10:");
        System.out.println("i=2: compara con 0 números → 0 operaciones");
        System.out.println("i=3: compara con 1 número → 1 operación");
        System.out.println("i=4: compara con 2 números → 2 operaciones");
        System.out.println("...");
        System.out.println("i=10: compara con 8 números → 8 operaciones");
        System.out.println("\nTotal: 0 + 1 + 2 + ... + 8 = 8·9/2 = 36 operaciones");
        
        System.out.println("\nFórmula general:");
        System.out.println("Σ(i=1 hasta n-1) i = (n-1)·n / 2 = (n² - n) / 2 ≈ n²/2");
        
        System.out.println("\n┌─ Tabla de verificación ──────────────────────────────┐");
        System.out.println("│ n\t| (n²-n)/2\t| Real / Teórico           │");
        System.out.println("├──────────────────────────────────────────────────────┤");
        
        for (int n = 10; n <= 100; n += 10) {
            long teorico = (long)(n * n - n) / 2;
            System.out.printf("│ %d\t| %d\t\t| %.4f                   │%n",
                            n, teorico, 1.0);
        }
        System.out.println("└──────────────────────────────────────────────────────┘");
    }
    
    public static void comparisonConOtraComplejidades() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│       COMPARACIÓN CON OTRAS COMPLEJIDADES (n = 100)                 │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        int n = 100;
        
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado\t| Operaciones");
        System.out.println("-".repeat(70));
        
        System.out.printf("O(log n)\t| log₂(n)\t\t| %.1f\t\t| ~%d%n",
                        Math.log(n) / Math.log(2), (int)(Math.log(n) / Math.log(2)));
        
        System.out.printf("O(√n)\t\t| √n\t\t\t| %.1f\t\t| ~%d%n",
                        Math.sqrt(n), (int)Math.sqrt(n));
        
        System.out.printf("O(n)\t\t| n\t\t\t| %d\t\t| ~%d%n",
                        n, n);
        
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %.1f\t\t| ~%d%n",
                        n * Math.log(n) / Math.log(2),
                        (int)(n * Math.log(n) / Math.log(2)));
        
        System.out.printf("O(n²) ← NUESTRO CASO | n²/2\t\t\t| %.1f\t\t| ~%d%n",
                        (n * n) / 2.0, (n * n) / 2);
        
        System.out.printf("O(n³)\t\t| n³\t\t\t| %d\t| ~%d%n",
                        n * n * n, n * n * n);
    }
    
    public static void tablaViabilidad() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│              ANÁLISIS DE VIABILIDAD POR TAMAÑO DE ENTRADA             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("n\t| Operaciones\t| Tiempo (est.)\t| Viable?");
        System.out.println("-".repeat(65));
        
        int[] valores = {100, 1000, 10000, 100000, 1000000};
        
        for (int n : valores) {
            long operaciones = (long)(n * n) / 2;
            String viable;
            String tiempo;
            
            if (operaciones < 1_000_000) {
                tiempo = String.format("%.2f ms", operaciones / 1000.0);
                viable = "✓ INMEDIATO";
            } else if (operaciones < 1_000_000_000) {
                tiempo = String.format("%.2f ms", operaciones / 1_000_000.0);
                viable = "✓ RÁPIDO";
            } else if (operaciones < 1_000_000_000_000L) {
                tiempo = String.format("%.2f s", operaciones / 1_000_000_000.0);
                viable = "⚠️ LENTO";
            } else {
                tiempo = String.format("%d ops", operaciones);
                viable = "✗ INVIABLE";
            }
            
            System.out.printf("%d\t| %d\t\t| %s\t\t| %s%n",
                            n, operaciones, tiempo, viable);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 1: NÚMEROS PRIMOS - DOS BUCLES ANIDADOS O(n²)            ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📌 INFORMACIÓN TEÓRICA:");
        System.out.println("   • Bucle externo: i de 2 a n");
        System.out.println("   • Bucle interno: j de 2 a i (verificar divisibilidad)");
        System.out.println("   • Anidamiento: complejidad se multiplica");
        System.out.println("   • Operaciones totales: (n² - n) / 2");
        System.out.println("   • Complejidad dominante: n²");
        
        analizarComplejidad();
        demostrarSuma();
        comparisonConOtraComplejidades();
        tablaViabilidad();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("✓ CONCLUSIÓN: Algoritmo cuadrático - O(n²)");
        System.out.println("✓ Máximo recomendado: n ≤ 100,000");
        System.out.println("=".repeat(75));
    }
}

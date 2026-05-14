/**
 * EJERCICIO 4: ANÁLISIS DE RECURRENCIA EXPONENCIAL
 * 
 * Función Original:
 * public static int fRecu(int n){
 *     if(n < 1){
 *         return 4 + n - 2;
 *     }else{
 *         int valor = 4 * fRecu(n - 3);
 *         return fRecu(n - 3) + valor + fRecu(n - 3);
 *     }
 * }
 * 
 * SIMPLIFICACIÓN:
 * valor = 4·fRecu(n-3)
 * return = fRecu(n-3) + valor + fRecu(n-3)
 *        = fRecu(n-3) + 4·fRecu(n-3) + fRecu(n-3)
 *        = 6·fRecu(n-3)
 * 
 * RECURRENCIA:
 * T(n) = 6·T(n-3) + O(1)
 * 
 * RESOLVIENDO POR ITERACIÓN:
 * 
 * T(n) = 6·T(n-3) + c
 *      = 6·(6·T(n-6) + c) + c = 6²·T(n-6) + 6c + c
 *      = 6²·(6·T(n-9) + c) + 6c + c = 6³·T(n-9) + 6²c + 6c + c
 * 
 * Patrón después de k iteraciones:
 * T(n) = 6^k·T(n-3k) + c·(6^(k-1) + 6^(k-2) + ... + 6 + 1)
 * 
 * Caso base: n - 3k ≤ 0 → k = ⌈n/3⌉ ≈ n/3
 * 
 * Suma geométrica: 1 + 6 + 6² + ... + 6^(k-1) = (6^k - 1) / (6 - 1) = (6^k - 1) / 5
 * 
 * Entonces:
 * T(n) = 6^(n/3)·O(1) + c·(6^(n/3) - 1) / 5
 *      = 6^(n/3)·O(1) + O(6^(n/3))
 *      = O(6^(n/3))
 * 
 * COMPLEJIDAD: O(6^(n/3)) - EXPONENCIAL ⚠️
 * 
 * ¡IMPRACTICABLE PARA n > 20!
 * 
 * ANÁLISIS COMPARATIVO:
 * n = 3:  6^(3/3) = 6^1 = 6
 * n = 6:  6^(6/3) = 6^2 = 36
 * n = 9:  6^(9/3) = 6^3 = 216
 * n = 12: 6^(12/3) = 6^4 = 1,296
 * n = 15: 6^(15/3) = 6^5 = 7,776
 * n = 18: 6^(18/3) = 6^6 = 46,656
 * n = 21: 6^(21/3) = 6^7 = 279,936
 * n = 30: 6^(30/3) = 6^10 ≈ 60 millones
 * n = 60: 6^(60/3) = 6^20 ≈ 3.6 × 10^15 operaciones
 * n = 90: 6^(90/3) = 6^30 ≈ 2.2 × 10^23 operaciones (EXTREMADAMENTE LENTO)
 */

public class Ejercicio4_RecurrenciaExponencial {
    
    private static long llamadas = 0;
    
    /**
     * Función original - ADVERTENCIA: Solo usar con n pequeño (n <= 20)
     */
    public static int fRecu(int n) {
        llamadas++;
        
        if (n < 1) {
            return 4 + n - 2;
        } else {
            int valor = 4 * fRecu(n - 3);
            return fRecu(n - 3) + valor + fRecu(n - 3);
        }
    }
    
    public static void analizarComplejidad() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ANÁLISIS DE COMPLEJIDAD: EXPONENCIAL T(n) = 6·T(n-3) + O(1)       │");
        System.out.println("│   ⚠️ ADVERTENCIA: SOLO VIABLE PARA n ≤ 20                             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📊 Datos Empíricos vs Teóricos:");
        System.out.println("n\t| Llamadas\t| 6^(n/3)\t\t| Status");
        System.out.println("-".repeat(70));
        
        for (int n = 3; n <= 18; n += 3) {
            llamadas = 0;
            fRecu(n);
            
            double teorico = Math.pow(6, n / 3.0);
            String status = (Math.abs(llamadas - teorico) < teorico * 0.1) ? "✓" : "~";
            
            System.out.printf("%d\t| %d\t\t| %.0f\t\t| %s%n",
                            n, llamadas, teorico, status);
        }
        
        System.out.println("\n❌ VALORES NO COMPUTADOS (tiempo infinito):");
        System.out.println("n\t| 6^(n/3)\t\t| Tiempo estimado");
        System.out.println("-".repeat(55));
        
        for (int n = 21; n <= 60; n += 3) {
            double teorico = Math.pow(6, n / 3.0);
            String tiempo = calcularTiempoEstimado(teorico);
            System.out.printf("%d\t| %.2E\t| %s%n", n, teorico, tiempo);
        }
    }
    
    private static String calcularTiempoEstimado(double operaciones) {
        // Asumiendo 1 millón de operaciones por segundo
        double segundos = operaciones / 1_000_000;
        
        if (segundos < 1) {
            return String.format("%.2f ms", segundos * 1000);
        } else if (segundos < 60) {
            return String.format("%.2f s", segundos);
        } else if (segundos < 3600) {
            return String.format("%.2f min", segundos / 60);
        } else if (segundos < 86400) {
            return String.format("%.2f h", segundos / 3600);
        } else if (segundos < 31536000) {
            return String.format("%.2f días", segundos / 86400);
        } else {
            return String.format("%.2E años", segundos / 31536000);
        }
    }
    
    public static void demostrarArbolRecursion() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│          ÁRBOL DE RECURSIÓN: 6 RAMAS EN CADA NIVEL                  │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nPara n = 9 (3 niveles):");
        System.out.println("\nNivel 0:                    T(9)");
        System.out.println("                 (1 nodo, 6^0 = 1 llamada)");
        
        System.out.println("\nNivel 1:        T(6) ×6 nodos");
        System.out.println("                 (6 nodos, 6^1 = 6 llamadas)");
        
        System.out.println("\nNivel 2:        T(3) ×36 nodos");
        System.out.println("                 (36 nodos, 6^2 = 36 llamadas)");
        
        System.out.println("\nNivel 3:        T(0) ×216 nodos");
        System.out.println("                 (216 nodos, 6^3 = 216 llamadas)");
        
        System.out.println("\nProfundidad: n/3 = 9/3 = 3 niveles");
        System.out.println("Nodos en cada nivel k: 6^k");
        System.out.println("Nodos totales: Σ(k=0 hasta 3) 6^k = 1 + 6 + 36 + 216 = 259 ≈ 6^3 ≈ 216");
        System.out.println("\nCálculo exacto: (6^(n/3+1) - 1) / (6 - 1) = (6^4 - 1) / 5 = 1295 / 5 = 259 ✓");
    }
    
    public static void comparisonConOtraComplejidades() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   COMPARACIÓN: EXPONENCIAL vs OTRAS COMPLEJIDADES (n = 30)          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        int n = 30;
        
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado\t| Viabilidad");
        System.out.println("-".repeat(75));
        
        long log_n = (long)(Math.log(n) / Math.log(2));
        double sqrtn = Math.sqrt(n);
        double nlogn = n * Math.log(n) / Math.log(2);
        long n2 = (long)(n * n);
        double exp_nuestro = Math.pow(6, n / 3.0);
        
        System.out.printf("O(log n)\t| log₂(n)\t\t| %d\t\t| ✓ µs%n", log_n);
        System.out.printf("O(√n)\t\t| √n\t\t\t| %.0f\t\t| ✓ ms%n", sqrtn);
        System.out.printf("O(n)\t\t| n\t\t\t| %d\t\t| ✓ ms%n", n);
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %.0f\t\t| ✓ ms%n", nlogn);
        System.out.printf("O(n²)\t\t| n²\t\t\t| %d\t| ✓ ms%n", n2);
        System.out.printf("O(6^(n/3)) ← NUESTRO | 6^(n/3)\t\t| %.2E | ❌ EXTREMO%n", exp_nuestro);
    }
    
    public static void tablaExplosionExponencial() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│    DEMOSTRACIÓN: EXPLOSIÓN EXPONENCIAL - POR QUÉ ES INVIABLE        │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("n\t| 6^(n/3)\t| Multiplicador c/aumento de 3");
        System.out.println("-".repeat(55));
        
        double anterior = 1;
        for (int n = 0; n <= 60; n += 3) {
            double actual = Math.pow(6, n / 3.0);
            double multiplicador = (anterior > 0) ? actual / anterior : 1;
            
            String nota = "";
            if (n > 0 && n <= 20) nota = " ✓ Viable";
            else if (n > 20 && n <= 30) nota = " ⚠️ Marginal";
            else if (n > 30) nota = " ❌ Inviable";
            
            System.out.printf("%d\t| %.2E\t| ×%.1f%s%n", n, actual, multiplicador, nota);
            anterior = actual;
        }
        
        System.out.println("\n📌 CONCLUSIÓN: Cada aumento de 3 en n multiplica el trabajo por 6");
        System.out.println("   Esto genera crecimiento EXPLOSIVO exponencial");
    }
    
    public static void recomendacionesOptimizacion() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│         RECOMENDACIONES: CÓMO MEJORAR ESTA COMPLEJIDAD               │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n❌ PROBLEMA: La recursión calcula muchas veces los mismos subproblemas");
        System.out.println("   Ejemplo: T(30) = 6·T(27)");
        System.out.println("           = 6·(6·T(24)) = 36·T(24)");
        System.out.println("           = 36·(6·T(21)) = 216·T(21)");
        System.out.println("           ...");
        System.out.println("           = 6^10 = 60,466,176 llamadas");
        
        System.out.println("\n✅ SOLUCIÓN 1: Memoización (Programación Dinámica)");
        System.out.println("   Guardar resultados ya calculados para no repetir trabajo");
        System.out.println("   Reduce de O(6^(n/3)) a O(n)");
        
        System.out.println("\n✅ SOLUCIÓN 2: Evitar recursión");
        System.out.println("   Si es posible, resolver de forma iterativa");
        System.out.println("   Reduce overhead de llamadas recursivas");
        
        System.out.println("\n✅ SOLUCIÓN 3: Análisis del patrón");
        System.out.println("   En algunos casos, la recurrencia tiene forma cerrada");
        System.out.println("   Resultado = f(n) calculable en O(1) o O(log n)");
    }
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 4: EXPONENCIAL - T(n) = 6·T(n-3) + O(1)                  ║");
        System.out.println("║  Complejidad: O(6^(n/3)) - EXPONENCIAL ⚠️ IMPRACTICABLE             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📌 INFORMACIÓN CRÍTICA:");
        System.out.println("   • Función divide en 6 subproblemas");
        System.out.println("   • Cada subproblema reduce n por 3");
        System.out.println("   • Crecimiento EXPONENCIAL: cada +3 multiplica por 6");
        System.out.println("   • Máximo viable: n ≤ 20");
        System.out.println("   • Para n = 60: 6^20 ≈ 3.6 × 10^15 operaciones");
        System.out.println("   • NUNCA usar en producción sin optimización");
        
        analizarComplejidad();
        demostrarArbolRecursion();
        comparisonConOtraComplejidades();
        tablaExplosionExponencial();
        recomendacionesOptimizacion();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("⚠️  CONCLUSIÓN: Algoritmo exponencial - O(6^(n/3))");
        System.out.println("❌ INVIABLE PARA n > 20 - Usar memoización o cambiar estrategia");
        System.out.println("=".repeat(75));
    }
}

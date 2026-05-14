/**
 * EJERCICIO 3: ANÁLISIS DE RECURRENCIA CON TEOREMA MASTER
 * 
 * Función Original:
 * public static int fRecu(int n){
 *     if(n <= 1){
 *         return n * 6 + 1;
 *     }else{
 *         int suma = 2 * fRecu(n / 3) + fRecu(n / 3);
 *         return suma + fRecu(n / 3) + fRecu(n / 3);
 *     }
 * }
 * 
 * SIMPLIFICACIÓN:
 * suma = 2·fRecu(n/3) + fRecu(n/3) = 3·fRecu(n/3)
 * return = suma + fRecu(n/3) + fRecu(n/3) = 3·fRecu(n/3) + 2·fRecu(n/3) = 5·fRecu(n/3)
 * 
 * RECURRENCIA:
 * T(n) = 5·T(n/3) + O(1)
 * 
 * APLICANDO TEOREMA MASTER:
 * 
 * Forma estándar: T(n) = a·T(n/b) + f(n)
 * a = 5 (número de llamadas recursivas)
 * b = 3 (factor de reducción)
 * f(n) = O(1) (trabajo no recursivo)
 * 
 * Comparación:
 * n^log_b(a) = n^log₃(5) = n^(log(5)/log(3)) = n^(0.699/0.477) = n^1.465
 * 
 * Caso 1 del Teorema Master:
 * Si f(n) = O(n^(log_b(a) - ε)) para algún ε > 0, entonces T(n) = Θ(n^log_b(a))
 * 
 * O(1) = O(n^0) es mucho menor que O(n^1.465)
 * Entonces: T(n) = Θ(n^log₃(5)) = Θ(n^1.465)
 * 
 * COMPLEJIDAD: O(n^1.465) - POLINÓMICA
 */

public class Ejercicio3_RecurrenciaAnalisis {
    
    private static long llamadas = 0;
    
    /**
     * Función original con contador de llamadas
     */
    public static int fRecu(int n) {
        llamadas++;
        
        if (n <= 1) {
            return n * 6 + 1;
        } else {
            int suma = 2 * fRecu(n / 3) + fRecu(n / 3);
            return suma + fRecu(n / 3) + fRecu(n / 3);
        }
    }
    
    public static void analizarComplejidad() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ANÁLISIS DE COMPLEJIDAD: TEOREMA MASTER T(n) = 5·T(n/3) + O(1)    │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📊 Datos Empíricos vs Teóricos:");
        System.out.println("n\t| Llamadas\t| n^1.465\t| Relación\t| Eficiencia");
        System.out.println("-".repeat(80));
        
        double log3_5 = Math.log(5) / Math.log(3);
        
        for (int n = 3; n <= 243; n *= 3) {
            llamadas = 0;
            fRecu(n);
            
            double teorico = Math.pow(n, log3_5);
            double relacion = llamadas / teorico;
            
            System.out.printf("%d\t| %d\t\t| %.0f\t\t| %.4f\t\t| %.1f%%%n",
                            n, llamadas, teorico, relacion, relacion * 100);
        }
    }
    
    public static void demostrarTeoremaM() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│       DEMOSTRACIÓN DEL TEOREMA MASTER                               │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nForma General: T(n) = a·T(n/b) + f(n)");
        System.out.println("Nuestro caso: T(n) = 5·T(n/3) + O(1)");
        
        System.out.println("\nPasos:");
        System.out.println("1. Identificar parámetros:");
        System.out.println("   • a = 5 (número de subproblemas)");
        System.out.println("   • b = 3 (factor de reducción del tamaño)");
        System.out.println("   • f(n) = O(1) (trabajo fuera de recursión)");
        
        System.out.println("\n2. Calcular n^log_b(a):");
        double log3_5 = Math.log(5) / Math.log(3);
        System.out.printf("   • log₃(5) = log(5)/log(3) = %.3f%n", log3_5);
        System.out.printf("   • n^log₃(5) = n^%.3f%n", log3_5);
        
        System.out.println("\n3. Comparar f(n) con n^log_b(a):");
        System.out.println("   • f(n) = O(1) = O(n^0)");
        System.out.printf("   • n^log₃(5) = O(n^%.3f)%n", log3_5);
        System.out.println("   • O(n^0) < O(n^1.465) → Caso 1 del Teorema Master");
        
        System.out.println("\n4. Conclusión (Caso 1):");
        System.out.printf("   • T(n) = Θ(n^log₃(5)) = Θ(n^%.3f)%n", log3_5);
    }
    
    public static void demostrarArbolRecursion() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│          ÁRBOL DE RECURSIÓN: 5 RAMAS EN CADA NIVEL                  │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\nPara n = 27 (3 niveles):");
        System.out.println("\nNivel 0:                        T(27)");
        System.out.println("                     (1 nodo, 5^0 = 1 llamada)");
        
        System.out.println("\nNivel 1:        T(9)    T(9)   T(9)   T(9)   T(9)");
        System.out.println("            (5 nodos, 5^1 = 5 llamadas)");
        
        System.out.println("\nNivel 2:   T(3) ×25 nodos");
        System.out.println("            (25 nodos, 5^2 = 25 llamadas)");
        
        System.out.println("\nNivel 3:   T(1) ×125 nodos");
        System.out.println("            (125 nodos, 5^3 = 125 llamadas)");
        
        System.out.println("\nProfundidad del árbol: log₃(27) = 3");
        System.out.println("Trabajo en nivel k: 5^k");
        System.out.println("Trabajo total: Σ(k=0 hasta log₃(n)) 5^k = (5^(log₃(n)+1) - 1) / (5 - 1)");
        System.out.printf("            = (n^1.465 · 5 - 1) / 4 ≈ O(n^1.465)%n");
    }
    
    public static void comparisonConOtraComplejidades() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   COMPARACIÓN: O(n^1.465) vs OTRAS COMPLEJIDADES (n = 1,000)        │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        int n = 1000;
        
        System.out.println("Complejidad\t| Fórmula\t\t| Resultado\t| Relación");
        System.out.println("-".repeat(70));
        
        double log3_5 = Math.log(5) / Math.log(3);
        double ej3 = Math.pow(n, log3_5);
        
        System.out.printf("O(n)\t\t| n\t\t\t| %.0f\t\t\t| 1x%n", (double)n);
        System.out.printf("O(n log n)\t| n·log₂(n)\t\t| %.0f\t\t\t| %.1fx%n",
                        n * Math.log(n) / Math.log(2),
                        (n * Math.log(n) / Math.log(2)) / n);
        System.out.printf("O(n^1.465) ← NUESTRO | n^1.465\t\t| %.0f\t\t| %.1fx%n",
                        ej3, ej3 / n);
        System.out.printf("O(n^1.585)\t| n^1.585\t\t| %.0f\t\t| %.1fx%n",
                        Math.pow(n, log3_5 + 0.12), Math.pow(n, log3_5 + 0.12) / n);
        System.out.printf("O(n²)\t\t| n²\t\t\t| %.0f\t\t| %.1fx%n",
                        (double)(n * n), (double)(n * n) / n);
    }
    
    public static void tablaViabilidad() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│              ANÁLISIS DE VIABILIDAD: O(n^1.465)                     │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘\n");
        
        System.out.println("n\t| Llamadas (est.)\t| Tiempo (est.)\t| Viable?");
        System.out.println("-".repeat(65));
        
        int[] valores = {100, 1000, 10000, 100000, 1000000};
        double log3_5 = Math.log(5) / Math.log(3);
        
        for (int n : valores) {
            double operaciones = Math.pow(n, log3_5);
            String viable;
            String tiempo;
            
            if (operaciones < 1_000_000) {
                tiempo = String.format("%.2f ms", operaciones / 1000);
                viable = "✓ INMEDIATO";
            } else if (operaciones < 1_000_000_000) {
                tiempo = String.format("%.2f ms", operaciones / 1_000_000);
                viable = "✓ RÁPIDO";
            } else if (operaciones < 1_000_000_000_000.0) {
                tiempo = String.format("%.2f s", operaciones / 1_000_000_000);
                viable = "⚠️ LENTO";
            } else {
                tiempo = String.format("%.2E ops", operaciones);
                viable = "✗ INVIABLE";
            }
            
            System.out.printf("%d\t| %.0f\t\t| %s\t\t| %s%n",
                            n, operaciones, tiempo, viable);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 3: TEOREMA MASTER - T(n) = 5·T(n/3) + O(1)               ║");
        System.out.println("║  Complejidad: O(n^1.465) - POLINÓMICA                               ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📌 INFORMACIÓN TEÓRICA:");
        System.out.println("   • Función divide en 5 subproblemas");
        System.out.println("   • Cada subproblema reduce n por 3");
        System.out.println("   • Complejidad entre lineal y cuadrática");
        System.out.println("   • Teorema Master → Caso 1");
        System.out.println("   • Máximo recomendado: n ≤ 1,000,000");
        
        analizarComplejidad();
        demostrarTeoremaM();
        demostrarArbolRecursion();
        comparisonConOtraComplejidades();
        tablaViabilidad();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("✓ CONCLUSIÓN: Algoritmo polinómico intermedio - O(n^1.465)");
        System.out.println("✓ Viable para n ≤ 1,000,000 (más eficiente que O(n²))");
        System.out.println("=".repeat(75));
    }
}

````markdown
# Soluciones - TP 3: Análisis de Algoritmos y Algoritmos Fundamentales

## 📋 Resumen de Ejercicios

| Ejercicio | Código | Complejidad | Tipo |
|-----------|--------|-------------|------|
| 1 - Números Primos | Dos bucles anidados | **O(n²)** | Cuadrática |
| 2 - Conversión Binaria | Bucle con división por 2 | **O(log n)** | Logarítmica |
| 3 - Recurrencia | T(n) = 5·T(n/3) + O(1) | **O(n^1.465)** | Polinómica |
| 4 - Recurrencia | T(n) = 6·T(n-3) + O(1) | **O(6^(n/3))** | Exponencial ⚠️ |
| 5 - Recurrencia | T(n) = T(n-1) + O(n) | **O(n²)** | Cuadrática |

---

## Ejercicios Obligatorios

### **Ejercicio 1: Números Primos**

**Código:**
```java
for (int i = 2; i < n; i++) {
    boolean esPrimo = true;
    for (int j = 2; j < i; j++) {
        if (i % j == 0) {
            esPrimo = false;
            break;
        }
    }
}
```

**Análisis Matemático:**

```
Bucle externo: i = 2 hasta n-1 → n-2 iteraciones
Bucle interno: j = 2 hasta i-1 → i-1 iteraciones

T(n) = Σ(i=2 a n) Σ(j=2 a i) 1
     = Σ(i=2 a n) (i-1)
     = 1 + 2 + 3 + ... + (n-1)
     = n(n-1)/2
     = (n² - n)/2
     ∈ O(n²)
```

**Complejidad: O(n²) - CUADRÁTICA**

---

### **Ejercicio 2: Conversión Binaria**

**Código:**
```java
while (n > 0) {
    binario = (n % 2) + binario * 10;
    n = n / 2;
}
```

**Análisis Matemático:**

```
Iteración 1: n
Iteración 2: n/2
Iteración 3: n/4
Iteración k: n/2^(k-1)

El bucle termina cuando: n/2^k ≤ 0
Resolviendo: 2^k ≥ n
            k = ⌈log₂(n)⌉

Número de iteraciones ≈ log₂(n)

T(n) = O(log n)
```

**Complejidad: O(log n) - LOGARÍTMICA**

---

### **Ejercicio 3: Análisis de Recurrencia**

**Función Original:**
```java
public static int fRecu(int n){
    if(n <= 1){
        return n * 6 + 1;
    }else{
        int suma = 2 * fRecu(n / 3) + fRecu(n / 3);
        return suma + fRecu(n / 3) + fRecu(n / 3);
    }
}
```

**Simplificación:**
```
suma = 2·T(n/3) + T(n/3) = 3·T(n/3)
return = suma + 2·T(n/3) = 3·T(n/3) + 2·T(n/3) = 5·T(n/3)
```

**Recurrencia: T(n) = 5·T(n/3) + O(1)**

**Aplicando Teorema Master:**

```
Forma: T(n) = a·T(n/b) + f(n)
a = 5,  b = 3,  f(n) = O(1)

Comparar f(n) con n^log_b(a):
log₃(5) = log(5)/log(3) ≈ 1.465
n^1.465 >> O(1)

Caso 1 del Teorema Master:
T(n) = Θ(n^log₃(5)) = Θ(n^1.465)
```

**Complejidad: O(n^1.465) - POLINÓMICA**

---

### **Ejercicio 4: Análisis de Recurrencia Exponencial**

**Función Original:**
```java
public static int fRecu(int n){
    if(n < 1){
        return 4 + n - 2;
    }else{
        int valor = 4 * fRecu(n - 3);
        return fRecu(n - 3) + valor + fRecu(n - 3);
    }
}
```

**Simplificación:**
```
valor = 4·T(n-3)
return = T(n-3) + valor + T(n-3) = 6·T(n-3)
```

**Recurrencia: T(n) = 6·T(n-3) + O(1)**

**Resolviendo por Iteración:**

```
T(n) = 6·T(n-3) + c
     = 6·(6·T(n-6) + c) + c = 6²·T(n-6) + 6c + c
     = 6²·(6·T(n-9) + c) + 6c + c = 6³·T(n-9) + 6²c + 6c + c

Después de k iteraciones:
T(n) = 6^k·T(n-3k) + c·(6^(k-1) + 6^(k-2) + ... + 1)

Caso base: n - 3k ≤ 0 → k = ⌈n/3⌉ ≈ n/3

Suma geométrica: 1 + 6 + 6² + ... + 6^(k-1) = (6^k - 1)/5

T(n) = 6^(n/3)·O(1) + O(6^(n/3))
     = O(6^(n/3))
```

**Complejidad: O(6^(n/3)) - EXPONENCIAL** ⚠️

**Análisis Comparativo:**
```
n=3:   6^1 = 6 operaciones
n=6:   6^2 = 36 operaciones
n=9:   6^3 = 216 operaciones
n=12:  6^4 = 1,296 operaciones
n=15:  6^5 = 7,776 operaciones
n=18:  6^6 = 46,656 operaciones
n=21:  6^7 = 279,936 operaciones
n=30:  6^10 ≈ 60 millones operaciones
n=60:  6^20 ≈ 3.6 × 10^15 operaciones 🚫 INVIABLE
```

---

### **Ejercicio 5: Análisis de Recurrencia con Bucle**

**Función Original:**
```java
public static int Func(int n){
    if(n < 1)
        return 1;
    else{
        int inicio=0, aux = n;
        while(inicio < n)
            aux = aux + (inicio++) * (n-1);
        return Func(n - 1) + aux;
    }
}
```

**Análisis:**

```
Bucle while: se ejecuta n veces → O(n)
Llamada recursiva: Func(n-1)

Recurrencia: T(n) = T(n-1) + O(n)
```

**Resolviendo por Iteración:**

```
T(n) = T(n-1) + c·n
     = (T(n-2) + c·(n-1)) + c·n
     = T(n-2) + c·(n-1) + c·n
     = (T(n-3) + c·(n-2)) + c·(n-1) + c·n
     = T(n-3) + c·(n-2) + c·(n-1) + c·n

Después de k iteraciones (k=n):
T(n) = T(0) + c·(1 + 2 + 3 + ... + n)
     = O(1) + c·(n(n+1)/2)
     = O(1) + c·(n²/2 + n/2)
     = O(n²)
```

**Complejidad: O(n²) - CUADRÁTICA**

---

## Ejercicios de Práctica

### **Práctica - Ejercicio 1**

```java
public static int fRecu(int n){
    if(n <= 1) return n * 6 + 1;
    else {
        int suma = 2 * fRecu(n / 2);
        return suma + fRecu(n / 2);
    }
}
```

**Recurrencia:** T(n) = 3·T(n/2) + O(1)
**Complejidad:** O(n^log₂(3)) ≈ **O(n^1.585)** - POLINÓMICA

---

### **Práctica - Ejercicio 2**

```java
public static int fRecu(int n){
    if(n <= 1) return n * 6 + 1;
    else return 2 + fRecu(n / 2);
}
```

**Recurrencia:** T(n) = T(n/2) + O(1)
**Complejidad:** **O(log n)** - LOGARÍTMICA

---

### **Práctica - Ejercicio 3**

```java
public static int fRecu(int n){
    if(n <= 1) return n * 2;
    else return 2 + fRecu(n - 2);
}
```

**Recurrencia:** T(n) = T(n-2) + O(1)
**Complejidad:** **O(n)** - LINEAL

---

### **Práctica - Ejercicio 4: Búsqueda Binaria**

```java
public static int busquedaBinaria(int[] arr, int objetivo) {
    int izq = 0, der = arr.length - 1;
    while (izq <= der) {
        int mid = (izq + der) / 2;
        if (arr[mid] == objetivo) return mid;
        if (arr[mid] < objetivo) izq = mid + 1;
        else der = mid - 1;
    }
    return -1;
}
```

**Análisis:**
```
En cada iteración, el espacio de búsqueda se divide por 2:
- Iteración 1: n elementos
- Iteración 2: n/2 elementos
- Iteración 3: n/4 elementos
- Iteración k: n/2^k elementos

Terminación: n/2^k ≤ 1 → k = log₂(n)

Recurrencia: T(n) = T(n/2) + O(1)
```

**Complejidad:** **O(log n)** - LOGARÍTMICA

**Ejemplo:**
```
n = 1,000: log₂(1000) ≈ 10 iteraciones
n = 1,000,000: log₂(1,000,000) ≈ 20 iteraciones
```

---

## Tabla Comparativa de Complejidades

| Complejidad | Ejemplo | n=10 | n=100 | n=1000 |
|------------|---------|------|-------|--------|
| O(1) | Acceso array | 1 | 1 | 1 |
| O(log n) | Búsqueda binaria | 3 | 7 | 10 |
| O(n) | Búsqueda lineal | 10 | 100 | 1000 |
| O(n log n) | Merge sort | 33 | 664 | 9966 |
| O(n²) | Bubble sort | 100 | 10,000 | 1,000,000 |
| O(n^1.585) | Ej. 3 | 28 | 1,000 | 31,623 |
| O(2^n) | Fibonacci recursivo | 1024 | 1.27×10^30 | ❌ INVIABLE |
| O(6^(n/3)) | Ej. 4 | ≈ 216 | ≈ 10^8 | ❌ INVIABLE |

---

## 📝 Conclusiones

1. **Ejercicio 1 & 5:** Complejidad cuadrática O(n²) - Aceptable para n moderados
2. **Ejercicio 2 & Práctica 4:** Complejidad logarítmica O(log n) - MUY EFICIENTE
3. **Ejercicio 3:** Complejidad polinómica O(n^1.585) - Intermedia entre lineal y cuadrática
4. **Ejercicio 4:** Complejidad exponencial O(6^(n/3)) - ⚠️ IMPRACTICABLE para n > 20

**Recomendación:** Evitar algoritmos exponenciales en producción.

````

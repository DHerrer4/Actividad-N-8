import java.util.ArrayList;
import java.util.Scanner;

interface Operaciones {
    double calcularArea();
    double calcularPerimetro();
}

abstract class Figura implements Operaciones {
    abstract String getNombre();
}

class Circulo extends Figura {
    private final double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public String getNombre() {
        return "Círculo";
    }
}

class Cuadrado extends Figura {
    private final double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public String getNombre() {
        return "Cuadrado";
    }
}

class Triangulo extends Figura {
    private final double base;
    private final double altura;
    private final double lado1;
    private final double lado2;

    public Triangulo(double base, double altura, double lado1, double lado2) {
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return base + lado1 + lado2;
    }

    @Override
    public String getNombre() {
        return "Triángulo";
    }
}

class Rectangulo extends Figura {
    private final double base;
    private final double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    @Override
    public String getNombre() {
        return "Rectángulo";
    }
}

class Pentagono extends Figura {
    private final double lado;

    public Pentagono(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        double apotema = lado / (2 * Math.tan(Math.toRadians(36)));
        return (5 * lado * apotema) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return 5 * lado;
    }

    @Override
    public String getNombre() {
        return "Pentágono";
    }
}

class CalculadoraPotencias {
    public static double calcularPotencia(double base, int exponente) {
        if (exponente == 0) return 1;
        if (exponente < 0) return 1 / calcularPotencia(base, -exponente);
        return base * calcularPotencia(base, exponente - 1);
    }
}

public class Actividad8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> resultados = new ArrayList<>();
        int opcion;

        System.out.println("\n=== Calculadora de área y de perímetro ===");

        do {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. Círculo");
            System.out.println("2. Cuadrado");
            System.out.println("3. Triángulo");
            System.out.println("4. Rectángulo");
            System.out.println("5. Pentágono");
            System.out.println("6. Potencias");
            System.out.println("7. Mostrar Resultados");
            System.out.println("8. Salir");
            System.out.print("\nOpción: ");

            try {
                opcion = scanner.nextInt();
                if (opcion == 8) break;

                if (opcion == 7) {
                    System.out.println("\nResultados:");
                    for (String res : resultados) System.out.println(res);
                    continue;
                }

                if (opcion == 6) {
                    System.out.print("Digite la base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Digite el exponente: ");
                    int exponente = scanner.nextInt();
                    double resultado = CalculadoraPotencias.calcularPotencia(base, exponente);
                    resultados.add("Potencia de " + base + " ^ " + exponente + " = " + resultado);
                    continue;
                }

                Figura figura = null;
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Escriba el radio (m): ");
                        figura = new Circulo(scanner.nextDouble());
                    }
                    case 2 -> {
                        System.out.print("Escriba el lado (m): ");
                        figura = new Cuadrado(scanner.nextDouble());
                    }
                    case 3 -> {
                        System.out.print("Digite la base (m): ");
                        double base = scanner.nextDouble();
                        System.out.print("Ingrese la altura (m): ");
                        double altura = scanner.nextDouble();
                        System.out.print("Ingrese los lados restantes (m): ");
                        double lado1 = scanner.nextDouble();
                        double lado2 = scanner.nextDouble();
                        figura = new Triangulo(base, altura, lado1, lado2);
                    }
                    case 4 -> {
                        System.out.print("Escriba la base (m): ");
                        double base = scanner.nextDouble();
                        System.out.print("Escriba la altura (m): ");
                        double altura = scanner.nextDouble();
                        figura = new Rectangulo(base, altura);
                    }
                    case 5 -> {
                        System.out.print("Digite el lado (m): ");
                        figura = new Pentagono(scanner.nextDouble());
                    }
                }

                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Área\n2. Perímetro");
                System.out.print("\nOpción: ");
                int calculo = scanner.nextInt();

                if (figura != null) {
                    if (calculo == 1) {
                        resultados.add("Área del " + figura.getNombre() + ": " + figura.calcularArea());
                    } else if (calculo == 2) {
                        resultados.add("Perímetro del " + figura.getNombre() + ": " + figura.calcularPerimetro());
                    } else {
                        System.out.println("ERR: Opción inválida.");
                    }
                }

            } catch (Exception e) {
                System.out.println("ERR: Entrada inválida. Intente de nuevo.");
                scanner.next();
            }
        } while (true);

        System.out.println("¡Hasta luego!");
        scanner.close();
    }
}
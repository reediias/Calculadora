package poo;


public class Numero{
    public double somar(double x, double y){
        return x + y;
    }


    public double subtrair(double x, double y){
        return x - y;
    }


    public double multiplicar(double x, double y){
        return x * y;
    }


    public double dividir(double x, double y){
        if(y != 0){
            return x / y;
        }
        else{
            System.out.println("Erro: Divisão por zero!");
            return 0;
        }
    }
}

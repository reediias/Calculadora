package poo;

import java.util.Stack;
import java.util.Scanner;

public class Testanumero{
    public static void main(String[] args){
        //vai para a classe Numero.java do mesmo pacote
        Numero n = new Numero();
        //inicia o loop do while como verdadeiro
        boolean continuar = true;
      
        Scanner scanner = new Scanner(System.in);

        while(continuar){
            int operacao;

            System.out.println("Digite a operação que você deseja realizar: "
            + "\n1 para soma "
            + "\n2 para subtração "
            + "\n3 para multiplicação "
            + "\n4 para divisão "
            + "\n5 para resolver uma expressão completa "
            + "\n0 para encerrar o programa "
            );


            operacao = scanner.nextInt();
            scanner.nextLine();


            if(operacao == 0){
                continuar = false;
                break;


            }
            else if(operacao == 5){
                System.out.println("Digite a expressão matemática (exemplo: 3 + 4 * 2 / (1 - 5)):");
                String expressao = scanner.nextLine();


                try{
                    double resultado = avaliarExpressao(expressao);
                    System.out.println("\nO resultado da expressão \"" + expressao + "\" é: " + resultado + "\n");
                }
                catch(Exception e){
                    System.out.println("Erro ao avaliar a expressão: " + e.getMessage() + "\n");
                }


                continue;
            }


            System.out.println("Informe o primeiro valor: ");
            double x = scanner.nextDouble();


            System.out.println("Informe o segundo valor: ");
            double y = scanner.nextDouble();


            double resultado = 0;


            switch(operacao){
                case 1:
                    resultado = n.somar(x, y);
                    break;


                case 2:
                    resultado = n.subtrair(x, y);
                    break;


                case 3:
                    resultado = n.multiplicar(x, y);
                    break;


                case 4:
                    resultado = n.dividir(x, y);
                    break;


                default:
                    System.out.println("Operação inválida!\n");


                    continue;
            }
            imprimeCalculadora(operacao, resultado, x, y);
        }
        scanner.close();
    }
    //printa o resultado entre apenas dois números
    static void imprimeCalculadora(int operacao, double resultado, double x, double y){
        System.out.println("\nO resultado de " + x + " " + getOperacaoString(operacao) + " por " + y + " é igual a " + resultado + "\n");
    }


    static String getOperacaoString(int operacao){


        switch(operacao){
            case 1:
                return "somado";


            case 2:
                return "subtraído";


            case 3:
                return "multiplicado";


            case 4:
                return "dividido";


            default:
                return "";
        }
    }


    public static double avaliarExpressao(String expressao){
        //remover espaços em branco da expressao
        expressao = expressao.replaceAll("\\s+", "");


        String posfixa = converterParaPosfixa(expressao);


        return avaliarPosfixa(posfixa);
    }


    public static String converterParaPosfixa(String expressao){
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pilha = new Stack<>();
       
        //usa pilha para percorrer toda a expressao
        for(char c : expressao.toCharArray()){
            if(Character.isDigit(c)){
                resultado.append(c);


            }
            else if(c == '('){
                pilha.push(c);


            }
            else if(c == ')'){


                while(!pilha.isEmpty() && pilha.peek() != '('){
                    resultado.append(' ').append(pilha.pop());
                }
                pilha.pop();


            }
            else if(isOperador(c)){


                while(!pilha.isEmpty() && precedencia(c) <= precedencia(pilha.peek())){
                    resultado.append(' ').append(pilha.pop());
                }


                resultado.append(' ');
                pilha.push(c);
            }
        }
        while(!pilha.isEmpty()){
            resultado.append(' ').append(pilha.pop());
        }
        return resultado.toString();
    }


    public static double avaliarPosfixa(String expressao){
        Stack<Double> pilha = new Stack<>();


        for(String token : expressao.split(" ")){
            if(token.isEmpty()){
                continue;
            }


            if(isNumero(token)){
                pilha.push(Double.parseDouble(token));
            }
            else if(isOperador(token.charAt(0))){
                double b = pilha.pop();
                double a = pilha.pop();


                switch(token.charAt(0)){
                    case '+':
                        pilha.push(a + b);
                        break;


                    case '-':
                        pilha.push(a - b);
                        break;


                    case '*':
                        pilha.push(a * b);
                        break;


                    case '/':
                        pilha.push(a / b);
                        break;
                }
            }
        }
        return pilha.pop();
    }


    public static boolean isOperador(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }


    public static int precedencia(char operador){
        switch(operador){
            case '+':
            case '-':
                return 1;


            case '*':
            case '/':
                return 2;


            default:
                return -1;
        }
    }


    public static boolean isNumero(String token){
        try{
            Double.parseDouble(token);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}

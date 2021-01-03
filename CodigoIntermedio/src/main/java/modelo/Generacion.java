/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Stack;

/**
 *
 * @author spart
 */
public class Generacion {

    private String funcionInfija;
    private String funcionPostfija;

    public Generacion(String funcionInfija) {
        this.funcionInfija = funcionInfija;
        funcionPostfija = "";
    }

    public void generarPostFija(String infija) {
        Stack<String> pila = new Stack<>();
        for (int i = 0; i < infija.length(); i++) {
            if (esNumero(Character.toString(infija.charAt(i)))) {
                funcionPostfija += infija.charAt(i);
            }
            if (esSimbolo(infija.charAt(i))) {
                if (!pila.isEmpty()) {
                    if (esMayorPrecedencia(infija.charAt(i), pila.peek().charAt(0))) {
                        pila.push(Character.toString(infija.charAt(i)));
                    } else {
                        if (esMenorPrecedencia(infija.charAt(i), pila.peek().charAt(0))) {
                            int tam = pila.size();
                            for (int j = 0; j < tam; j++) {
                                if (esSignoAbre(pila.peek().charAt(0))) {
                                    pila.pop();
                                }else{
                                funcionPostfija += pila.pop();
                            }
                            }
                            pila.push(Character.toString(infija.charAt(i)));
                        } else {
                            if (esIgualPrecedencia(infija.charAt(i), pila.peek().charAt(0))) {
                                funcionPostfija += pila.pop();
                                pila.push(Character.toString(infija.charAt(i)));
                            } else {
                                if (esSignoCierre(infija.charAt(i))) {
                                    for (int j = 0; j < pila.size(); j++) {
                                        if (esSignoAbre(pila.peek().charAt(0))) {
                                    pila.pop();
                                }else{
                                funcionPostfija += pila.pop();
                            }
                                    }
                                }else{
                                    if (esSignoAbre(infija.charAt(i))) {
                                       pila.push(Character.toString(infija.charAt(i)));
                                    }
                                }
                            }
                        }

                    }
                } else {
                    pila.push(Character.toString(infija.charAt(i)));
                }
            }
        }
        if (!pila.isEmpty()) {
            int tam = pila.size();
            for (int i = 0; i < tam; i++) {
                 if (esSignoAbre(pila.peek().charAt(0))) {
                                    pila.pop();
                                }else{
                                funcionPostfija += pila.pop();
                            }
            }
        }
    }

    public boolean esNumero(String text) {
        if (text.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public boolean esSignoCierre(char text) {
        return text == ')' || text == '}' || text == ']';
    }
    public boolean esSignoAbre(char text) {
        return text == '(' || text == '{' || text == '[';
    }

    public boolean esSimbolo(char text) {
        return text == ')' || text == '}' || text == ']' || text == '√' || text == '*' || text == '/' || text == '+' || text == '-' || text == '^' || text== '(' || text== '{' || text== '[';
    }

    public boolean esMenorPrecedencia(char nuevo, char pop) {
        if ((nuevo == ('+') || nuevo == ('-')) && (pop == ('*') || pop == ('/') || pop == ('√') || pop == ('^'))) {
            return true;
        }
        if ((nuevo == ('*') || nuevo == ('/')) && (pop == ('√') || pop == ('^'))) {
            return true;
        }
        return false;
    }

    public boolean esMayorPrecedencia(char nuevo, char pop) {
        if ((nuevo == ('*') || nuevo == ('/')) && (pop == ('-') || pop == ('+') || pop== '(' || pop== '{' || pop== '[')) {
            return true;
        }
        if ((nuevo == ('^') || nuevo == ('√')) && (pop == ('*') || pop == ('/') || pop == ('-') || pop == ('+') || pop== '(' || pop== '{' || pop== '[')) {
            return true;
        }
        if ((nuevo == ('+') || nuevo == ('-')) && ( pop== '(' || pop== '{' || pop== '[')) {
            return true;
        }
        return false;
    }

    public boolean esIgualPrecedencia(char nuevo, char pop) {
        if ((nuevo == ('*') || nuevo == ('/')) && (pop == ('*') || pop == ('/'))) {
            return true;
        }
        if ((nuevo == ('+') || nuevo == ('-')) && (pop == ('-') || pop == ('+'))) {
            return true;
        }
        if ((nuevo == ('^') || nuevo == ('√')) && (pop == ('^') || pop == ('√'))) {
            return true;
        }
        return false;
    }

    public String getFuncionInfija() {
        return funcionInfija;
    }

    public void setFuncionInfija(String funcionInfija) {
        this.funcionInfija = funcionInfija;
    }

    public String getFuncionPostfija() {
        return funcionPostfija;
    }

    public void setFuncionPostfija(String funcionPostfija) {
        this.funcionPostfija = funcionPostfija;
    }

}

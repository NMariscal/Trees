import ejercicios.BinaryTreeUtils;
import ejercicios.NAryTreeUtils;
import material.BinaryTree.BinaryTree;
import material.BinaryTree.LinkedBinaryTree;
import material.NAryTree.LinkedTree;
import material.NAryTree.NAryTree;
import material.Position;
import material.iterators.PreorderBinaryTreeIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BinaryTree<Integer> arbolBinarioEnteros = new LinkedBinaryTree<>();
        Position<Integer> raiz = arbolBinarioEnteros.addRoot(8);
        Position<Integer> n1 = arbolBinarioEnteros.insertLeft(raiz, 7);
        Position<Integer> n2 = arbolBinarioEnteros.insertRight(raiz, 6);

        Position<Integer> n3 = arbolBinarioEnteros.insertLeft(n1, 5);

        Position<Integer> n4 = arbolBinarioEnteros.insertRight(n1, 4);
        Position<Integer> n5 = arbolBinarioEnteros.insertLeft(n4, 3);

        Position<Integer> n6 = arbolBinarioEnteros.insertLeft(n2, 2);
        Position<Integer> n7 = arbolBinarioEnteros.insertRight(n2, 1);



        /*Iterator<Position<Integer>> it = new PreorderBinaryTreeIterator<>(arbolBinarioEnteros, raiz);
        while (it.hasNext()){
            System.out.println(it.next().getElement());
        }*/

        System.out.println("Es arbol vacio = " + arbolBinarioEnteros.isEmpty());
        /*BinaryTreeUtils<Integer> binaryTreeUtils = new BinaryTreeUtils<>(arbolBinarioEnteros);
        boolean solution = binaryTreeUtils.isPerfect(arbolBinarioEnteros);
        System.out.println(solution);*/

        // arbol NArio
        LinkedTree<Integer> arbolNArioEnteros = new LinkedTree<>();

        Position<Integer> positionRaiz = arbolNArioEnteros.addRoot(5);


        Position<Integer> primerHijo = arbolNArioEnteros.add (10,positionRaiz);
        Position<Integer> segundoHijo = arbolNArioEnteros.add (20,positionRaiz);
        Position<Integer> tercerHijo = arbolNArioEnteros.add (30,positionRaiz);

        Position<Integer> nieto1 = arbolNArioEnteros.add (100, primerHijo);
        /*Position<Integer> N1 = arbolNArioEnteros.add(9, arbolNArioEnteros.root());
        Position<Integer> N2 = arbolNArioEnteros.add(8, arbolNArioEnteros.root());
        Position<Integer> N3 = arbolNArioEnteros.add(7, arbolNArioEnteros.root());
        Position<Integer> N4 = arbolNArioEnteros.add(6, N1);
        Position<Integer> N5 = arbolNArioEnteros.add(5, N2);
        Position<Integer> N6 = arbolNArioEnteros.add(4, N2);
        Position<Integer> N7 = arbolNArioEnteros.add(3, N6);
        Position<Integer> N8 = arbolNArioEnteros.add(2, N3);*/

        NAryTreeUtils<Integer> nAryTreeUtils = new NAryTreeUtils<>(arbolNArioEnteros);
        System.out.println("Numero de hermanos del nodo que contiene el 8 = "+ nAryTreeUtils.numHermanos(positionRaiz));



    }
}

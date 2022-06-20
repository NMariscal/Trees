package ejercicios;

import javafx.geometry.Pos;
import material.BinaryTree.BinaryTree;
import material.BinaryTree.LinkedBinaryTree;
import material.Position;
import material.Tree;
import material.iterators.BFSIterator;

import java.util.ArrayList;

public class BinaryTreeUtils<E> {
    public LinkedBinaryTree<E> tree;

    public BinaryTreeUtils(LinkedBinaryTree<E> tree) {
        this.tree = tree;
    }

    // Altura de manera iterativa
    public int altura(){
        int max = 0;
        for(Position<E> position : tree){
            if (tree.isLeaf(position)){
                int alturaHoja = 1;
                while(!tree.isRoot(position)) {
                    alturaHoja++;
                    position = tree.parent(position);
                }
                if (alturaHoja > max){
                    max = alturaHoja;
                }
            }
        }
        return max;
    }

    // Altura de manera recursiva
    private int altura_2(Position<E> position){
        if (tree.isLeaf(position)){
            return 0;
        }else{
            int maxAlturaVista = 0;
            for(Position<E> hijo : tree.children(position)){
                int alturaHijo = altura_2(hijo);
                if (alturaHijo > maxAlturaVista){
                    maxAlturaVista = alturaHijo;
                }
            }
            return 1 + maxAlturaVista;
        }
    }


    public Iterable<E> ancestros(Position<E> position){
        ArrayList<E> ancestros = new ArrayList<>();
        while (!tree.isRoot(position)){
            ancestros.add(position.getElement());
            position = tree.parent(position);
        }
        return ancestros;
    }


    // Un árbol es perfecto si todos sus nodos internos tienen dos hijos
    public boolean isPerfect(BinaryTree<E> binaryTree){
       //Position<E> root = binaryTree.root();
        boolean isPerfect = true;
       for (Position<E> position : binaryTree){
           System.out.println("Position : "+ position.getElement());
           if(((binaryTree.hasLeft(position) && binaryTree.hasLeft(position)) || (!binaryTree.hasLeft(position) && binaryTree.hasLeft(position)) || (binaryTree.hasLeft(position) && !binaryTree.hasLeft(position)))){
                return false;
           }
       }
       return true;
    }
}

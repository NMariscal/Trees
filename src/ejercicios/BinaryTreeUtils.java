package ejercicios;

import javafx.geometry.Pos;
import material.BinaryTree.BinaryTree;
import material.BinaryTree.LinkedBinaryTree;
import material.Position;
import material.Tree;

import java.util.ArrayList;

public class BinaryTreeUtils<E> {
    public Tree<E> tree = new LinkedBinaryTree<>();

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
}

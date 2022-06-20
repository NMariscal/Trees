package ejercicios;

import javafx.geometry.Pos;
import material.NAryTree.LinkedTree;
import material.NAryTree.NAryTree;
import material.Position;
import material.Tree;

import java.util.ArrayList;

public class NAryTreeUtils<E> {
    LinkedTree<E> tree ;

    public NAryTreeUtils(LinkedTree<E> tree) {
        this.tree = tree;
    }

    // EERCICIO 1 NARIOS : Implementar una función que incremente la funcionalidad de la clase LinkedTree;

    // La función deberá devolver el nivel de position pasado y si este position no pertenece al arbol lanza una excepcion
    public int level(Position<E> position) throws IllegalStateException{
        int level = 0;
        while (tree.isRoot(position)){
            level ++;
            position = tree.parent(position);
        }
        return level;
    }


    // EJERCICIO 2 NARIOS : devolver el número de hermanos que tiene un position determinado
    public int numHermanos(Position<E> position)throws IllegalStateException{
        int counter = 0;
        if (tree.isRoot(position)){
            return 0;
        }else {
            Position<E> parent = tree.parent(position);
            for (Position<E> node : tree.children(parent)) {
                counter++;
            }
        }
        // Le quito uno porque el nodo que nos dan no es hermano de si mismo
        return counter - 1;
    }

    public Iterable<E> preorden(){
        ArrayList<E> recorrido = new ArrayList<>();
        if (!tree.isEmpty()){
            Position<E> root = tree.root();
            preorden(root, recorrido);
        }
        return recorrido;
    }

    public void preorden(Position<E> root, ArrayList<E> recorrido){
        recorrido.add(root.getElement());
        for(Position<E> position : tree.children(root)) {
            preorden(position, recorrido);
        }
    }

    public Iterable<Position<E>> front(){
        ArrayList<Position<E>> recorrido = new ArrayList<>();
        for (Position<E> position : tree){
            if (tree.isLeaf(position)){
                recorrido.add(position);
            }
        }
        return recorrido;
    }
}

package ejercicios;

import material.Position;
import material.Tree;

public class TreeUtils<E> {
    Tree<E> tree ;

    public TreeUtils(Tree<E> tree) {
        this.tree = tree;
    }

    public int numHermanos (Position<E> p) {
        if (tree.isRoot(p)) {
            return 0;
        }else{
            Position<E> padre = tree.parent(p);
            //Tengo que contar todos los hijos que tiene ese position Padre
            int contHijos = 0;
            for (Position<E> hijo: tree.children(padre)) {
                contHijos++;
            }
            return contHijos-1;
        }
    }
}

package ejercicios;

import material.NAryTree.LinkedTree;
import material.Position;
import material.Tree;

import java.util.HashSet;

public class CommonAncestorTree<E> {
    public LinkedTree<E> tree;

    public CommonAncestorTree(LinkedTree<E> tree) {
        this.tree = tree;
    }

    // Crear una copia del arbol cuya raiz es el ancestroco
    public Tree<E> createCommonTreeAncestor(Position<E> n1, Position<E> n2){
        // Creamos un mapa con todos los ancestros de n1
        HashSet<E> ancestros = new HashSet<>();
        while(!tree.isRoot(n1)){
            ancestros.add(n1.getElement());
            n1 = tree.parent(n1);
        }

        // Recoremos el mapa hasta que encontremos un ancestro común
        while (!ancestros.contains(n2)){
            n2 = tree.parent(n2);
        }

        // Una vez hallado el ancestro comun lo ponemos como raiz del nuevo arbol y copiamos el arbol a partir de ahi
        LinkedTree<E> newTree = new LinkedTree<>();
        newTree.addRoot(n2.getElement());
        CopiarArbol(newTree, newTree.root(), tree, n2);

        return newTree;
    }

    private void CopiarArbol(LinkedTree<E> arbolCopia, Position<E> raizCopia, LinkedTree<E> arbol, Position<E> raiz){
        for (Position<E> hijo : tree.children(raiz)){
            Position<E> aux = arbolCopia.add(hijo.getElement(), raizCopia);
            CopiarArbol(arbolCopia, aux, arbol, hijo);
        }

    }
}

import material.BinaryTree.BinaryTree;
import material.BinaryTree.LinkedBinaryTree;
import material.Position;
import material.iterators.PreorderBinaryTreeIterator;
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

        Iterator<Position<Integer>> it = new PreorderBinaryTreeIterator<>(arbolBinarioEnteros, raiz);
        while (it.hasNext()){
            System.out.println(it.next().getElement());
        }

    }
}

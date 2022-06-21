package ejercicios;


import material.BinaryTree.BinaryTree;
import material.Position;
import material.Tree;
import material.iterators.BFSIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class WeightedLinkedBinaryTree<E> implements Tree<E> {

    public int getWeight(Position<E> parent, Position<E> child) {
        BTNode<E> p = checkPosition(parent);
        BTNode<E> co = checkPosition(child);
        int result = 0;
        while (this.parent(child)  != parent){
            BTNode<E> c = checkPosition(child);
            result += c.getWeight();
            child = this.parent(child);
        }
        return result;
    }


    protected class BTNode<T> implements Position<T> {

        private T element;
        private BTNode<T> left, right, parent;
        private int weight;

        /**
         * Main constructor
         *
         * @param element
         * @param parent
         * @param left
         * @param right
         */
        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right, int weight) {
            setElement(element);
            setParent(parent);
            setLeft(left);
            setRight(right);
            setWeight(weight);
        }

        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            setElement(element);
            setParent(parent);
            setLeft(left);
            setRight(right);

        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        /**
         * Returns the element stored at this position
         *
         * @return
         */
        @Override
        public T getElement() {
            return element;
        }

        /**
         * Sets the element stored at this position
         *
         * @param o
         */
        public final void setElement(T o) {
            element = o;
        }

        /**
         * Returns the left child of this position
         *
         * @return
         */
        public final BTNode<T> getLeft() {
            return left;
        }

        /**
         * Sets the left child of this position
         *
         * @param v
         */
        public final void setLeft(BTNode<T> v) {
            left = v;
        }

        /**
         * Returns the right child of this position
         *
         * @return
         */
        public final BTNode<T> getRight() {
            return right;
        }

        /**
         * Sets the right child of this position
         *
         * @param v
         */
        public final void setRight(BTNode<T> v) {
            right = v;
        }

        /**
         * Returns the parent of this position
         *
         * @return
         */
        public final BTNode<T> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this position
         *
         * @param v
         */
        public final void setParent(BTNode<T> v) {
            parent = v;
        }
    }

    private BTNode<E> root;
    private int size;

    /**
     * Creates an empty binary tree.
     */
    public WeightedLinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns whether a node is internal.
     *
     * @param v
     * @return
     */
    @Override
    public boolean isInternal(Position<E> v) {
        checkPosition(v);
        return (hasLeft(v) || hasRight(v));
    }

    /**
     * Returns whether a node is external.
     *
     * @param p
     * @return
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return !isInternal(p);
    }

    /**
     * Returns whether a node is the root.
     *
     * @param p
     * @return
     */
    @Override
    public boolean isRoot(Position<E> p) {
        checkPosition(p);
        return (p == root());
    }

    /**
     * Returns whether a node has a left child.
     *
     * @param p
     * @return
     */
    public boolean hasLeft(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return (node.getLeft() != null);
    }

    /**
     * Returns whether a node has a right child.
     *
     * @param p
     * @return
     */
    public boolean hasRight(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return (node.getRight() != null);
    }

    /**
     * Returns the root of the tree.
     *
     * @return
     */
    @Override
    public Position<E> root() {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the left child of a node.
     *
     * @param p
     * @return
     */
    public Position<E> left(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos == null) {
            throw new RuntimeException("No left child");
        }
        return leftPos;
    }

    /**
     * Returns the right child of a node.
     *
     * @param p
     * @return
     */
    public Position<E> right(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos == null) {
            throw new RuntimeException("No right child");
        }
        return rightPos;
    }

    /**
     * Returns the parent of a node.
     *
     * @param p
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     *
     * @param p
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
        List<Position<E>> children = new ArrayList<>();
        if (hasLeft(p)) {
            children.add(left(p));
        }
        if (hasRight(p)) {
            children.add(right(p));
        }
        return Collections.unmodifiableCollection(children);
    }

    /**
     * Returns an iterator of the elements stored at the nodes.
     */
    @Override
    public Iterator<Position<E>> iterator() {
        return new BFSIterator<>(this, root);
    }

    /**
     * Replaces the element at a node.
     *
     * @param p updated position.
     * @param e element introduced in position p.
     * @return
     */
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Return the sibling of a node
     *
     * @param p
     * @return
     */
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> parentPos = node.getParent();
        if (parentPos != null) {
            BTNode<E> sibPos;
            BTNode<E> leftPos = parentPos.getLeft();
            if (leftPos == node) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new RuntimeException("No sibling");
    }

    /**
     * Adds a root node to an empty tree
     *
     * @param e
     * @return
     */
    @Override
    public Position<E> addRoot(E e) {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        size = 1;
        root = new BTNode<>(e, null, null, null, 0);
        return root;
    }

    /**
     * Inserts a left child at a given node.
     *
     * @param p
     * @param e
     * @return
     */
    public Position<E> insertLeft(Position<E> p, E e, int weight) {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos != null) {
            throw new RuntimeException("Node already has a left child");
        }
        BTNode<E> newNode = new BTNode<>(e, node, null, null, weight);

        node.setLeft(newNode);
        size++;
        return newNode;
    }

    /**
     * Inserts a right child at a given node.
     *
     * @param p
     * @param e
     * @return
     */
    public Position<E> insertRight(Position<E> p, E e, int weight) {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos != null) {
            throw new RuntimeException("Node already has a right child");
        }
        BTNode<E> newNode = new BTNode<>(e, node, null, null, weight);

        node.setRight(newNode);
        size++;
        return newNode;
    }

    /**
     * Removes a node with zero or one child.
     *
     * @param p
     * @return
     */
    public E remove(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> leftPos = node.getLeft();
        BTNode<E> rightPos = node.getRight();
        if (leftPos != null && rightPos != null) {
            throw new RuntimeException("Cannot remove node with two children");
        }
        BTNode<E> child; // the only child of v, if any
        if (leftPos != null) {
            child = leftPos;
        } else if (rightPos != null) {
            child = rightPos;
        } else // v is a leaf
        {
            child = null;
        }
        if (node == root) { // v is the root
            if (child != null) {
                child.setParent(null);
            }
            root = child;
        } else { // v is not the root
            BTNode<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
            if (child != null) {
                child.setParent(parent);
            }
        }
        size--;
        return p.getElement();
    }

    /**
     * Attaches two trees to be subtrees of a leaf node.
     *
     * @param p
     * @param t1
     * @param t2
     */
    public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) {
        BTNode<E> node = checkPosition(p);
        if (isInternal(p)) {
            throw new RuntimeException("Cannot attach from internal node");
        }
        if ((t1 == this) || (t2 == this)) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }

        int newSize = size + t1.size() + t2.size();
        if (!t1.isEmpty()) {
            BTNode<E> r1 = checkPosition(t1.root());
            node.setLeft(r1);
            r1.setParent(node); // T1 should be invalidated
        }
        if (!t2.isEmpty()) {
            BTNode<E> r2 = checkPosition(t2.root());
            node.setRight(r2);
            r2.setParent(node); // T2 should be invalidated
        }
        size = newSize;
    }

    /**
     * Swap the elements at two nodes
     *
     * @param p1
     * @param p2
     */
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> node1 = checkPosition(p1);
        BTNode<E> node2 = checkPosition(p2);

        BTNode<E> copyNode1 = new BTNode<>(node1.element, node1.parent, node1.left, node1.right);

        node1.parent = node2.parent == node1 ? node2 : node2.parent;
        node1.left = node2.left == node1 ? node2 : node2.left;
        node1.right = node2.right == node1 ? node2 : node2.right;

        node2.parent = copyNode1.parent == node2 ? node1 : copyNode1.parent;
        node2.left = copyNode1.left == node2 ? node1 : copyNode1.left;
        node2.right = copyNode1.right == node2 ? node1 : copyNode1.right;

        if (node1.parent != null) {
            if (node1.parent.left == node2) {
                node1.parent.left = node1;
            } else {
                node1.parent.right = node1;
            }
        }

        if (node2.parent != null) {
            if (node2.parent.left == node1) {
                node2.parent.left = node2;
            } else {
                node2.parent.right = node2;
            }
        }
    }

    // Auxiliary methods
    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    private BTNode<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof BTNode)) {
            throw new RuntimeException("The position is invalid");
        }
        return (BTNode<E>) p;
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     *
     * @param p
     * @param pos
     */
    protected void preorderPositions(Position<E> p, List<Position<E>> pos) {
        pos.add(p);
        if (hasLeft(p)) {
            preorderPositions(left(p), pos); // recurse on left child
        }
        if (hasRight(p)) {
            preorderPositions(right(p), pos); // recurse on right child
        }
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     *
     * @param v
     * @param pos
     */
    protected void inorderPositions(Position<E> v, List<Position<E>> pos) {
        if (hasLeft(v)) {
            inorderPositions(left(v), pos); // recurse on left child
        }
        pos.add(v);
        if (hasRight(v)) {
            inorderPositions(right(v), pos); // recurse on right child
        }
    }

    /**
     * @return The size above a node
     */
    private int calculateSize(BTNode<E> n) {
        if (n != null) {
            return 1 + calculateSize(n.getLeft()) + calculateSize(n.getRight());
        } else {
            return 0;
        }
    }

    /**
     * Convert the node passed by parameter in the new root.
     *
     * @param v new root node
     */
    public void subTree(Position<E> v) {
        root = checkPosition(v);
        size = calculateSize(root);
    }
}

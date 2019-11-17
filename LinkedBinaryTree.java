/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import colas.LinkedQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author jedah
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode(element);
    }

    public LinkedBinaryTree(T element, LinkedBinaryTree<T> leftSubtree, LinkedBinaryTree<T> rightSubtree) {
        root = new BinaryTreeNode(element);
        count = 1;
        if (leftSubtree != null) {
            count = count + leftSubtree.size();
            root.left = leftSubtree.root;
        } else {
            root.left = null;

        }
        if (rightSubtree != null) {
            count = count + rightSubtree.size();
            root.right = rightSubtree.root;
        } else {
            root.right = null;

        }
    }

    @Override
    public void removeLeftSubtree() {
        if (root.left != null) {
            count = count - root.left.numChildren() - 1;
            root.left = null;
        }

    }

    @Override
    public void removeRightSubtree() {
        if (root.right != null) {
            count = count - root.right.numChildren();
            root.right = null;
        }
    }

    @Override
    public void removeAllElements() {
        this.removeLeftSubtree();
        this.removeRightSubtree();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T element) {
        return findAgain(element, root)!=null;
    }

    @Override
    public T find(T element) throws NoSuchElementException {
        BinaryTreeNode<T> current = findAgain(element, root);
        if (current == null) {
            throw new NoSuchElementException("Binarytree");
        }
        return current.element;
    }

    private BinaryTreeNode<T> findAgain(T element, BinaryTreeNode next) {
        if (next == null) {
            return null;
        }
        if (next.element.equals(element)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(element, next.left);
        if (temp == null) {
            temp = findAgain(element, next.right);
        }
        return temp;
    }

    @Override
    /*visita el hijo izquierdo del nodo luego el nodo 
    y luego los nodos restantes, comenzando por la raiz
    recorrer (hijo izquierdo)
    visitar nodo
    recorrer (hijo derecho)
    D, B, E, A, C
     */
    public Iterator<T> iteratorInOrder() {
        ArrayList<T> templist = new ArrayList<T>();
        inorder(root, templist);
        return templist.iterator();
    }

    protected void inorder(BinaryTreeNode<T> node, ArrayList<T> templist) {
        if (node != null) {
            inorder(node.left, templist);
            templist.add(node.element);
            inorder(node.right, templist);
        }
    }

    @Override
    /* visita cada nodo seguido de sus hijos  comenzando por la raiz
    A,B,D,E,C
    visitar nodo
    recorre hijo izquierdo
    recorre hijo derecho
     */
    public Iterator<T> iteratorPreOrder() {
        ArrayList<T> templist = new ArrayList<T>();
        preorder(root, templist);
        return templist.iterator();
    }

    protected void preorder(BinaryTreeNode<T> node, ArrayList<T> templist) {
        if (node != null) {
            templist.add(node.element);
            preorder(node.left, templist);
            preorder(node.right, templist);
        }
    }

    /**
     * visita primer los hijos y luego el nodo comenzando por la raiz D,E,B,C,A
     * recorrer (hijo izquierdo) recorrer (hijo derecho) visitar nodo
     *
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayList<T> templist = new ArrayList<T>();
        posorder(root, templist);
        return templist.iterator();
    }

    protected void posorder(BinaryTreeNode<T> node, ArrayList<T> templist) {
        if (node != null) {
            posorder(node.left, templist);
            posorder(node.right, templist);
            templist.add(node.element);

        }
    }

    /*   recorre por orden de niveles
    A,B,C,D,E
    Crear una cola denominada nodos
    crear una lista denominada resultado
    introducir la raiz en la cola de nodos
    mientras que la coola de nodos no este vacia{
        sacar el primer elemento de la cola
        si dicho elemento no es nulo{
            añadir dicho elemento al final de la lista de resultado
            introducir en la cola los hijos del elemento
        }
    en caso contrario{
        añadir el valor nulo a la lista de resultados
        }
    }
    devolver un iterador para la lista de resultados
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayList<T> resultado = new ArrayList();
        levelOrder(root, resultado);
        return resultado.iterator();
    }
protected void levelOrder(BinaryTreeNode<T> node, ArrayList<T> tempList){
        /*if(node != null){
            levelOrder(node.left, tempList);
            levelOrder(node.right, tempList);
            tempList.add(node.element);
            LinkedQueue nodos = new LinkedQueue();
            ArrayList resultado = new ArrayList();
            nodos.enqueue(root);
            while(nodos.size()!=0){
                BinaryTreeNode firstElement = (BinaryTreeNode)nodos.dequeue();
                if(firstElement!=null){
                    resultado.add(firstElement);
                    nodos.enqueue(firstElement.left);
                    nodos.enqueue(firstElement.right);
                }else{
                    resultado.add(null);
                }
                
            }
        }*/
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            tempList.add((T) current.element);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}

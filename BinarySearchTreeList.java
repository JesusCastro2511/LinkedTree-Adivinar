/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.Iterator;
import listas.OrderedListADT;

/**
 *
 * @author jedah
 */
public class BinarySearchTreeList<T> extends LinkedBinarySearchTree<T>
        implements listas.ListADT<T>, OrderedListADT<T> {

    @Override
    public T removeFirst() {
       return removeMin();
    }

    @Override
    public T removeLast() {
        return removeMax();
    }

    @Override
    public T remove(T element) {
        return removeElemtent(element);
    }

    @Override
    public T first() {
     return findMin();
    }

    @Override
    public T last() {
        return findMax();
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

    @Override
    public void add(T element) {
        addElement(element);
    }

}

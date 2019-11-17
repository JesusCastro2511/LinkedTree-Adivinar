/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author jedah
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    public void addElement(T element);

    public T removeElemtent(T element);

    public void removeAllOcurrences(T element);

    public T removeMin();

    public T removeMax();

    public T findMin();

    public T findMax();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.NoSuchElementException;

/**
 *
 * @author jedah
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;
        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
        count++;
    }

    @Override
    public T removeElemtent(T element) throws NoSuchElementException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) element).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            }
        } else {
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;
            if (((Comparable) element).compareTo(root.element) < 0) {
                current = root.left;
            } else {
                current = root.right;
            }
            while (current != null && !found) {
                if (element.equals(current.element)) {
                    found = true;
                    count--;
                    result = current.element;
                    if (current == parent.left) {
                        parent.left = replacement(current);
                    } else {
                        parent.right = replacement(current);
                    }
                } else {
                    parent = current;
                    if (((Comparable) element).compareTo(current.element) < 0) {
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                }
            } //while
            if (!found) {
                throw new NoSuchElementException("Binary Tree");
            }
        }
        return result;
    }

    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if (node.left == null && node.right == null) {
            result = null;
        } else if (node.left != null && node.right == null) {
            result = node.left;
        } else if (node.left == null && node.right != null) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

    @Override
    public void removeAllOcurrences(T element) throws NoSuchElementException {
        while (contains(element)) {
            removeElemtent(element);
        }
    }

    public T removeMin() throws NoSuchElementException {
        T result = null;
        if (isEmpty()) {
            throw new NoSuchElementException("binary tree");
        } else {
            if (root.left == null) {
                result = root.element;
                root = root.right;
            } else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.left;
                while (current.left != null) {
                    parent = current;
                    current = current.left;
                }
                result = current.element;
                parent.left = current.right;
            }
            count--;
        }
        return result;
    }

    @Override
    public T removeMax() throws NoSuchElementException {
        T result = null;
        if (isEmpty()) {
            throw new NoSuchElementException("binary tree");
        } else {
            if (root.right == null) {
                result = root.element;
                root = root.left;
            } else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.right;

                while (current.right != null) {
                    parent = current;
                    current = current.right;
                }
                result = current.element;
                parent.right = current.left;
            }
            count--;
        }
        return result;
    }

    @Override
    public T findMin() throws NoSuchElementException {
        T result = null;
        if (isEmpty()) {
            throw new NoSuchElementException("binary tree");
        } else {
            BinaryTreeNode<T> current = root;
            while (current.left != null) {
                current = current.left;
            }
            result = current.element;
        }
        return result;
    }

    @Override
    public T findMax() throws NoSuchElementException {
        T result = null;
        if (isEmpty()) {
            throw new NoSuchElementException("binary tree");
        } else {
            BinaryTreeNode<T> current = root;
            while (current.right != null) {
                current = current.right;
            }
            result = current.element;
        }
        return result;
    }
}

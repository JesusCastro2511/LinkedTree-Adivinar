/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import conexion.BD;
import java.util.ArrayList;
/**
 *
 * @author jedah
 */
public class BinaryTreeNode<T> extends BD{

    protected T element;
    protected BinaryTreeNode<T> left, right;
    protected String id;
 // constructor para crear e insertar un nuevo nodo a la bd   
    public BinaryTreeNode(T object) {
        insertNewNode(object.toString());
        ArrayList array= selectNodeLast();
        id=array.get(0).toString();
        element = (T) array.get(1);
        left = null;
        right =null;
    }
  // contructor para crear un nuevo nodo a partir del array que se obtiene al consultar el nodo padre 
    public BinaryTreeNode(ArrayList array) {
        id= array.get(0).toString();
        element = (T) array.get(1);
        if (array.get(2)!= null) {
            left = new BinaryTreeNode<>(selectNode( array.get(2).toString()));
        }else{
            left=null;
        }
        if (array.get(3) != null) {
            right = new BinaryTreeNode<>(selectNode(array.get(3).toString()));
        }else{
            right=null;
        }
        
    }
    // este constructor nos ayudara a cargar el id 1 que sera siempre root
    public BinaryTreeNode() {
        ArrayList array= selectNode("1");
        id= array.get(0).toString();
        element= (T) array.get(1);
        left = new BinaryTreeNode<>((selectNode(array.get(2).toString())));
        right = new BinaryTreeNode<>((selectNode(array.get(3).toString())));
    }
    
    public int numChildren(){
        int children=0;
        if(left != null){
            children=children+1+left.numChildren();
        }
        if (right!= null) {
            children = children+1+right.numChildren();
        }
        return children;
    }
}

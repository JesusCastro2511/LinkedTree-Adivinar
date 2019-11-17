/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import conexion.BD;
import java.util.Scanner;

/**
 *
 * @author jedah
 */
public class Memoria extends BD{

    private Scanner leer = new Scanner(System.in);

    public boolean si(String pregunta) {
        System.out.println(pregunta);
        String resp = leer.nextLine();
        return "si".equals(resp.toLowerCase());
    }

    public void ejecutar() {
        boolean bucle = true;
        while (bucle) {
            BinaryTreeNode<String> arbol = new BinaryTreeNode<>();
            if (!si("Estas pensando en un animal?")) {
                break;
            }
            while (arbol.left != null) {
                if (si(arbol.element + " ?")) {
                    arbol = arbol.left;
                } else {
                    arbol = arbol.right;
                }
            }
            //adivinar 
            String animal= arbol.element;
            if (si("Es un "+ animal + " ?")) {
                System.out.println("Adivine!!");
                continue;
            }
            //obtenemos  nueva informacion
            System.out.println("Que animal era?");
            String nuevo = leer.nextLine();
            System.out.println("Que diferencia a un "+animal + " de un "+nuevo+" ?");
            String info = leer.nextLine();
            String indicador= "Si el animal fuera un "+animal+" este "+info+" ?";
            if (si(indicador)) {
                arbol.left= new BinaryTreeNode<>(animal);
                arbol.right= new BinaryTreeNode<>(nuevo);
            }else{
                arbol.left= new BinaryTreeNode<>(nuevo);
                arbol.right=new BinaryTreeNode<>(animal);
            }
            updateNode(arbol.id, info,arbol.left.id, arbol.right.id);
            /* se pone a null para que el recolector de basura lo quite de memoria y asi poder seguir consultando el root
            actualizado de la base de datos */
            arbol=null;
        }
    }
}

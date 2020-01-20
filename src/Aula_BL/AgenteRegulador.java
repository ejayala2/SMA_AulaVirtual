/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_BL;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author Joel
 */
public class AgenteRegulador extends Agent {

    public int temperatura;
    String msj;
    int cont = 0;
    ImageIcon imgtemp[] = new ImageIcon[5];

    class Regulador extends CyclicBehaviour {

        GUI UI = new GUI();

        public void action() {
            ACLMessage mensaje = blockingReceive();

            if (mensaje != null) {

                System.out.println("------Ejecutando Agentes------");
                System.out.println("Temperatura: " + mensaje.getContent() +"°C ");
                temperatura = Integer.parseInt(mensaje.getContent());

                UI.setVisible(true);

                temperatura = Integer.parseInt(mensaje.getContent());
                if (temperatura >= 24) {
                    msj = "Enciende Aire acondicionado";
                    System.out.println(msj);
                    imgtemp[3] = new ImageIcon(getClass().getResource("/IMG/temp3.png"));
                    UI.lbltemp.setIcon(imgtemp[3]);
                    UI.lblmsj.setText(temperatura + "°C " + msj);
                    UI.lblactivo.setVisible(true);
                    UI.lblactivo2.setVisible(false);
                } else if (temperatura <= 18) {
                    msj = "Enciende Calefacción";
                    System.out.println(msj);
                    imgtemp[2] = new ImageIcon(getClass().getResource("/IMG/temp2.png"));
                    UI.lbltemp.setIcon(imgtemp[2]);
                    UI.lblmsj.setText(temperatura + "°C " + msj);
                    UI.lblactivo.setVisible(false);
                    UI.lblactivo2.setVisible(true);
                } else {
                    msj = "Temperatura adecuada";
                    System.out.println(msj);
                    imgtemp[4] = new ImageIcon(getClass().getResource("/IMG/temp4.png"));
                    UI.lbltemp.setIcon(imgtemp[4]);
                    UI.lblmsj.setText(temperatura + "°C " + msj);
                    UI.lblactivo.setVisible(false);
                    UI.lblactivo2.setVisible(false);
                }
            }
            for (int i = 1; i < imgtemp.length; i++) {
                imgtemp[i] = new ImageIcon(getClass().getResource("/IMG/temp" + i + ".png"));
            }
        }
    }

    protected void setup() {
        System.out.println("Estado: " + getAgentState());
        System.out.println("Agente: " + getLocalName());
        addBehaviour(new Regulador());
    }
}

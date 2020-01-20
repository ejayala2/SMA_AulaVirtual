/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_BL;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Joel
 */
public class AgenteTemperatura extends Agent {

    int temperatura = 0;
   
    class Temp extends Behaviour {
        
        long intervalo = 10000;
        int cont = 0;

        public void action() {
            temperatura = (int) (Math.random() * 38) + 5;

            AID id_Agent  = new AID();
            id_Agent.setLocalName("Regulador");
            ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
            mensaje.setSender(getAID());
            mensaje.setLanguage("Español");
            mensaje.addReceiver(id_Agent );
            mensaje.setContent(String.valueOf(temperatura));
            send(mensaje);
            block(intervalo);
        }

        public boolean done() {
            return false;
        }
    }

    protected void setup() {
        System.out.println("Estado: " + getAgentState());
        System.out.println("Agente: " + getLocalName());
        addBehaviour(new Temp());
    }
}

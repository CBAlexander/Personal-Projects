package uk.co.coryalexander.conquer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MouseHandler implements MouseListener {

    Map map;

    public MouseHandler(Map m) {
        map = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean pressingButton = false;
        for(Vertex v : map.getGraph().getVertices()) {
            v.setPotential(false);
        }
        System.out.println(String.format("X: %d | Y: %d", e.getX(), e.getY()));
        boolean found = false;
        for(Vertex v : map.getGraph().getVertices()) {
            if(e.getX() >= v.getX() && e.getX() <= v.getX() + v.getSize() && e.getY() >= v.getY() && e.getY() <= v.getY() + v.getSize()) {
                System.out.println(v.getName());
                if(e.getButton() == MouseEvent.BUTTON1) {
                   if(v.isPlayerOwned()) map.setSelected(v);
                }
                found = true;
            }
        }


        for(ActionButton button : map.getButtons()) {
            if(e.getX() >= button.getX() && e.getX() <= button.getX() + button.getWidth() && e.getY() >= button.getY() && e.getY() <= button.getY() + button.getHeight()) {
                if(button.isVisible()) {
                    if(button.getName().equals("MOVE")) {
                        if(map.getSelected().getArmyCount() > 1) {
                            map.getSelected().setArmyCount(map.getSelected().getArmyCount() - 1);
                            button.getAssociatedVertex().setArmyCount(button.getAssociatedVertex().getArmyCount() + 1);
                        }
                        map.setTurnOver(true);

                    }

                    if(button.getName().equals("ATTACK")) {
                        int attackForce, defendForce, chance, roll;

                        attackForce = map.getSelected().getArmyCount();
                        defendForce = button.getAssociatedVertex().getArmyCount();

                        chance = new Random().nextInt(attackForce + defendForce);

                        if(chance < attackForce) {

                        } else {

                        }
                        map.setTurnOver(true);

                    }

                    if(button.getName().equals("REINFORCE")) {
                        if(map.getReinforcements() > 0) {
                            map.getSelected().setArmyCount(map.getSelected().getArmyCount() + 1);
                            map.setReinforcements(map.getReinforcements()-1);
                            map.setTurnOver(true);

                        }
                    }
                    pressingButton = true;
                }
            }
        }

        if(!found && !pressingButton) {
            map.setSelected(null);
            map.setTargeted(null);

        }

        for(ActionButton button : map.getButtons()) {
           button.setVisible(false);
        }
        if(map.getSelected() != null) {
            ActionButton reinforce = new ActionButton("REINFORCE", map.getSelected().getX() + map.getSelected().getSize() + 10,map.getSelected().getY(), 125, 25, map.getSelected());
            map.getButtons().add(reinforce);
            reinforce.setVisible(true);
            for (Edge edge : map.getGraph().getEdgesFrom(map.getSelected().getName())) {
                if (edge.getStartVertex().getName().equals(map.getSelected().getName())) {
                    edge.getEndVertex().setPotential(true);
                    if (map.getSelected().isPlayerOwned() && edge.getEndVertex().isPlayerOwned()) {
                        ActionButton move = new ActionButton("MOVE", edge.getEndVertex().getX() + edge.getEndVertex().getSize() + 10, edge.getEndVertex().getY(), 75, 25, edge.getEndVertex());
                        map.getButtons().add(move);
                        move.setVisible(true);
                    }
                    if ((map.getSelected().isPlayerOwned()) && !(map.getSelected().isPlayerOwned() && edge.getEndVertex().isPlayerOwned())) {
                        ActionButton attack = new ActionButton("ATTACK", edge.getEndVertex().getX() + edge.getEndVertex().getSize() + 10, edge.getEndVertex().getY(), 75, 25, edge.getEndVertex());
                        map.getButtons().add(attack);
                        attack.setVisible(true);

                    }
                    if (!(map.getSelected().isPlayerOwned() && edge.getEndVertex().isPlayerOwned())) {

                    }
                } else {
                    edge.getStartVertex().setPotential(true);
                    if (map.getSelected().isPlayerOwned() && edge.getStartVertex().isPlayerOwned()) {
                        ActionButton move = new ActionButton("MOVE", edge.getStartVertex().getX() + edge.getStartVertex().getSize() + 10, edge.getStartVertex().getY(), 75, 25, edge.getStartVertex());
                        map.getButtons().add(move);
                        move.setVisible(true);

                    }
                    if ((map.getSelected().isPlayerOwned()) && !(map.getSelected().isPlayerOwned() && edge.getStartVertex().isPlayerOwned())) {
                        ActionButton attack = new ActionButton("ATTACK", edge.getStartVertex().getX() + edge.getStartVertex().getSize() + 10, edge.getStartVertex().getY(), 75, 25, edge.getStartVertex());
                        map.getButtons().add(attack);
                        attack.setVisible(true);

                    }
                    if (!(map.getSelected().isPlayerOwned() && edge.getStartVertex().isPlayerOwned())) {

                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

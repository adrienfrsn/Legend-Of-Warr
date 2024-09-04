package main.java;

import java.util.ArrayList;

public class Turn {
    private Joueur player;
    private Mob mob;
    private ArrayList<Competence> effectsQueuePlayer;
    private ArrayList<Competence> effectsQueueMob;
    public Turn(Joueur player, Mob mob) {
        this.player = player;
        this.mob = mob;
        this.effectsQueuePlayer = new ArrayList<Competence>();
        this.effectsQueueMob = new ArrayList<Competence>();
    }

    public Joueur getJoueur() {
        return this.player;
    }

    public Mob getMob() {
        return this.mob;
    }

    public int damageSimpleAttaque(Joueur attaquant, Mob defenseur) {
        if(Math.random()<attaquant.getCategorie().getCritRate()) {
            return (int)(attaquant.getAtk()*attaquant.getCategorie().getCritDamage()*((double)defenseur.getDef()/(defenseur.getDef()+100)));
        } else {
            return (int)(attaquant.getAtk()*((double)defenseur.getDef()/(defenseur.getDef()+100)));
        }
    }

    public int damageSimpleAttaque(Mob attaquant, Joueur defenseur) {
        if(Math.random()<0.25) {
            return (int)(attaquant.getAtk()*1.5*((double)defenseur.getDef()/(defenseur.getDef()+100)));
        } else {
            return (int)(attaquant.getAtk()*((double)defenseur.getDef()/(defenseur.getDef()+100)));
        }
    }
}
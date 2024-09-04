package main.java;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Keyboard  implements NativeKeyListener {

    public static void initKeyboard(){

         try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Keyboard());

    }



    public void nativeKeyReleased(NativeKeyEvent e) {

        switch (main.getGame().getState()) {
            case MENU:

                if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
                    main.getGame().setState(GameState.CLASSE);
                }
                break;

            case PLAYING:
                if (e.getKeyCode() == 57416 || e.getKeyCode() == 57424) { // arrow up
                    UI.attack = !UI.attack;
                }
                if (e.getKeyCode() == 12) { // [°]
                    main.getGame().cheatAttackPlayer();
                }
                if (e.getKeyCode() == 13) { // [+]
                    main.getGame().cheatCapacityPlayer();
                }
                if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
                    if (UI.attack){
                        main.getGame().attackPlayer();
                    }
                    else{
                        main.getGame().capacityPlayer();
                    }
                }
                break;
            case CLASSE:

                Joueur joueur = main.getGame().getJoueur();

                if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {

                    if (joueur.getCategorie().ordinal() < 2) {
                        joueur.setCategorie(Classe.values()[joueur.getCategorie().ordinal() + 1]);
                    }
                }

                if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) {

                    if (joueur.getCategorie().ordinal() > 0) {
                        joueur.setCategorie(Classe.values()[joueur.getCategorie().ordinal() - 1]);
                    }
                }

                if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
                    main.getGame().startGame();
                }

                break;

        }

        UI.update();

    }
    
}

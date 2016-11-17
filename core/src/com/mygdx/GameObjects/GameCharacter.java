package com.mygdx.GameObjects;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Helpers.AssetLoader;
import com.mygdx.ai.StackFSM;

public class GameCharacter {

    private StackFSM brain;

    public float x;
    public float y;
    TextureRegion currentTextureRegion;

    public GameCharacter(String initialState) {

        brain = new StackFSM();
        brain.pushState(initialState);

        x = 0;
        y = 0;
        currentTextureRegion = AssetLoader.walkAnimation[0].getKeyFrames()[0];
    }

    public void update(float runTime) {
        if (brain.getCurrentState() == "WALK") {

        }
        else if (brain.getCurrentState() == "IDLE") {

        }
        else if (brain.getCurrentState() == "ACTION") {

        }

    }




}

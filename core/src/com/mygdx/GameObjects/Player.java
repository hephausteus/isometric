package com.mygdx.GameObjects;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Helpers.AssetLoader;

public class Player {
    public float x;
    public float y;
    private int direction;
    public int iso_x;
    public int iso_y;

    TextureRegion currentTextureRegion;

    public Player() {
        x = 0;
        y = 0;
        iso_x = 0;
        iso_y = 0;

        direction = AssetLoader.DOWN;
        currentTextureRegion = AssetLoader.walkAnimation[direction].getKeyFrames()[0];
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setIsoXY(int x, int y) {
        this.iso_x = x;
        this.iso_y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setCurrentFrame(TextureRegion textureRegion) {
        currentTextureRegion = textureRegion;
    }

    public TextureRegion getCurrentFrame() {
        return currentTextureRegion;
    }

    public TextureRegion getCurrentFrame(float runTime) {
        switch (direction) {
            case AssetLoader.DOWN:
                if (!AssetLoader.walkAnimation[AssetLoader.DOWN].isAnimationFinished(runTime))
                    return AssetLoader.walkAnimation[AssetLoader.DOWN].getKeyFrame(runTime, true);
                else
                    return AssetLoader.walkAnimation[AssetLoader.DOWN].getKeyFrames()[0];
            case AssetLoader.LEFT:
                if (!AssetLoader.walkAnimation[AssetLoader.LEFT].isAnimationFinished(runTime))
                    return AssetLoader.walkAnimation[AssetLoader.LEFT].getKeyFrame(runTime, true);
                else
                    return AssetLoader.walkAnimation[AssetLoader.LEFT].getKeyFrames()[0];
            case AssetLoader.UP:
                if (!AssetLoader.walkAnimation[AssetLoader.UP].isAnimationFinished(runTime))
                    return AssetLoader.walkAnimation[AssetLoader.UP].getKeyFrame(runTime, true);
                else
                    return AssetLoader.walkAnimation[AssetLoader.UP].getKeyFrames()[0];
            case AssetLoader.RIGHT:
                if (!AssetLoader.walkAnimation[AssetLoader.RIGHT].isAnimationFinished(runTime))
                    return AssetLoader.walkAnimation[AssetLoader.RIGHT].getKeyFrame(runTime, true);
                else
                    return AssetLoader.walkAnimation[AssetLoader.RIGHT].getKeyFrames()[0];
        }

        return AssetLoader.walkAnimation[AssetLoader.DOWN].getKeyFrame(runTime, true);
    }

    public boolean animationFinished(float runTime) {
        switch (direction) {
            case AssetLoader.DOWN:
                return AssetLoader.walkAnimation[AssetLoader.DOWN].isAnimationFinished(runTime);
            case AssetLoader.LEFT:
                return AssetLoader.walkAnimation[AssetLoader.LEFT].isAnimationFinished(runTime);
            case AssetLoader.UP:
                return AssetLoader.walkAnimation[AssetLoader.UP].isAnimationFinished(runTime);
            case AssetLoader.RIGHT:
                return AssetLoader.walkAnimation[AssetLoader.RIGHT].isAnimationFinished(runTime);
        }

        return true;
    }
}

package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mygdx.GameWorld.GameRenderer;
import com.mygdx.GameWorld.GameWorld;
import com.mygdx.Helpers.InputHandler;



public class GameScreen implements Screen {

    private GameHUD gameHUD;
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    private float second;
    private int frameCount;

    private final float FPS = 12;
    private float frameDuration;
    private float deltaPassed;
    private int previousFrame;

    private float weekToSeconds;

    public GameScreen() {

        weekToSeconds = 0;

        second = 0f;
        frameCount = 0;

        frameDuration = 1 / FPS;
        deltaPassed = 0f;
        previousFrame = 0;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = screenWidth;
        float gameHeight = screenHeight;// / (screenWidth / gameWidth);

        world = new GameWorld();
        gameHUD = new GameHUD(world);

        renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight);

        gameHUD.create();

        InputProcessor gameInputProcessor = new InputHandler(world);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameInputProcessor);
        inputMultiplexer.addProcessor(gameHUD.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        second += delta;
        runTime += delta;

        float currentFrame = second / frameDuration;

        if ((int)currentFrame > previousFrame) {
            previousFrame = (int)currentFrame;

            //Gdx.app.log("GameScreen", "current frame " + previousFrame);

            world.update(delta);

        }
        renderer.render(delta, runTime);
        gameHUD.render();
        if (second >= 1f) {
            Gdx.app.log("GameScreen", "second " + weekToSeconds);
            second = 0f;
            previousFrame = 0;
            weekToSeconds++;
        }

        if (weekToSeconds >= 2) {
            weekToSeconds = 0;
            world.week++;
            Gdx.app.log("GameScreen", "week " + world.week);
        }
    }

    @Override
    public void resize(int width, int height) {
        gameHUD.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameHUD.dispose();
    }
}

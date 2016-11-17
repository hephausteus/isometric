package com.mygdx.GameWorld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GameObjects.Player;
import com.mygdx.Helpers.AssetLoader;

public class GameRenderer {

    private GameWorld myWorld;


    private SpriteBatch batch;

    // Game Objects
    Player player;

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
        myWorld = world;


        AssetLoader.camera.setToOrtho(false, gameWidth, gameHeight);
        AssetLoader.camera.position.x = 600;
        AssetLoader.camera.position.y = 40;

        AssetLoader.camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(AssetLoader.camera.combined);

        initGameObjects();
        initAssets();

    }

    private void initGameObjects() {
        player = myWorld.getPlayer();

    }

    private void initAssets() {

    }

    private void drawPlayer(float runTime) {

        batch.draw(player.getCurrentFrame(), player.x, player.y);
    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AssetLoader.camera.update();
        AssetLoader.tiledMapRenderer.setView(AssetLoader.camera);
        AssetLoader.tiledMapRenderer.render();

        batch.begin();

        drawPlayer(runTime);

        batch.end();

    }
}

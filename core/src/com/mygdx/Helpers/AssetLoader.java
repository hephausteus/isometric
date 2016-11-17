package com.mygdx.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class AssetLoader {

    public static float TILE_WIDTH = 64;
    public static float TILE_HEIGHT = 32;

    public static TiledMap tiledMap;
    public static TiledMapRenderer tiledMapRenderer;

    public static Animation[] walkAnimation;
    public static Texture walkSheet;
    public static Array<TextureRegion> walkFrames;
    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static OrthographicCamera camera;

    public static void load() {

        Gdx.app.log("AssetLoader", "load()");

        camera = new OrthographicCamera();

        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new IsometricTiledMapRenderer(tiledMap);

        int FRAME_COLS = 3;
        int FRAME_ROWS = 4;
        walkSheet = new Texture(Gdx.files.internal("character1.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10

        walkAnimation = new Animation[4];
        for (int j = 0; j < FRAME_ROWS; j++) {

            walkFrames = new Array<TextureRegion>(FRAME_COLS);
            int index = 0;
            for (int k = 0; k < FRAME_COLS; k++) {
                walkFrames.add(tmp[j][k]);
            }

            walkAnimation[j] = new Animation(0.25f, walkFrames, Animation.PlayMode.NORMAL);
        }
    }

    public static void dispose() {
        Gdx.app.log("AssetLoader", "dispose()");
    }
}

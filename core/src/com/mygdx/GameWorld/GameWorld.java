package com.mygdx.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.GameObjects.GameCharacter;
import com.mygdx.GameObjects.Player;
import com.mygdx.Helpers.AssetLoader;

import java.util.LinkedList;
import java.util.Queue;


public class GameWorld {

    private float runTime = 0;


    private Player player;

    private GameCharacter character;


    private Queue moveQueue;

    float x_increment;
    float y_increment;

    public int year;
    public int month;
    public int week;

    public GameWorld() {

        year = 0;
        month = 0;
        week = 0;

        player = new Player();
        setPlayerIsometricPosition(5, 5);
        moveQueue = new LinkedList();

        x_increment = (AssetLoader.TILE_WIDTH/2)/12;
        y_increment = (AssetLoader.TILE_HEIGHT/2)/12;

        character = new GameCharacter("IDLE");

    }

    public void update(float delta) {

        if (week > 4) {
            week = 1;
            month++;
            if (month > 12) {
                year++;
                month = 1;
            }
        }

        runTime += delta;

        character.update(runTime);

        //Gdx.app.log("GameWorld", "delta  " +delta + ", runTime " + runTime);
        if (!moveQueue.isEmpty()) {

            Vector3 v = (Vector3) moveQueue.poll();
            if (v != null) {
                player.setXY(player.x + v.x, player.y + v.y);
                //Gdx.app.log("GameWorld", "player  " + player.x+ ", " + player.y);
                player.setCurrentFrame(AssetLoader.walkAnimation[(int)v.z].getKeyFrame(runTime, true));
            }
        }
    }

    public Player getPlayer() {
        return player;

    }

    public void onClick(int x, int y) {

    }

    public void setPlayerPixelPosition(int x, int y) {
        player.setXY(x, y);
    }

    public void setPlayerIsometricPosition(int iso_x, int iso_y) {

        int playerY = (int)((iso_y - iso_x) * (AssetLoader.TILE_HEIGHT/2) + (AssetLoader.TILE_HEIGHT/2));
        int playerX = (int)((iso_x + iso_y) * (AssetLoader.TILE_WIDTH/2) + (AssetLoader.TILE_WIDTH/2));
        Vector3 playerLocation = new Vector3(playerX, playerY, 0);
        player.setXY((int)playerLocation.x, (int)playerLocation.y);
        player.setIsoXY(iso_x, iso_y);

        Gdx.app.log("GameWorld", "player location " + (int)playerLocation.x + "," + (int)playerLocation.y);
    }

    public void screenCoordinatesToIso(int x, int y) {
        TiledMapTileLayer tiledLayer = (TiledMapTileLayer)AssetLoader.tiledMap.getLayers().get("bottom");
        tiledLayer.getCell(1,3).setTile(null);

        Vector3 screen = new Vector3(x, y, 0);

        Gdx.app.log("GameWorld", "screen pixels  " +x + "," + y);

        Vector3 map = new Vector3(0, 0, 0);
        AssetLoader.camera.unproject(screen);

        Gdx.app.log("GameWorld", "screen unprojected pixels  " + screen.x + "," + screen.y);

        // pixel coordinates to isometric coordinates:
        map.x = (screen.x - 2 * screen.y) / AssetLoader.TILE_WIDTH;
        map.y = ((2 * screen.y) + screen.x - AssetLoader.TILE_WIDTH) / AssetLoader.TILE_WIDTH;
        int roundedX = (int)Math.round(map.x);
        int roundedY = (int)Math.round(map.y);

        Gdx.app.log("GameWorld", "isometric coordinates " + roundedX + "," + roundedY);
    }

    public void action1() {

        moveRight();
    }

    public void action2() {

        moveLeft();
    }

    public void action3() {

        moveUp();
    }

    public void action4() {

        moveDown();
    }

    // moves right one tile
    public void moveRight() {
        for (int i = 0; i < 12; i++) {
            Vector3 v = new Vector3(x_increment, y_increment, AssetLoader.RIGHT);
            moveQueue.add(v);
        }

    }

    // moves left one tile
    public void moveLeft() {

        for (int i = 0; i < 12; i++) {
            Vector3 v = new Vector3(-1 * x_increment, -1 * y_increment, AssetLoader.LEFT);
            moveQueue.add(v);
        }
    }

    // moves up one tile
    public void moveUp() {
        for (int i = 0; i < 12; i++) {
            Vector3 v = new Vector3(-1 * x_increment, y_increment, AssetLoader.UP);
            moveQueue.add(v);
        }
    }

    // moves down one tile
    public void moveDown() {
        for (int i = 0; i < 12; i++) {
            Vector3 v = new Vector3(x_increment, -1 * y_increment, AssetLoader.DOWN);
            moveQueue.add(v);
        }
    }
}

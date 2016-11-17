package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.GameWorld.GameWorld;


public class GameHUD {
    Skin skin;
    Stage stage;
    GameWorld world;
    Label timeLabel;

    public GameHUD(GameWorld w) {
        world = w;
    }

    public void create () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        table.align(Align.left | Align.bottom);

        //table.debug();

        timeLabel = new Label(world.year + "y " + world.month + "m " + world.week + "w", skin);
        //timeLabel.setAlignment(Align.left | Align.top);
        timeLabel.setPosition(0, 450);
        stage.addActor(timeLabel);

        TextButton button = new TextButton("Right", skin);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.action1();
                return false;
            }
        });
        table.add(button);

        TextButton button2 = new TextButton("Left", skin);
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.action2();
                return false;
            }
        });
        table.add(button2);

        TextButton button3 = new TextButton("Up", skin);
        button3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.action3();
                return false;
            }
        });
        table.add(button3);

        TextButton button4 = new TextButton("Down", skin);
        button4.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.action4();
                return false;
            }
        });
        table.add(button4);

        /*
        Window window = new Window("Dialog", skin);
        window.getTitleTable().add(new TextButton("X", skin)).height(window.getPadTop());
        window.setPosition(100, 100);
        window.defaults().spaceBottom(10);
        window.row().fill().expandX();
        stage.addActor(window);*/

    }

    public void render () {
        timeLabel.setText(world.year + "y " + world.month + "m " + world.week + "w");
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose () {
        stage.dispose();
    }
}

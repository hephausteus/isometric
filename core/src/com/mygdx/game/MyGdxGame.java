package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.Helpers.AssetLoader;
import com.mygdx.Screens.GameScreen;

public class MyGdxGame extends Game {

	
	@Override
	public void create () {
        AssetLoader.load();
        setScreen(new GameScreen());
	}

	@Override
	public void dispose () {
        super.dispose();
        AssetLoader.dispose();
	}
}

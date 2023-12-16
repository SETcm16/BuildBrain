package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class BuildBrain extends Game {
	public static final float SCR_WIDTH = 1920; //разрешение
	public static final float SCR_HEIGHT = 1080;

	SpriteBatch batch;
	Vector3 touch;
	OrthographicCamera camera;
	InputKeyboard keyboard;

	BitmapFont introFont;
	BitmapFont gameFont;

	ScreenMenu screenMenu;
	ScreenAbout screenAbout;
	ScreenGames screenGames;
	ScreenGamePoryadok screenGamePoryadok;
	ScreenPoryadokPobeda screenPoryadokPobeda;
	ScreenPoryadokPorazh screenPoryadokPorazh;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();

		createFonts();
		keyboard = new InputKeyboard(SCR_WIDTH, SCR_HEIGHT, 10);

		screenMenu = new ScreenMenu(this);
		screenAbout = new ScreenAbout(this);
		screenGames = new ScreenGames(this);
		screenGamePoryadok = new ScreenGamePoryadok(this);
		screenPoryadokPobeda = new ScreenPoryadokPobeda(this);
		screenPoryadokPorazh = new ScreenPoryadokPorazh(this);


		setScreen(screenMenu);
	}

	void createFonts(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("introFont.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.size = 65;
		parameter.color = Color.WHITE;
		parameter.borderWidth = 3;
		introFont = generator.generateFont(parameter);

		parameter.size = 75;
		parameter.color = Color.YELLOW;
		parameter.borderWidth = 4;
		gameFont = generator.generateFont(parameter);
		generator.dispose();
	}

	@Override
	public void dispose () {
		batch.dispose();
		keyboard.dispose();
	}
}

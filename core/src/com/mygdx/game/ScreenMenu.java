package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;


public class ScreenMenu implements Screen {
    BuildBrain bb;

    Texture fon;

    TextButton btnAbout, btnGames;

    ScreenMenu(BuildBrain buildBrain) {
        bb = buildBrain;

        fon = new Texture("foni/fonMenu.png");

        btnAbout = new TextButton(bb.introFont, "ОБ ИГРЕ", 50, 150);
        btnGames = new TextButton(bb.introFont, "МИНИ - ИГРЫ", 1357, 150);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            bb.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            bb.camera.unproject(bb.touch);
            if(bb.touch.x > 7 && bb.touch.x < 415 && bb.touch.y > 15 && bb.touch.y < 260){
                bb.setScreen(bb.screenAbout);
            }
            if(bb.touch.x > 1315 && bb.touch.x < 1910 && bb.touch.y > 15 && bb.touch.y < 260){
                bb.setScreen((bb.screenGames));
            }
            if(bb.touch.x > 1667 && bb.touch.x < 1915 && bb.touch.y > 814 && bb.touch.y < 1060){
                Gdx.app.exit();
            }
        }

        bb.camera.update();
        bb.batch.setProjectionMatrix(bb.camera.combined);
        bb.batch.begin();
        bb.batch.draw(fon, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        bb.introFont.draw(bb.batch, "СЧЁТ: " + bb.screenGamePoryadok.result, 75, 975);
        btnAbout.font.draw(bb.batch,btnAbout.text, btnAbout.x, btnAbout.y);
        btnGames.font.draw(bb.batch, btnGames.text, btnGames.x, btnGames.y);
        bb.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        fon.dispose();
    }

}

package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenGames implements Screen {
    BuildBrain bb;

    Texture imgGames;

    public ScreenGames(BuildBrain buildBrain) {
        bb = buildBrain;

        imgGames = new Texture("foni/minigames.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            bb.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            bb.camera.unproject(bb.touch);
            if(bb.touch.x > 880 && bb.touch.x < 1170 && bb.touch.y > 69 && bb.touch.y < 225){
                bb.setScreen(bb.screenMenu);
            }
            if(bb.touch.x > 615 && bb.touch.x < 1381 && bb.touch.y > 601 && bb.touch.y < 766){
                bb.setScreen(bb.screenGamePoryadok);
                bb.screenGamePoryadok.gameStart();
            }

        }
        bb.batch.begin();
        bb.batch.draw(imgGames, 0, 0, SCR_WIDTH, SCR_HEIGHT);
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

    }
}

package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenAbout implements Screen {
    BuildBrain bb;

    Texture imgAbout;

    public ScreenAbout(BuildBrain buildBrain) {
        bb = buildBrain;

        imgAbout = new Texture("foni/about.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            bb.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            bb.camera.unproject(bb.touch);
            if(bb.touch.x > 850 && bb.touch.x < 1140 && bb.touch.y > 50 && bb.touch.y < 210){
                bb.setScreen(bb.screenMenu);
            }
        }
        bb.batch.begin();
        bb.batch.draw(imgAbout, 0, 0, SCR_WIDTH, SCR_HEIGHT);
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
        imgAbout.dispose();
    }
}

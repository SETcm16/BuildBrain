package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenPoryadokPobeda implements Screen {
    BuildBrain bb;

    Texture imgPobeda;
    Texture imgRuba;

    TextButton btnRestart;


    public ScreenPoryadokPobeda(BuildBrain buildBrain) {
        bb= buildBrain;

        imgPobeda = new Texture("foni/pobedaPoryadok.png");
        imgRuba = new Texture("kartiDlyaPoryadka/RUBAshka.png");

        btnRestart = new TextButton(bb.gameFont, "ПЕРЕИГРАТЬ", SCR_WIDTH/2-250, 460);
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

        }


        bb.batch.begin();
        bb.batch.draw(imgPobeda, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnRestart.font.draw(bb.batch, btnRestart.text, btnRestart.x, btnRestart.y);
        if(btnRestart.hit(bb.touch.x, bb.touch.y)){
            bb.setScreen(bb.screenGames);
        }
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

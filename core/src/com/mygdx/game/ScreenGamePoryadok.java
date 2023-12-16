package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGamePoryadok implements Screen {
    BuildBrain bb;
    Texture imgPoryadok;
    Texture[] imgKarti = new Texture[12];
    Texture[] imgKartiPolya = new Texture[5];
    Texture[] imgRuba1 = new Texture[5];
    Texture[] imgRuba2 = new Texture[5];
    TextButton btnRestart;
    TextButton[] btnKarta = new TextButton[12];
    long timeNachZapom, timeZapom;
    int[] numKart = new int[5];
    int[] kol = new int[5];

    boolean opa = true;
    int result = 0;

    public ScreenGamePoryadok(BuildBrain buildBrain) {
        bb = buildBrain;
        imgPoryadok = new Texture("foni/fonGame.png");
        for (int i = 0; i < imgRuba1.length; i++) {
            imgRuba1[i] = new Texture("kartiDlyaPoryadka/RUBAshka.png");
            imgRuba2[i] = new Texture("kartiDlyaPoryadka/RUBAshka.png");
        }
        for (int i = 0; i < imgKarti.length; i++) {
            imgKarti[i] = new Texture("kartiDlyaPoryadka/" + i + ".png");
        }

        btnRestart = new TextButton(bb.gameFont, "ПЕРЕИГРАТЬ", 560, 450);
        for (int i = 0; i < btnKarta.length; i++) {
            if (i < 6) {
                btnKarta[i] = new TextButton(bb.gameFont, "            \n            \n            \n            \n", 93 + i * 251, 450);
            } else {
                btnKarta[i] = new TextButton(bb.gameFont, "            \n            \n            \n            \n", 93 + (i - 6) * 251, 200);
            }
        }
        for (int i = 0; i < kol.length; i++) {
            kol[i] = -1;
        }

    }

    @Override
    public void show() {
        timeNachZapom = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        bb.camera.update();
        bb.batch.setProjectionMatrix(bb.camera.combined);
        bb.batch.begin();


        timeZapom = TimeUtils.millis() - timeNachZapom;

        bb.batch.draw(imgPoryadok, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        if (TimeUtils.millis() - timeNachZapom < 6000) {
            int k1 = 0;
            while (k1 < 5) {
                bb.batch.draw(imgKartiPolya[k1], 50 + k1 * 370, 570, 334, 372);
                k1++;
            }
            bb.introFont.draw(bb.batch, "ЗАПОМНИ КАРТОЧКИ " + timeToString(timeZapom), SCR_WIDTH / 2 - 420, 1020);
        }

        if (TimeUtils.millis() - timeNachZapom > 6000 && TimeUtils.millis() - timeNachZapom < 15000 && opa) {
            int k = 0;
            while (k < 12) {
                if (k < 6) {
                    bb.batch.draw(imgKarti[k], 100 + k * 250, 240, 185, 206);
                } else if (k > 5 && k < 12) {
                    bb.batch.draw(imgKarti[k], 100 + (k - 6) * 250, 10, 185, 206);
                }
                k++;
            }
            if (Gdx.input.justTouched()) {
                bb.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                bb.camera.unproject(bb.touch);
                check();
            }

            String sec1 = "СЕКУНДА";
            String sec2 = "СЕКУНДЫ";
            String sec3 = "СЕКУНД";
            String secs = "";
            if (timeZapom < 11000) {
                secs = sec3;
            } else if (timeZapom > 11000 && timeZapom < 14000) {
                secs = sec2;
            } else {
                secs = sec1;
            }
            bb.introFont.draw(bb.batch, "ВОССТАНОВИ ПОРЯДОК! ОСТАЛОСЬ " + timeToString(timeZapom - 10000) + " " + secs, 120, 1020);
            for (int i = 0; i < btnKarta.length; i++) {
                btnKarta[i].font.draw(bb.batch, btnKarta[i].text, btnKarta[i].x, btnKarta[i].y);
            }
            for (int i = 0; i < 5; i++) {
                bb.batch.draw(imgRuba1[i], 50 + i * 370, 570, 334, 372);
            }

        }
        if(kol[4] != -1 || timeZapom > 15000){
            sovpal();
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

    // Метод для преобразования времени в строковый формат
    private String timeToString(long time) {
        String sec = "" + (6000 - time) / 1000 % 60 % 10;
        return sec;
    }

    void check() {
        if (kol[0] == -1) {
            for (int j = 0; j < btnKarta.length; j++) {
                if (btnKarta[j].hit(bb.touch.x, bb.touch.y)) {
                    imgRuba1[0] = imgKarti[j];
                    kol[0] = j;
                }
            }
        } else if (kol[1] == -1) {
            for (int j = 0; j < btnKarta.length; j++) {
                if (btnKarta[j].hit(bb.touch.x, bb.touch.y)) {
                    imgRuba1[1] = imgKarti[j];
                    kol[1] = j;
                }
            }
        } else if (kol[2] == -1) {
            for (int j = 0; j < btnKarta.length; j++) {
                if (btnKarta[j].hit(bb.touch.x, bb.touch.y)) {
                    imgRuba1[2] = imgKarti[j];
                    kol[2] = j;
                }
            }
        } else if (kol[3] == -1) {
            for (int j = 0; j < btnKarta.length; j++) {
                if (btnKarta[j].hit(bb.touch.x, bb.touch.y)) {
                    imgRuba1[3] = imgKarti[j];
                    kol[3] = j;
                }
            }
        } else if (kol[4] == -1) {
            for (int j = 0; j < btnKarta.length; j++) {
                if (btnKarta[j].hit(bb.touch.x, bb.touch.y)) {
                    imgRuba1[4] = imgKarti[j];
                    kol[4] = j;
                }
            }
        }
        if(kol[4] != -1){
            opa = false;
        }
    }

    void sovpal(){
        int kaka = 0;
        for (int i = 0; i < 5; i++) {
            if(numKart[i] == kol[i]) {
                kaka++;
            }
        }
        if(kaka == 5){
            bb.setScreen(bb.screenPoryadokPobeda);
            result = result + 100;
            gameEnd();
        } else{
            bb.setScreen(bb.screenPoryadokPorazh);
            if(result - 100 < 0){
                result = 0;
            } else{
                result -= 100;
            }
            gameEnd();
        }
    }

    void gameStart(){
        for (int i = 0; i < imgKartiPolya.length; i++) {
            int k = MathUtils.random(0, 11);
            imgKartiPolya[i] = new Texture("kartiDlyaPoryadka/" + k + ".png");
            numKart[i] = k;
        }
    }

    void gameEnd(){
        bb.screenGamePoryadok.timeZapom = 0;
        for (int i = 0; i < 5; i++) {
            kol[i] = -1;
            numKart[i] = 0;
            imgKartiPolya[i] = imgRuba1[i];
            imgRuba1[i] = imgRuba2[i];
        }
        timeNachZapom = 0;
        opa = true;

    }
}

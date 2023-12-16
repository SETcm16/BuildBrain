package com.mygdx.game;

import static com.mygdx.game.BuildBrain.SCR_HEIGHT;
import static com.mygdx.game.BuildBrain.SCR_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextButton {
    float x, y;
    float width, height;
    BitmapFont font;
    String text;

    public TextButton(BitmapFont font, String text, float x, float y){
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;
    }

    public TextButton(BitmapFont font, String text, float y){
        this.y = y*5/4;
        this.text = text;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;
        x = SCR_WIDTH/2-width/2;
    }

    boolean hit(float tx, float ty) {
        return x<tx && tx<x+width && y-height<ty && ty<y;
    }
}

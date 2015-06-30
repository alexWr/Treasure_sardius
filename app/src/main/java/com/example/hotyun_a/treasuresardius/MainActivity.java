package com.example.hotyun_a.treasuresardius;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends SimpleBaseGameActivity {

    public int CAMERA_WIDTH;
    public int CAMERA_HEIGHT;
    public static int countRectHeight,countRectWidth;
    Rectangle GridRect[][];
    static ITextureRegion red_ruby,diamond,blue_ruby,coin,purple_ruby;
    static Sprite sprite_red,sprite_blue,sprite_purple,sprite_coin,sprite_diamond;
    ArrayList<Sprite> arraySprite=new ArrayList<>();
    Text bitmapText;
    long scoreSum;
    Font font;
    int i=0,j=0,k=0,m=0;
    Scene scene;
    @Override
    protected void onCreateResources() {
        try {
            ITexture blueRubyTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/blue_ruby.png");
                }
            });
            ITexture redRubyTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/red_ruby.png");
                }
            });
            ITexture purpleRubyTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/purple_ruby.png");
                }
            });
            ITexture coinTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/coin.png");
                }
            });
            ITexture diamondTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/Diamond-icon.png");
                }
            });
            blueRubyTexture.load();
            redRubyTexture.load();
            purpleRubyTexture.load();
            coinTexture.load();
            diamondTexture.load();
            this.red_ruby= TextureRegionFactory.extractFromTexture(redRubyTexture);
            this.blue_ruby=TextureRegionFactory.extractFromTexture(blueRubyTexture);
            this.purple_ruby=TextureRegionFactory.extractFromTexture(purpleRubyTexture);
            this.coin=TextureRegionFactory.extractFromTexture(coinTexture);
            this.diamond=TextureRegionFactory.extractFromTexture(diamondTexture);
        }catch (IOException e) {
            Debug.e(e);
        }
    }

    @Override
    protected Scene onCreateScene() {
        scene=new Scene();
        scene.setBackground(new Background(new Color(0.05f, 0.7f, 0.9f)));
        font = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 256, 256, this.getAssets(),
                "font/smoothie.otf", 40, true, android.graphics.Color.BLACK);
        font.load();
        bitmapText=new Text(100,CAMERA_HEIGHT-170 , font,"SCORES:",50,getVertexBufferObjectManager());
        scene.attachChild(bitmapText);
        countRectWidth=CAMERA_WIDTH/70;
        countRectHeight=(CAMERA_HEIGHT-210)/70;
        GridRect=new Rectangle[countRectHeight][countRectWidth];
        for(j=0,m=0;j<countRectHeight;j++) {
            for (i = 0, k = 0; i < countRectWidth; i++) {
                GridRect[j][i]=new Rectangle((CAMERA_WIDTH % 70 - countRectWidth) / 2 + k, 50+m, 70, 70, getVertexBufferObjectManager()){
                    private final int id2 = i;
                    private final int id1 = j;
                    private final int col = j;
                    private final int row = i;
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        if(pSceneTouchEvent.getAction()==TouchEvent.ACTION_DOWN) {
                            Color x,y;
                            y = new Color(0, 0, 0, 1);
                            int count=0;
                            for(int l=0;l<countRectHeight;l++) {
                                for (int o = 0; o < countRectWidth; o++) {
                                    x = GridRect[l][o].getColor();
                                    if (x.equals(y)) {
                                        count++;
                                        GridRect[l][o].setColor(1, 1, 1);
                                        if((l == col+1)&&(o == id2)){
                                            ChangeElement(GridRect[l][o], GridRect[id1][id2]);
                                            RubyRect rc=new RubyRect(id1,id2,GridRect,scoreSum,getVertexBufferObjectManager());
                                            rc.checkLineThreeRubyTop(bitmapText);
                                            scoreSum=rc.getScore();
                                            /*if(id1==0 || id2==0) {
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1 - 1][id2], GridRect[id1 - 2][id2]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 + 1], GridRect[id1][id2 - 1]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 + 1], GridRect[id1][id2 + 2]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 - 1], GridRect[id1][id2 - 2]);
                                            }
                                            else{
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1 - 1][id2], GridRect[id1 - 2][id2]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 + 1], GridRect[id1][id2 - 1]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 + 1], GridRect[id1][id2 + 2]);
                                                checkLineThreeRuby(GridRect[id1][id2], GridRect[id1][id2 - 1], GridRect[id1][id2 - 2]);
                                            }*/

                                        }
                                        if((l == col-1)&&(o == id2)){
                                            ChangeElement(GridRect[l][o], GridRect[id1][id2]);
                                        }
                                        if((o == row+1)&&(l == id1)){
                                            ChangeElement(GridRect[l][o],GridRect[id1][row]);
                                        }
                                        if((o == row-1)&&(l == id1)){
                                            ChangeElement(GridRect[l][o],GridRect[id1][row]);
                                        }
                                    }
                                }
                            }
                            if(count==0){
                                GridRect[id1][id2].setColor(0, 0, 0);
                            }
                            if(count==1){
                                GridRect[id1][id2].setColor(1, 1, 1);
                            }
                            if(count>1){
                                GridRect[id1][id2].setColor(1, 1, 1);
                            }
                        }
                        return true;
                    }
                };
                AllSpriteInRect(randInt(1,5),GridRect[j][i],scene);
                scene.registerTouchArea(GridRect[j][i]);
                k += 72;
            }
            m+=72;
        }
        return scene;
    }
    public void ChangeElement(Rectangle first,Rectangle second){
        IEntity firstEnt=first.getLastChild();
        IEntity secondEnt=second.getLastChild();
        if(!(firstEnt.getTag()==secondEnt.getTag())){
            first.getLastChild().detachSelf();
            second.getLastChild().detachSelf();
            first.attachChild(secondEnt);
            second.attachChild(firstEnt);
        }
    }
    public Sprite CreateNewRubySprite(ITextureRegion region,int width,int height){
        Sprite sprite = new Sprite(width,height,region,getVertexBufferObjectManager());
        sprite.setHeight(70);
        sprite.setWidth(70);
        return sprite;
    }
    public void AllSpriteInRect(int num,Rectangle rect,Scene scene){
        switch(num){
            case 1:
                sprite_red=CreateNewRubySprite(red_ruby,0,0);
                sprite_red.setTag(1);
                rect.attachChild(sprite_red);
                //scene.attachChild(GridRect[j][i]);
                scene.attachChild(rect);
                break;
            case 2:
                sprite_blue=CreateNewRubySprite(blue_ruby,0,0);
                sprite_blue.setTag(2);
                arraySprite.add(sprite_blue);
                rect.attachChild(sprite_blue);
                //scene.attachChild(GridRect[j][i]);
                scene.attachChild(rect);
                break;
            case 3:
                sprite_diamond=CreateNewRubySprite(diamond,0,0);
                sprite_diamond.setTag(3);
                arraySprite.add(sprite_diamond);
                rect.attachChild(sprite_diamond);
                //scene.attachChild(GridRect[j][i]);
                scene.attachChild(rect);
                break;
            case 4:
                sprite_coin=CreateNewRubySprite(coin,0,0);
                sprite_coin.setTag(4);
                arraySprite.add(sprite_coin);
                rect.attachChild(sprite_coin);
                //scene.attachChild(GridRect[j][i]);
                scene.attachChild(rect);
                break;
            case 5:
                sprite_purple=CreateNewRubySprite(purple_ruby,0,0);
                sprite_purple.setTag(5);
                arraySprite.add(sprite_purple);
                rect.attachChild(sprite_purple);
                //scene.attachChild(GridRect[j][i]);
                scene.attachChild(rect);
                break;
        }
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    @Override
    public EngineOptions onCreateEngineOptions() {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        wm.getDefaultDisplay().getRotation();
        CAMERA_WIDTH = displayMetrics.widthPixels;
        CAMERA_HEIGHT = displayMetrics.heightPixels;
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }
}

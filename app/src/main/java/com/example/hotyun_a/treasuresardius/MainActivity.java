package com.example.hotyun_a.treasuresardius;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.hotyun_a.treasuresardius.FiveRuby.BottomFiveLineRuby;
import com.example.hotyun_a.treasuresardius.FiveRuby.TopFiveLineRuby;
import com.example.hotyun_a.treasuresardius.FourRuby.BottomFourLineRuby;
import com.example.hotyun_a.treasuresardius.FourRuby.LeftFourLineRuby;
import com.example.hotyun_a.treasuresardius.FourRuby.RightFourLineRuby;
import com.example.hotyun_a.treasuresardius.FourRuby.TopFourLineRuby;
import com.example.hotyun_a.treasuresardius.ThreeRuby.BottomThreeLineRuby;
import com.example.hotyun_a.treasuresardius.ThreeRuby.LeftThreeLineRuby;
import com.example.hotyun_a.treasuresardius.ThreeRuby.RightThreeLineRuby;
import com.example.hotyun_a.treasuresardius.ThreeRuby.TopThreeLineRuby;

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
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class MainActivity extends SimpleBaseGameActivity {

    public int CAMERA_WIDTH;
    public int CAMERA_HEIGHT;
    public static int countRectHeight, countRectWidth;
    public String lightningLocation;
    public int lightHeight,lightWidth;
    int temp,start_position;
    Rectangle GridRect[][];
    public static ITextureRegion red_ruby,diamond,blue_ruby,coin,purple_ruby,big_ruby;
    private BitmapTextureAtlas texLight;
    static public TiledTextureRegion regLight;
    private static int   SPR_COLUMN  = 8;
    private static int   SPR_ROWS  = 1;
    Text bitmapText;
    long scoreSum;
    Font font;
    public static int rectSize,rectTopMargin,rectBottomMargin,textBottomMargin,RectBetweenRect,textSize,textMarginLeft;
    int i=0,j=0,k=0,m=0;
    Scene scene;
    boolean result;
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
            ITexture bigRuby = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/ruby_five.png");
                }
            });
            texLight = new BitmapTextureAtlas(this.getTextureManager(), lightHeight, lightWidth, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
            regLight = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texLight, this.getAssets(),
                    lightningLocation, 0, 0, SPR_COLUMN, SPR_ROWS);
            texLight.load();
            blueRubyTexture.load();
            redRubyTexture.load();
            purpleRubyTexture.load();
            coinTexture.load();
            diamondTexture.load();
            bigRuby.load();
            this.red_ruby= TextureRegionFactory.extractFromTexture(redRubyTexture);
            this.blue_ruby=TextureRegionFactory.extractFromTexture(blueRubyTexture);
            this.purple_ruby=TextureRegionFactory.extractFromTexture(purpleRubyTexture);
            this.coin=TextureRegionFactory.extractFromTexture(coinTexture);
            this.diamond=TextureRegionFactory.extractFromTexture(diamondTexture);
            this.big_ruby=TextureRegionFactory.extractFromTexture(bigRuby);
        }catch (IOException e) {
            Debug.e(e);
        }
    }

    @Override
    protected Scene onCreateScene() {
        scene=new Scene();
        scene.setBackground(new Background(new Color(0.05f, 0.7f, 0.9f)));
        font = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 256, 256, this.getAssets(),
                "font/smoothie.otf", textSize, true, android.graphics.Color.BLACK);
        font.load();
        bitmapText=new Text(textMarginLeft,CAMERA_HEIGHT-textBottomMargin, font,"SCORES:",textSize,getVertexBufferObjectManager());
        scene.attachChild(bitmapText);
        temp=CAMERA_WIDTH/rectSize;
        countRectWidth=(CAMERA_WIDTH-(temp*RectBetweenRect))/rectSize;
        countRectHeight=(CAMERA_HEIGHT-rectBottomMargin)/rectSize;
        start_position=((CAMERA_WIDTH-(temp*RectBetweenRect)) % rectSize) - countRectWidth;
        if(start_position<0)
            start_position=0;
        GridRect=new Rectangle[countRectHeight][countRectWidth];
        for(j=0,m=0;j<countRectHeight;j++) {
            for (i = 0, k = 0; i < countRectWidth; i++) {
                GridRect[j][i]=new Rectangle((((CAMERA_WIDTH-(temp*RectBetweenRect)) % rectSize) - countRectWidth) / 2 + k,
                        rectTopMargin+m, rectSize, rectSize, getVertexBufferObjectManager()){
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
                                        if((l == col+1)&&(o == row)){//top
                                            ChangeElement(GridRect[l][o], GridRect[col][row]);
                                            TopFiveLineRuby topFive=new TopFiveLineRuby(scoreSum,col,row,getVertexBufferObjectManager());
                                            result=topFive.checkLineFiveRubyTop(bitmapText, GridRect);
                                            scoreSum=topFive.getScore();
                                            if(!result) {
                                                TopFourLineRuby topFour = new TopFourLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                result = topFour.checkLineFourRubyTop(bitmapText, GridRect);
                                                scoreSum = topFour.getScore();
                                                if (!result) {
                                                    BottomThreeLineRuby bottomThree = new BottomThreeLineRuby(scoreSum, col + 1, row, getVertexBufferObjectManager());
                                                    bottomThree.checkLineThreeRubyBottom(bitmapText, GridRect);
                                                    scoreSum = bottomThree.getScore();
                                                    TopThreeLineRuby topThree = new TopThreeLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                    topThree.checkLineThreeRubyTop(bitmapText, GridRect);
                                                    scoreSum = topThree.getScore();
                                                }
                                            }
                                        }
                                        if((l == col-1)&&(o == row)){//bottom
                                            ChangeElement(GridRect[l][o], GridRect[col][row]);
                                            BottomFiveLineRuby bottomFive=new BottomFiveLineRuby(scoreSum,col,row,getVertexBufferObjectManager());
                                            result=bottomFive.checkLineFiveRubyBottom(bitmapText,GridRect);
                                            scoreSum=bottomFive.getScore();
                                            if(!result) {
                                                BottomFourLineRuby bottomFour = new BottomFourLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                result = bottomFour.checkLineFourRubyBottom(bitmapText, GridRect);
                                                scoreSum = bottomFour.getScore();
                                                if (!result) {
                                                    BottomThreeLineRuby bottomThree = new BottomThreeLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                    bottomThree.checkLineThreeRubyBottom(bitmapText, GridRect);
                                                    scoreSum = bottomThree.getScore();
                                                    TopThreeLineRuby topThree = new TopThreeLineRuby(scoreSum, col - 1, row, getVertexBufferObjectManager());
                                                    topThree.checkLineThreeRubyTop(bitmapText, GridRect);
                                                    scoreSum = topThree.getScore();
                                                }
                                            }
                                        }
                                        if((o == row+1)&&(l == col)){//left
                                            ChangeElement(GridRect[l][o],GridRect[col][row]);
                                            LeftFourLineRuby leftFour=new LeftFourLineRuby(scoreSum,col,row,getVertexBufferObjectManager());
                                            result=leftFour.checkLineFourRubyLeft(bitmapText,GridRect);
                                            scoreSum=leftFour.getScore();
                                            if(!result) {
                                                LeftThreeLineRuby leftThree = new LeftThreeLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                leftThree.checkLineThreeRubyLeft(bitmapText, GridRect);
                                                scoreSum = leftThree.getScore();
                                                RightThreeLineRuby rightThree = new RightThreeLineRuby(scoreSum, col, row + 1, getVertexBufferObjectManager());
                                                rightThree.checkLineThreeRubyRight(bitmapText, GridRect);
                                                scoreSum = rightThree.getScore();
                                            }
                                        }
                                        if((o == row-1)&&(l == col)){//right
                                            ChangeElement(GridRect[l][o],GridRect[col][row]);
                                            RightFourLineRuby rightFour=new RightFourLineRuby(scoreSum,col,row,getVertexBufferObjectManager());
                                            result=rightFour.checkLineFourRubyRight(bitmapText,GridRect);
                                            scoreSum=rightFour.getScore();
                                            if(!result) {
                                                RightThreeLineRuby rightThree = new RightThreeLineRuby(scoreSum, col, row, getVertexBufferObjectManager());
                                                rightThree.checkLineThreeRubyRight(bitmapText, GridRect);
                                                scoreSum = rightThree.getScore();
                                                LeftThreeLineRuby leftThree = new LeftThreeLineRuby(scoreSum, col, row - 1, getVertexBufferObjectManager());
                                                leftThree.checkLineThreeRubyLeft(bitmapText, GridRect);
                                                scoreSum = leftThree.getScore();
                                            }
                                        }
                                    }
                                }
                            }
                            if(count==0){
                                GridRect[col][row].setColor(0, 0, 0);
                            }
                            if((count>=1)){
                                GridRect[col][row].setColor(1, 1, 1);
                            }
                        }
                        return true;
                    }
                };
                AllSpriteInRect(randInt(1,100),GridRect[j][i],scene);
                scene.registerTouchArea(GridRect[j][i]);
                k += (rectSize+RectBetweenRect);
            }
            m+=(rectSize+RectBetweenRect);
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
        sprite.setHeight(rectSize);
        sprite.setWidth(rectSize);
        return sprite;
    }
    public void AllSpriteInRect(int num,Rectangle rect,Scene scene){
        Sprite sprite;
        switch(num){
            case 1:
                sprite=CreateNewRubySprite(red_ruby,0,0);
                sprite.setTag(1);
                break;
            case 2:
                sprite=CreateNewRubySprite(blue_ruby,0,0);
                sprite.setTag(2);
                break;
            case 3:
                sprite=CreateNewRubySprite(diamond,0,0);
                sprite.setTag(3);
                break;
            case 4:
                sprite=CreateNewRubySprite(coin,0,0);
                sprite.setTag(4);
                break;
            case 5:
                sprite=CreateNewRubySprite(purple_ruby,0,0);
                sprite.setTag(5);
                break;
            default:
                sprite=CreateNewRubySprite(purple_ruby,0,0);
                sprite.setTag(5);
        }
        rect.attachChild(sprite);
        scene.attachChild(rect);
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int rando=rand.nextInt((max - min) + 1) + min;
        if((rando<=10)||(rando>=50&&rando<=60))
            return 1;
        if((rando>10&&rando<=20)||(rando>60&&rando<=70))
            return 2;
        if((rando>20&&rando<=30)||(rando>70&&rando<=80))
            return 3;
        if((rando>30&&rando<=40)||(rando>80&&rando<=90))
            return 4;
        if((rando>40&&rando<50)||rando>90)
            return 5;
        else{ System.out.println("else return 3");
            return 3;}
    }
    @Override
    public EngineOptions onCreateEngineOptions() {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        wm.getDefaultDisplay().getRotation();
        ScreenSize(displayMetrics);
        CAMERA_WIDTH = displayMetrics.widthPixels;
        CAMERA_HEIGHT = displayMetrics.heightPixels;
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }
    public boolean ScreenSize(DisplayMetrics dm){
        float density=dm.density;
        if (density >= 4.0) {
            lightningLocation="gfx/lightningXXXHDPI.png";
            lightHeight=512;
            lightWidth=200;
            rectSize=200;
            rectTopMargin=180;
            rectBottomMargin=rectSize*3;
            textBottomMargin=rectSize*2;
            RectBetweenRect=15;
            textMarginLeft=300;
            textSize=120;
            return true;
        }
        if (density >= 3.0) {
            lightningLocation="gfx/lightningXXHDPI.png";
            lightHeight=512;
            lightWidth=150;
            rectSize=150;
            rectTopMargin=130;
            rectBottomMargin=rectSize*3;
            textBottomMargin=rectSize*2;
            RectBetweenRect=10;
            textMarginLeft=200;
            textSize=100;
            return true;
        }
        if (density >= 2.0) {
            lightningLocation="gfx/lightningXHDPI.png";
            lightHeight=512;
            lightWidth=100;
            rectSize=100;
            rectTopMargin=80;
            rectBottomMargin=rectSize*3;
            textBottomMargin=rectSize*2;
            RectBetweenRect=5;
            textMarginLeft=150;
            textSize=80;
            return true;
        }
        if (density >= 1.5) {
            lightningLocation="gfx/lightningHDPI.png";
            lightHeight=256;
            lightWidth=70;
            rectSize=70;
            rectTopMargin=50;
            rectBottomMargin=70*3;
            textBottomMargin=160;
            RectBetweenRect=2;
            textMarginLeft=100;
            textSize=50;
            return true;
        }
        else {
            System.out.println("ldpi");
            return false;
        }
    }
}

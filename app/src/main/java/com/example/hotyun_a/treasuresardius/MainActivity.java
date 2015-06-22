package com.example.hotyun_a.treasuresardius;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
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


public class MainActivity extends SimpleBaseGameActivity {

    public int CAMERA_WIDTH;
    public int CAMERA_HEIGHT;
    public int countRectHeight,countRectWidth;
    Rectangle GridRect[][];
    ITextureRegion red_ruby,diamond,blue_ruby,coin,purple_ruby;
    Sprite sprite_red,sprite_blue,sprite_purple,sprite_coin,sprite_diamond;
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
        Scene scene=new Scene();
        scene.setBackground(new Background(new Color(0.05f, 0.7f, 0.9f)));
        countRectWidth=CAMERA_WIDTH/70;
        countRectHeight=(CAMERA_HEIGHT-210)/70;
        GridRect=new Rectangle[countRectHeight][countRectWidth];
        for(int j=0,m=0;j<countRectHeight;j++) {
            for (int i = 0, k = 0; i < countRectWidth; i++) {
                GridRect[j][i]=new Rectangle((CAMERA_WIDTH % 70 - countRectWidth) / 2 + k, 50+m, 70, 70, getVertexBufferObjectManager()){
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        Log.d("myLogs","rect");
                        return true;
                    }
                };
                scene.attachChild(sprite_blue);
                scene.attachChild(GridRect[j][i]);
                scene.registerTouchArea(GridRect[j][i]);
                k += 72;
            }
            m+=72;
        }
        return scene;
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
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }
}

package com.example.hotyun_a.treasuresardius;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;


public class MainActivity extends SimpleBaseGameActivity {

    public int CAMERA_WIDTH;
    public int CAMERA_HEIGHT;
    public int countRectHeight,countRectWidth;
    Rectangle GridRect[][];
    @Override
    protected void onCreateResources() {

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
                GridRect[j][i]=new Rectangle((CAMERA_WIDTH % 70 - countRectWidth) / 2 + k, 50+m, 70, 70, getVertexBufferObjectManager());
                scene.attachChild(GridRect[j][i]);
                k += 72;
            }
            m+=72;
        }
        /*Rectangle rect1 = new Rectangle(10, 50, 70, 70, getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
                        pSceneTouchEvent.getY() - this.getHeight() / 2);
                return true;
            }
        };
        Rectangle rect2 = new Rectangle(82, 50, 70, 70, getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
                        pSceneTouchEvent.getY() - this.getHeight() / 2);
                return true;
            }
        };*/
        /*scene.attachChild(rect1);
        scene.attachChild(rect2);
        scene.registerTouchArea(rect1);
        scene.registerTouchArea(rect2);*/
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

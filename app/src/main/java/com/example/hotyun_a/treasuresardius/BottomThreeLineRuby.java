package com.example.hotyun_a.treasuresardius;

import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;

public class BottomThreeLineRuby {
    public long scores;
    VertexBufferObjectManager vObject;
    int col,row;
    private int isSprite;

    public BottomThreeLineRuby(long score,int col,int row,VertexBufferObjectManager vb ){
        this.scores=score;
        this.vObject=vb;
        this.col=col;
        this.row=row;
    }
    public boolean checkLineThreeRubyBottom(Text bitmapText,Rectangle masRect[][]){
        if(col!=(MainActivity.countRectHeight-1)&&col!=(MainActivity.countRectHeight-2)&&row!=0&&row!=(MainActivity.countRectWidth-1)){
            if ((masRect[col][row].getLastChild().getTag() == masRect[col+1][row].getLastChild().getTag()) &&
                    (masRect[col+1][row].getLastChild().getTag() == masRect[col+2][row].getLastChild().getTag())&&
                    (masRect[col][row].getLastChild().getTag() == masRect[col][row+1].getLastChild().getTag())&&
                    masRect[col][row].getLastChild().getTag() == masRect[col][row-1].getLastChild().getTag()) {
                isSprite=masRect[col][row].getLastChild().getTag();
                masRect[col][row].getLastChild().detachSelf();
                masRect[col+1][row].getLastChild().detachSelf();
                masRect[col+2][row].getLastChild().detachSelf();
                masRect[col][row+1].getLastChild().detachSelf();
                masRect[col][row-1].getLastChild().detachSelf();
                scores += 500;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,4);
                return true;
            }
        }
        if(col!=(MainActivity.countRectHeight-1)&&col!=(MainActivity.countRectHeight-2)&&row!=0&&row!=1){
            if ((masRect[col][row].getLastChild().getTag() == masRect[col+1][row].getLastChild().getTag()) &&
                    (masRect[col+1][row].getLastChild().getTag() == masRect[col+2][row].getLastChild().getTag())&&
                    (masRect[col][row].getLastChild().getTag() == masRect[col][row-1].getLastChild().getTag())&&
                    masRect[col][row].getLastChild().getTag() == masRect[col][row-2].getLastChild().getTag()) {
                isSprite=masRect[col][row].getLastChild().getTag();
                masRect[col][row].getLastChild().detachSelf();
                masRect[col-1][row].getLastChild().detachSelf();
                masRect[col-2][row].getLastChild().detachSelf();
                masRect[col][row-1].getLastChild().detachSelf();
                masRect[col][row-2].getLastChild().detachSelf();
                scores += 500;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,5);
                return true;
            }
        }
        if(col!=(MainActivity.countRectHeight-1)&&col!=(MainActivity.countRectHeight-2)&&
                row!=(MainActivity.countRectWidth-1)&&row!=(MainActivity.countRectWidth-2)){
            if ((masRect[col][row].getLastChild().getTag() == masRect[col+1][row].getLastChild().getTag()) &&
                    (masRect[col+1][row].getLastChild().getTag() == masRect[col+2][row].getLastChild().getTag())&&
                    (masRect[col][row].getLastChild().getTag() == masRect[col][row+1].getLastChild().getTag())&&
                    masRect[col][row].getLastChild().getTag() == masRect[col][row+2].getLastChild().getTag()) {
                isSprite=masRect[col][row].getLastChild().getTag();
                masRect[col][row].getLastChild().detachSelf();
                masRect[col+1][row].getLastChild().detachSelf();
                masRect[col+2][row].getLastChild().detachSelf();
                masRect[col][row+1].getLastChild().detachSelf();
                masRect[col][row+2].getLastChild().detachSelf();
                scores += 500;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,6);
                return true;
            }
        }
        if(col!=(MainActivity.countRectHeight-1) && col!=(MainActivity.countRectHeight-2)) {
            if ((masRect[col][row].getLastChild().getTag() == masRect[col+1][row].getLastChild().getTag()) &&
                    (masRect[col+1][row].getLastChild().getTag() == masRect[col+2][row].getLastChild().getTag())) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col+1][row].getLastChild().detachSelf();
                masRect[col+2][row].getLastChild().detachSelf();
                scores += 300;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,0);
                return true;
            }
        }
        if(row!=0&&row<(MainActivity.countRectWidth-1)) {
            if ((masRect[col][row].getLastChild().getTag() == masRect[col][row+1].getLastChild().getTag()) &&
                    (masRect[col][row+1].getLastChild().getTag() == masRect[col][row-1].getLastChild().getTag())) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col][row+1].getLastChild().detachSelf();
                masRect[col][row-1].getLastChild().detachSelf();
                scores += 300;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,1);
                return true;
            }
        }
        if(row!=0&&row!=1) {
            if ((masRect[col][row].getLastChild().getTag() == masRect[col][row - 1].getLastChild().getTag()) &&
                    (masRect[col][row - 1].getLastChild().getTag() == masRect[col][row - 2].getLastChild().getTag())) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col][row - 1].getLastChild().detachSelf();
                masRect[col][row - 2].getLastChild().detachSelf();
                scores += 300;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,2);
                return true;
            }
        }
        if(row!=(MainActivity.countRectWidth-1)&&row!=(MainActivity.countRectWidth-2)) {
            if ((masRect[col][row].getLastChild().getTag() == masRect[col][row + 1].getLastChild().getTag()) &&
                    (masRect[col][row + 1].getLastChild().getTag() == masRect[col][row + 2].getLastChild().getTag())) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col][row + 1].getLastChild().detachSelf();
                masRect[col][row + 2].getLastChild().detachSelf();
                scores += 300;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,3);
                return true;
            } else
                return false;
        }
        else
            return false;
    }
    public void FillingEmptyCellsThree(int col,int row,Rectangle rect[][], int direction){
        IEntity entity,entity1,entity2;
        ArrayList<IEntity> entityArray=new ArrayList<>();
        switch(direction) {
            case 0:
                entityArray.clear();
                if((col-1)>=0) {
                    for(int j=col-1;j>=0;j--){
                        entityArray.add(rect[j][row].getLastChild());
                        rect[j][row].getLastChild().detachSelf();
                    }
                    for (int i = col+2,k=0; i>2; i--) {
                        rect[i][row].attachChild(entityArray.get(k));
                        k++;
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[2][row]);
                }
                else{
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[2][row]);
                }
                break;
            case 1:
                if((col-1)>=0) {
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity = rect[i][row].getLastChild();
                        entity1=rect[i][row-1].getLastChild();
                        entity2=rect[i][row+1].getLastChild();
                        rect[i][row].getLastChild().detachSelf();
                        rect[i][row-1].getLastChild().detachSelf();
                        rect[i][row+1].getLastChild().detachSelf();
                        rect[k][row].attachChild(entity);
                        rect[k][row-1].attachChild(entity1);
                        rect[k][row+1].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                }
                else{
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                }
                break;
            case 2:
                if((col-1)>=0) {
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity = rect[i][row].getLastChild();
                        entity1=rect[i][row-1].getLastChild();
                        entity2=rect[i][row-2].getLastChild();
                        rect[i][row].getLastChild().detachSelf();
                        rect[i][row-1].getLastChild().detachSelf();
                        rect[i][row-2].getLastChild().detachSelf();
                        rect[k][row].attachChild(entity);
                        rect[k][row-1].attachChild(entity1);
                        rect[k][row-2].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-2]);
                }
                else{
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-2]);
                }
                break;
            case 3:
                if((col-1)>=0) {
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity = rect[i][row].getLastChild();
                        entity1=rect[i][row+1].getLastChild();
                        entity2=rect[i][row+2].getLastChild();
                        rect[i][row].getLastChild().detachSelf();
                        rect[i][row+1].getLastChild().detachSelf();
                        rect[i][row+2].getLastChild().detachSelf();
                        rect[k][row].attachChild(entity);
                        rect[k][row+1].attachChild(entity1);
                        rect[k][row+2].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+2]);
                }
                else{
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+2]);
                }
                break;
            case 4:
                if((col-1)>=0) {
                    AllSpriteTwoLightInRect(isSprite, rect[col][row]);
                    entityArray.clear();
                    for(int j=col-1;j>=0;j--){
                        entityArray.add(rect[j][row].getLastChild());
                        rect[j][row].getLastChild().detachSelf();
                    }
                    for (int i = col+2,k=0; i>1; i--) {
                        if(i!=col) {
                            rect[i][row].attachChild(entityArray.get(k));
                            k++;
                        }
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[1][row]);
                }
                if(col-1>0){
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity1=rect[i][row-1].getLastChild();
                        entity2=rect[i][row+1].getLastChild();
                        rect[i][row-1].getLastChild().detachSelf();
                        rect[i][row+1].getLastChild().detachSelf();
                        rect[k][row-1].attachChild(entity1);
                        rect[k][row+1].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                }
                else{
                    AllSpriteTwoLightInRect(isSprite,rect[col][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                }
                break;
            case 5:
                if ((col - 3) >= 0) {
                    AllSpriteTwoLightInRect(isSprite, rect[col][row]);
                    entityArray.clear();
                    for(int j=col-3;j>=0;j--){
                        entityArray.add(rect[j][row].getLastChild());
                        rect[j][row].getLastChild().detachSelf();
                    }
                    for (int i = col-1,k=0; i>1; i--) {
                        rect[i][row].attachChild(entityArray.get(k));
                        k++;
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[1][row]);

                }
                if(col-1>0){
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity1=rect[i][row-1].getLastChild();
                        entity2=rect[i][row-2].getLastChild();
                        rect[i][row-1].getLastChild().detachSelf();
                        rect[i][row-2].getLastChild().detachSelf();
                        rect[k][row-1].attachChild(entity1);
                        rect[k][row-2].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-2]);
                }
                else{
                    AllSpriteTwoLightInRect(isSprite, rect[col][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row-2]);
                }
                break;
            case 6:
                if ((col - 1) >= 0) {
                    AllSpriteTwoLightInRect(isSprite, rect[col][row]);
                    entityArray.clear();
                    for(int j=col-1;j>=0;j--){
                        entityArray.add(rect[j][row].getLastChild());
                        rect[j][row].getLastChild().detachSelf();
                    }
                    for (int i = col+2,k=0; i>1; i--) {
                        if(i!=col) {
                            rect[i][row].attachChild(entityArray.get(k));
                            k++;
                        }
                    }
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[1][row]);
                }
                if(col-1>0){
                    for (int i = col-1,k=col; i >= 0; i--) {
                        entity1=rect[i][row+1].getLastChild();
                        entity2=rect[i][row+2].getLastChild();
                        rect[i][row+1].getLastChild().detachSelf();
                        rect[i][row+2].getLastChild().detachSelf();
                        rect[k][row+1].attachChild(entity1);
                        rect[k][row+2].attachChild(entity2);
                        k=i;
                    }
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+2]);
                } else {
                    AllSpriteTwoLightInRect(isSprite, rect[col][row]);
                    AllSpriteInRect(MainActivity.randInt(1, 100), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+1]);
                    AllSpriteInRect(MainActivity.randInt(1,100),rect[0][row+2]);
                }
                break;
        }
    }
    public Sprite CreateNewRubySprite(ITextureRegion region,int width,int height){
        Sprite sprite = new Sprite(width,height,region,vObject);
        sprite.setHeight(MainActivity.rectSize);
        sprite.setWidth(MainActivity.rectSize);
        return sprite;
    }
    public void AllSpriteInRect(int num,Rectangle rect){
        Sprite sprite;
        switch(num){
            case 1:
                sprite=CreateNewRubySprite(MainActivity.red_ruby,0,0);
                sprite.setTag(1);
                break;
            case 2:
                sprite=CreateNewRubySprite(MainActivity.blue_ruby,0,0);
                sprite.setTag(2);
                break;
            case 3:
                sprite=CreateNewRubySprite(MainActivity.diamond,0,0);
                sprite.setTag(3);
                break;
            case 4:
                sprite=CreateNewRubySprite(MainActivity.coin,0,0);
                sprite.setTag(4);
                break;
            case 5:
                sprite=CreateNewRubySprite(MainActivity.purple_ruby,0,0);
                sprite.setTag(5);
                break;
            default:
                sprite=CreateNewRubySprite(MainActivity.purple_ruby, 0, 0);
                sprite.setTag(5);
        }
        rect.attachChild(sprite);
    }
    public void AllSpriteTwoLightInRect(int num,Rectangle rect){
        Sprite sprite;
        AnimatedSprite aSprite,bSprite;
        switch(num){
            case 1:
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite=CreateNewRubySprite(MainActivity.red_ruby,0,0);
                sprite.setTag(1);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
                break;
            case 2:
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite=CreateNewRubySprite(MainActivity.blue_ruby,0,0);
                sprite.setTag(2);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
                break;
            case 3:
                sprite=CreateNewRubySprite(MainActivity.diamond,0,0);
                sprite.setTag(3);
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
                break;
            case 4:
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite=CreateNewRubySprite(MainActivity.coin,0,0);
                sprite.setTag(4);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
                break;
            case 5:
                sprite=CreateNewRubySprite(MainActivity.purple_ruby,0,0);
                sprite.setTag(5);
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
                break;
            default:
                sprite=CreateNewRubySprite(MainActivity.purple_ruby, 0, 0);
                sprite.setTag(5);
                aSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                bSprite=new AnimatedSprite(MainActivity.rectSize/4, 0, MainActivity.regLight, vObject);
                sprite.attachChild(aSprite);
                sprite.attachChild(bSprite);
                bSprite.setRotation(90);
                bSprite.animate(100);
                aSprite.animate(100);
        }
        rect.attachChild(sprite);
    }
    public long getScore(){
        return this.scores;
    }
}

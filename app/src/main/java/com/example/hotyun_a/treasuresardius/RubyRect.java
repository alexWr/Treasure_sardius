package com.example.hotyun_a.treasuresardius;


import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RubyRect {

    long scores=0;
    int col,row;
    Rectangle masRect[][];
    VertexBufferObjectManager vObject;
    Scene scene;

    public RubyRect(int col,int row,Rectangle rect[][],long score,VertexBufferObjectManager vb){
        this.col=col;
        this.row=row;
        this.masRect=rect;
        this.scores=score;
        this.vObject=vb;
    }
    //----------------Start------------------------
    //check line of ruby, when you replace top ruby
    public boolean checkLineThreeRubyTop(Text bitmapText){
        if(col!=0&&col!=1&&row!=0&&row!=(MainActivity.countRectWidth-1)){
            if ((masRect[col][row].getLastChild().getTag() == masRect[col-1][row].getLastChild().getTag()) &&
                    (masRect[col-1][row].getLastChild().getTag() == masRect[col-2][row].getLastChild().getTag())&&
                    (masRect[col][row].getLastChild().getTag() == masRect[col][row+1].getLastChild().getTag())&&
                    masRect[col][row].getLastChild().getTag() == masRect[col][row-1].getLastChild().getTag()) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col-1][row].getLastChild().detachSelf();
                masRect[col-2][row].getLastChild().detachSelf();
                masRect[col][row+1].getLastChild().detachSelf();
                masRect[col][row-1].getLastChild().detachSelf();
                scores += 500;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,4);
                return true;
            }
        }
        if(col!=0&&col!=1) {
            if ((masRect[col][row].getLastChild().getTag() == masRect[col-1][row].getLastChild().getTag()) &&
                    (masRect[col-1][row].getLastChild().getTag() == masRect[col-2][row].getLastChild().getTag())) {
                masRect[col][row].getLastChild().detachSelf();
                masRect[col-1][row].getLastChild().detachSelf();
                masRect[col-2][row].getLastChild().detachSelf();
                System.out.println(col - 1 + " " + row);
                System.out.println(col-2 + " " + row);
                scores += 300;
                bitmapText.setText("Scores: " + scores);
                FillingEmptyCellsThree(col, row, masRect,0);
                return true;
            }
        }
        if(row!=0&&row!=(MainActivity.countRectWidth-1)) {
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
    //------------------end----------------------------
    //------------------------Start--------------------
    //Func for filling empty cells after detach
    public void FillingEmptyCellsThree(int col,int row,Rectangle rect[][], int direction){
        System.out.println("stroka: "+col+" stolbec: "+ row);
        System.out.println("rect["+col+"]["+ row+"]");
        int top,top2,left,left2,right,right2;
        IEntity entity;
        switch(direction) {
            case 0:
                top=rect[col-1][row].getChildCount();
                top2=rect[col-2][row].getChildCount();
                if((col-3)>=0) {
                    for (int i = col - 3, k = 0; i >= 0; i--) {
                        entity = rect[i][row].getLastChild();
                        rect[i][row].getLastChild().detachSelf();
                        rect[col - k][row].attachChild(entity);
                        k++;
                    }
                    System.out.println(rect[0][row].getChildCount());
                    System.out.println(rect[1][row].getChildCount());
                    System.out.println(rect[2][row].getChildCount());
                    AllSpriteInRect(MainActivity.randInt(1, 5), rect[0][row]);
                    AllSpriteInRect(MainActivity.randInt(1,5),rect[1][row]);
                    AllSpriteInRect(MainActivity.randInt(1,5),rect[2][row]);
                }
                break;
            case 1:
                left=rect[col][row-1].getChildCount();
                right=rect[col][row+1].getChildCount();
                break;
            case 2:
                left=rect[col][row-1].getChildCount();
                left2=rect[col][row-2].getChildCount();
                break;
            case 3:
                right=rect[col][row+1].getChildCount();
                right2=rect[col][row+2].getChildCount();
                break;
            default:
                top=rect[col-1][row].getChildCount();
                top2=rect[col-2][row].getChildCount();
                left=rect[col][row-1].getChildCount();
                right=rect[col][row+1].getChildCount();
        }

        /*for(int i=row,k=row;i>0;i--){
            if(rect[col][i].getChildCount()==0){
                IEntity entity=rect[i][row-1].getLastChild();
                rect[col][row-1].getLastChild().detachSelf();
                rect[i][row].attachChild(entity);
            }
            k=i;
        }*/
    }
    //-------------------------end---------------------
    public boolean checkLineFourRuby(Text bitmapText,Rectangle rect1,Rectangle rect2,Rectangle rect3,Rectangle rect4){
        IEntity firstEnt=rect1.getLastChild();
        IEntity secondEnt=rect2.getLastChild();
        IEntity thirdEnt=rect3.getLastChild();
        IEntity fourthEnt=rect4.getLastChild();
        if((firstEnt.getTag()==secondEnt.getTag())&&(secondEnt.getTag()==thirdEnt.getTag())&&(thirdEnt.getTag()==fourthEnt.getTag())){
            rect1.getLastChild().detachSelf();
            rect2.getLastChild().detachSelf();
            rect3.getLastChild().detachSelf();
            rect4.getLastChild().detachSelf();
            scores+=400;
            bitmapText.setText("Scores: " + scores);
            return true;
        }
        else
            return false;
    }
    public boolean checkLineFifeRuby(Text bitmapText,Rectangle rect1,Rectangle rect2,Rectangle rect3,Rectangle rect4,Rectangle rect5){
        IEntity firstEnt=rect1.getLastChild();
        IEntity secondEnt=rect2.getLastChild();
        IEntity thirdEnt=rect3.getLastChild();
        IEntity fourthEnt=rect4.getLastChild();
        IEntity fifthEnt=rect5.getLastChild();
        if((firstEnt.getTag()==secondEnt.getTag())&&(secondEnt.getTag()==thirdEnt.getTag())&&(thirdEnt.getTag()==fourthEnt.getTag())&&
                (fourthEnt.getTag()==fifthEnt.getTag())){
            rect1.getLastChild().detachSelf();
            rect2.getLastChild().detachSelf();
            rect3.getLastChild().detachSelf();
            rect4.getLastChild().detachSelf();
            rect5.getLastChild().detachSelf();
            scores+=500;
            bitmapText.setText("Scores: "+scores);
            return true;
        }
        else
            return false;
    }
    public long getScore(){
        return this.scores;
    }
    public Sprite CreateNewRubySprite(ITextureRegion region,int width,int height){
        Sprite sprite = new Sprite(width,height,region,vObject);
        sprite.setHeight(70);
        sprite.setWidth(70);
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
                sprite=CreateNewRubySprite(MainActivity.purple_ruby,0,0);
                sprite.setTag(5);
        }
        rect.attachChild(sprite);
    }
}

package com.example.hotyun_a.treasuresardius;


import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RubyRect {

    long scores=0;
    int col,row;
    Rectangle masRect[][];
    VertexBufferObjectManager vObject;

    public RubyRect(int col,int row,Rectangle rect[][],long score,VertexBufferObjectManager vb){
        this.col=col;
        this.row=row;
        this.masRect=rect;
        this.scores=score;
        this.vObject=vb;
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
    public Sprite CreateNewRubySprite(ITextureRegion region,int width,int height){
        Sprite sprite = new Sprite(width,height,region,vObject);
        sprite.setHeight(70);
        sprite.setWidth(70);
        return sprite;
    }
}

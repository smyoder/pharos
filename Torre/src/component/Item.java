/**
 * 
 */
package component;

import java.awt.Graphics;
import java.util.Random;

import model.Game;
import model.Loader;
import view.SpriteSheet;

/**
 * @author Spencer Yoder
 *
 */
public abstract class Item extends ClickableComponent {
    private int speed;
    private double direction;
    
    /**
     * @param texture
     */
    public Item(SpriteSheet spriteSheet, int x, int y) {
        super(null);
        animator = new Animator(spriteSheet, 5);
        place(x+ 3, y + 3);
        speed = 15;
        Random r = new Random();
        direction = r.nextDouble() * 360;
    }

    /* (non-Javadoc)
     * @see component.ClickableComponent#onMouseOver()
     */
    @Override
    public void onMouseOver() {
        Game.gameState.handleItemClick(this);
    }

    /* (non-Javadoc)
     * @see component.ClickableComponent#onMouseLeave()
     */
    @Override
    public void onMouseLeave() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see component.ClickableComponent#onClick()
     */
    @Override
    public void onClick() {
        
    }
    
    public abstract Tile getTile();
    
    @Override
    public void render(Graphics g) {
        super.render(g);
        if(speed > 0) {
            int dx = (int) (Math.cos(direction) * speed);
            int dy = (int) (Math.sin(direction) * speed);
            place(x + dx, y + dy);
            speed --;
        }
    }
    
    public static class MudItem extends Item {
        private static final SpriteSheet mudSheet = new SpriteSheet(44, 44, Loader.loadTexture("/textures/item/mud_item_sheet2.png"));

        /**
         * @param texture
         * @param x
         * @param y
         */
        public MudItem(int x, int y) {
            super(mudSheet, x, y);
        }

        /* (non-Javadoc)
         * @see component.Item#getTile()
         */
        @Override
        public Tile getTile() {
            return new Tile.GrassTile();
        }
    }
    
    public static class StoneItem extends Item {
        /**
         * @param spriteSheet
         * @param x
         * @param y
         */
        public StoneItem(int x, int y) {
            super(stoneSheet, x, y);
        }

        private static final SpriteSheet stoneSheet = new SpriteSheet(44, 44, Loader.loadTexture("/textures/item/cobblestone_item_sheet.png"));

        /* (non-Javadoc)
         * @see component.Item#getTile()
         */
        @Override
        public Tile getTile() {
            return new Tile.StoneTile();
        }
    }
}

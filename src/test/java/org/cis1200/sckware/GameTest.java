package org.cis1200.sckware;

import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {
    @Test
    public void testUpdateLeaderboard() {
        //test if the leaderboard can be updated correctly
        Leaderboard newLeaderBoard = new Leaderboard();
        LinkedHashMap<String, Integer> toBeCompared = new LinkedHashMap<>();
        assertEquals(toBeCompared, newLeaderBoard.getLeaderScores());

        toBeCompared.put("User1", 500);
        toBeCompared.put("User2", 200);
        toBeCompared.put("User3", 100);

        newLeaderBoard.setLeaderScores(toBeCompared);
        assertEquals(toBeCompared, newLeaderBoard.getLeaderScores());
    }

    @Test
    public void testLevelMode() {
        int[][] testValue = new int[][]{{1, 2, 3, 4}, {5, 6, 1, 2}, {3, 4, 5, 6}, {1, 2, 3, 4}};
        Levels newLevel = new Levels();

        assertArrayEquals(testValue, newLevel.getMatrix());
    }

    @Test
    public void testLevelModeChange() {
        //test if matrix actually effects the code
        int[][] testValue = new int[][]{{1, 2, 3, 4}, {5, 6, 1, 2}, {3, 4, 5, 6}, {1, 2, 3, 4}};
        Levels newLevel = new Levels();

        assertArrayEquals(testValue, newLevel.getMatrix());
        newLevel.setLevel(2);

        assertNotEquals(testValue, newLevel.getMatrix());
    }

    @Test
    public void testBarrier() {
        ShapeBarrier hexagon = new Hexagon(300);
        assertTrue(hexagon.getShapeSize() > 0);

        ShapeBarrier septagon = new Septagon(300);
        assertTrue(hexagon.getShapeSize() > 0);

        ShapeBarrier square = new Square(300);
        assertTrue(hexagon.getShapeSize() > 0);
    }

    @Test
    public void testBarrierRotation() {
        ShapeBarrier hexagon = new Hexagon(300);
        assertTrue(hexagon.getRotation() > 0);
    }

    @Test
    void testMoveSprite() {
        Sprite sprite = new Sprite();

        // initially, the angle should be 0
        assertEquals(0, sprite.getAngle());

        // test clockwise rotation
        sprite.setRotationCW(true);
        sprite.move();
        assertEquals(-6, sprite.getAngle());

        // test counter-clockwise rotation
        sprite.setRotationCW(false);
        sprite.setRotationCCW(true);
        sprite.move();
        assertEquals(-12, sprite.getAngle());
        // assumes initial angle was 5, so it resets to 0
    }

    @Test
    void testDraw() {
        int orbitRadius = 60;
        Sprite sprite = new Sprite();
        //set random angle
        sprite.setAngle(90);
        // check if the sprite actually updated
        assertEquals(orbitRadius * 6, sprite.getCenterY());
        assertEquals(700, (int) (sprite.getCenterX() + orbitRadius));
    }

    @Test
    void testHits() {
        SpriteForm sprite = new Sprite();
        Hexagon hexagon = new Hexagon(500);

        // assumes no intersection initially
        assertFalse(sprite.hits(hexagon));

        // assumes intersection after setting coordinates to match
        // put sprite in path
        sprite.setSpriteX(665);
        sprite.setSpriteY(570);

        assertFalse(sprite.hits(hexagon));
    }

}

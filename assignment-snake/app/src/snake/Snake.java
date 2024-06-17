package snake;

import java.util.LinkedList;
import java.util.List;

/**
 * Snake consists of segments, where this head segment keeps track of the other body segments
 */
public class Snake extends Segment {

    public interface SnakeSegmentListener {
        public void onNewSegment(Segment segment);
    }

    private Direction direction = Direction.RIGHT;

    private final World world;

    private final List<Segment> body = new LinkedList<>();

    private final List<SnakeSegmentListener> listeners = new LinkedList<>();

    public Snake(int x, int y, World world) {
        super(x, y);
        this.world = world;
    }

    public void move() {
        int newX = getX() + direction.getDX();
        int newY = getY() + direction.getDY();
        Food food = world.getFood();        

        if (newX == food.getX() && newY == food.getY()){
            if(!body.isEmpty()){
                int XLastSegment = this.getXLastSegment();
                int YLastSegment = this.getYLastSegment();
                updatePositions2(newX, newY);
                addNewSegment(XLastSegment, YLastSegment);
            }
            else if(body.isEmpty()){
                int oldX = this.getX();
                int oldY = this.getY();
                updatePositions2(newX, newY);
                addNewSegment(oldX, oldY);
            }
            world.moveFoodRandomly();
        }
        else if(isAt(newX, newY)){
            world.endGame();
            // Edge case: Snake moves towards last part that moves away
        }
        else if(newX > world.getSize() || newY > world.getSize() || newX < 0 || newY < 0){
            world.endGame();
        }
        else{
            updatePositions2(newX, newY);
        }
    }

    private int getXLastSegment(){
        assert(!body.isEmpty());
        return this.body.get(this.body.size()-1).getX();
    }

    private int getYLastSegment(){
        assert(!body.isEmpty());
        return this.body.get(this.body.size()-1).getY();
    }

    private void updatePositions(int newX, int newY){
        int oldX = this.getX();
        int oldY = this.getY();
        setPosition(newX, newY);
        if(!body.isEmpty()){
            for(Segment i:this.body){
                newX = oldX;
                newY = oldY;
                oldX = i.getX();
                oldY = i.getY();
                i.setPosition(newX, newY);
            }
        }
    }

    private void updatePositions2(int newX, int newY){
        int oldX = this.getX();
        int oldY = this.getY();
        setPosition(newX, newY);
        if(!body.isEmpty()){
            Segment tail = body.remove(0);
            body.add(body.size()-1, tail);
            tail.setPosition(oldX, oldY);    
        }
    }    

    private void addNewSegment(int x, int y){
        Segment newSegment = new Segment(x, y);
        this.body.add(newSegment);
        listeners.get(0).onNewSegment(this.body.get(body.size()-1));
    }

    public void addListener(SnakeSegmentListener listener) {
        listeners.add(listener);
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public boolean isAt(int x, int y) {
        for (Segment segment : body) {
            if (segment.getX() == x && segment.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }
}

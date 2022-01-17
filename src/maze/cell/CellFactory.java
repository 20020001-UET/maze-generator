package maze.cell;

import maze.cell.path.Path;
import maze.cell.wall.Wall;

public class CellFactory {
    public static Cell create(int value) {
        switch (value) {
            case 0 -> {
                return new Path();
            }
            case 1 -> {
                return new Wall();
            }
            default -> {
                return null;
            }
        }
    }
}

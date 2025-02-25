package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;

    public Board(int id) {
        this.id = id;
    }
}

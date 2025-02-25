package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.ObjectMapperUtils.OBJECT_MAPPER;

@Getter
@Setter
public class Category {
    // category id
    private int id;

    // category name
    private String name;

    // board id
    private Board board;

    // parent mapping info
    @JsonIgnore
    private List<Category> parents;

    // children mapping info
    private List<Category> children;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        parents = new ArrayList<>();
        children = new ArrayList<>();
    }

    public Category(String name, Board board) {
        this.name = name;
        this.board = board;
        parents = new ArrayList<>();
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this.id == ((Category) o).id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
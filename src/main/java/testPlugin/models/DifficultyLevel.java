package testPlugin.models;

import net.java.ao.Entity;

public interface DifficultyLevel extends Entity {
    String getName();

    void setName(String name);
}
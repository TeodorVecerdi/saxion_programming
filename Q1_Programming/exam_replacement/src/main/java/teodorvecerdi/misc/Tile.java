package teodorvecerdi.misc;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Tile {
    // @formatter:off
    Grass   ("grass",  "Grass",    new java.awt.Color(0x22, 0x8b, 0x22, 0xff)),
    Sand    ("sand",    "Sand",     new java.awt.Color(0xc2, 0xb2, 0x80, 0xff)),
    Stone   ("stone",  "Stone",    new java.awt.Color(0x95, 0x94, 0x8B, 0xff)),
    None    ("none", "None", new java.awt.Color(0xff,0xff,0xff,0x0)),
    Air     (None),
    Error   ("error",  "Error",    new java.awt.Color(0xff, 0x0, 0xff, 0xff));
    // @formatter:on

    public String Id;
    public String Name;
    public java.awt.Color Color;

    @org.jetbrains.annotations.Contract(pure = true)
    Tile (String id, String name, java.awt.Color color) {
        Id = id;
        Name = name;
        Color = color;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    Tile (String id, String name) {
        this(id, name, new java.awt.Color(0xff, 0x0, 0xff, 0xff));
    }

    @org.jetbrains.annotations.Contract(pure = true)
    Tile (String id, String name, int rgb) {
        this(id, name, new java.awt.Color(rgb));
    }

    @org.jetbrains.annotations.Contract(pure = true)
    Tile (String id, String name, int r, int g, int b) {
        this(id, name, new java.awt.Color(r, g, b));
    }

    @Contract(pure = true)
    Tile (@NotNull Tile tile) {
        this(tile.Id, tile.Name, tile.Color);
    }
}

package com.davixdevelop.worldeditselections.selections.json;

public class JsonBlockVector {
    int X;
    int Y;
    int Z;

    public JsonBlockVector(int X, int Y, int Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getZ() {
        return Z;
    }
}

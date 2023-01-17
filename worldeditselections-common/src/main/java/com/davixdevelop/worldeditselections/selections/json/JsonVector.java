package com.davixdevelop.worldeditselections.selections.json;

public class JsonVector {
    double X;
    double Y;
    double Z;

    public JsonVector(double X, double Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ() {
        return Z;
    }
}

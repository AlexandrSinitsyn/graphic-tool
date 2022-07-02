package alexsin.graphic.tool

import java.awt.Color

abstract class WorldObject : DrawableObject {
    abstract var pos: Vec protected set
    abstract var color: Color protected set
}
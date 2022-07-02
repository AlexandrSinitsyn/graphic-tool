package alexsin.graphic.tool

import java.awt.Color

@Deprecated("Is not currently supported", level = DeprecationLevel.ERROR)
abstract class WorldObject3D : DrawableObject3D {
    abstract var pos: Vec protected set
    abstract var drawablePos: Vec protected set
    abstract var color: Color protected set
}

package alexsin.graphic.tool

import java.awt.Graphics2D

interface DrawableObject {
    fun drawAndUpdate(graphics: Graphics2D, dt: Double)
}
package alexsin.graphic.tool

import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.MouseEvent
import javax.swing.event.MouseInputListener

class Button(override var pos: Vec, private val action: Runnable) : WorldObject(), MouseInputListener {

    private val width: Int = 100
    private val height: Int = 50
    override var color: Color = Color.gray
    private var onMouse = false
    private var clicked = Pair(false, false)

    override fun drawAndUpdate(graphics: Graphics2D, dt: Double) {
        graphics.color = if (onMouse) color.brighter() else color
        graphics.fillRect(pos, width, height)
    }

    private fun onMouse(v: Vec) = v.x in pos.x .. pos.x + width &&
            v.y in pos.y .. pos.y + height

    override fun mouseClicked(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {
        if (!clicked.first && onMouse) {
            clicked = Pair(true, true)
        }

        clicked = Pair(true, clicked.second)
    }
    override fun mouseReleased(e: MouseEvent) {
        if (clicked.second && onMouse) {
            action.run()
        }

        clicked = Pair(false, false)
    }
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}
    override fun mouseMoved(e: MouseEvent) {
        onMouse = onMouse(Vec(e.x, e.y))
    }
    override fun mouseDragged(e: MouseEvent) {
        onMouse = onMouse(Vec(e.x, e.y))
    }
}
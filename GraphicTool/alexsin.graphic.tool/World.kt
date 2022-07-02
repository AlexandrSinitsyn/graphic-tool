package alexsin.graphic.tool

import java.awt.Graphics2D
import java.awt.Toolkit
import java.util.concurrent.ConcurrentLinkedDeque
import javax.swing.JFrame

class World : JFrame("World") {

    private val drawables = ConcurrentLinkedDeque<DrawableObject>()
    private var lastFrame = 0L

    init {
        extendedState = 6
        defaultCloseOperation = 3
        isVisible = true
        createBufferStrategy(2)
        lastFrame = System.currentTimeMillis()
    }

    fun start() {
        Thread {
            while (true) {
                val time = System.currentTimeMillis()
                val dt = time - lastFrame
                lastFrame = time

                draw(dt / 1000.0)
            }
        }.start()
    }

    private fun draw(dt: Double) {
        @Suppress("UNCHECKED_CAST")
        val graphics = bufferStrategy.drawGraphics as Graphics2D
        graphics.clearRect(0, 0, width, height)

        val seq = sequenceOf(before, this::drawAndUpdate, after)
        seq.forEach { it?.invoke(graphics, dt) }

        graphics.dispose()
        bufferStrategy.show()
        Toolkit.getDefaultToolkit().sync()
    }

    private fun drawAndUpdate(graphics: Graphics2D, dt: Double) = drawables.forEach {
        try {
            it.drawAndUpdate(graphics, dt)
        } catch (e: Exception) {
            DrawFailedException(e).printStackTrace()
        }
    }

    fun addDrawableObject(drawableObject: DrawableObject) {
        drawables += drawableObject
    }

    fun removeDrawableObject(drawableObject: DrawableObject) {
        drawables -= drawableObject
    }

    fun getDrawableObjectList(): List<DrawableObject> = drawables.stream().toList()

    companion object {
        private val instance = World()
        private var before: ((Graphics2D, Double) -> Unit)? = null
        private var after: ((Graphics2D, Double) -> Unit)? = null

        @JvmStatic
        fun i(): World = instance
        @JvmStatic
        fun setBeforeDrawing(fn: (Graphics2D, Double) -> Unit) { before = fn }
        @JvmStatic
        fun setAfterDrawing(fn: (Graphics2D, Double) -> Unit) { after = fn }
    }
}
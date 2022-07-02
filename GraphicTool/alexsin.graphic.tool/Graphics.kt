package alexsin.graphic.tool

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageOp
import java.awt.image.ImageObserver
import java.text.AttributedCharacterIterator

fun <V : NArc<*>> Graphics2D.translate(pos: V) = translate(pos[0], pos[1])

fun <V : NArc<*>> Graphics2D.clipRect(pos: V, width: Int, height: Int) = clipRect(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.setClip(pos: V, width: Int, height: Int) = setClip(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.copyArea(pos: V, width: Int, height: Int, d: V) =
    copyArea(pos[0].toInt(), pos[1].toInt(), width, height, d[0].toInt(), d[1].toInt())

fun <V : NArc<*>> Graphics2D.drawLine(from: V, to: V) = drawLine(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())

fun <V : NArc<*>> Graphics2D.fillRect(pos: V, width: Int, height: Int) = fillRect(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.clearRect(pos: V, width: Int, height: Int) = clearRect(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.drawRoundRect(pos: V, width: Int, height: Int, arcWidth: Int, arcHeight: Int) =
    drawRoundRect(pos[0].toInt(), pos[1].toInt(), width, height, arcWidth, arcHeight)

fun <V : NArc<*>> Graphics2D.draw3DRect(pos: V, width: Int, height: Int, raised: Boolean) =
    draw3DRect(pos[0].toInt(), pos[1].toInt(), width, height, raised)

fun <V : NArc<*>> Graphics2D.fill3DRect(pos: V, width: Int, height: Int, raised: Boolean) =
    fill3DRect(pos[0].toInt(), pos[1].toInt(), width, height, raised)

fun <V : NArc<*>> Graphics2D.fillRoundRect(pos: V, width: Int, height: Int, arcWidth: Int, arcHeight: Int) =
    fillRoundRect(pos[0].toInt(), pos[1].toInt(), width, height, arcWidth, arcHeight)

fun <V : NArc<*>> Graphics2D.drawOval(pos: V, width: Int, height: Int) = drawOval(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.fillOval(pos: V, width: Int, height: Int) = fillOval(pos[0].toInt(), pos[1].toInt(), width, height)

fun <V : NArc<*>> Graphics2D.drawArc(pos: V, width: Int, height: Int, startAngle: Int, arcAngle: Int) =
    drawArc(pos[0].toInt(), pos[1].toInt(), width, height, startAngle, arcAngle)

fun <V : NArc<*>> Graphics2D.fillArc(pos: V, width: Int, height: Int, startAngle: Int, arcAngle: Int) =
    fillArc(pos[0].toInt(), pos[1].toInt(), width, height, startAngle, arcAngle)

fun <V : NArc<*>> Graphics2D.drawPolyline(points: Array<V>) =
    drawPolyline(points.map { it[0].toInt() }.toIntArray(), points.map { it[1].toInt() }.toIntArray(), points.size)

fun <V : NArc<*>> Graphics2D.drawPolygon(points: Array<V>) =
    drawPolygon(points.map { it[0].toInt() }.toIntArray(), points.map { it[1].toInt() }.toIntArray(), points.size)

fun <V : NArc<*>> Graphics2D.fillPolygon(points: Array<V>) =
    fillPolygon(points.map { it[0].toInt() }.toIntArray(), points.map { it[1].toInt() }.toIntArray(), points.size)

fun <V : NArc<*>> Graphics2D.drawString(str: String, pos: V) = drawString(str, pos[0].toInt(), pos[1].toInt())

fun <V : NArc<*>> Graphics2D.drawString(iterator: AttributedCharacterIterator, pos: V) = drawString(iterator, pos[0].toInt(), pos[1].toInt())

fun <V : NArc<*>> Graphics2D.drawImage(img: BufferedImage?, op: BufferedImageOp, pos: V) = drawImage(img, op, pos[0].toInt(), pos[1].toInt())

fun <V : NArc<*>> Graphics2D.drawImage(img: Image?, pos: V, observer: ImageObserver?): Boolean = drawImage(img, pos[0].toInt(), pos[1].toInt(), observer)

fun <V : NArc<*>> Graphics2D.drawImage(img: Image?, pos: V, width: Int, height: Int, observer: ImageObserver?): Boolean =
    drawImage(img, pos[0].toInt(), pos[1].toInt(), width, height, observer)

fun <V : NArc<*>> Graphics2D.drawImage(img: Image?, pos: V, bgcolor: Color?, observer: ImageObserver?): Boolean =
    drawImage(img, pos[0].toInt(), pos[1].toInt(), bgcolor, observer)

fun <V : NArc<*>> Graphics2D.drawImage(
    img: Image?,
    pos: V,
    width: Int,
    height: Int,
    bgcolor: Color?,
    observer: ImageObserver?
): Boolean =
    drawImage(img, pos[0].toInt(), pos[1].toInt(), width, height, bgcolor, observer)

fun <V : NArc<*>> Graphics2D.rotate(theta: Double, pos: V) = rotate(theta, pos[0], pos[1])
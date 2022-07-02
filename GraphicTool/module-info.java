module GraphicTool {
    requires java.base;
    requires java.desktop;
    requires kotlin.stdlib;

    provides alexsin.graphic.tool.NArc
            with alexsin.graphic.tool.Vec,
                    alexsin.graphic.tool.Vec3,
                    alexsin.graphic.tool.MultiVec;

    exports alexsin.graphic.tool;
}

package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.opengl;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

class TriangleSurfaceView extends GLSurfaceView {

    private final TriangleRenderer renderer;

    public TriangleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        renderer = new TriangleRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setPreserveEGLContextOnPause(true);
        setRenderer(renderer);

//        // Render the view only when there is a change in the drawing data
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }
}
package se.habjo;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class Main {
    public static void main(String[] args) {

        System.setProperty("org.lwjgl.librarypath", "target/lib");

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        // Create the window
        long window = GLFW.glfwCreateWindow(800, 600, "Color Blending Example", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Get the window size passed to glfwCreateWindow
            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            // Center the window
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

// Draw the colored box based on x, y positions
            GLFW.glfwGetCursorPos(window, xBuffer, yBuffer);

            float x = (float) xBuffer.get(0);
            float y = (float) yBuffer.get(0);

            float normalizedX = x / 800.0f;
            float normalizedY = y / 600.0f;

            GL11.glClearColor(normalizedX, normalizedY, 0.5f, 1.0f);
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(normalizedX, normalizedY, 0.5f);
            GL11.glVertex2f(-1.0f, 1.0f);
            GL11.glVertex2f(1.0f, 1.0f);
            GL11.glVertex2f(1.0f, -1.0f);
            GL11.glVertex2f(-1.0f, -1.0f);
            GL11.glEnd();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }

}

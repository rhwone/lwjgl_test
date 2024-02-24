package se.habjo;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class Main {
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", "target/lib");
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException();
        }

        // Configure GLFW
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        // Create a windowed mode window and its OpenGL context
        java.lang.String string = "Hello World!";
        long window = GLFW.glfwCreateWindow(800, 600, string, 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Set up your rendering loop here
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Your rendering code goes here
            // Swap buffers and poll events
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        // Terminate GLFW and free the error callback
        GLFW.glfwTerminate();
    }
}

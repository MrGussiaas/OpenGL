package com.gussiaas.window.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.util.Optional;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import com.gussiaas.service.GLProgram;
import com.gussiaas.utils.FileUtils;


public class Window {
	private GLProgram program;
	public void start(){
		if(!glfwInit()){
			System.err.println("Error initializing glfw");
			System.exit(1);
		}
		
		long windowID = glfwCreateWindow(640, 480, "My window", NULL, NULL);
		glfwMakeContextCurrent(windowID);  
		System.out.println("Working Directory = " +
	               System.getProperty("user.dir"));
		//GL.createCapabilities();
		
		glfwWindowHint(GLFW_SAMPLES, 4);
	    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
	    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	   // glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
	    
		glfwSetWindowPosCallback(windowID, (window, x, y) -> windowMoved(x, y));
		glfwSetWindowSizeCallback(windowID, (window, width, height) -> windowResized(width, height));
	    
		

		
		//GL.createCapabilities();
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		glfwSwapInterval(1);
		System.out.println( glGetString(GL_VERSION));
		program = new GLProgram();
		program.attachVertexShader("gl/shader.txt");
		program.attachFragmentShader("gl/fragmenter.txt");
		program.link();
		
		int vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		float[] vertices = new float[]{
		  0.0f,		0.8f,
		 -0.8f,	   -0.8f,
		  0.8f,	   -0.8f
		};
		
		float[] colors = new float[]{
			1,	0,	0,
			0,	1,	0,
			0,	0,	1
		};
		
		FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
		colorBuffer.put(colors).flip();
		int vboColID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColID);
		glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0,0);
		
		
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(vertices).flip();
		
		int vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		

		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindVertexArray(0);

		//glClear(GL_COLOR_BUFFER_BIT);
		
		//glBindVertexArray(vaoID);
		//glEnableVertexAttribArray(0);
		//glDrawArrays(GL_TRIANGLES, 0, 3);
		//program.link();
		//glDisableVertexAttribArray(0);
		//glBindVertexArray(0);
		//program.unbind();
		//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		while(!glfwWindowShouldClose(windowID)){
			
			//if(glfwGetKey(windowID, GLFW_KEY_UP) == GLFW_PRESS){
			//	System.out.println("Up key is pressed");
			//}
	        // Clear the screen
	        
	        
			//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	        program.bind();
	        // Use our program
	        

	        // Bind the vertex array and enable our location

	        // Draw a triangle of 3 vertices
	        glDrawArrays(GL_TRIANGLES, 0, 3);

	        // Disable our location
	        glBindVertexArray(0);
	        glBindVertexArray(1);

	        // Un-bind our program
	       
	        glfwSwapBuffers(windowID);
	        glfwPollEvents();
	       GLProgram.unbind();
			
			//System.out.println("loop");
		}
		
		glfwSetWindowPosCallback(windowID, null).free();
		program.dispoise();
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoID);
		glDeleteBuffers(vboID);
		glDeleteBuffers(vboColID);
		glfwDestroyWindow(windowID);
		glfwTerminate();
		
	}

	

	
	public void windowMoved(int x, int y){
		System.out.println("the window has been moved");	
	}
	
	public void windowResized(int width, int height){
		System.out.println("The window has been resized");
	}
	
	public void windowClosing(){
		System.out.println("The window is now closing");
	}
	
	public void windowFocusChanged(boolean focused){
		System.out.println("Window focus is now");
	}
	
	public void windowIconfyChanged(boolean iconified){
		System.out.println("the window is iconified");
	}
	
	public void framebufferResized(int width, int height){
		System.out.println("frame buffer has been resized");
	}
	
	public void initialize(){
		if(!glfwInit()){
			System.err.println("Error initializing glfw");
			System.exit(1);
		}
		GL.createCapabilities();
	}
	
	public void terminate(){
		glfwTerminate();
	}
	
	public static void main(String[] args){
		
		new Window().start();
	
	}
}

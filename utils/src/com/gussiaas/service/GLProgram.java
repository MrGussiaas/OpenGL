package com.gussiaas.service;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.Optional;

import com.gussiaas.utils.FileUtils;

public class GLProgram {
	private int programID;
	private int shaderID;
	private int fragmenterID;
	
	public GLProgram(){
		try{
			programID = glCreateProgram();
			//programID = GL41.glGenProgramPipelines();
			//;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void attachVertexShader(String fileName){
		Optional<String> answer = FileUtils.readFile(fileName);
		if(answer.isPresent()){

			shaderID =glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(shaderID, answer.get());
			glCompileShader(shaderID);
			if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE){
				throw new RuntimeException("Error creating vertex shader\n" + glGetShaderInfoLog(shaderID, glGetShaderi(shaderID, GL_INFO_LOG_LENGTH)));
			}
			glAttachShader(programID, shaderID);
		
		}
	}
	
	public void attachFragmentShader(String fileName){
		Optional<String> answer = FileUtils.readFile(fileName);
		if(answer.isPresent()){
			fragmenterID = glCreateShader(GL_FRAGMENT_SHADER);
			glShaderSource(fragmenterID, answer.get());
			glCompileShader(fragmenterID);
			if(glGetShaderi(fragmenterID, GL_COMPILE_STATUS) == GL_FALSE){
				throw new RuntimeException("Error creating vertex shader\n" + glGetShaderInfoLog(fragmenterID, glGetShaderi(fragmenterID, GL_INFO_LOG_LENGTH)));
			}
			glAttachShader(programID, fragmenterID);

		}
	}
	
	public void link(){
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE){
			throw new RuntimeException("Unable to link shader program:");
		}
	}
	
    public static void unbind()
    {
        glUseProgram(0);
    }
    
	public void bind()
	{
		glUseProgram(programID);
	}
	public void dispoise(){
		unbind();
		glDetachShader(programID, shaderID);
		glDetachShader(programID, fragmenterID);
		
		glDeleteShader(shaderID);
		glDeleteShader(fragmenterID);
	}


	public int getProgramID() {
		return programID;
	}


	public void setProgramID(int programID) {
		this.programID = programID;
	}
	
	
	
	
}

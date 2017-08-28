package edu.gmu.cs795.hw1.inst;

import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

public class LineCoverageMV extends MethodVisitor {

	public String className;
	public String methodName;
	public String methodDesc;

	public LineCoverageMV(String className, String methodName, String methodDesc, MethodVisitor mv) {
		super(ASM5, mv);
		this.className = className;
		this.methodName = methodName;
		this.methodDesc = methodDesc;
	}

}

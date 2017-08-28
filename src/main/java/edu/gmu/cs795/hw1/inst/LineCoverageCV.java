package edu.gmu.cs795.hw1.inst;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LineCoverageCV extends ClassVisitor {
	
	public LineCoverageCV(ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
	}
	
	String className;
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		this.className = name;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		//Insert our method visitor in the chain
		mv = new LineCoverageMV(className, name, desc, mv);
		return mv; 
	}
}

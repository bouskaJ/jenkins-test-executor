package org.jboss.qa.jenkins.test.executor.phase.download;

import org.jboss.qa.phaser.Id;
import org.jboss.qa.phaser.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Download {

	@Id
	String id() default "";

	public String url();

	public boolean verbose() default false;

	public Dst destination() default @Dst;

	public UnPack unpack() default @UnPack;

	@Order
	int order() default 0;
}

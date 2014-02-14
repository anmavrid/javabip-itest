/*
 * Copyright (c) 2012 Crossing-Tech TM Switzerland. All right reserved.
 * Copyright (c) 2012, RiSD Laboratory, EPFL, Switzerland.
 *
 * Author: Simon Bliudze, Alina Zolotukhina, Anastasia Mavridou, and Radoslaw Szymanek
 * Date: 10/15/12
 */

package org.bip.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * It specifies the interface for transition guards.
 */
public interface Guard {
	
	/**
	 * It returns the name of the guard.
	 *
	 * @return the name of the guard.
	 */
	public String name();

	// TODO, It does not seem to be used actually by anything in GuardImpl class. Maybe, it should be removed as it ties as to Reflection mechanism.
	/**
	 * Method.
	 *
	 * @return the method
	 */
	public Method method();

	/**
	 * It returns the collection of specifications of data that is required by this guard in order to be evaluated.
	 *
	 * @return the collection
	 */
	public Collection<Data<?>> dataRequired();

	/**
	 * It returns true if guard requires data for its evaluation.
	 *
	 * @return the boolean true if guard requires data and false otherwise.
	 */
	public Boolean hasData();

	// TODO, this function is subsumed by the other one and could be removed, as well in GuardImpl.
	/**
	 * Evaluate guard.
	 *
	 * @param component the component
	 * @return true, if successful
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public boolean evaluateGuard(Object component) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	// TODO, maybe any exception that can be thrown by a given implementation of the guard should be simply wrapped in BIP Exception?
	/**
	 * Evaluate guard.
	 *
	 * @param component the component
	 * @param args the args
	 * @return true, if successful
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public boolean evaluateGuard(Object component, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
}
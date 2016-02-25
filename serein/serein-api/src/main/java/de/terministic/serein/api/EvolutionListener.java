/**
 * Copyright (C) 2015 Tobias Uhlig (tobias.uhlig@unibw.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.terministic.serein.api;

public interface EvolutionListener<P> {
	
	public enum Msg{StartEvolution, NextGeneration, FinishedEvolution}
	
	/**
	 * Method to send updates to the listener consisting a message and a
	 * reference to the evolutionary environment the listener is notified by.
	 * 
	 * @param msg
	 *            a message by an evolutionary algorithm.
	 * @param environment
	 *            notifiying evolutionary algorithm.
	 */
	public void update(Msg msg, EvolutionEnvironment<P> environment);

}

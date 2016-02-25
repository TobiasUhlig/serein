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

public interface EvolutionObject<P> {

	/**
	 * Sets the reference environment of this object to the given evolutionary
	 * environment. Implicitly it is expected that any initialization needed for
	 * this object is performed, so that after calling this method the object is
	 * in a valid working state. For example a TerminationCondiction might
	 * reset its internal counter.
	 * 
	 * @param environment
	 */
	void setEnvironment(EvolutionEnvironment<P> environment);

}

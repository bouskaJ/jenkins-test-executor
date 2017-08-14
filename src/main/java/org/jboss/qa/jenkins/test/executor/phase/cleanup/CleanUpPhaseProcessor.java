/*
 * Copyright 2015 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.qa.jenkins.test.executor.phase.cleanup;

import org.jboss.qa.jenkins.test.executor.beans.Workspace;
import org.jboss.qa.jenkins.test.executor.utils.FileUtils;
import org.jboss.qa.phaser.Inject;
import org.jboss.qa.phaser.PhaseDefinitionProcessor;

import java.io.IOException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CleanUpPhaseProcessor extends PhaseDefinitionProcessor {

	@NonNull
	private CleanUp cleanUp;

	@Inject
	private Workspace workspace;

	public void execute() {
		log.debug("@{} - {}", CleanUp.class.getName(), cleanUp.id());

		if (cleanUp.cleanWorkspace()) {
			try {
				// Recursive delete except for destinations of symbolic links
				FileUtils.removeRecursive(workspace.getDestination().toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/** 
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Felipe Oliveira (http://mashup.fm)
 * 
 */
package controllers;

import play.Logger;
import play.modules.jobs.PlayJobs;
import play.modules.jobs.PlayJobsService;
import play.mvc.Controller;

/**
 * The Class Jobs.
 */
public class PlayJobsDashboard extends Controller {

	/**
	 * Index.
	 */
	public static void index() {
		PlayJobsService service = new PlayJobsService();
		PlayJobs jobs = service.getJobs();
		render(jobs);
	}

	/**
	 * Trigger.
	 * 
	 * @para m jobClass the job class
	 */
	public static void executeNow(String jobClass, Integer instances) {
		Logger.info(String.format("Job Class: %s, Number of Concurrent Instances: %s", jobClass, instances));
		PlayJobsService service = new PlayJobsService();
		service.triggerJob(jobClass, instances);
		flash.success("Triggered Job: %s", jobClass);
		index();
	}
}

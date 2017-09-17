package org.smarttechie.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarttechie.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OverOpsTestingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OverOpsTestingController.class);
	@RequestMapping("/name")
	public String screenName(@RequestParam(name="sName",required=true) String screenName) {
		LOGGER.info("Invoking the controller");
		if (StringUtils.isNotBlank(screenName)) {
			return "Hello! "+screenName+" Good morning";
		} else {
			throw new EntityNotFoundException("The entity for which you are looking is not found!!");
		}
	}
}

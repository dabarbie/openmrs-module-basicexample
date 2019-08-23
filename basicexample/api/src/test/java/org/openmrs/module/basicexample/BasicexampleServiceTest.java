/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.basicexample;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.module.basicexample.api.dao.BasicexampleDao;
import org.openmrs.module.basicexample.api.impl.BasicexampleServiceImpl;

/**
 * This is a unit test, which verifies logic in BasicexampleService. It doesn't extend
 * BaseModuleContextSensitiveTest, thus it is run without the in-memory DB and Spring context.
 */
public class BasicexampleServiceTest {
	
	@InjectMocks
	BasicexampleServiceImpl basicModuleService;
	
	@Mock
	BasicexampleDao dao;
	
	@Mock
	PatientService patientService;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveItem_shouldSetOwnerIfNotSet() {
		//Given
		Item item = new Item();
		item.setDescription("some description");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		Patient patient = new Patient();
		when(patientService.getPatient(7)).thenReturn(patient);
		
		//When
		basicModuleService.saveItem(item);
		
		//Then
		assertThat(item, hasProperty("owner", is(patient)));
	}
}

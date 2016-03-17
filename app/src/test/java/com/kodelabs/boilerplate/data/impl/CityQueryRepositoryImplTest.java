package com.kodelabs.boilerplate.data.impl;

import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVOSCloud;
import com.kodelabs.boilerplate.LeanCloudConstants;
import com.kodelabs.boilerplate.data.CityQueryRepository;

import static org.junit.Assert.*;

/**
 * Created by lixindong on 16/3/17.
 */
public class CityQueryRepositoryImplTest {
    CityQueryRepository repository;

    @Before
    public void setUp() throws Exception {
        AVOSCloud.initialize(new MockContext(), LeanCloudConstants.LEANCLOUD_APPID, LeanCloudConstants.LEANCLOUD_APPKEY);
        repository = new CityQueryRepositoryImpl();
    }

    @Test
    public void testQuery() throws Exception {
        assertEquals(10, repository.query("北", 0, 10));
    }
}
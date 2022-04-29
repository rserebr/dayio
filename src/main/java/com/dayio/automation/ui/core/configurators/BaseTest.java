package com.dayio.automation.ui.core.configurators;

import com.dayio.automation.ui.core.listeners.TestResultListener;
import org.testng.annotations.Listeners;

@Listeners(TestResultListener.class)
public abstract class BaseTest {
}

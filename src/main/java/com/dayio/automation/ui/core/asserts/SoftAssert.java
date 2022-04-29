package com.dayio.automation.ui.core.asserts;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Iterator;
import java.util.Map;

public class SoftAssert extends Assertion {
    private static final Logger log = LogManager.getLogger(SoftAssert.class);
    private final Map<AssertionError, IAssert<?>> errors = Maps.newLinkedHashMap();

    public SoftAssert() {
    }

    @Step("Asserting")
    protected void doAssert(IAssert<?> assertCommand) {
        this.onBeforeAssert(assertCommand);

        try {
            assertCommand.doAssert();
            this.onAssertSuccess(assertCommand);
        } catch (AssertionError var6) {
            this.onAssertFailure(assertCommand, var6);
            this.errors.put(var6, assertCommand);
        } finally {
            this.onAfterAssert(assertCommand);
        }

    }

    public void onAssertSuccess(IAssert<?> assertCommand) {
        super.onAssertSuccess(assertCommand);
        log.info(assertCommand.getMessage());
    }

    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        super.onAssertFailure(assertCommand, ex);
        log.error(ex.getMessage());
    }

    public void assertAll() {
        if (!this.errors.isEmpty()) {
            boolean first = true;
            StringBuilder errorMessageBuilder = new StringBuilder("The following asserts failed:");
            Iterator var3 = this.errors.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<AssertionError, IAssert<?>> assertEntry = (Map.Entry)var3.next();
                if (first) {
                    first = false;
                } else {
                    errorMessageBuilder.append(",");
                }

                errorMessageBuilder.append("\n\t");
                errorMessageBuilder.append((assertEntry.getKey()).getMessage());
            }

            throw new AssertionError(errorMessageBuilder.toString());
        }
    }
}

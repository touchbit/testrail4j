/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.touchbit.testrail4j.integration.tests.jackson2;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "case_fields_operations")
public class CaseFieldTests extends BaseJackson2Test {

    @Test(description = "Expecting a successful receive of the existing case fields")
    @Details()
    public void test_20190106014139() {
        List<TRCaseField> fields = CLIENT.getTRCaseFields();
        assertThat(fields).isNotEmpty();
        for (TRCaseField field : fields) {
            assertThat(field.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful add new custom case field")
    @Details()
    public void test_20190106015332() {
        TRProject project = CLIENT.getProject();
        TRCaseFieldConfig config = new TRCaseFieldConfig()
                .withContext(new Context()
                        .withIsGlobal(false)
                        .withProjectIds(new ArrayList<Long>() {{ add(project.getId()); }}))
                .withOptions(new Options().withIsRequired(false).withItems("0, A\n1, B").withDefaultValue("1"));
        TRCaseField field = new TRCaseField()
                .withName("with_name_" + getRandomString(5))
                .withDescription("withDescription")
                .withLabel("test_20190106015332")
                .withType("6")
                .withIncludeAll(true);
        CLIENT.addTRCaseField(field, config);
        List<TRCaseField> fields = CLIENT.getTRCaseFields();
        List<String> stringList = fields.stream().map(TRCaseField::getLabel).collect(Collectors.toList());
        assertThat(stringList).contains("test_20190106015332");
    }

}

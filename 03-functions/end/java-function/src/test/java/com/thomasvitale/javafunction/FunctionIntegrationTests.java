package com.thomasvitale.javafunction;

import java.util.function.Consumer;
import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

@FunctionalSpringBootTest
public class FunctionIntegrationTests {
 
  @Autowired
  private FunctionCatalog functionCatalog;

  @Test
  void whenInstrumentThenBuildSentence() {
    var instrument = "piano";
    Function<String,Skill> func = functionCatalog.lookup("uppercase|sentence");
    var skill = func.apply(instrument);
    Assertions.assertThat(skill).isEqualTo(new Skill("I play the PIANO"));
  }

  @Test
  void whenInstrumentThenBuildSentenceAndPrint() {
    var instrument = "piano";
    Consumer<String> func = functionCatalog.lookup("uppercase|sentence|print");
    func.accept(instrument);
  }

}

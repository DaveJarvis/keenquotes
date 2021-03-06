/* Copyright 2021 White Magic Software, Ltd. -- All rights reserved. */
package com.whitemagicsoftware.keenquotes;

import org.junit.jupiter.api.Test;

import static com.whitemagicsoftware.keenquotes.LexemeType.*;
import static com.whitemagicsoftware.keenquotes.LexerTest.testType;

/**
 * Test that parsing XML documents ignores elements.
 */
final class XmlLexerTest {

  @Test
  void test_Lexing_Xml_EmitTags() {
    final var actual =
      "A <em>world's</em> aflame <pre><code>ch = '\\''</code></pre>.";
    testType(
      createXmlLexer( actual ), actual,
      WORD, SPACE, WORD, QUOTE_SINGLE, WORD, SPACE, WORD, SPACE, PERIOD
    );
  }

  @Test
  void test_Lexing_XmlAttribute_EmitTags() {
    final var actual = "<a href=\"http://x.org\">X11</a>";
    testType( createXmlLexer( actual ), actual, WORD );
  }

  static Lexer createXmlLexer( final String text ) {
    return new XmlLexer( text );
  }
}

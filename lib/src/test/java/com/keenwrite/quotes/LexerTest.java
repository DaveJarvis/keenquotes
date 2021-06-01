/* Copyright 2020-2021 White Magic Software, Ltd. -- All rights reserved. */
package com.keenwrite.quotes;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.keenwrite.quotes.LexemeType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests lexing words, numbers, punctuation, spaces, and newlines.
 */
class LexerTest {
  @Test
  void test_Lexing_Words_TokenValues() {
    testText( "abc 123", "abc", " ", "123" );
    testText( "-123 abc", "-", "123", " ", "abc" );
  }

  @Test
  void test_Lexing_Numbers_EmitNumbers() {
    testType( ".123", NUMBER );
    testType( "-123.", PUNCT, NUMBER, PERIOD );
    testType( " 123.123.123", SPACE, NUMBER );
    testType( "123 123\"", NUMBER, SPACE, NUMBER, QUOTE_DOUBLE );
    testType( "-123,123.123", PUNCT, NUMBER );
  }

  @Test
  void test_Lexing_Words_EmitWords() {
    testType( "abc", WORD );
    testType( "abc abc", WORD, SPACE, WORD );
    testType( "abc123", WORD );
    testType( "-123abc", PUNCT, NUMBER, WORD );
    testType( "abc-o'-abc", WORD, PUNCT, WORD, QUOTE_SINGLE, PUNCT, WORD );
  }

  @Test
  void test_Lexing_PunctuationMarks_EmitPunctuationMarks() {
    testType( "!", PUNCT );
    testType( ";", PUNCT );
    testType( ".", PERIOD );
  }

  @Test
  void test_Lexing_Quotes_EmitQuotes() {
    testType( "'", QUOTE_SINGLE );
    testType( "\"", QUOTE_DOUBLE );
    testType( "3 o'clock", NUMBER, SPACE, WORD, QUOTE_SINGLE, WORD );
  }

  @Test
  void test_Lexing_Newlines_EmitNewlines() {
    testType( "\r", NEWLINE );
    testType( "\n", NEWLINE );
    testType( "\r\n", NEWLINE );
    testType( "\r\n\r\n", NEWLINE, NEWLINE );
    testType( "\r\n\n\r", NEWLINE, NEWLINE, NEWLINE );
    testType( "abc \r\nabc\n", WORD, SPACE, NEWLINE, WORD, NEWLINE );
  }

  private void testType(
    final String actual, final LexemeType... expected ) {
    final var tokenizer = new Lexer();
    final var counter = new AtomicInteger();

    tokenizer.parse( actual, ( token ) -> {
      final var expectedType = expected[ counter.getAndIncrement() ];
      final var actualType = token.getType();
      assertEquals( expectedType, actualType );
    } );

    // Ensure all expected tokens are matched (verify end of text reached).
    assertEquals( expected.length, counter.get() );
  }

  private void testText( final String actual, final String... expected ) {
    final var tokenizer = new Lexer();
    final var counter = new AtomicInteger();

    tokenizer.parse( actual, ( token ) -> {
      final var expectedText = expected[ counter.getAndIncrement() ];
      final var actualText = token.toString( actual );
      assertEquals( expectedText, actualText );
    } );

    // Ensure all expected tokens are matched (verify end of text reached).
    assertEquals( expected.length, counter.get() );
  }
}
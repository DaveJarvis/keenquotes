/* Copyright 2021 White Magic Software, Ltd. -- All rights reserved. */
package com.whitemagicsoftware.keenquotes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

import static com.whitemagicsoftware.keenquotes.Converter.convert;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test that English straight quotes are converted to curly quotes and
 * apostrophes.
 */
public class KeenQuotesTest {
  /**
   * This is a single-use test that is useful for debugging.
   */
  @Test
  @Disabled
  public void test_parse_SingleLine_Parsed() {
    out.println( convert(
      "\"’Kearney lives on the banks of Killarney—’",
      out::println
    ) );
  }

  /**
   * Tests that straight quotes are converted to curly quotes.
   *
   * @throws IOException Error opening file full of fun.
   */
  @Test
  public void test_Parse_StraightQuotes_CurlyQuotes() throws IOException {
    testConverter( text -> convert( text, ( lexeme ) -> {} ) );
  }

  /**
   * Re-enable using <a href="https://www.gutenberg.org/">Project Gutenberg</a>
   * texts.
   *
   * @param filename A plain text file to convert.
   * @throws IOException Could not find, open, or read from text file.
   */
  @ParameterizedTest
  @ValueSource( strings = {"habberton"} )
  @Disabled
  void test_Parse_Story_Converted( final String filename ) throws IOException {
    final var sb = new StringBuilder( 2 ^ 20 );

    try( final var reader = open( filename + ".txt" ) ) {
      String line;

      while( (line = reader.readLine()) != null ) {
        sb.append( line ).append( '\n' );
      }
    }

    System.out.println( convert( sb.toString(), out::println ) );
  }

  /**
   * Reads a file full of couplets. The first of the pair is the input,
   * the second of the pair is the expected result. Couplets may include
   * newline characters to indicate end of lines and end of paragraphs.
   * Lines that start with {@code #} are ignored.
   *
   * @param parser The text processor capable of straight quote conversion.
   * @throws IOException Error opening file full of fun.
   */
  private void testConverter( final Function<String, String> parser )
    throws IOException {
    try( final var reader = open( "smartypants.txt" ) ) {
      String line;
      String testLine = "";
      String expected = "";

      while( ((line = reader.readLine()) != null) ) {
        if( line.startsWith( "#" ) || line.isBlank() ) { continue; }

        // Read the first line of the couplet.
        if( testLine.isBlank() ) {
          testLine = line;
          continue;
        }

        // Read the second line of the couplet.
        if( expected.isBlank() ) {
          expected = line;
        }

        testLine = unescapeEol( testLine );
        expected = unescapeEol( expected );

        final var actual = parser.apply( testLine );
        assertEquals( expected, actual );

        testLine = "";
        expected = "";
      }
    }
  }

  private static String unescapeEol( final String s ) {
    return String.join( "\n", s.split( "\\\\n" ) );
  }

  /**
   * Opens a text file for reading. Callers are responsible for closing.
   *
   * @param filename The file to open.
   * @return An instance of {@link BufferedReader} that can be used to
   * read all the lines in the file.
   */
  private BufferedReader open( final String filename ) {
    final var is = getClass().getResourceAsStream( filename );
    assertNotNull( is );

    return new BufferedReader( new InputStreamReader( is ) );
  }
}

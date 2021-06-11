/* Copyright 2021 White Magic Software, Ltd. -- All rights reserved. */
package com.keenwrite.quotes;

import java.util.Set;

/**
 * Placeholder for various types of contractions.
 */
public class Contractions {
  /**
   * Answers whether the given word is a contraction that always starts
   * with an apostrophe. The comparison is case insensitive. This must
   * only be called when a straight quote is followed by a word.
   *
   * @param word The word to compare against the list of known unambiguous
   *             contractions.
   * @return {@code true} when the given word is in the set of unambiguous
   * contractions.
   */
  public static boolean contractionBeganUnambiguously( final String word ) {
    assert word != null;
    return BEGAN_UNAMBIGUOUS.contains( word.toLowerCase() ) ||
      word.equals( "em" );
  }

  /**
   * Answers whether the given word could be a contraction but is also a
   * valid word in non-contracted form.
   *
   * @param word The word to compare against the list of known ambiguous
   *             contractions.
   * @return {@code true} when the given word is in the set of ambiguous
   * contractions.
   */
  public static boolean contractionBeganAmbiguously( final String word ) {
    assert word != null;
    return BEGAN_AMBIGUOUS.contains( word.toLowerCase() );
  }

  public static boolean contractionEndedUnambiguously( final String word ) {
    assert word != null;
    return ENDED_UNAMBIGUOUS.contains( word.toLowerCase() );
  }

  public static boolean contractionEndedAmbiguously( final String word ) {
    assert word != null;
    final var check = word.toLowerCase();

    // Ensure that 'n' isn't matched for ambiguity by enforcing length, yet
    // allow o' to match because 'a sentence can end with the letter o'.
    return ENDED_AMBIGUOUS.contains( check ) ||
      check.endsWith( "s" ) || check.endsWith( "z" ) ||
      check.endsWith( "x" ) || (check.length() > 1 && check.endsWith( "n" ));
  }

  /**
   * Words having a straight apostrophe that cannot be mistaken for an
   * opening single quote.
   */
  private static final Set<String> BEGAN_UNAMBIGUOUS = Set.of(
    "aporth",
    "boutcha",
    "boutchu",
    "cept",
    "dillo",
    "fraid",
    "gainst",
    "n",
    "neath",
    "nother",
    "onna",
    "onna'",
    "pon",
    "s",
    "sblood",
    "scuse",
    "sfar",
    "sfoot",
    "t",
    "taint",
    "tain",
    "til",
    "tis",
    "tisn",
    "tshall",
    "twas",
    "twasn",
    "tween",
    "twere",
    "tweren",
    "twixt",
    "twon",
    "twou",
    "twould",
    "twouldn",
    "ve"
  );

  /**
   * Words having a straight apostrophe that may be either part of a
   * contraction or a word that stands alone beside an opening single quote.
   */
  private static final Set<String> BEGAN_AMBIGUOUS = Set.of(
    // about|boxing match
    "bout",
    // because|causal
    "cause",
    // what you|choo choo train
    "choo",
    // he|e pluribus unum
    "e",
    // here|earlier
    "ere",
    // afro|to and fro
    "fro",
    // whore|ho ho!
    "ho",
    // okay|letter K
    "kay",
    // lo|lo and behold
    "lo",
    // are|regarding
    "re",
    // what's up|to sup
    "sup",
    // it will|twill fabric
    "twill",
    // them|utterance
    "um",
    // is that|Iranian village
    "zat"
  );

  private static final Set<String> ENDED_AMBIGUOUS = Set.of(
    // and|an
    "an",
    // give|martial arts garment
    "gi",
    // in|I
    "i",
    // of|letter o
    "o"
  );

  private static final Set<String> ENDED_UNAMBIGUOUS = Set.of(
    // old
    "ol",
    // the
    "th",
    // Top ~500 common -ing words as English contractions.
    "acceptin",
    "accompanyin",
    "accordin",
    "accountin",
    "achievin",
    "acquirin",
    "actin",
    "addin",
    "addressin",
    "adjoinin",
    "adoptin",
    "advancin",
    "advertisin",
    "affectin",
    "agin",
    "allowin",
    "amazin",
    "analyzin",
    "answerin",
    "anythin",
    "appearin",
    "applyin",
    "approachin",
    "arguin",
    "arisin",
    "arrivin",
    "askin",
    "assessin",
    "assumin",
    "attackin",
    "attemptin",
    "attendin",
    "avoidin",
    "bankin",
    "bargainin",
    "bearin",
    "beatin",
    "becomin",
    "beginnin",
    "bein",
    "believin",
    "belongin",
    "bendin",
    "bindin",
    "bleedin",
    "blessin",
    "blowin",
    "boilin",
    "borrowin",
    "breakin",
    "breathin",
    "breedin",
    "bringin",
    "broadcastin",
    "buildin",
    "burnin",
    "buyin",
    "calculatin",
    "callin",
    "carryin",
    "castin",
    "causin",
    "ceilin",
    "challengin",
    "changin",
    "checkin",
    "choosin",
    "claimin",
    "cleanin",
    "clearin",
    "climbin",
    "closin",
    "clothin",
    "collectin",
    "combinin",
    "comin",
    "commandin",
    "comparin",
    "compellin",
    "competin",
    "computin",
    "concernin",
    "concludin",
    "conditionin",
    "conductin",
    "conflictin",
    "connectin",
    "considerin",
    "consistin",
    "constructin",
    "consultin",
    "consumin",
    "containin",
    "continuin",
    "contractin",
    "contributin",
    "controllin",
    "convincin",
    "cookin",
    "coolin",
    "copin",
    "correspondin",
    "counselin",
    "countin",
    "couplin",
    "coverin",
    "creatin",
    "crossin",
    "cryin",
    "cuttin",
    "dancin",
    "darlin",
    "datin",
    "dealin",
    "decidin",
    "declarin",
    "declinin",
    "decreasin",
    "definin",
    "demandin",
    "denyin",
    "dependin",
    "descendin",
    "describin",
    "designin",
    "destroyin",
    "determinin",
    "developin",
    "differin",
    "dinin",
    "directin",
    "discussin",
    "distinguishin",
    "disturbin",
    "dividin",
    "doin",
    "drawin",
    "dressin",
    "drinkin",
    "drivin",
    "droppin",
    "dryin",
    "durin",
    "dwellin",
    "dyin",
    "eatin",
    "editin",
    "emergin",
    "employin",
    "enablin",
    "encouragin",
    "endin",
    "engagin",
    "engineerin",
    "enjoyin",
    "enterin",
    "establishin",
    "evaluatin",
    "evenin",
    "everythin",
    "examinin",
    "exceedin",
    "excitin",
    "excludin",
    "existin",
    "expandin",
    "expectin",
    "experiencin",
    "explainin",
    "explorin",
    "expressin",
    "extendin",
    "facin",
    "failin",
    "fallin",
    "farmin",
    "fascinatin",
    "feedin",
    "feelin",
    "fightin",
    "filin",
    "fillin",
    "financin",
    "findin",
    "firin",
    "fishin",
    "fittin",
    "fixin",
    "floatin",
    "flowin",
    "flyin",
    "focusin",
    "followin",
    "forcin",
    "foregoin",
    "formin",
    "forthcomin",
    "foundin",
    "freezin",
    "functionin",
    "fundin",
    "gainin",
    "gatherin",
    "generatin",
    "gettin",
    "givin",
    "goin",
    "governin",
    "grantin",
    "growin",
    "handlin",
    "hangin",
    "happenin",
    "havin",
    "headin",
    "healin",
    "hearin",
    "heatin",
    "helpin",
    "hidin",
    "holdin",
    "hopin",
    "housin",
    "huntin",
    "identifyin",
    "imagin",
    "implementin",
    "imposin",
    "improvin",
    "includin",
    "increasin",
    "indicatin",
    "interestin",
    "interpretin",
    "introducin",
    "involvin",
    "joinin",
    "judgin",
    "keepin",
    "killin",
    "knowin",
    "lackin",
    "landin",
    "lastin",
    "laughin",
    "layin",
    "leadin",
    "leanin",
    "learnin",
    "leavin",
    "lettin",
    "liftin",
    "lightin",
    "lightnin",
    "limitin",
    "listenin",
    "listin",
    "livin",
    "loadin",
    "lookin",
    "losin",
    "lovin",
    "lowerin",
    "lyin",
    "maintainin",
    "makin",
    "managin",
    "manufacturin",
    "mappin",
    "marketin",
    "markin",
    "matchin",
    "meanin",
    "measurin",
    "meetin",
    "meltin",
    "minin",
    "misleadin",
    "missin",
    "mixin",
    "modelin",
    "monitorin",
    "mornin",
    "movin",
    "neighborin",
    "nothin",
    "notin",
    "notwithstandin",
    "nursin",
    "observin",
    "obtainin",
    "occurrin",
    "offerin",
    "offsprin",
    "ongoin",
    "openin",
    "operatin",
    "opposin",
    "orderin",
    "organizin",
    "outstandin",
    "overwhelmin",
    "packin",
    "paintin",
    "parkin",
    "participatin",
    "passin",
    "payin",
    "pendin",
    "performin",
    "pickin",
    "placin",
    "plannin",
    "plantin",
    "playin",
    "pleasin",
    "pointin",
    "possessin",
    "preachin",
    "precedin",
    "preparin",
    "presentin",
    "preservin",
    "pressin",
    "prevailin",
    "preventin",
    "pricin",
    "printin",
    "proceedin",
    "processin",
    "producin",
    "programmin",
    "promisin",
    "promotin",
    "protectin",
    "providin",
    "provin",
    "publishin",
    "pullin",
    "purchasin",
    "pursuin",
    "pushin",
    "puttin",
    "questionin",
    "rangin",
    "ratin",
    "reachin",
    "readin",
    "reasonin",
    "receivin",
    "recognizin",
    "recordin",
    "reducin",
    "referrin",
    "reflectin",
    "refusin",
    "regardin",
    "regulatin",
    "relatin",
    "remainin",
    "rememberin",
    "removin",
    "renderin",
    "repeatin",
    "replacin",
    "reportin",
    "representin",
    "requirin",
    "respectin",
    "respondin",
    "restin",
    "resultin",
    "returnin",
    "revealin",
    "ridin",
    "risin",
    "rulin",
    "runnin",
    "sailin",
    "samplin",
    "satisfyin",
    "savin",
    "sayin",
    "scatterin",
    "schoolin",
    "screenin",
    "searchin",
    "securin",
    "seein",
    "seekin",
    "selectin",
    "sellin",
    "sendin",
    "separatin",
    "servin",
    "settin",
    "settlin",
    "shakin",
    "shapin",
    "sharin",
    "shiftin",
    "shinin",
    "shippin",
    "shootin",
    "shoppin",
    "showin",
    "singin",
    "sinkin",
    "sittin",
    "sleepin",
    "smilin",
    "smokin",
    "solvin",
    "somethin",
    "speakin",
    "spellin",
    "spendin",
    "spinnin",
    "spreadin",
    "standin",
    "starin",
    "startin",
    "statin",
    "stayin",
    "sterlin",
    "stimulatin",
    "stirrin",
    "stoppin",
    "strengthenin",
    "stretchin",
    "strikin",
    "strugglin",
    "studyin",
    "succeedin",
    "sufferin",
    "suggestin",
    "supplyin",
    "supportin",
    "surprisin",
    "surroundin",
    "survivin",
    "sweepin",
    "swellin",
    "swimmin",
    "switchin",
    "takin",
    "talkin",
    "teachin",
    "tellin",
    "testin",
    "thinkin",
    "threatenin",
    "throwin",
    "timin",
    "touchin",
    "tradin",
    "trainin",
    "travelin",
    "treatin",
    "tremblin",
    "tryin",
    "turnin",
    "underlyin",
    "understandin",
    "undertakin",
    "unwillin",
    "usin",
    "varyin",
    "viewin",
    "visitin",
    "votin",
    "waitin",
    "walkin",
    "wanderin",
    "wantin",
    "warnin",
    "washin",
    "watchin",
    "wearin",
    "weddin",
    "willin",
    "windin",
    "winnin",
    "wishin",
    "wonderin",
    "workin",
    "writin",
    "yieldin"
  );
}

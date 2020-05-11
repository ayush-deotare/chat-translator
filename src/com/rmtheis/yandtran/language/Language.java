/*
 * Copyright 2013 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rmtheis.yandtran.language;

/**
 * Language - an enum of language codes supported by the Yandex API
 */
public enum Language {
  AFRIKAANS("afrikaans"),
  ALBANIAN("albanian"),
  ARABIC("arabic"),
  ARMENIAN("armenian"),
  AZERBAIJANI("azerbaijani"),
  BELARUSIAN("belarustan"),
  BULGARIAN("bulgarian"),
  CATALAN("catalan"),
  CHINESE("chinese"),
  CROATIAN("croatian"),
  CZECH("czech"),
  DANISH("danish"),
  DUTCH("dutch"),
  ENGLISH("english"),
  ESTONIAN("estonian"),
  FINNISH("finnish"),
  FRENCH("french"),
  GERMAN("german"),
  GREEK("el"),
  HEBREW("hebrew"),
  HINDI("hindi"),
  HUNGARIAN("hungarian"),
  GEORGIAN("georgian"),
  ICELANDIC("icelandic"),
  INDONESIAN("indonesian"),
  ITALIAN("italian"),
  JAPANESE("japanese"),
  KAZAKH("kazakh"),
  KOREAN("korean"),
  LATIN("latin"),
  LATVIAN("latvian"),
  LITHUANIAN("lithuanian"),
  MACEDONIAN("macedonian"),
  MALAY("malay"),
  MALTESE("maltese"), // NEW
  NORWEGIAN("norwegian"),
  POLISH("polish"),
  PORTUGUESE("pt"),
  ROMANIAN("romanian"),
  RUSSIAN("russian"),
  SERBIAN("serbian"),
  SLOVAK("slovak"),
  SLOVENIAN("slovenian"),
  SPANISH("spanish"),
  SWEDISH("swedish"),
  TAGALOG("tagalog"),
  THAI("thai"),
  TURKISH("turkish"),
  UKRAINIAN("ukrainian"),
  URDU("urdu"),
  UZBEK("uzbek"),
  VIETNAMESE("vietnamese"),
  WELSH("welsh");

  /**
   * String representation of this language.
   */
  private final String language;

  /**
   * Enum constructor.
   * @param pLanguage The language identifier.
   */
  private Language(final String pLanguage) {
    language = pLanguage;
  }

  public static Language fromString(final String pLanguage) {
    for (Language l : values()) {
      if (l.toString().equals(pLanguage)) {
        return l;
      }
    }
    return null;
  }

  /**
   * Returns the String representation of this language.
   * @return The String representation of this language.
   */
  @Override
  public String toString() {
    return language;
  }

}

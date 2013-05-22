/*
 * Copyright (C) 2003 - 2013 OpenSubsystems.com/net/org and its owners. All rights reserved.
 * 
 * This file is part of OpenSubsystems.
 *
 * OpenSubsystems is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.opensubsystems.digester.data;

import java.util.Set;
import org.opensubsystems.core.error.OSSDataNotFoundException;
import org.opensubsystems.core.error.OSSException;

/**
 * Interface representing record read by a reader. The record can represent data
 * that can be directly mapped to a data object or can represent an element of 
 * an data object.
 *
 * @author bastafidli
 */
public interface Record<T>
{
   public static enum Presence
   {
      REQUIRED, OPTIONAL;
   }
   
   /**
    * Get sequential number for this record as read by the reader. The record
    * number can be used for example by mappers, for which the position of the 
    * record determines its type (e.g., first, odd vs event, etc.)
    *
    * @return long - sequential number for this record as read by the reader
    * @throws OSSException - an error has occurred 
    */
   long getRecordNumber();
   
   /**
    * Get data representing this record as read by the reader.
    * 
    * @return T
    */
   T getRecordData();
   
   /**
    * Extract String value between two delimiters.
    * 
    * @param strStart - first delimiter
    * @param strEnd - second delimiter
    * @param presence - is the value required or optional
    * @return String - value between the two delimiters. If the first delimiter
    *                  doesn't exist, and the value is required then an exception
    *                  will be thrown
    * @throws OSSDataNotFoundException - required data were not found
    */
   String extractBetweenAsString(
      String   strStart,
      String   strEnd,
      Presence presence
   ) throws OSSDataNotFoundException;

   /**
    * Extract Record value between two delimiters.
    * 
    * @param strStart - first delimiter
    * @param strEnd - second delimiter
    * @param presence - is the value required or optional
    * @return Record - value between the two delimiters. If the first delimiter
    *                  doesn't exist, and the value is required then an exception
    *                  will be thrown
    * @throws OSSDataNotFoundException - required data were not found
    */
   Record extractBetweenAsRecord(
      String   strStart,
      String   strEnd,
      Presence presence
   ) throws OSSDataNotFoundException;

   /**
    * Extract Record value between two delimiters.
    * 
    * @param strDelimiter - first delimiter
    * @param presence - is the value required or optional
    * @return String - value up to the specified delimiter. If the record starts
    *                  with the delimiter, then the returned value will be empty.
    * @throws OSSDataNotFoundException - required data were not found
    */
   String extractSeparatedAsString(
      String   strDelimiter,
      Presence presence
   ) throws OSSDataNotFoundException;

   /**
    * Extract Record value between two delimiters.
    * 
    * @param strDelimiter - first delimiter
    * @param presence - is the value required or optional
    * @return Set<String> - set of value separated by the specified delimiter. 
    *                       If the record starts with the delimiter, then an 
    *                       empty value will be included in the set.
    * @throws OSSDataNotFoundException - required data were not found
    */
   Set<String> extractSeparatedAsStringSet(
      String   strDelimiter,
      Presence presence
   ) throws OSSDataNotFoundException;
}

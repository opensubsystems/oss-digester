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

import org.opensubsystems.core.error.OSSDataNotFoundException;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.util.TwoElementStruct;

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
   T getData();
   
   /**
    * Get the record data as string.
    * 
    * @return String - the record data represented as string.
    */
   String getDataAsString();
   
   /**
    * Get the record data as string after removing the specified postfix.
    * 
    * @param strPostfix - the postfix to remove from the data before it is 
    *                     returned as String
    * @param presence - if the presence is required, then the record must contain
    *                   the postfix
    * @return String - the record data represented as string.
    * @throws OSSDataNotFoundException - the postfix has not been found in the
    *                                    record and its presence was required
    */
   String getDataAsStringWithoutPostfix(
      String   strPostfix,
      Presence presence
   ) throws OSSDataNotFoundException;
   
   /**
    * Split the record into two subrecords based on the first occurrence of the 
    * separator.
    * 
    * @param strSeparator - separator the first occurrence of which should be 
    *                       used to split the record into two subrecords
    * @param presence - if the presence is required, then the record must contain
    *                   the separator
    * @return TwoElementStruct<Record<T>, Record<T>> - the first element is the
    *                                                  portion of the original  
    *                                                  record before the first 
    *                                                  occurrence of the separator
    *                                                  and the second element 
    *                                                  is the rest of the record
    *                                                  after the separator
    * @throws OSSDataNotFoundException - the separator has not been found in the
    *                                    record and its presence was required
    */
   TwoElementStruct<Record<T>, Record<T>> split(
      String   strSeparator,
      Presence presence
   ) throws OSSDataNotFoundException;
}

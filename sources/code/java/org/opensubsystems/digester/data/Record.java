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
}

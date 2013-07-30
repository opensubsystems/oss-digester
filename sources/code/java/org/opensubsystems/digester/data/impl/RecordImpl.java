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

package org.opensubsystems.digester.data.impl;

import java.util.Collection;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.util.GlobalConstants;
import org.opensubsystems.core.util.TwoElementStruct;
import org.opensubsystems.digester.data.Record;
import org.opensubsystems.digester.data.Record.Presence;

/**
 * Common base class for record interface implementations.
 * 
 * @author bastafidli
 */
public abstract class RecordImpl<T> implements Record<T>
{
   /**
    * {@inheritDoc}
    */
   @Override
   public void getDataAsCollection(
     String             strSeparator,
     Collection<String> colParsed
   ) throws OSSException
   {
      TwoElementStruct<Record, Record> split;
      Record                           value;
      Record                           items;

      // Start with the content of this record
      items = this;

      do
      {
         split = items.split(strSeparator, Presence.OPTIONAL); 

         value = split.getFirst(); 
         items = split.getSecond();

         if (value != null)
         {
            if (GlobalConstants.ERROR_CHECKING)
            {
               assert items != null
                       : "split didn't created not null second result";
            }

            colParsed.add(value.getDataAsString());
         }
      }
      while (value != null);
   }
}

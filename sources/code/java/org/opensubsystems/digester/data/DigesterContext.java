/*
 * Copyright (C) 2003 - 2014 OpenSubsystems.com/net/org and its owners. All rights reserved.
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

import java.io.Reader;
import org.opensubsystems.digester.logic.OSSReader;
import org.opensubsystems.pattern.parameter.data.ParametrizedContext;

/**
 * Interface representing context within which is an digester operation such 
 * as file import being processed.
 *
 * @author bastafidli
 */
public interface DigesterContext extends ParametrizedContext
{
   /**
    * Get the reader that is using this context.
    * 
    * @return StateMachine
    */
   OSSReader getReader();
   
   /**
    * Test if this source has more data to be read.
    * 
    * @return boolean - if true then the reader is unable to read any more data
    *                   in this context, false otherwise
    */
   // This method cannot be effectively implemented because not every reader has
   // a way to check  if there is more data and if does it by reading the data
   // it may not be able to repeat the read
   // boolean hasMoreData();

   /**
    * Test if this context is closed for reading.
    * 
    * @return boolean - if true then the reader has been closed and is unable to 
    *                   read any more data in this context, false otherwise
    */
   boolean isClosed();

   /**
    * Get source that has been opened and ready to be used by reader.
    * 
    * @return Reader - source opened and ready to be used by reader. 
    */
   Reader getOpenedSource();

   /**
    * Get user friendly name that identifies the source from where the data will 
    * be read
    * 
    * @return Reader - source opened and ready to be used by reader. 
    */
   String getOpenedSourceName();
   
   /**
    * Set source that has been opened and ready to be used by reader.
    * 
    * @param openedSource - source opened and ready to be used by reader. If null
    *                       then any previously configured opened source will be 
    *                       forgotten and the context will be considered closed.
    * @param strSourceName - user friendly name that identifies the source from 
    *                        where the data will be read.
    */
   void setOpenedSource(
      Reader openedSource,
      String strSourceName
   );
}

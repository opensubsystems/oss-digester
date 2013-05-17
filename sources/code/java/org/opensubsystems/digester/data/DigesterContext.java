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

import org.opensubsystems.digester.logic.Reader;
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
   Reader getReader();
   
   /**
    * Test if this context is closed for reading.
    * 
    * @return boolean - if true then the reader is unable to read any more data
    *                   in this context, false otherwise
    */
   boolean isClosed();

   /**
    * Get source that has been opened and ready to be used by reader.
    * 
    * @return Object - source opened and ready to be used by reader. If null
    *                  then any previously configured opened source will be 
    *                  forgotten and the context will be considered closed. The
    *                  return value is null since it is not known what type of
    *                  object is reader implementation using to represent an 
    *                  opened source.
    */
   Object getOpenedSource();

   /**
    * Set source that has been opened and ready to be used by reader.
    * 
    * @param openedSource - source opened and ready to be used by reader. If null
    *                       then any previously configured opened source will be 
    *                       forgotten and the context will be considered closed.
    */
   void setOpenedSource(
      Object openedSource
   );
}

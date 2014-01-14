/*
 * Copyright (C) 2013 OpenSubsystems.com/net/org and its owners. All rights reserved.
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

import org.opensubsystems.digester.data.DigesterContext;
import org.opensubsystems.digester.logic.Reader;
import org.opensubsystems.pattern.parameter.data.impl.ParametrizedContextImpl;

/**
 * Default implementation of the EventDispatchContext interface.
 * 
 * @author bastafidli
 */
public class DigesterContextImpl extends ParametrizedContextImpl
                                 implements DigesterContext
{
   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * The reader using this context.
    */
   protected Reader m_reader;
   
   /**
    * Object that represents source opened by reader and ready for reading.
    */
   protected Object m_openedSource;
   
   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Full constructor.
    * 
    * @param reader - reader to user to read the data
    */
   public DigesterContextImpl(
      Reader reader
   )
   {
      super();
      
      m_reader = reader;
   }

   // Logic ////////////////////////////////////////////////////////////////////

   /**
    * {@inheritDoc}
    */
   @Override
   public void toString(
      StringBuilder sb,
      int           iIndentLevel
   )
   {
      append(sb, iIndentLevel + 0, "DigesterContextImpl[");
      append(sb, iIndentLevel + 1, "m_reader = ", m_reader);
      append(sb, iIndentLevel + 1, "m_openedSource = ", m_openedSource);
      super.toString(sb, iIndentLevel + 1);
      append(sb, iIndentLevel + 0, "]");
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Reader getReader()
   {
      return m_reader;
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isClosed()
   {
      // If there is an opened source that this context is not closed.
      return m_openedSource != null;
   }

   // Helper methods ///////////////////////////////////////////////////////////

   /**
    * {@inheritDoc}
    */
   @Override
   public Object getOpenedSource(
   )
   {
      return m_openedSource;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void setOpenedSource(
      Object openedSource
   )
   {
      m_openedSource = openedSource;
   }
}

/*
 * Copyright (C) 2013 - 2014 OpenSubsystems.com/net/org and its owners. All rights reserved.
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

import java.io.Reader;
import org.opensubsystems.digester.data.DigesterContext;
import org.opensubsystems.digester.logic.OSSReader;
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
   protected OSSReader m_reader;
   
   /**
    * Object that represents source opened by reader and ready for reading.
    */
   protected Reader m_openedSource;
   
   /**
    * User friendly name that identifies the source from where the data will be
    * read.
    */
   protected String m_strOpenedSourceName;
   
   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Full constructor.
    * 
    * @param reader - reader to user to read the data
    */
   public DigesterContextImpl(
      OSSReader reader
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
      append(sb, iIndentLevel + 1, "m_strOpenedSourceName = ", m_strOpenedSourceName);
      super.toString(sb, iIndentLevel + 1);
      append(sb, iIndentLevel + 0, "]");
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public OSSReader getReader()
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
   public Reader getOpenedSource(
   )
   {
      return m_openedSource;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getOpenedSourceName()
   {
      return m_strOpenedSourceName;
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void setOpenedSource(
      Reader openedSource,
      String strSourceName
   )
   {
      m_openedSource = openedSource;
      if (m_openedSource != null)
      {
         m_strOpenedSourceName = strSourceName;
      }
      else
      {
         m_strOpenedSourceName = "";
      }
   }
}
